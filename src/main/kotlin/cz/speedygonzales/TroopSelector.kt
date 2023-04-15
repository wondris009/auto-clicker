package cz.speedygonzales

import cz.speedygonzales.Constants.SECOND
import java.awt.event.InputEvent

class TroopSelector(private val clicker: Clicker) {

    fun griffins6All() {

        //scroll
        clicker.mouseMove(958, 632)
        Thread.sleep(2L * SECOND)
        clicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(2L * SECOND)

        //select griffin amount
        clicker.mouseMove(703, 643)
        Thread.sleep(2L * SECOND)
        clicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(2L * SECOND)
    }

    fun vultures7Monsters5() {

        //select monsters
        clicker.robot.mouseMove(619, 707)
        Thread.sleep(1.toLong() * SECOND)
        clicker.input200()

        clicker.robot.mouseMove(612, 809)
        Thread.sleep(1.toLong() * SECOND)
        clicker.input200()

        clicker.robot.mouseMove(843, 809)
        Thread.sleep(1.toLong() * SECOND)
        clicker.input200()

        clicker.robot.mouseMove(844, 707)
        Thread.sleep(1.toLong() * SECOND)
        clicker.input318()

        //select vultures
        clicker.robot.mouseMove(901, 656)
        Thread.sleep(1.toLong() * SECOND)
        clicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)

        clicker.robot.mouseMove(876, 795)
        Thread.sleep(1.toLong() * SECOND)
        clicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)
    }

    fun vultures7Monsters5Logos() {

        //select monsters
        clicker.robot.mouseMove(619, 707)
        Thread.sleep(1.toLong() * SECOND)
        clicker.input55()

        clicker.robot.mouseMove(612, 809)
        Thread.sleep(1.toLong() * SECOND)
        clicker.input55()

        clicker.robot.mouseMove(843, 809)
        Thread.sleep(1.toLong() * SECOND)
        clicker.input53()

        clicker.robot.mouseMove(844, 707)
        Thread.sleep(1.toLong() * SECOND)
        clicker.input123()

        //select vultures
        clicker.robot.mouseMove(901, 656)
        Thread.sleep(1.toLong() * SECOND)
        clicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)

        clicker.robot.mouseMove(876, 795)
        Thread.sleep(1.toLong() * SECOND)
        clicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)
    }

    fun vultures6Sedlak() {

        clicker.robot.mouseMove(900, 618)
        Thread.sleep(1.toLong() * SECOND)
        clicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)
        clicker.robot.mouseMove(657, 582)
        Thread.sleep(1.toLong() * SECOND)
        clicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)


        clicker.robot.mouseMove(901, 716)
        Thread.sleep(1.toLong() * SECOND)
        clicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)
        clicker.robot.mouseMove(648, 723)
        Thread.sleep(1.toLong() * SECOND)
        clicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)
        clicker.input45000()

        //select monsters
//        autoClicker.robot.mouseMove(899, 728)
//        Thread.sleep(1.toLong() * SECOND)
//        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
//        Thread.sleep(1L * SECOND)
//
//        autoClicker.robot.mouseMove(834, 630)
//        Thread.sleep(1.toLong() * SECOND)
//        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
//        Thread.sleep(1L * SECOND)

    }

//    fun vultures6M5BeastsSedlak() {
//
//        //select monsters
//        autoClicker.robot.mouseMove(899, 728)
//        Thread.sleep(1.toLong() * SECOND)
//        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
//        Thread.sleep(1L * SECOND)
//
//        autoClicker.robot.mouseMove(834, 630)
//        Thread.sleep(1.toLong() * SECOND)
//        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
//        Thread.sleep(1L * SECOND)
//        autoClicker.input44000()
//    }
}