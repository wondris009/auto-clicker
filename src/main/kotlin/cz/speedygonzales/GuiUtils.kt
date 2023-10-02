package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import java.awt.event.ActionEvent
import javax.swing.*
import kotlin.system.exitProcess

object GuiUtils {

    fun getInputRow(label: String = "", labelComponent: JComponent, alignment: Float = Component.LEFT_ALIGNMENT): JPanel {

        val container = JPanel()
        container.alignmentX = alignment
        container.preferredSize = Dimension(200, 32)
        container.size = Dimension(200, 32)
        val boxLayout = BoxLayout(container, BoxLayout.X_AXIS)
        container.layout = boxLayout
        if (label.isNotEmpty()) {
            container.add(JLabel(label))
        }
        container.add(labelComponent)

        return container
    }

    fun createButton(buttonLabel: String, fn: (a: ActionEvent) -> Unit): JButton {
        val button = JButton(buttonLabel)
        button.setFont(Font(Font.MONOSPACED, Font.PLAIN, 14))
        button.foreground = Color.DARK_GRAY
        button.addActionListener(fn)
        return button
    }

    fun exit() {
        GlobalScreen.unregisterNativeHook()
        exitProcess(-1)
    }
}