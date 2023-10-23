package cz.sg

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent
import com.github.kwhat.jnativehook.mouse.NativeMouseListener
import java.awt.MouseInfo
import java.awt.Point
import javax.swing.JLabel
import javax.swing.JTextArea

class AddPointMouseListener(
    private val presets: MutableMap<String, Preset>,
    private val points: MutableList<Point>,
    private val pointsTextArea: JTextArea
) : NativeMouseListener {

    override fun nativeMouseClicked(e: NativeMouseEvent) {
        if (ctrlAltPressed(e)) {
            if(presets.isEmpty()) {
                GuiUtils.showErrorMessage(pointsTextArea, JLabel("Create preset first !!!"))
            } else {
                val position = MouseInfo.getPointerInfo().location
                pointsTextArea.append("$position\n")
                points.add(position)
                FileUtils.savePoints(presets.values.first { it.selected }, points)
            }
        }
    }

    private fun ctrlAltPressed(e: NativeMouseEvent) = e.modifiers == 10
}
