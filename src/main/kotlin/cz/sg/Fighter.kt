package cz.sg

import mu.KotlinLogging
import javax.swing.JLabel
import javax.swing.SwingUtilities

class Fighter(
    private val preset: Preset,
    private val rounds: Int,
    private val waitAfterSpeedUps: Int,
    private val reviveAfterNRounds: Int,
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
                    Thread.sleep(1000)
                } else {
                    logger.info { "Moving" }
                    //scroll to troop selection
                    clicker.clickAndWait(point)
                }
            }

            setText("Round $round of $rounds")

            if(reviveAfterNRounds in 1..round && round % reviveAfterNRounds == 0) {
                clicker.doAction(points[points.size - 5])
                clicker.openSpeedupPage(points[points.size - 4])
                clicker.speedUp(points[points.size - 3])
                clicker.waitAfterSpeedUps(rounds, waitAfterSpeedUps, round, this)
                clicker.revive(points)
            } else {
                clicker.doAction(points[points.size - 3])
                clicker.openSpeedupPage(points[points.size - 2])
                clicker.speedUp(points[points.size - 1])
                clicker.waitAfterSpeedUps(rounds, waitAfterSpeedUps, round, this)
            }


        }
        setText("$rounds round(s) of fighting finished")
    }

    override fun setText(text: String) {
        SwingUtilities.invokeLater { label.text = text }
    }

    private val logger = KotlinLogging.logger { }
}