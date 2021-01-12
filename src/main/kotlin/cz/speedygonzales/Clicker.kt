package cz.speedygonzales

import java.awt.Robot

class Clicker(private val delay: Int) {

    private val robot = Robot()

    fun click(button: Int) {
        robot.mousePress(button)
        robot.delay(delay)
        robot.mouseRelease(button)
        robot.delay(delay)
    }
}