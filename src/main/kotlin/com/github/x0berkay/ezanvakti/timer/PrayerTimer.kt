package com.github.x0berkay.ezanvakti.timer

import com.github.x0berkay.ezanvakti.settings.AppSettingsState
import utils.SoundService
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import java.util.*


class PrayerTimer(
    private val project: Project,
    private val timeText : String
) : TimerTask() {
    override fun run() {
        val config = AppSettingsState.instance
        val lang = if (config.language == "tr") Locale.forLanguageTag("tr-TR") else Locale.forLanguageTag("en-US")
        val bundle = ResourceBundle.getBundle("messages",lang)
        ApplicationManager.getApplication().invokeLater {
            SoundService.playSound()
            //show notification
            Messages.showMessageDialog(project, timeText, bundle.getString("timer.text"), Messages.getInformationIcon())
        }
    }
}


fun schedulePrayerTime(project: Project, prayerTime: Calendar, timeText: String) {
    val timer = Timer()
    timer.schedule(PrayerTimer(project,timeText), prayerTime.time)
}