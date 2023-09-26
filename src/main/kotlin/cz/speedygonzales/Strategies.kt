package cz.speedygonzales

import java.awt.MouseInfo
import java.awt.Point
import java.awt.event.InputEvent
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

class Strategies(private val clicker: Clicker, troopSelector: TroopSelector) {

    private val fightingStrategies = FightingStrategies(clicker, troopSelector)

    fun autoClick(delayBeforeStartField: Long, numberOfClicks: Int, label: JLabel) {

        clicker.delay = 70

        Thread.sleep(delayBeforeStartField * Constants.SECOND)

        val point: Point = MouseInfo.getPointerInfo().location
        clicker.mouseMove(point.x, point.y)

        for (i in 1..numberOfClicks) {
            clicker.clickLeftMouse()
            label.text = "Auto clicker | ${numberOfClicks - i}"
        }

    }

    fun fight(whenToRevive: Int, rounds: Int, waitBeforeRun: Long, type: TroopsType, frame: JFrame) {

        clicker.delay = 135

        Thread.sleep(waitBeforeRun * Constants.SECOND)
        for (i in rounds downTo 1) {

            frame.title = "$i round(s) left | at least 40s left"
            fightingStrategies.selectFightingStrategy(i, type, frame)

            //println("trying reviving i=$i, rounds=$rounds, whenToRevive=$whenToRevive....${i % whenToRevive == 0}")
            if (i % whenToRevive == 0) {
                clicker.revive()
            }
        }

    }
}
