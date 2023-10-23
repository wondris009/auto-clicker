package cz.sg

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener

class ExitButtonKeyListener : NativeKeyListener {

    override fun nativeKeyPressed(e: NativeKeyEvent) {
        if (controlF1Pressed(e)) {
            GuiUtils.exit()
        }
    }

    private fun controlF1Pressed(e: NativeKeyEvent) =
        e.modifiers == 2 && e.keyCode == NativeKeyEvent.VC_F1
}
