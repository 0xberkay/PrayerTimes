package com.github.x0berkay.ezanvakti.reader

import com.github.x0berkay.ezanvakti.client.Client
import com.github.x0berkay.ezanvakti.models.TimesItem
import com.github.x0berkay.ezanvakti.settings.AppSettingsState
import com.github.x0berkay.ezanvakti.utils.DateHelper
import java.util.*

class PrayerTimesService {
    private val reader = PrayerTimesReader()
    private val client = Client()

    fun getCurrentPrayerTime(): TimesItem? {
        val config = AppSettingsState.instance
        var times = reader.readPrayerTimes()

        val date = Calendar.getInstance().time
        val formattedDate = DateHelper.formatter.format(date)

        var currentPrayerTime = times.find { it.miladiTarihKisa == formattedDate }
        if (currentPrayerTime == null) {
            times = client.getTimes(config.townId.toString())
            reader.writePrayerTimes(times)
            currentPrayerTime = times.find { it.miladiTarihKisa == formattedDate }
        }
        return currentPrayerTime
    }
}