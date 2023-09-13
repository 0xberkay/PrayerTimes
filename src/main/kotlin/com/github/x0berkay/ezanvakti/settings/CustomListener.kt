package com.github.x0berkay.ezanvakti.settings

import javax.swing.event.PopupMenuEvent
import javax.swing.event.PopupMenuListener

abstract class CustomListener : PopupMenuListener {
    override fun popupMenuWillBecomeVisible(e: PopupMenuEvent) {}
    override fun popupMenuWillBecomeInvisible(e: PopupMenuEvent) {}
    override fun popupMenuCanceled(e: PopupMenuEvent) {}
}
