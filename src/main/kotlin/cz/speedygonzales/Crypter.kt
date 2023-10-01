package cz.speedygonzales

import java.awt.Point
import javax.swing.JLabel

class Crypter(
    private val clicker: Clicker,
    private val points: MutableList<Point>,
    private val rounds: Int,
    private val label: JLabel
) : Runnable {

    override fun run() {

        for (i in 3 downTo 1) {
            setText("There will be $rounds of rounds. Start crypting in $i")
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

    fun setText(text: String) {
        label.text = text
    }
}