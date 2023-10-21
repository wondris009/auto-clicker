package cz.sg

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent
import com.github.kwhat.jnativehook.mouse.NativeMouseListener
import java.awt.MouseInfo
import java.awt.Point
import javax.swing.JTextArea

class GlobalMouseListener(private val points: MutableList<Point>, private val pointsTextArea: JTextArea) :
    NativeMouseListener {

    override fun nativeMouseClicked(e: NativeMouseEvent) {
        if (ctrlAltPressed(e)) {
            val position = MouseInfo.getPointerInfo().location
            pointsTextArea.append("$position\n")
            points.add(position)
        }
    }

    private fun ctrlAltPressed(e: NativeMouseEvent) = e.modifiers == 10
}
