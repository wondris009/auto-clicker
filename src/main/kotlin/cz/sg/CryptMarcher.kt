package cz.sg

import cz.sg.TotalBattleApp.Companion.WAIT_BEFORE_SECONDS
import java.awt.Point
import javax.swing.JLabel
import javax.swing.SwingUtilities

class CryptMarcher(
    private val points: MutableList<Point>,
    private val rounds: Int,
    private val rare: Boolean,
    private val label: JLabel
) : Runnable, Notifier {

    override fun run() {

        for (second in WAIT_BEFORE_SECONDS downTo 0) {
            setText("There will be $rounds rounds. Start crypting in $second")
            Thread.sleep(1_000)
        }

        val clicker = Clicker()

        for (round in 1..rounds) {

            clicker.mouseMove(points[0].x, points[0].y)
            clicker.clickLeftMouse()
            Thread.sleep(2_800)

            clicker.mouseMove(points[1].x, points[1].y)
            clicker.clickLeftMouse()
            Thread.sleep(2_800)

            clicker.mouseMove(points[2].x, points[2].y)
            clicker.clickLeftMouse()
            Thread.sleep(2_800)

            if(rare) {
                clicker.mouseMove(points[3].x, points[3].y)
                clicker.clickLeftMouse()
                Thread.sleep(2_800)
            }

            setText("Round $round of $rounds")

            clicker.mouseMove(points[4].x, points[4].y)
            clicker.clickLeftMouse()
            Thread.sleep(2_800)

            clicker.mouseMove(points[5].x, points[5].y)
            clicker.clickLeftMouse()
            Thread.sleep(2_800)

            clicker.speedUp(points, 6)
            clicker.waitAfterSpeedUps(rounds, round, this)
        }
        setText("$rounds crypts finished")
    }

    override fun setText(text: String) {
        SwingUtilities.invokeLater { label.text = text }
    }
}