package com.github.x0berkay.ezanvakti.reader

import com.github.x0berkay.ezanvakti.models.TimesItem
import com.intellij.openapi.application.PathManager
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

class PrayerTimesReader {
    private val fileName = "EzanTimes.json"
    private val filePath = PathManager.getOptionsPath() + File.separator + fileName
    fun readPrayerTimes():  List<TimesItem> {
        //check is file exists

        if (!File(filePath).exists()) {
            return emptyList()
        }
        val file = File(filePath)

        val fileContent = file.readText()
        return Json.decodeFromString<List<TimesItem>>(fileContent)
    }

    fun writePrayerTimes(timesState: List<TimesItem>) {
        val file = File(filePath)
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileContent = Json.encodeToString(ListSerializer(TimesItem.serializer()), timesState)
        file.writeText(fileContent)
    }
}