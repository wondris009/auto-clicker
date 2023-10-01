package cz.speedygonzales

import java.awt.Point
import javax.swing.JLabel

class CryptMarcher(
    private val clicker: Clicker,
    private val points: MutableList<Point>,
    private val rounds: Int,
    private val label: JLabel
) : Runnable, Notifier {

    override fun run() {

        for (second in 3 downTo 1) {
            setText("There will be $rounds rounds. Start crypting in $second")
            Thread.sleep(1_000)
        }

        for (round in rounds downTo 1) {
            setText("Round $round")
            clicker.mouseMove(points[0].x, points[0].y)
            clicker.clickLeftMouse()
            Thread.sleep(2_800)

            clicker.mouseMove(points[1].x, points[1].y)
            clicker.clickLeftMouse()
            Thread.sleep(2_800)

            clicker.mouseMove(points[2].x, points[2].y)
            clicker.clickLeftMouse()
            Thread.sleep(2_800)

            clicker.mouseMove(points[3].x, points[3].y)
            clicker.clickLeftMouse()
            Thread.sleep(2_800)

            clicker.mouseMove(points[4].x, points[4].y)
            clicker.clickLeftMouse()
            Thread.sleep(2_800)

            clicker.speedUp(points)
            clicker.waitAfterSpeedUps(round, this)
        }
        setText("$rounds crypts finished")
    }

    override fun setText(text: String) {
        label.text = text
    }
}