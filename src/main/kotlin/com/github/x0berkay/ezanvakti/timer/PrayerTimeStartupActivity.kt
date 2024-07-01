package com.github.x0berkay.ezanvakti.timer


import com.github.x0berkay.ezanvakti.reader.PrayerTimesService
import com.github.x0berkay.ezanvakti.settings.AppSettingsState
import com.github.x0berkay.ezanvakti.utils.DateHelper
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import java.util.*

class PrayerTimeStartupActivity : ProjectActivity {
    private var project: Project? = null
    override suspend fun execute(project: Project) {
        this.project = project
        //delete all previous scheduled tasks
        Timer().cancel()


        //find the current prayer time
        val currentPrayerTime = PrayerTimesService().getCurrentPrayerTime()
        val config = AppSettingsState.instance
        val lang = if (config.language == "tr") Locale.forLanguageTag("tr-TR") else Locale.forLanguageTag("en-US")
        val bundle = ResourceBundle.getBundle("messages",lang)

        if (currentPrayerTime != null) {
            //schedule prayer time
            schedulePrayer(currentPrayerTime.imsak, bundle.getString("dialog.fajr"))
            schedulePrayer(currentPrayerTime.gunes, bundle.getString("dialog.sunrise"))
            schedulePrayer(currentPrayerTime.ogle, bundle.getString("dialog.dhuhr"))
            schedulePrayer(currentPrayerTime.ikindi, bundle.getString("dialog.asr"))
            schedulePrayer(currentPrayerTime.aksam, bundle.getString("dialog.maghrib"))
            schedulePrayer(currentPrayerTime.yatsi, bundle.getString("dialog.isha"))
        }
    }

    private fun schedulePrayer(prayerTime: String?, timeText: String) {
        if (prayerTime == null) {
            return
        }
        val current = Calendar.getInstance()

        val calendar = DateHelper.parsePrayerTime(prayerTime, AppSettingsState.instance.timeBefore, current)

        //check if the time is in the past
        if (calendar.before(current)) {
            return
        }

        project?.let { schedulePrayerTime(it, calendar, timeText) }
    }
}
