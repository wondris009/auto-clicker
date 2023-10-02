package cz.speedygonzales

import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.SwingUtilities

private const val DEFAULT_CLICK_COUNT = 20

class AutoClickPanel(clicker: Clicker) : JPanel() {

    private val numberOfCountsTextField = JTextField(DEFAULT_CLICK_COUNT.toString())
    private val numberOfCountsLabel = InfoLabel("Number of clicks left: $DEFAULT_CLICK_COUNT")

    init {
        this.layout = BorderLayout()

        this.add(GuiUtils.getInputRow("How many clicks?", numberOfCountsTextField), BorderLayout.NORTH)
        this.add(GuiUtils.getInputRow(labelComponent = numberOfCountsLabel), BorderLayout.CENTER)

        val startClickingButton = GuiUtils.createButton("Start") {
            Thread(DisplayTextRunnable(clicker, numberOfCountsLabel, numberOfCountsTextField.text.toInt())).start()
        }
        this.add(GuiUtils.getInputRow(labelComponent = startClickingButton), BorderLayout.SOUTH)

        this.size = Dimension(500,200)
        this.preferredSize = Dimension(500,200)
    }

    class DisplayTextRunnable(
        private val clicker: Clicker, private val label: JLabel, private val count: Int
    ) : Runnable, Notifier {

        override fun run() {
            setText("Clicking will start after 3 seconds")

            for (i in 2 downTo 0) {
                Thread.sleep(1L * Constants.SECOND)
                setText("Clicking will start after $i seconds")
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