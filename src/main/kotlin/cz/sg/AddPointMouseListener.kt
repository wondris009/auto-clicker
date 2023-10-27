package cz.sg

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent
import com.github.kwhat.jnativehook.mouse.NativeMouseListener
import java.awt.MouseInfo
import java.awt.Point
import javax.swing.JLabel
import javax.swing.JTextArea

class AddPointMouseListener(
    private val presets: MutableMap<String, Preset>,
    private val coordinatesTable: CoordinatesTable
) : NativeMouseListener {

    override fun nativeMouseClicked(e: NativeMouseEvent) {
        if (ctrlAltPressed(e)) {
            if(presets.isEmpty()) {
                GuiUtils.showErrorMessage(coordinatesTable, JLabel("Create preset first !!!"))
            } else {
                val preset = presets.getSelectedPreset()
                val position = MouseInfo.getPointerInfo().location
                preset.add(PointAmount(position))
                FileUtils.savePoints(preset)
                coordinatesTable.reloadData(preset)
            }
        }
    }

    private fun ctrlAltPressed(e: NativeMouseEvent) = e.modifiers == 10
}
