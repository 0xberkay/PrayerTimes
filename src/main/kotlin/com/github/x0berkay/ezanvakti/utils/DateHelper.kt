package com.github.x0berkay.ezanvakti.utils

object DateHelper {
    private const val DATE_FORMAT = "dd.MM.yyyy"
    val formatter = java.text.SimpleDateFormat(DATE_FORMAT)

    fun parsePrayerTime(prayerTime: String,timeBefore: Int): java.util.Calendar {
        val calendar = java.util.Calendar.getInstance()
        val time = prayerTime.split(":")
        var hour = time[0].toInt()
        var minute = time[1].toInt()
        if (minute - timeBefore < 0) {
            hour -= 1
            minute = 60 + (minute - timeBefore)
        } else {
            minute -= timeBefore
        }
        calendar.set(java.util.Calendar.HOUR_OF_DAY, hour)
        calendar.set(java.util.Calendar.MINUTE, minute)
        calendar.set(java.util.Calendar.SECOND, 0)
        return calendar
    }
}