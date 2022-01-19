package cz.speedygonzales

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.MouseInfo
import java.awt.Point
import java.awt.event.InputEvent
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.*


private const val SECOND = 1_000

class TotalBattleApp : MouseListener {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeAndWait {
                TotalBattleApp().initGui()
            }
        }
    }

    private lateinit var frame: JFrame

    private lateinit var button: JButton
    private lateinit var numberOfClicksField: JTextField
    private lateinit var delayBeforeStartField: JTextField

    private val autoClicker = Clicker()

    fun initGui() {

        button = JButton("Click")
        button.size = Dimension(10, 30)
        numberOfClicksField = JTextField("10")
        delayBeforeStartField = JTextField("5")

        val inputs = JPanel()
        inputs.layout = BoxLayout(inputs, BoxLayout.Y_AXIS)

        inputs.add(getInputRow(numberOfClicksField, "Number of clicks:"))
        inputs.add(getInputRow(delayBeforeStartField, "Seconds before start:"))

        button.addActionListener {

            autoClick()
//            checkMousePosition()
//            totalBattleMain()
        }

        frame = JFrame("Total battle helper | ${numberOfClicksField.text}")
        frame.contentPane.add(inputs, BorderLayout.NORTH)
        frame.contentPane.add(button, BorderLayout.SOUTH)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isAlwaysOnTop = true
        frame.size = Dimension(320, 200)
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }

    private fun autoClick() {

        autoClicker.delay = 70

        Thread.sleep(delayBeforeStartField.text.toLong() * SECOND)

        val numberOfClicks = numberOfClicksField.text.toInt()
        val point: Point = MouseInfo.getPointerInfo().location
        autoClicker.robot.mouseMove(point.x, point.y)

        for (i in 1..numberOfClicks) {
            autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
            frame.title = "Auto clicker | ${numberOfClicks - i}"
        }

    }

    private fun checkMousePosition() {

        autoClicker.delay = 70

        val point: Point = MouseInfo.getPointerInfo().location
        val data = mutableSetOf<String>()
        data.add("autoClicker.robot.mouseMove(${point.x}, ${point.y})")
        println(data.elementAt(0))
        println("Thread.sleep(4.5.toLong() * SECOND)")
    }

    private fun totalBattleMain() {

        autoClicker.delay = 500

        Thread.sleep(delayBeforeStartField.text.toLong() * SECOND)
        val numberOfClicks = numberOfClicksField.text.toInt()
        for (i in 1..numberOfClicks) {
            frame.title = "Auto attack clicker | ${numberOfClicks - i}"
            totalBattle()
        }

    }

    private fun totalBattle() {

        autoClicker.robot.mouseMove(708, 1098)
        Thread.sleep(2L * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(4.5.toLong() * SECOND)

        autoClicker.robot.mouseMove(1243, 703)
        Thread.sleep(2L * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(6L * SECOND)

        autoClicker.robot.mouseMove(978, 666)
        Thread.sleep(2L * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(4.5.toLong() * SECOND)

        autoClicker.robot.mouseMove(1012, 805)
        Thread.sleep(2L * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(
            4.5.toLong()
                    * SECOND
        )

        autoClicker.robot.mouseMove(958, 632)
        Thread.sleep(2L * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(4.5.toLong() * SECOND)

        autoClicker.robot.mouseMove(703, 679)
        Thread.sleep(2L * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(4.5.toLong() * SECOND)

        autoClicker.robot.mouseMove(876, 946)
        Thread.sleep(2L * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(4.5.toLong() * SECOND)
        autoClicker.robot.mouseMove(1272, 231)
        Thread.sleep(2L * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(4.5.toLong() * SECOND)

        autoClicker.robot.mouseMove(1144, 612)
        Thread.sleep(1.25.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1.25.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1.25.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1.25.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1.25.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1.25.toLong() * SECOND)

        autoClicker.robot.mouseMove(1240, 431)
        Thread.sleep(1L * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)

        Thread.sleep(45L * SECOND)

        cleanFailures(3)
    }

    private fun cleanFailures(numberOfEscapePress: Int) {
        for (i in 1..numberOfEscapePress) {
            autoClicker.pressEscape()
            Thread.sleep(1L * SECOND)
        }
    }

    override fun mouseClicked(e: MouseEvent) {
        println("${e.x} : ${e.y}")

    }

    override fun mousePressed(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mouseReleased(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mouseEntered(e: MouseEvent?) {
        TODO("Not yet implemented")
    }

    override fun mouseExited(e: MouseEvent?) {
        TODO("Not yet implemented")
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


