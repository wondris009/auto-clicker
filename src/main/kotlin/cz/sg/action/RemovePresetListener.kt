package cz.sg.action

import cz.sg.CoordinatesTable
import cz.sg.FileUtils
import cz.sg.InfoLabel
import cz.sg.model.Presets
import mu.KotlinLogging
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JLabel
import javax.swing.JTable
import javax.swing.table.DefaultTableModel

class RemovePresetListener(
    private val presetTable: JTable,
    private val coordinatesTable: CoordinatesTable,
    private val infoLabel: JLabel,
    private val presets: Presets,
) : ActionListener {

    override fun actionPerformed(e: ActionEvent?) {
        if (presetTable.selectedRow != -1) {
            removePreset(infoLabel)
        } else {
            infoLabel.text = "Select preset from the table"
        }
    }

    private fun removePreset(infoLabel: JLabel) {
        val presetName = presetTable.model.getValueAt(presetTable.selectedRow, 0).toString()
        logger.info { "Removing preset with name: $presetName" }
        val preset = presets.getByName(presetName)!!
        if (preset.selected) {
            preset.clear()
        }
        presets.removePreset(presetName)
        infoLabel.text = "Preset $presetName deleted"
        FileUtils.deleteCoordsFile(preset)
        (presetTable.model as DefaultTableModel).removeRow(presetTable.selectedRow)
        coordinatesTable.clear()
    }

    private val logger = KotlinLogging.logger {  }
}