package com.github.x0berkay.ezanvakti.dialog

import com.github.x0berkay.ezanvakti.reader.PrayerTimesService
import com.github.x0berkay.ezanvakti.settings.AppSettingsState
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import java.util.*

class ShowCurrentPrayerTimeDialog : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val config = AppSettingsState.instance
        val lang = if (config.language == "tr") Locale.forLanguageTag("tr-TR") else Locale.forLanguageTag("en-US")
        val bundle = ResourceBundle.getBundle("messages",lang)

        val currentPrayerTime = PrayerTimesService().getCurrentPrayerTime()
        if (currentPrayerTime != null) {
            Messages.showMessageDialog(
                "${bundle.getString("dialog.prayerTimes")}\n" +
                        "${bundle.getString("dialog.fajr")}: ${currentPrayerTime.imsak}\n" +
                        "${bundle.getString("dialog.sunrise")}: ${currentPrayerTime.gunes}\n" +
                        "${bundle.getString("dialog.dhuhr")}: ${currentPrayerTime.ogle}\n" +
                        "${bundle.getString("dialog.asr")}: ${currentPrayerTime.ikindi}\n" +
                        "${bundle.getString("dialog.maghrib")}: ${currentPrayerTime.aksam}\n" +
                        "${bundle.getString("dialog.isha")}: ${currentPrayerTime.yatsi}\n" +
                        "${bundle.getString("dialog.gregorianDate")}: ${currentPrayerTime.miladiTarihKisa}\n" +
                        "${bundle.getString("dialog.hijriDate")}: ${currentPrayerTime.hicriTarihKisa}",
                "${bundle.getString("dialog.title")} - ${config.town} - ${config.city}",
                Messages.getInformationIcon()
            )
        } else {
            Messages.showMessageDialog(
                bundle.getString("dialog.noInformation"),
                "${bundle.getString("dialog.title")} - ${config.town} - ${config.city}",
                Messages.getInformationIcon()
            )
        }
    }
}