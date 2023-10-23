package cz.sg

import cz.sg.Constants.WAIT_BEFORE_SECONDS
import javax.swing.JLabel
import javax.swing.JTextField
import javax.swing.SwingUtilities

class AutoClickPanel(infoLabel: InfoLabel) : FlowLeftPanel() {

    init {
        val label = JLabel("How many clicks?")
        this.addLeft(label)

        val numberOfCountsTextField = JTextField("20", 5)
        this.addLeft(numberOfCountsTextField)

        val startClickingButton = GuiUtils.createButton(buttonLabel = "Start") {
            Thread(DisplayTextRunnable(infoLabel, numberOfCountsTextField.text.toInt())).start()
        }
        this.addLeft(startClickingButton)
    }

    class DisplayTextRunnable(
        private val label: JLabel, private val count: Int
    ) : Runnable, Notifier {

        override fun run() {
            for (second in WAIT_BEFORE_SECONDS downTo 0) {
                setText("Clicking will start after $second seconds")
                Thread.sleep(1_000)
            }

            val clicker = Clicker()

            (1..count).reversed().forEach {
                setText("Clicks left: ${it - 1}")
                clicker.clickLeftMouse()
            }
        }

        override fun setText(text: String) {
            SwingUtilities.invokeLater { label.text = text }
        }
    }
}