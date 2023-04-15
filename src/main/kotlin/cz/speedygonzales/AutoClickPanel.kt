package cz.speedygonzales

import java.awt.Component
import java.awt.event.InputEvent
import javax.swing.*

private const val DEFAULT_CLICK_COUNT = 20

class AutoClickPanel(clicker: Clicker) : JPanel() {

    private val numberOfCountsTextField = JTextField(DEFAULT_CLICK_COUNT.toString())

    private val numberOfCountsLabel = JLabel("Number of clicks left: $DEFAULT_CLICK_COUNT")

    private val startClickingButton = JButton("Start")

    init {
        this.layout = BoxLayout(this, BoxLayout.Y_AXIS)

        this.add(GuiUtils.getInputRow("How many clicks?", numberOfCountsTextField))
        this.add(GuiUtils.getInputRow("", numberOfCountsLabel))
        this.add(GuiUtils.getInputRow("", startClickingButton))

        startClickingButton.addActionListener {
            Thread(DisplayTextRunnable(clicker, numberOfCountsLabel, numberOfCountsTextField.text.toInt())).start()
        }
    }

    class DisplayTextRunnable(
        private val clicker: Clicker, private val label: JLabel, private val count: Int
    ) : Runnable {

        override fun run() {
            setText("Clicking will start after 3 seconds")
            Thread.sleep(3L * Constants.SECOND)
            (1..count).reversed().forEach {
                setText("Clicks left: ${it - 1}")

                Thread.sleep(100)

//                clicker.click(InputEvent.BUTTON1_DOWN_MASK)
            }
        }

        private fun setText(text: String) {
            SwingUtilities.invokeLater { label.text = text }
        }
    }
}