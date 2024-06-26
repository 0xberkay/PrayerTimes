package com.github.x0berkay.ezanvakti.settings

import com.github.x0berkay.ezanvakti.client.Client
import com.github.x0berkay.ezanvakti.models.CitiesItem
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.KeyEvent
import java.util.*
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JPanel
import javax.swing.event.PopupMenuEvent

class AppSettingsComponent {
    val panel: JPanel
    private val pickCity: ComboBox<String> = ComboBox()
    private val pickTown: ComboBox<String> = ComboBox()
    private val pickLanguage: ComboBox<String> = ComboBox()
    private val pickTimeBefore: JBTextField = JBTextField()
    private val soundPanel = JPanel(FlowLayout(FlowLayout.LEFT))
    private var country: String = ""
    private var city: String = ""
    private var town: String = ""
    var townId = 0
    private var language: String = "en"
    private var timeBefore = 0
    private var pickedSound: String = ""

    private var cityItems = mutableListOf<CitiesItem>()

    private val client = Client()

    init {
        pickTimeBefore.size = Dimension(50, 20)
        pickTimeBefore.layout = FlowLayout(FlowLayout.LEFT)
        cityItems =
            Json.decodeFromString(ListSerializer(CitiesItem.serializer()), getResourceAsText("/cities.json")!!)
                .toMutableList()

        for (city in cityItems) {
            pickCity.addItem(city.sehirAdi)
        }

        pickLanguage.addItem("en")
        pickLanguage.addItem("tr")
        val appSettingsState = AppSettingsState.instance

        val lang = if (appSettingsState.language == "tr") Locale.forLanguageTag("tr-TR") else Locale.forLanguageTag("en-US")
        val bundle = ResourceBundle.getBundle("messages",lang)

        val cityPanel = JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(JBLabel(bundle.getString("settings.city")))
            add(pickCity)
        }

        val townPanel = JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(JBLabel(bundle.getString("settings.town")))
            add(pickTown)
        }

        val timeBeforePanel = JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(JBLabel(bundle.getString("settings.timeBefore")))
            add(pickTimeBefore)
        }

        val languagePanel = JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(JBLabel(bundle.getString("settings.language")))
            add(pickLanguage)
        }



        panel = FormBuilder.createFormBuilder()
            .addComponent(cityPanel)
            .addComponent(townPanel)
            .addComponent(timeBeforePanel)
            .addSeparator()
            .addComponent(languagePanel)
            .addSeparator()
            .addComponent(soundPanel)
            .addComponentFillVertically(JPanel(), 0)
            .panel

        panel.preferredSize = Dimension(500, panel.preferredSize.height)

        country = appSettingsState.country
        city = appSettingsState.city
        town = appSettingsState.town
        language = appSettingsState.language
        timeBefore = appSettingsState.timeBefore
        pickedSound = appSettingsState.pickedSound

        soundPanel.apply {
            val pickSound = JBTextField()
            val pickSoundButton = JButton("...")
            pickSoundButton.addActionListener {
                val fileChooser = JFileChooser()
                fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY
                //only allow .wav files
                fileChooser.fileFilter = object : javax.swing.filechooser.FileFilter() {
                    override fun accept(f: java.io.File): Boolean {
                        return f.isDirectory || f.name.endsWith(".wav")
                    }

                    override fun getDescription(): String {
                        return "WAV files"
                    }
                }
                fileChooser.isMultiSelectionEnabled = false
                fileChooser.showOpenDialog(null)
                val selectedFile = fileChooser.selectedFile
                if (selectedFile != null) {
                    pickSound.text = selectedFile.absolutePath
                    pickedSound = selectedFile.absolutePath
                }
            }
            add(JBLabel(bundle.getString("settings.sound")))
            add(pickSound)
            add(pickSoundButton)
        }

        soundPanel.components.forEach {
            if (it is JBTextField) {
                it.text = pickedSound
            }
        }

        pickTimeBefore.text = timeBefore.toString()

        pickCity.selectedIndex = cityItems.indexOfFirst { it.sehirAdi == city }
        pickTown.addItem(town)
        pickLanguage.selectedIndex = if (language == "en") 0 else 1

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
            }
        })

        pickLanguage.addPopupMenuListener(object : CustomListener() {
            override fun popupMenuWillBecomeInvisible(e: PopupMenuEvent) {
                language = pickLanguage.selectedItem?.toString() ?: "en"
            }
        })

        pickTimeBefore.addKeyListener(object : CustomKeyListener {
            override fun keyReleased(e: KeyEvent) {
                if (!e.keyChar.isDigit() || (pickTimeBefore.text.toIntOrNull() ?: 0) < 60) {
                    timeBefore = pickTimeBefore.text.toIntOrNull() ?: 0
                }
            }
        })

        //add listener for sound picker
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
    var preferredFocusedComponentLanguage: String
        get() = language
        set(value) {
            language = value
        }
    var preferredFocusedComponentTimeBefore: Int
        get() = timeBefore
        set(value) {
            timeBefore = value
        }

    var preferredFocusedComponentSound: String
        get() = pickedSound
        set(value) {
            pickedSound = value
        }

    private fun getResourceAsText(path: String): String? =
        object {}.javaClass.getResource(path)?.readText()
}