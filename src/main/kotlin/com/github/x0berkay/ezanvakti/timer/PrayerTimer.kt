package com.github.x0berkay.ezanvakti.timer

import utils.SoundService
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import java.util.Calendar
import java.util.Timer
import java.util.TimerTask


class PrayerTimer(
    private val project: Project,
    private val timeText : String
) : TimerTask() {
    override fun run() {
        ApplicationManager.getApplication().invokeLater {
            SoundService.playSound()
            //show notification
            Messages.showMessageDialog(project, timeText, "Vakit Cikti", Messages.getInformationIcon())
        }
    }
}


fun schedulePrayerTime(project: Project, prayerTime: Calendar, timeText: String) {
    val timer = Timer()
    timer.schedule(PrayerTimer(project,timeText), prayerTime.time)
}