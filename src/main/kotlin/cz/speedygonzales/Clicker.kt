package cz.speedygonzales

import cz.speedygonzales.Constants.SECOND
import java.awt.Point
import java.awt.Robot
import java.awt.event.InputEvent
import java.awt.event.KeyEvent.*
import javax.swing.JFrame

class Clicker {

    var delay = 70

    val robot = Robot()

    fun mouseMove(x: Int, y: Int) {
        robot.mouseMove(x, y)
    }

    fun clickLeftMouse() {
        click(BUTTON1_DOWN_MASK)
    }

    fun click(button: Int) {
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

    fun input200() {
        click(BUTTON1_DOWN_MASK)
        robot.delay(delay)

        keyPress(VK_2)
        keyPress(VK_0)
        keyPress(VK_0)
    }

    fun input55() {
        click(BUTTON1_DOWN_MASK)
        robot.delay(delay)

        keyPress(VK_5)
        keyPress(VK_5)
    }

    fun input38800() {
        click(BUTTON1_DOWN_MASK)
        robot.delay(delay)
        keyPress(VK_3)
        keyPress(VK_8)
        keyPress(VK_8)
        keyPress(VK_0)
        keyPress(VK_0)
    }

    fun input45000() {
        click(BUTTON1_DOWN_MASK)
        robot.delay(delay)
        keyPress(VK_4)
        keyPress(VK_5)
        keyPress(VK_0)
        keyPress(VK_0)
        keyPress(VK_0)
    }

    fun input53() {
        click(BUTTON1_DOWN_MASK)
        robot.delay(delay)

        keyPress(VK_5)
        keyPress(VK_3)
    }

    fun input123() {
        click(BUTTON1_DOWN_MASK)
        robot.delay(delay)

        keyPress(VK_1)
        keyPress(VK_2)
        keyPress(VK_3)
    }

    fun input290() {
        click(BUTTON1_DOWN_MASK)
        robot.delay(delay)

        keyPress(VK_2)
        keyPress(VK_9)
        keyPress(VK_0)
    }

    fun input318() {
        click(BUTTON1_DOWN_MASK)
        robot.delay(delay)

        keyPress(VK_3)
        keyPress(VK_1)
        keyPress(VK_8)
    }

    fun input(keyEventCodes: List<Int>) {
        if (keyEventCodes.isNotEmpty()) {

            click(BUTTON1_DOWN_MASK)
            robot.delay(delay)

            keyEventCodes.forEach { keyPress(it) }
        }
    }

    fun cleanFailures(numberOfEscapePress: Int = 3) {
        repeat(numberOfEscapePress) {
            pressEscape()
            Thread.sleep(0.4.toLong() * SECOND)
        }
    }

    fun revive() {
        mouseMove(899, 999)
        Thread.sleep(4.5.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(3.5.toLong() * SECOND)

        mouseMove(988, 940)
        Thread.sleep(4.5.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(3.5.toLong() * SECOND)

        pressEscape()
    }

    fun selectTroopsPage() {

        //watch tover
        mouseMove(646, 1066)
        Thread.sleep(1.4.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(1.4.toLong() * SECOND)

        //go creature
        mouseMove(1180, 697)
        Thread.sleep(1.4.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(3L * SECOND)

        //select creature on map
        mouseMove(912, 673)
        Thread.sleep(1.4.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(1.4.toLong() * SECOND)

        //click attack
        mouseMove(933, 803)
        Thread.sleep(1.4.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(1.4.toLong() * SECOND)
    }

    fun march(rounds: Int, frame: JFrame) {
        //hit march
        mouseMove(846, 943)
        Thread.sleep(1.4.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(1.4.toLong() * SECOND)

        frame.title = "${rounds - 1} round(s) left | at least 40s left"

        //select speedups window
        mouseMove(1201, 244)
        Thread.sleep(2.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(1.5.toLong() * SECOND)

        //do speedups
        mouseMove(1052, 610)
        Thread.sleep(1.4.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(0.99.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(0.99.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(0.99.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(0.99.toLong() * SECOND)
        click(BUTTON1_DOWN_MASK)
        Thread.sleep(1.4.toLong() * SECOND)

        pressEscape()
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

    fun waitAfterSpeedUps(round: Int, notifier: Notifier) {
        (1..40).reversed().forEach {
            notifier.setText("Round $round | Waiting $it second(s) until next round")
            Thread.sleep(1L * SECOND)
        }
        cleanFailures()
    }
}