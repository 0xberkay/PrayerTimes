package utils

object DateHelper {
    private const val DATE_FORMAT = "dd.MM.yyyy"
    val formatter = java.text.SimpleDateFormat(DATE_FORMAT)

    fun parsePrayerTime(prayerTime: String): java.util.Calendar {
        val calendar = java.util.Calendar.getInstance()
        val time = prayerTime.split(":")
        calendar.set(java.util.Calendar.HOUR_OF_DAY, time[0].toInt())
        calendar.set(java.util.Calendar.MINUTE, time[1].toInt())
        calendar.set(java.util.Calendar.SECOND, 0)
        return calendar
    }
}