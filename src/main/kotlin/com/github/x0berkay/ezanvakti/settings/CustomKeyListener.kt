package com.github.x0berkay.ezanvakti.settings

interface CustomKeyListener : java.awt.event.KeyListener {
    override fun keyTyped(e: java.awt.event.KeyEvent) {}
    override fun keyPressed(e: java.awt.event.KeyEvent) {}
    override fun keyReleased(e: java.awt.event.KeyEvent) {}
}