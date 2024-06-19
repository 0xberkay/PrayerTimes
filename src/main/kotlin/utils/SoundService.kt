package utils

import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

object SoundService {
    fun playSound() {
        val resource = javaClass.getResource("/some.wav")
        val audioInputStream: AudioInputStream = AudioSystem.getAudioInputStream(resource)
        val clip = AudioSystem.getClip()
        clip.open(audioInputStream)
        clip.start()
    }
}