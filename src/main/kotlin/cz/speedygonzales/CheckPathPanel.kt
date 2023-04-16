package cz.speedygonzales

import java.awt.Color
import java.awt.Dimension
import java.awt.Point
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTextArea

class CheckPathPanel(private val points: MutableList<Point>, pointsTextArea: JTextArea) : JPanel() {

    init {
        this.layout = BoxLayout(this, BoxLayout.Y_AXIS)

        this.preferredSize = Dimension(100, 100)
        this.size = Dimension(100, 100)
        this.background = Color.LIGHT_GRAY

        val removeLastValueButton = JButton("Remove last value")
        removeLastValueButton.addActionListener {
            if (points.isNotEmpty()) {
                points.removeLast()
                val linesCount = pointsTextArea.lineCount
                val start = pointsTextArea.getLineStartOffset(linesCount - 2)
                val end = pointsTextArea.getLineEndOffset(linesCount - 1)
                pointsTextArea.replaceRange("", start, end)
            }
        }
        this.add(removeLastValueButton)

        val clearAllPointsButton = JButton("Clear all")
        clearAllPointsButton.addActionListener {
            points.clear()
            pointsTextArea.text = ""
        }
        this.add(clearAllPointsButton)

        this.add(pointsTextArea)
    }
}

