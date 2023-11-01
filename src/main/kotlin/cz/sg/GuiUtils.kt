package cz.sg

import com.github.kwhat.jnativehook.GlobalScreen
import mu.KotlinLogging
import java.awt.Color
import java.awt.Font
import java.awt.event.ActionEvent
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JOptionPane
import kotlin.system.exitProcess

object GuiUtils {

    fun createButton(color: Color = Color.DARK_GRAY, buttonLabel: String, fn: (a: ActionEvent) -> Unit): JButton {
        val button = JButton(buttonLabel)
        button.setFont(Font(Font.MONOSPACED, Font.PLAIN, 12))
        button.foreground = color
        button.addActionListener(fn)
        return button
    }

    fun createButton2(color: Color = Color.DARK_GRAY, buttonLabel: String): JButton {
        val button = JButton(buttonLabel)
        button.setFont(Font(Font.MONOSPACED, Font.PLAIN, 12))
        button.foreground = color
        return button
    }

    fun createExitButton(): JButton {
        val button = JButton("EXIT")
        button.setFont(Font(Font.MONOSPACED, Font.BOLD, 12))
        button.foreground = Color.RED
        button.addActionListener {
            exit()
        }
        return button
    }

    fun exit() {
        logger.info { "Closing application" }
        GlobalScreen.unregisterNativeHook()
        exitProcess(-1)
    }

    fun showErrorMessage(parentComponent: JComponent, msg: JLabel) {
        JOptionPane.showMessageDialog(parentComponent, msg, "Error", JOptionPane.ERROR_MESSAGE)
    }

    fun showConfirmDialog(parentComponent: JComponent, msg: JLabel): Int {
        return JOptionPane.showConfirmDialog(parentComponent, msg, "Warning", JOptionPane.YES_NO_OPTION)
    }

    private val logger = KotlinLogging.logger { }
}