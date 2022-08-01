package cz.speedygonzales

import cz.speedygonzales.Constants.SECOND
import java.awt.event.InputEvent

class TroopSelector(private val autoClicker: Clicker) {

    fun griffins6All() {

        //scroll
        autoClicker.mouseMove(958, 632)
        Thread.sleep(2L * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(2L * SECOND)

        //select griffin amount
        autoClicker.mouseMove(703, 643)
        Thread.sleep(2L * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(2L * SECOND)
    }

    fun vultures7Monsters5() {

        //select monsters
        autoClicker.robot.mouseMove(619, 707)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.input200()

        autoClicker.robot.mouseMove(612, 809)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.input200()

        autoClicker.robot.mouseMove(843, 809)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.input200()

        autoClicker.robot.mouseMove(844, 707)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.input318()

        //select vultures
        autoClicker.robot.mouseMove(901, 656)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)

        autoClicker.robot.mouseMove(876, 795)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)
    }

    fun vultures7Monsters5Logos() {

        //select monsters
        autoClicker.robot.mouseMove(619, 707)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.input55()

        autoClicker.robot.mouseMove(612, 809)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.input55()

        autoClicker.robot.mouseMove(843, 809)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.input53()

        autoClicker.robot.mouseMove(844, 707)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.input123()

        //select vultures
        autoClicker.robot.mouseMove(901, 656)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)

        autoClicker.robot.mouseMove(876, 795)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)
    }

    fun vultures6Sedlak() {

        autoClicker.robot.mouseMove(900, 618)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)
        autoClicker.robot.mouseMove(657, 582)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)


        autoClicker.robot.mouseMove(901, 716)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)
        autoClicker.robot.mouseMove(648, 723)
        Thread.sleep(1.toLong() * SECOND)
        autoClicker.click(InputEvent.BUTTON1_DOWN_MASK)
        Thread.sleep(1L * SECOND)
        autoClicker.input45000()

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