package cz.sg

import cz.sg.Constants.SECOND
import java.awt.Point
import java.awt.Robot
import java.awt.event.InputEvent
import java.awt.event.KeyEvent.*

class Clicker {

    private var delay = 70

    private val robot = Robot()

    fun mouseMove(x: Int, y: Int) {
        robot.mouseMove(x, y)
    }

    fun clickLeftMouse() {
        click()
    }

    private fun click(button: Int = BUTTON1_DOWN_MASK) {
        robot.mousePress(button)
        robot.delay(delay)
        robot.mouseRelease(button)
        robot.delay(delay)
    }

    private fun pressEscape() {
        robot.keyPress(VK_ESCAPE)
        robot.delay(delay)
        robot.keyRelease(VK_ESCAPE)
        robot.delay(delay)
    }

    private fun keyPress(keyEvent: Int) {
        robot.keyPress(keyEvent)
        robot.delay(delay)
        robot.keyRelease(keyEvent)
        robot.delay(delay)
    }

    fun input318() {
        click(BUTTON1_DOWN_MASK)
        robot.delay(delay)

        keyPress(VK_3)
        keyPress(VK_1)
        keyPress(VK_8)
    }


    private fun cleanFailures(numberOfEscapePress: Int = 3) {
        repeat(numberOfEscapePress) {
            pressEscape()
            Thread.sleep(0.4.toLong() * SECOND)
        }
    }

    fun moveLeftDoubleScreen() {
        robot.mouseMove(-2285, 439)
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(100)
        robot.mouseMove(-1885, 439)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    }

    fun moveRightDoubleScreen() {
        robot.mouseMove(-271, 439)
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(100)
        robot.mouseMove(-671, 439)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    }

    fun moveUpDoubleScreen() {
        robot.mouseMove(-1240, 159)
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(100)
        robot.mouseMove(-1240, 459)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    }

    fun moveDownDoubleScreen() {
        robot.mouseMove(-1240, 819)
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(100)
        robot.mouseMove(-1240, 519)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    }

    fun moveLeftMacOnly() {
        robot.mouseMove(335, 439)
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(100)
        robot.mouseMove(735, 439)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    }

    fun moveRightMacOnly() {
        robot.mouseMove(1672, 439)
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(100)
        robot.mouseMove(1272, 439)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    }

    fun moveUpMacOnly() {
        robot.mouseMove(950, 1000)
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(100)
        robot.mouseMove(950, 1300)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    }

    fun moveDownMacOnly() {
        robot.mouseMove(950, 988)
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(100)
        robot.mouseMove(950, 688)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    }

    fun speedUp(points: List<Point>) {
        mouseMove(points[5].x, points[5].y)
        for (j in 1..5) {
            clickLeftMouse()
            Thread.sleep(300)
        }
        cleanFailures(2)
    }

    fun waitAfterSpeedUps(rounds: Int, round: Int, notifier: Notifier) {
        (0..40).reversed().forEach {
            notifier.setText("Round $round of $rounds | Waiting $it second(s) until next round")
            Thread.sleep(1L * SECOND)
        }
        cleanFailures()
    }
}