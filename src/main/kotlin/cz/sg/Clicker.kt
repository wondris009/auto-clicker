package cz.sg

import cz.sg.Constants.SECOND
import java.awt.Point
import java.awt.Robot
import java.awt.event.InputEvent
import java.awt.event.KeyEvent.BUTTON1_DOWN_MASK
import java.awt.event.KeyEvent.VK_ESCAPE

class Clicker(private var delay: Int = 70) {

    private val robot = Robot()

    fun mouseMove(p: Point) {
        robot.mouseMove(p.x, p.y)
    }

    fun clickLeftMouse() {
        click()
        robot.waitForIdle()
    }

    private fun delay() = robot.delay(delay)

    private fun click(button: Int = BUTTON1_DOWN_MASK) {
        robot.mousePress(button)
        delay()
        robot.mouseRelease(button)
        delay()
    }

    private fun pressEscape() {
        robot.keyPress(VK_ESCAPE)
        delay()
        robot.keyRelease(VK_ESCAPE)
        delay()
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

    fun speedUp(point: Point) {
        mouseMove(point)
        for (j in 1..5) {
            clickLeftMouse()
            Thread.sleep(300)
        }
        cleanFailures(2)
        robot.waitForIdle()
    }

    fun waitAfterSpeedUps(rounds: Int, secondsAfterAction: Int, round: Int, notifier: Notifier) {
        (0..secondsAfterAction).reversed().forEach {
            notifier.setText("Round $round of $rounds | Waiting $it second(s) until next round")
            Thread.sleep(1L * SECOND)
        }
        cleanFailures()
    }

    fun enterInput(input: String) {
        input.toKeys().forEach {
            robot.keyPress(it)
            delay()
            robot.keyRelease(it)
            delay()
        }
    }

    fun openWatchTower(point: Point) {
        clickAndWait(point)
    }

    fun locateTarget(point: Point) {
        clickAndWait(point)
    }

    fun selectTarget(point: Point) {
        clickAndWait(point)
    }

    fun selectTroopsPage(point: Point) {
        clickAndWait(point)
    }

    fun doAction(point: Point) {
        clickAndWait(point)
    }

    fun openSpeedupPage(point: Point) {
        clickAndWait(point)
    }

    fun clickAndWait(point: Point) {
        mouseMove(point)
        clickLeftMouse()
        Thread.sleep(2_800)
    }
}