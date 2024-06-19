package com.github.x0berkay.ezanvakti.timer


import com.github.x0berkay.ezanvakti.reader.PrayerTimesService
import utils.DateHelper
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class PrayerTimeStartupActivity : StartupActivity.DumbAware {
    private var project: Project? = null
    override fun runActivity(project: Project) {
        this.project = project

        //find the current prayer time
        val currentPrayerTime = PrayerTimesService().getCurrentPrayerTime()

        if (currentPrayerTime != null) {
            //schedule prayer time
            schedulePrayer(currentPrayerTime.imsak, "İmsak")
            schedulePrayer(currentPrayerTime.gunes, "Güneş")
            schedulePrayer(currentPrayerTime.ogle, "Öğle")
            schedulePrayer(currentPrayerTime.ikindi, "İkindi")
            schedulePrayer(currentPrayerTime.aksam, "Akşam")
            schedulePrayer(currentPrayerTime.yatsi, "Yatsı")
        }
    }

    private fun schedulePrayer(prayerTime: String?, timeText: String) {
        if (prayerTime == null) {
            return
        }
        val calendar = DateHelper.parsePrayerTime(prayerTime)
        //println("Scheduling prayer time: $prayerTime")
        //println("calendar: ${calendar.timeInMillis}")
        //check if the time is in the past
        if (calendar.timeInMillis < System.currentTimeMillis()) {
            return
        }

        project?.let { schedulePrayerTime(it, calendar, timeText) }
    }
}
