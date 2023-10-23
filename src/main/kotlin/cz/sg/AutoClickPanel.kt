package cz.sg

import cz.sg.TotalBattleApp.Companion.WAIT_BEFORE_SECONDS
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.SwingUtilities

class AutoClickPanel(infoLabel: InfoLabel) : JPanel() {

    init {
        this.layout = FlowLayout(FlowLayout.LEFT)

        val label = JLabel("How many clicks?")
        this.addLeft(label)

        val numberOfCountsTextField = JTextField("20", 5)
        this.addLeft(numberOfCountsTextField)

        val startClickingButton = GuiUtils.createButton(buttonLabel = "Start") {
            Thread(DisplayTextRunnable(infoLabel, numberOfCountsTextField.text.toInt())).start()
        }
        this.addLeft(startClickingButton)

        this.preferredSize = Dimension(this.width, 100)
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