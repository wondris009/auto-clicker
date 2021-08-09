package cz.speedygonzales

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.MouseInfo
import java.awt.Point
import java.awt.event.InputEvent
import javax.swing.*


private const val SECOND = 1_000

class ClickerApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeAndWait {
                ClickerApplication().initGui()
            }
        }
    }

    private lateinit var frame: JFrame

    private lateinit var button: JButton
    private lateinit var numberOfClicksField: JTextField
    private lateinit var delayBeforeStartField: JTextField

    fun initGui() {

        button = JButton("Click")
        button.size = Dimension(10, 30)
        numberOfClicksField = JTextField("10")
        delayBeforeStartField = JTextField("5")

        val inputs = JPanel()
        inputs.layout = BoxLayout(inputs, BoxLayout.Y_AXIS)

        inputs.add(getInputRow(numberOfClicksField, "Number of clicks:"))
        inputs.add(getInputRow(delayBeforeStartField, "Seconds before start:"))

        val autoClicker = Clicker(55)

        button.addActionListener {
            Thread.sleep(delayBeforeStartField.text.toLong() * SECOND)

            val numberOfClicks = numberOfClicksField.text.toInt()
            val point: Point = MouseInfo.getPointerInfo().location
            println("Mouse point - X:${point.x}, Y:${point.y}")

            autoClicker.robot.mouseMove(point.x,point.y)

            for (i in 1..numberOfClicks) {
                autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
                frame.title = "Gririm's auto clicker | ${numberOfClicks - i}"
            }
        }

        frame = JFrame("Gririm's auto clicker | ${numberOfClicksField.text}")
        frame.contentPane.add(inputs, BorderLayout.NORTH)
        frame.contentPane.add(button, BorderLayout.SOUTH)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isAlwaysOnTop = true
        frame.size = Dimension(320, 200)
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }

    private fun getInputRow(tf: JTextField, label: String): JPanel {

        val container = JPanel()
        container.size = Dimension(200, 50)
        val boxLayout = BoxLayout(container, BoxLayout.X_AXIS)
        container.layout = boxLayout
        container.add(JLabel(label))
        container.add(tf)

        return container
    }
}


