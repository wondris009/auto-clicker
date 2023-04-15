package cz.speedygonzales

import javax.swing.JTextArea
import javax.swing.SwingUtilities

class AppendTextAreaRunnable(
    private val label: JTextArea, private val coordinates: String
) : Runnable {

    override fun run() {
        appendText("$coordinates\n")
    }

    private fun appendText(text: String) {
        SwingUtilities.invokeLater { label.append(text) }
    }
}