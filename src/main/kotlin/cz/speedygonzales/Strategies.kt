package cz.speedygonzales

import java.awt.MouseInfo
import java.awt.Point
import java.awt.event.InputEvent
import javax.swing.JFrame

class Strategies(private val autoClicker: Clicker, private val troopSelector: TroopSelector) {

    private val fightingStrategies = FightingStrategies(autoClicker, troopSelector)

    fun autoClick(delayBeforeStartField: Long, numberOfClicks: Int, frame: JFrame) {

        autoClicker.delay = 70

        Thread.sleep(delayBeforeStartField * Constants.SECOND)

        val point: Point = MouseInfo.getPointerInfo().location
        autoClicker.mouseMove(point.x, point.y)

        for (i in 1..numberOfClicks) {
            autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
            frame.title = "Auto clicker | ${numberOfClicks - i}"
        }

    }

    fun checkMousePosition() {

        autoClicker.delay = 70

        val point: Point = MouseInfo.getPointerInfo().location
        val data = mutableSetOf<String>()
        data.add("autoClicker.robot.mouseMove(${point.x}, ${point.y})")
        println(data.elementAt(0))
        println("Thread.sleep(4.5.toLong() * SECOND)")
    }

    fun fight(whenToRevive: Int, rounds: Int, waitBeforeRun: Long, type: TroopsType, frame: JFrame) {

        autoClicker.delay = 135

        Thread.sleep(waitBeforeRun * Constants.SECOND)
        for (i in rounds downTo 1) {

            frame.title = "$i round(s) left | at least 40s left"
            fightingStrategies.selectFightingStrategy(i, type, frame)

            if (i % whenToRevive == 0) {
                autoClicker.revive()
            }
        }

    }
}