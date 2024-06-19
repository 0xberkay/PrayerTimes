package com.github.x0berkay.ezanvakti.settings

import com.github.x0berkay.ezanvakti.client.Client
import com.github.x0berkay.ezanvakti.models.CitiesItem
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.swing.JPanel
import javax.swing.event.PopupMenuEvent

/**
 * Supports creating and managing a [JPanel] for the Settings Dialog.
 */
class AppSettingsComponent {
    val panel: JPanel
    private val pickCity: ComboBox<String> = ComboBox()
    private val pickTown: ComboBox<String> = ComboBox()
    private var country: String = ""
    private var city: String = ""
    private var town: String = ""
    var townId = 0

    private var cityItems = mutableListOf<CitiesItem>()

    private val client = Client()


    init {

        cityItems =
            Json.decodeFromString(ListSerializer(CitiesItem.serializer()), getResourceAsText("/cities.json")!!)
                .toMutableList()


        for (city in cityItems) {
            pickCity.addItem(city.sehirAdi)
        }
        panel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Şehir"), pickCity, 1, false)
            .addLabeledComponent(JBLabel("İlçe"), pickTown, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel

        val appSettingsState = AppSettingsState.instance
        country = appSettingsState.country
        city = appSettingsState.city
        town = appSettingsState.town


        //set selected
        pickCity.selectedIndex = cityItems.indexOfFirst { it.sehirAdi == city }

        //just set text for town
        pickTown.addItem(town)



        pickCity.addPopupMenuListener(object : CustomListener() {
            override fun popupMenuWillBecomeInvisible(e: PopupMenuEvent) {
                val selectedItem = cityItems[pickCity.selectedIndex]
                city = selectedItem.sehirAdi

                pickTown.removeAllItems()

                val towns = client.getTowns(selectedItem.sehirID)
                for (town in towns) {
                    pickTown.addItem(town.ilceAdi)
                }
            }
        })

        pickTown.addPopupMenuListener(object : CustomListener() {
            override fun popupMenuWillBecomeInvisible(e: PopupMenuEvent) {
                val selectedItem = client.getTowns(cityItems[pickCity.selectedIndex].sehirID)[pickTown.selectedIndex]
                town = pickTown.selectedItem?.toString() ?: ""
                townId = selectedItem.ilceID.toIntOrNull() ?: 0

//                val times = TimesState.instance.items
//
//                // Convert times to string
//                val timesString = times.joinToString(separator = "\n") { time ->
//                    "Imsak: ${time.imsak}, Gunes: ${time.gunes}, Ogle: ${time.ogle}, Ikindi: ${time.ikindi}, Aksam: ${time.aksam}, Yatsi: ${time.yatsi}, MiladiTarihKisa: ${time.miladiTarihKisa}, HicriTarihKisa: ${time.hicriTarihKisa}"
//                }
//
//                // Show times in dialog
//                Messages.showMessageDialog(null, timesString, "Ezan Times", Messages.getInformationIcon())
            }
        })
    }


    var preferredFocusedComponentCity: String
        get() = city
        set(value) {
            city = value
        }

    var preferredFocusedComponentTown: String
        get() = town
        set(value) {
            town = value
        }


    private fun getResourceAsText(path: String): String? =
        object {}.javaClass.getResource(path)?.readText()
}

