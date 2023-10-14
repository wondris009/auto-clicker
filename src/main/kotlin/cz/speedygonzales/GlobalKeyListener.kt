package cz.speedygonzales

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import java.awt.Point

class GlobalKeyListener(
    private val clicker: Clicker,
    private val enableScreenMoving: Boolean,
    private val pointsPath: String,
    private val points: MutableList<Point>
) : NativeKeyListener {

    override fun nativeKeyPressed(e: NativeKeyEvent) {
        if (controlF1Pressed(e)) {
            GuiUtils.exit(pointsPath, points)
        }

        if (enableScreenMoving) {
            when (e.keyCode) {
                NativeKeyEvent.VC_LEFT -> left()
                NativeKeyEvent.VC_RIGHT -> right()
                NativeKeyEvent.VC_UP -> up()
                NativeKeyEvent.VC_DOWN -> down()
            }
        }
    }

    private fun controlF1Pressed(e: NativeKeyEvent) =
        e.modifiers == 2 && e.keyCode == NativeKeyEvent.VC_F1

    private fun left() {
        if(ScreenHelper.hasTwoScreens()) {
            clicker.moveLeftDoubleScreen()
        } else {
            clicker.moveLeftMacOnly()
        }
    }

    private fun right() {
        if(ScreenHelper.hasTwoScreens()) {
            clicker.moveRightDoubleScreen()
        } else {
            clicker.moveRightMacOnly()
        }
    }

    private fun up() {
        if(ScreenHelper.hasTwoScreens()) {
            clicker.moveUpDoubleScreen()
        } else {
            clicker.moveUpMacOnly()
        }
    }

    private fun down() {
        if(ScreenHelper.hasTwoScreens()) {
            clicker.moveDownDoubleScreen()
        } else {
            clicker.moveDownMacOnly()
        }
    }
}
