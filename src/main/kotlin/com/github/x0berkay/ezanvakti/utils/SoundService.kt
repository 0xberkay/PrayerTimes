package com.github.x0berkay.ezanvakti.utils

import java.net.URL
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

object SoundService {
    fun playSound(path: String? = null) {
        val resource: URL? = if (path != null) {
            java.io.File(path).toURI().toURL()
        } else {
            javaClass.getResource("/some.wav")
        }
        val audioInputStream: AudioInputStream = AudioSystem.getAudioInputStream(resource)
        val clip = AudioSystem.getClip()
        clip.open(audioInputStream)
        clip.start()
    }
}