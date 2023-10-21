package cz.sg

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import java.awt.Point

class ExitButtonKeyListener(
    private val pointsPath: String,
    private val points: MutableList<Point>
) : NativeKeyListener {

    override fun nativeKeyPressed(e: NativeKeyEvent) {
        if (controlF1Pressed(e)) {
            GuiUtils.exit(pointsPath, points)
        }
    }

    private fun controlF1Pressed(e: NativeKeyEvent) =
        e.modifiers == 2 && e.keyCode == NativeKeyEvent.VC_F1
}
