package cz.speedygonzales

import cz.speedygonzales.Constants.SECOND
import java.awt.Frame
import java.awt.Robot
import java.awt.event.InputEvent
import java.awt.event.KeyEvent
import javax.swing.JFrame

class Clicker() {

    var delay = 70

    val robot = Robot()

    fun mouseMove(x: Int, y: Int) {
        robot.mouseMove(x, y)
    }

    fun click(button: Int) {
        robot.mousePress(button)
        robot.delay(delay)
        robot.mouseRelease(button)
        robot.delay(delay)
    }

    private fun pressEscape() {
        robot.keyPress(KeyEvent.VK_ESCAPE)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_ESCAPE)
        robot.delay(delay)
    }

    fun input200() {
        click(InputEvent.BUTTON1_DOWN_MASK)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_2)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_2)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_0)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_0)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_0)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_0)
        robot.delay(delay)
    }

    fun input55() {
        click(InputEvent.BUTTON1_DOWN_MASK)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_5)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_5)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_5)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_5)
        robot.delay(delay)
    }

    fun input53() {
        click(InputEvent.BUTTON1_DOWN_MASK)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_5)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_5)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_3)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_3)
        robot.delay(delay)
    }

    fun input123() {
        click(InputEvent.BUTTON1_DOWN_MASK)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_1)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_1)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_2)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_2)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_3)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_3)
        robot.delay(delay)
    }

    fun input290() {
        click(InputEvent.BUTTON1_DOWN_MASK)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_2)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_2)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_9)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_9)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_0)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_0)
        robot.delay(delay)
    }

    fun input318() {
        click(InputEvent.BUTTON1_DOWN_MASK)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_3)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_3)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_1)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_1)
        robot.delay(delay)
        robot.keyPress(KeyEvent.VK_8)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_8)
        robot.delay(delay)
    }

    private fun cleanFailures(numberOfEscapePress: Int) {
        for (i in 1..numberOfEscapePress) {
            pressEscape()
            Thread.sleep(0.4.toLong() * SECOND)
        }
    }

    fun revive() {
        mouseMove(899, 999)
        Thread.sleep(4.5.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(3.5.toLong() * SECOND)

        mouseMove(988, 940)
        Thread.sleep(4.5.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(3.5.toLong() * SECOND)

        pressEscape()
    }

    fun selectTroopsPage() {

        //watch tover
        mouseMove(646, 1066)
        Thread.sleep(1.4.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1.4.toLong() * SECOND)

        //go creature
        mouseMove(1180, 697)
        Thread.sleep(1.4.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(3L * SECOND)

        //select creature on map
        mouseMove(912, 673)
        Thread.sleep(1.4.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1.4.toLong() * SECOND)

        //click attack
        mouseMove(933, 803)
        Thread.sleep(1.4.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1.4.toLong() * SECOND)
    }

    fun march(rounds: Int, frame: JFrame) {
        //hit march
        mouseMove(846, 943)
        Thread.sleep(1.4.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1.4.toLong() * SECOND)

        frame.title = "${rounds-1} round(s) left | at least 40s left"

        //select speedups window
        mouseMove(1201, 244)
        Thread.sleep(2.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1.5.toLong() * SECOND)

        //do speedups
        mouseMove(1052, 610)
        Thread.sleep(1.4.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(0.99.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(0.99.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(0.99.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(0.99.toLong() * SECOND)
        click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1.4.toLong() * SECOND)

        pressEscape()
    }

    fun finishRound(rounds: Int, frame: Frame) {

        for(i in 40 downTo 1) {
            Thread.sleep(1L * SECOND)
            frame.title = "${rounds-1} round(s) left | at least ${i-1}s left"
        }

        cleanFailures(3)
    }
}