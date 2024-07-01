package com.github.x0berkay.ezanvakti.utils

import java.util.*

object DateHelper {
    private const val DATE_FORMAT = "dd.MM.yyyy"
    private const val TIME_FORMAT = "HH:mm:ss"
    val formatter = java.text.SimpleDateFormat(DATE_FORMAT)
    val timeFormatter = java.text.SimpleDateFormat(TIME_FORMAT)

    fun parsePrayerTime(prayerTime: String, timeBefore: Int, current: Calendar): Calendar {
        val calendar = current.clone() as Calendar
        val time = prayerTime.split(":")
        var hour = time[0].toInt()
        var minute = time[1].toInt()
        if (minute - timeBefore < 0) {
            hour -= 1
            minute = 60 + (minute - timeBefore)
        } else {
            minute -= timeBefore
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        return calendar
    }

    fun nextPrayerTime(prayerTime: String): Calendar {
        val calendar = Calendar.getInstance()
        val time = prayerTime.split(":")
        val hour = time[0].toInt()
        val minute = time[1].toInt()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        return calendar
    }

    fun getFormattedDate(): String {
        val date = Calendar.getInstance().time
        return formatter.format(date)
    }
}