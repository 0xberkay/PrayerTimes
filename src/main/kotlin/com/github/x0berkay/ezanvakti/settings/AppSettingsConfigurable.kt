package com.github.x0berkay.ezanvakti.settings

import com.github.x0berkay.ezanvakti.client.Client
import com.github.x0berkay.ezanvakti.reader.PrayerTimesReader
import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent
import com.intellij.openapi.project.ProjectManager

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
        val language = mySettingsComponent!!.preferredFocusedComponentLanguage
        val timeBefore = mySettingsComponent!!.preferredFocusedComponentTimeBefore

        return city != settings.city || town != settings.town || language != settings.language || timeBefore != settings.timeBefore
    }

    override fun apply() {
        val settings = AppSettingsState.instance

        if (mySettingsComponent!!.townId != settings.townId) {
            val times = Client().getTimes(settings.townId.toString())
            //save times to state
            val reader = PrayerTimesReader()
            reader.writePrayerTimes(times)
        }
        //val timesState = TimesState.instance
        settings.city = mySettingsComponent!!.preferredFocusedComponentCity
        settings.town = mySettingsComponent!!.preferredFocusedComponentTown
        settings.townId = mySettingsComponent!!.townId
        settings.language = mySettingsComponent!!.preferredFocusedComponentLanguage
        settings.timeBefore = mySettingsComponent!!.preferredFocusedComponentTimeBefore

        val project = ProjectManager.getInstance().openProjects.firstOrNull()
        if (project != null) {
            requestRestart(project)
        }
    }

    override fun reset() {
        val settings = AppSettingsState.instance
        mySettingsComponent!!.preferredFocusedComponentCity = settings.city
        mySettingsComponent!!.preferredFocusedComponentTown = settings.town
        mySettingsComponent!!.townId = settings.townId
        mySettingsComponent!!.preferredFocusedComponentLanguage = settings.language
        mySettingsComponent!!.preferredFocusedComponentTimeBefore = settings.timeBefore

    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}

