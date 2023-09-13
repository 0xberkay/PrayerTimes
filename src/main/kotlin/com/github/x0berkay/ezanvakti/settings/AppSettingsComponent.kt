package com.github.x0berkay.ezanvakti.settings

import com.github.x0berkay.ezanvakti.models.CitiesItem
import com.github.x0berkay.ezanvakti.models.CountryItem
import com.github.x0berkay.ezanvakti.models.TimesItem
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import com.jetbrains.rd.swing.visibleProperty
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.event.PopupMenuEvent

/**
 * Supports creating and managing a [JPanel] for the Settings Dialog.
 */
class AppSettingsComponent {
    val panel: JPanel
    private val pickCountry: ComboBox<String> = ComboBox()
    private val pickCity: ComboBox<String> = ComboBox()
    private val pickCounties: ComboBox<String> = ComboBox()

    private var country: String = ""
    private var counties: String = ""
    private var city: String = ""

    init {
        val countries = Json.decodeFromString(ListSerializer(CountryItem.serializer()), getResourceAsText("/a.json")!!)
        for (country in countries) {
            pickCountry.addItem(country.ulkeAdiEn)
        }
        panel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Country"), pickCountry, 1, false)
            .addLabeledComponent(JBLabel("City"), pickCity, 1, false)
            .addLabeledComponent(JBLabel("Counties"), pickCounties, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel

        pickCountry.addPopupMenuListener(object : CustomListener() {
            override fun popupMenuWillBecomeInvisible(e: PopupMenuEvent) {
                country = countries[pickCountry.selectedIndex].ulkeAdiEn
                println(country)

                pickCounties.removeAllItems()

                 val json = Json { encodeDefaults = false }

                val times= json.decodeFromString(ListSerializer(TimesItem.serializer()), getResourceAsText("/c.json")!!).toMutableList()

                val timesState = TimesState.instance
                timesState.state.items = times




//                pickCity.removeAllItems()
//                val cities= Json.decodeFromString(ListSerializer(CitiesItem.serializer()), getResourceAsText("/b.json")!!)
//
//                for (city in cities) {
//                println(city.sehirAdi)
//                    pickCity.addItem(city.sehirAdi)
//                }
            }
        })



    }

    val preferredFocusedComponent: JComponent
        get() = pickCountry


    var preferredFocusedComponentCountry: String
        get() = country
        set(value) {
            country = value
        }

    var preferredFocusedComponentCounties: String
        get() = counties
        set(value) {
            counties = value
        }

    var preferredFocusedComponentCity: String
        get() = city
        set(value) {
            city = value
        }



    private fun getResourceAsText(path: String): String? =
        object {}.javaClass.getResource(path)?.readText()
}

