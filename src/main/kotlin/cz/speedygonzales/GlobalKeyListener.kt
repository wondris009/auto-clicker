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
//            NativeKeyEvent.VC_LEFT -> left()
//            NativeKeyEvent.VC_RIGHT -> right()
//            NativeKeyEvent.VC_UP -> up()
//            NativeKeyEvent.VC_DOWN -> down()
        }
    }

    private fun controlEscapePressed(e: NativeKeyEvent) =
        e.modifiers == 2 && e.keyCode == NativeKeyEvent.VC_ESCAPE

    private fun exit() {
        GlobalScreen.unregisterNativeHook()
        exitProcess(-1)
    }

    private fun left() {
        clicker.moveLeft()
    }

    private fun right() {
        clicker.moveRight()
    }

    private fun up() {
        clicker.moveUp()
    }

    private fun down() {
        clicker.moveDown()
    }
}
