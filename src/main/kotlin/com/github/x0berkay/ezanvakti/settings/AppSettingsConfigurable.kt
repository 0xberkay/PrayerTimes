package com.github.x0berkay.ezanvakti.settings

import com.github.x0berkay.ezanvakti.client.Client
import com.github.x0berkay.ezanvakti.reader.PrayerTimesReader
import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 */
class AppSettingsConfigurable : Configurable {
    private var mySettingsComponent: AppSettingsComponent? = null

    // A default constructor with no arguments is required because this implementation
    // is registered in an applicationConfigurable EP
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return "Ezan Vakti"
    }

    override fun createComponent(): JComponent {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.instance
        val city = mySettingsComponent!!.preferredFocusedComponentCity
        val town = mySettingsComponent!!.preferredFocusedComponentTown

        return  city != settings.city || town != settings.town
    }

    override fun apply() {
        val settings = AppSettingsState.instance
        //val timesState = TimesState.instance
        settings.city = mySettingsComponent!!.preferredFocusedComponentCity
        settings.town = mySettingsComponent!!.preferredFocusedComponentTown
        settings.townId = mySettingsComponent!!.townId

        val times = Client().getTimes(settings.townId.toString())
        //save times to state
        val reader = PrayerTimesReader()
        reader.writePrayerTimes(times)
    }

    override fun reset() {
        val settings = AppSettingsState.instance
        mySettingsComponent!!.preferredFocusedComponentCity = settings.city
        mySettingsComponent!!.preferredFocusedComponentTown = settings.town
        mySettingsComponent!!.townId = settings.townId

    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}