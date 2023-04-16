package cz.speedygonzales

import java.awt.Component
import java.awt.Dimension
import java.awt.event.ActionEvent
import javax.swing.*

object GuiUtils {

    fun getInputRow(label: String, tf: JComponent?, alignment: Float = Component.LEFT_ALIGNMENT): JPanel {

        val container = JPanel()
        container.alignmentX = alignment
        container.preferredSize = Dimension(200, 32)
        val boxLayout = BoxLayout(container, BoxLayout.X_AXIS)
        container.layout = boxLayout
        if (label.isNotEmpty()) {
            container.add(JLabel(label))
        }
        if (tf != null) {
            container.add(tf)
        }

        return container
    }

    fun createButton(buttonLabel: String, fn: (a: ActionEvent) -> Unit): JButton {
        val button = JButton(buttonLabel)
        button.addActionListener(fn)
        return button
    }
}