package cz.sg

import cz.sg.TotalBattleApp.Companion.WAIT_BEFORE_SECONDS
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.SwingUtilities

private const val DEFAULT_CLICK_COUNT = 20

class AutoClickPanel(infoLabel: InfoLabel, clicker: Clicker) : JPanel() {

    private val numberOfCountsTextField = JTextField(DEFAULT_CLICK_COUNT.toString())

    init {
        this.layout = BorderLayout()

        this.add(GuiUtils.getInputRow("How many clicks?", numberOfCountsTextField), BorderLayout.NORTH)

        val startClickingButton = GuiUtils.createButton(buttonLabel = "Start") {
            Thread(DisplayTextRunnable(clicker, infoLabel, numberOfCountsTextField.text.toInt())).start()
        }
        this.add(GuiUtils.getInputRow(labelComponent = startClickingButton), BorderLayout.SOUTH)

        this.size = Dimension(500, 200)
        this.preferredSize = Dimension(500, 200)
    }

    class DisplayTextRunnable(
        private val clicker: Clicker, private val label: JLabel, private val count: Int
    ) : Runnable, Notifier {

        override fun run() {
            for (second in WAIT_BEFORE_SECONDS downTo 0) {
                setText("Clicking will start after $second seconds")
                Thread.sleep(1_000)
            }

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