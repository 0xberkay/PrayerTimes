package com.github.x0berkay.ezanvakti.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * Supports storing the application settings in a persistent way.
 * The [State] and [Storage] annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(name = "com.github.x0berkay.ezanvakti.settings.AppSettingsState", storages = [Storage("EzanVaktiPlugin.xml")])
class AppSettingsState : PersistentStateComponent<AppSettingsState?> {
    var country: String = ""
    var city: String = ""
    var town: String = ""
    var townId: Int = 0
    var language: String = "en"
    var timeBefore: Int = 0
    var pickedSound: String = ""


    override fun getState(): AppSettingsState {
        return this
    }

    override fun loadState(state: AppSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: AppSettingsState
            get() = ApplicationManager.getApplication().getService(
                AppSettingsState::class.java
            )
    }
}