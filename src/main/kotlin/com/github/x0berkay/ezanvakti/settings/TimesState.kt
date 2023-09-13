package com.github.x0berkay.ezanvakti.settings

import com.github.x0berkay.ezanvakti.models.TimesItem
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

/**
 * Supports storing the application settings in a persistent way.
 * The [State] and [Storage] annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(name = "com.github.x0berkay.ezanvakti.settings.TimesState", storages = [Storage("EzanTimes.xml")])
class TimesState : PersistentStateComponent<TimesState?> {
//    val aksam: String = ""
//    val ayinSekliURL: String = ""
//    val greenwichOrtalamaZamani: Double = 0.0
//    val gunes: String = ""
//    val gunesBatis: String = ""
//    val gunesDogus: String = ""
//    val hicriTarihKisa: String = ""
//    val hicriTarihKisaIso8601: String = ""
//    val hicriTarihUzun: String = ""
//    val hicriTarihUzunIso8601: String = ""
//    val ikindi: String = ""
//    val imsak: String = ""
//    val kibleSaati: String = ""
//    val miladiTarihKisa: String = ""
//    val miladiTarihKisaIso8601: String = ""
//    val miladiTarihUzun: String = ""
//    val miladiTarihUzunIso8601: String = ""
//    val ogle: String = ""
//    val yatsi: String = ""

    var items = mutableListOf<TimesItem>()



    override fun getState(): TimesState {
        return this
    }

    override fun loadState(state: TimesState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: TimesState
            get() = ApplicationManager.getApplication().getService(
                TimesState::class.java
            )
    }
}