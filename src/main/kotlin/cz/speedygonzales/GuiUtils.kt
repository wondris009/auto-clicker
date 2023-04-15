package cz.speedygonzales

import java.awt.Component
import java.awt.Dimension
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

object GuiUtils {

    fun getInputRow(label: String, tf: JComponent?, alignment: Float = Component.LEFT_ALIGNMENT): JPanel {

        val container = JPanel()
        container.alignmentX = alignment
        container.preferredSize = Dimension(200, 32)
        val boxLayout = BoxLayout(container, BoxLayout.X_AXIS)
        container.layout = boxLayout
        if(label.isNotEmpty()) {
            container.add(JLabel(label))
        }
        if(tf != null) {
            container.add(tf)
        }

        return container
    }
}