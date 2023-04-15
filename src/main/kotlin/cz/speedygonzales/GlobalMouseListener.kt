package cz.speedygonzales

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent
import com.github.kwhat.jnativehook.mouse.NativeMouseListener
import java.awt.MouseInfo
import java.awt.Point
import javax.swing.JTextArea

class GlobalMouseListener(private val points: MutableList<Point>, private val pointsTextArea: JTextArea) :
    NativeMouseListener {

    override fun nativeMouseClicked(e: NativeMouseEvent) {
        if (e.modifiers == 10) {
            val position = MouseInfo.getPointerInfo().location
            val positionMessage = "X: ${position.x}, Y: ${position.y}"
//            Thread(AppendTextAreaRunnable(pointsTextArea, positionMessage)).start()
            pointsTextArea.append("$positionMessage\n")
            points.add(position)
        }
    }
}
