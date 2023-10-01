package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import kotlin.system.exitProcess

class GlobalKeyListener(private val clicker: Clicker) : NativeKeyListener {

    override fun nativeKeyPressed(e: NativeKeyEvent) {

        if(controlEscapePressed(e)) {
            exit()
        }

        when (e.keyCode) {
            NativeKeyEvent.VC_LEFT -> left()
            NativeKeyEvent.VC_RIGHT -> right()
            NativeKeyEvent.VC_UP -> up()
            NativeKeyEvent.VC_DOWN -> down()
        }
    }

    private fun controlEscapePressed(e: NativeKeyEvent) =
        e.modifiers == 2 && e.keyCode == NativeKeyEvent.VC_ESCAPE

    private fun exit() {
        GlobalScreen.unregisterNativeHook()
        exitProcess(-1)
    }

    private fun left() {
//        clicker.moveLeftDoubleScreen()
        clicker.moveLeftMacOnly()
    }

    private fun right() {
//        clicker.moveRightDoubleScreen()
        clicker.moveRightMacOnly()
    }

    private fun up() {
//        clicker.moveUpDoubleScreen()
        clicker.moveUpMacOnly()
    }

    private fun down() {
//        clicker.moveDownDoubleScreen()
        clicker.moveDownMacOnly()
    }
}
