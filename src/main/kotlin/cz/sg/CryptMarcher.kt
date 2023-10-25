package cz.sg

import cz.sg.Constants.WAIT_BEFORE_SECONDS
import java.awt.Point
import javax.swing.JLabel
import javax.swing.SwingUtilities

class CryptMarcher(
    private val points: MutableList<Point>,
    private val rounds: Int,
    private val waitAfterSpeedUps: Int,
    private val rare: Boolean,
    private val label: JLabel
) : Runnable, Notifier {

    override fun run() {

        for (second in WAIT_BEFORE_SECONDS downTo 0) {
            setText("There will be $rounds rounds. Start crypting in $second")
            Thread.sleep(1_000)
        }

        val clicker = Clicker()

        repeat(rounds) { round ->

            clicker.openWatchTower(points[0])
            clicker.locateTarget(points[1])
            clicker.selectTarget(points[2])

            if (rare) {
                clicker.clickAndWait(points[3])
            }

            setText("Round $round of $rounds")

            clicker.doAction(points[points.size - 3])
            clicker.openSpeedupPage(points[points.size - 2])
            clicker.speedUp(points[points.size - 1])
            clicker.waitAfterSpeedUps(rounds, waitAfterSpeedUps, round, this)
        }
        setText("$rounds crypts finished")
    }

    override fun setText(text: String) {
        SwingUtilities.invokeLater { label.text = text }
    }
}