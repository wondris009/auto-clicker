package cz.speedygonzales

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.*


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
    private lateinit var reviveAfterNRoundsField: JTextField

    private val autoClicker = Clicker()
    private val troopSelector = TroopSelector(autoClicker)
    private val strategies = Strategies(autoClicker, troopSelector)

    fun initGui() {

        button = JButton("Click")
        button.size = Dimension(10, 30)
        numberOfClicksField = JTextField("10")
        delayBeforeStartField = JTextField("3")
        reviveAfterNRoundsField = JTextField("10")

        val inputs = JPanel()
        inputs.layout = BoxLayout(inputs, BoxLayout.Y_AXIS)

        inputs.add(getInputRow(numberOfClicksField, "Number of clicks:"))
        inputs.add(getInputRow(delayBeforeStartField, "Seconds before start:"))
        inputs.add(getInputRow(reviveAfterNRoundsField, "Revive after N rounds:"))

        val reviveAfterNRounds = reviveAfterNRoundsField.text.toInt()

        button.addActionListener {

//            strategies.autoClick(delayBeforeStartField.text.toLong(), numberOfClicksField.text.toInt(), frame)
//            strategies.checkMousePosition()
            strategies.fight(
                reviveAfterNRounds,
                numberOfClicksField.text.toInt(),
                delayBeforeStartField.text.toLong(),
                TroopsType.VULTURES6_SEDLAK,
                frame
            )
        }

        frame = JFrame("${numberOfClicksField.text} round(s) left | at least 40s left")
        frame.contentPane.add(inputs, BorderLayout.NORTH)
        frame.contentPane.add(button, BorderLayout.SOUTH)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isAlwaysOnTop = true
        frame.size = Dimension(320, 200)
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }

    override fun mouseClicked(e: MouseEvent) {
        println("${e.x} : ${e.y}")

//                || e.isControlDown()
//        if (SwingUtilities.isRightMouseButton(e)) {
//            println("right")
//        }
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
