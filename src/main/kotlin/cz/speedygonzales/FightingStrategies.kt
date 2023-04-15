package cz.speedygonzales

import javax.swing.JFrame

class FightingStrategies(private val clicker: Clicker, private val troopSelector: TroopSelector) {

    fun selectFightingStrategy(rounds: Int, type: TroopsType, frame: JFrame) {

        clicker.selectTroopsPage()

        when(type) {
            TroopsType.VULTURES7_M5_HERO -> troopSelector.vultures7Monsters5()
            TroopsType.G6_ALL -> troopSelector.griffins6All()
            TroopsType.VULTURES7_M5_LOGOS -> troopSelector.vultures7Monsters5Logos()
            TroopsType.VULTURES6_SEDLAK -> troopSelector.vultures6Sedlak()
            TroopsType.VULTURES6_M5_SEDLAK -> troopSelector.vultures6Sedlak() //both are same
        }

        clicker.march(rounds, frame)
        clicker.finishRound(rounds, frame)
    }
}
