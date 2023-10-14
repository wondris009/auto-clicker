package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import java.awt.*
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

    fun createExitButton(pointsPath: String, points: MutableList<Point>): JButton {
        val button = JButton("EXIT")
        button.setMnemonic('X')
        button.setFont(Font(Font.MONOSPACED, Font.PLAIN, 16))
        button.foreground = Color.RED
        button.addActionListener {
            exit(pointsPath, points)
        }
        return button
    }

    fun exit() {
        GlobalScreen.unregisterNativeHook()
        exitProcess(-1)
    }

    fun exit(pointsPath: String, points: List<Point>) {
        FileUtils.savePoints(pointsPath, points)
        exit()
    }

    fun showErrorMessage(parentComponent: JComponent, msg: JLabel) {
        JOptionPane.showMessageDialog(parentComponent, msg, "Error", JOptionPane.ERROR_MESSAGE)
    }

    fun showConfirmDialog(parentComponent: JComponent, msg: JLabel): Int {
        return JOptionPane.showConfirmDialog(parentComponent, msg, "Warning", JOptionPane.YES_NO_OPTION)
    }
}