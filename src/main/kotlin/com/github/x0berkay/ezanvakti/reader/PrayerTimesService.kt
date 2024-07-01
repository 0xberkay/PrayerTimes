package com.github.x0berkay.ezanvakti.reader

import com.github.x0berkay.ezanvakti.client.Client
import com.github.x0berkay.ezanvakti.models.TimesItem
import com.github.x0berkay.ezanvakti.settings.AppSettingsState
import com.github.x0berkay.ezanvakti.utils.DateHelper
import java.util.*
import java.util.concurrent.TimeUnit

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

    fun getNextPrayerTime(currentPrayerTime: TimesItem?, bundle: ResourceBundle): String? {
        val calendar = Calendar.getInstance()
        val getTime = { time: String -> DateHelper.nextPrayerTime(time) }
        val prayerTimes = mapOf(
            bundle.getString("dialog.fajr") to currentPrayerTime?.imsak,
            bundle.getString("dialog.sunrise") to currentPrayerTime?.gunes,
            bundle.getString("dialog.dhuhr") to currentPrayerTime?.ogle,
            bundle.getString("dialog.asr") to currentPrayerTime?.ikindi,
            bundle.getString("dialog.maghrib") to currentPrayerTime?.aksam,
            bundle.getString("dialog.isha") to currentPrayerTime?.yatsi
        )

        val nextPrayerTime = prayerTimes
            .filterValues { it != null }
            .mapValues { getTime(it.value!!) }
            .filter { it.value.after(calendar) }
            .minByOrNull { it.value.timeInMillis }

        //now - nextPrayerTime
        val diff = nextPrayerTime?.value?.timeInMillis?.minus(calendar.timeInMillis) ?: 0
        val diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff)

        return if (nextPrayerTime != null) {
            "${nextPrayerTime.key}: ${DateHelper.timeFormatter.format(nextPrayerTime.value)} - $diffInMinutes ${bundle.getString("dialog.minutes")}"
        } else {
            null
        }
    }
}