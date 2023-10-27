package cz.sg

import mu.KotlinLogging
import java.awt.Point
import java.lang.RuntimeException
import javax.swing.JLabel
import javax.swing.SwingUtilities

class Fighter(
    private val preset: Preset,
    private val rounds: Int,
    private val waitAfterSpeedUps: Int,
    private val label: JLabel
) : Runnable, Notifier {

    private val clicker = Clicker()

    override fun run() {
        for (second in Constants.WAIT_BEFORE_SECONDS downTo 0) {
            setText("There will be $rounds rounds. Fight in $second")
            Thread.sleep(1_000)
        }

        val pointsAmounts = preset.pointsAndAmounts
        val points = pointsAmounts.map { it.point }
        val amounts = preset.pointsAndAmounts.map { it.amount }

        val numberOfPointsBeforeSelectingTroops = 4

        repeat(rounds) { round ->

            clicker.openWatchTower(points[0])
            clicker.locateTarget(points[1])
            clicker.selectTarget(points[2])
            clicker.selectTroopsPage(points[3])

            //select troops
            points.subList(numberOfPointsBeforeSelectingTroops, points.size - 3).forEachIndexed { i, _ ->

                val realIndex = i + numberOfPointsBeforeSelectingTroops
                val point = points[realIndex]

                //enter amount
                if (pointsAmounts[realIndex].amount.isNotEmpty()) {

                    val amount = amounts[realIndex]

                    logger.info { "Entering amount $amount" }

                    clicker.mouseMove(point)
                    Thread.sleep(1000)
                    clicker.clickLeftMouse()
                    Thread.sleep(1000)
                    clicker.enterInput(amount)
                } else {
                    logger.info { "Moving" }
                    //scroll to troop selection
                    clicker.clickAndWait(point)
                }
            }

            setText("Round $round of $rounds")

            clicker.doAction(points[points.size - 3])
            clicker.openSpeedupPage(points[points.size - 2])
            clicker.speedUp(points[points.size - 1])
            clicker.waitAfterSpeedUps(rounds, waitAfterSpeedUps, round, this)
        }
        setText("$rounds round(s) of fighting finished")
    }

    private fun getAmounts(input: String): Map<Int, String> = try {
        input.trim().split(",").associate {
            val (index, amount) = it.trim().split("-")
            fromRealIndex(index) to amount.trim()
        }
    } catch (e: Exception) {
        throw RuntimeException("Unable to convert $input to amounts")
    }

    /**
     * User is filling values 6-2000. Real index of point and amount.
     * Collections starts with 0, that's why it's decreased by 1.
     */
    private fun fromRealIndex(index: String) = index.trim().toInt() - 1

    override fun setText(text: String) {
        SwingUtilities.invokeLater { label.text = text }
    }

    private val logger = KotlinLogging.logger { }
}

fun main() {
    val result = mutableListOf<Int>()
    repeat(10) {
        result.add(it)
    }


}