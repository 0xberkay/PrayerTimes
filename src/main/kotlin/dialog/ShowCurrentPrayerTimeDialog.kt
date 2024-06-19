package dialog

import com.github.x0berkay.ezanvakti.reader.PrayerTimesService
import com.github.x0berkay.ezanvakti.settings.AppSettingsState
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class ShowCurrentPrayerTimeDialog : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {

        val config = AppSettingsState.instance

        val currentPrayerTime = PrayerTimesService().getCurrentPrayerTime()
        if (currentPrayerTime != null) {
            Messages.showMessageDialog(
                "Bugün için ezan vakitleri:\n" +
                        "İmsak: ${currentPrayerTime.imsak}\n" +
                        "Güneş: ${currentPrayerTime.gunes}\n" +
                        "Öğle: ${currentPrayerTime.ogle}\n" +
                        "İkindi: ${currentPrayerTime.ikindi}\n" +
                        "Akşam: ${currentPrayerTime.aksam}\n" +
                        "Yatsı: ${currentPrayerTime.yatsi}\n" +
                        "Miladi Tarih: ${currentPrayerTime.miladiTarihKisa}\n" +
                        "Hicri Tarih: ${currentPrayerTime.hicriTarihKisa}",
                "Ezan Vakti - ${config.town} - ${config.city}",
                Messages.getInformationIcon()
            )
        } else {
            Messages.showMessageDialog(
                "Bugün için ezan vakti bilgisi bulunamadı.",
                "Ezan Vakti - ${config.town} - ${config.city}",
                Messages.getInformationIcon()
            )
        }
    }
}