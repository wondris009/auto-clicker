package cz.sg

import java.awt.GraphicsEnvironment

object ScreenHelper {

    private var screenCount = 0

    init {
        val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
        val screens = ge.screenDevices
        screenCount = screens.size
    }

    fun hasTwoScreens() = screenCount > 1
}