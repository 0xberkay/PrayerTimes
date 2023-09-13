package com.github.x0berkay.ezanvakti.settings

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

    override fun getPreferredFocusedComponent(): JComponent {
        return mySettingsComponent!!.preferredFocusedComponent
    }

    override fun createComponent(): JComponent {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.instance

        return mySettingsComponent!!.preferredFocusedComponentCountry != settings.country
    }

    override fun apply() {
        val settings = AppSettingsState.instance
        settings.country = mySettingsComponent!!.preferredFocusedComponentCountry

//        settings.ideaStatus = mySettingsComponent!!.ideaUserStatus

    }

    override fun reset() {
        val settings = AppSettingsState.instance
        mySettingsComponent!!.preferredFocusedComponentCountry = settings.country

//        mySettingsComponent!!.ideaUserStatus = settings.ideaStatus

    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}