package cz.sg

import java.awt.Color
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JTable
import javax.swing.table.DefaultTableModel

class CoordinatesTable(private var preset: Preset?) : JTable() {

    private var data: DefaultTableModel = object : DefaultTableModel(0, 0) {
        override fun isCellEditable(row: Int, column: Int): Boolean {
            return editAmountColumn(column)
        }
    }

    init {
        this.model = data
        data.setColumnIdentifiers(arrayOf("Coordinate", "Amount"))
        this.tableHeader.isOpaque = false
        this.tableHeader.background = Color(204, 229, 255)

        this.addKeyListener(object : KeyAdapter() {
            override fun keyReleased(e: KeyEvent) {
                if (e.keyCode == KeyEvent.VK_ENTER) {
                    preset!!.pointsAndAmounts[getRow()].amount = data.getValueAt(getRow(), 1).toString()
                    FileUtils.savePoints(preset!!)
                }
            }
        })

        setData()
        reloadData(preset)
    }

    fun reloadData(newPreset: Preset?) {
        setPreset(newPreset)
        clear()
        setData()
    }

    private fun setPreset(newPreset: Preset?) {
        preset = newPreset
    }

    fun clear() {
        data.rowCount = 0
    }

    private fun addRow(row: Array<String>) {
        data.addRow(row)
    }

    private fun setData() {
        preset?.pointsAndAmounts?.forEach {
            addRow(arrayOf(it.point.toString(), it.amount))
        }
    }

    private fun getRow() = this.selectedRow

    private fun editAmountColumn(column: Int) = column == 1
}