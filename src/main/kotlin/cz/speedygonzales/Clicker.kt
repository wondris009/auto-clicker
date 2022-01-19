package cz.speedygonzales

import java.awt.Robot
import java.awt.event.KeyEvent

class Clicker() {

    var delay = 70

    val robot = Robot()

    fun click(button: Int) {
        robot.mousePress(button)
        robot.delay(delay)
        robot.mouseRelease(button)
        robot.delay(delay)
    }

    fun pressEscape() {
        robot.keyPress(KeyEvent.VK_ESCAPE)
        robot.delay(delay)
        robot.keyRelease(KeyEvent.VK_ESCAPE)
        robot.delay(delay)
    }
}