package cz.sg

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import mu.KotlinLogging

class MoveScreenKeyListener : NativeKeyListener {

    private val clicker = Clicker()

    override fun nativeKeyPressed(e: NativeKeyEvent) {
        when (e.keyCode) {
            NativeKeyEvent.VC_LEFT -> left()
            NativeKeyEvent.VC_RIGHT -> right()
            NativeKeyEvent.VC_UP -> up()
            NativeKeyEvent.VC_DOWN -> down()
        }
    }

    private fun left() {
        logger.info { "Moving left" }
        if (ScreenHelper.hasTwoScreens()) {
            clicker.moveLeftDoubleScreen()
        } else {
            clicker.moveLeftMacOnly()
        }
    }

    private fun right() {
        logger.info { "Moving right" }
        if (ScreenHelper.hasTwoScreens()) {
            clicker.moveRightDoubleScreen()
        } else {
            clicker.moveRightMacOnly()
        }
    }

    private fun up() {
        logger.info { "Moving up" }
        if (ScreenHelper.hasTwoScreens()) {
            clicker.moveUpDoubleScreen()
        } else {
            clicker.moveUpMacOnly()
        }
    }

    private fun down() {
        logger.info { "Moving down" }
        if (ScreenHelper.hasTwoScreens()) {
            clicker.moveDownDoubleScreen()
        } else {
            clicker.moveDownMacOnly()
        }
    }

    private val logger = KotlinLogging.logger { }
}
