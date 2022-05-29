package cz.speedygonzales

import javax.swing.JFrame

class FightingStrategies(private val autoClicker: Clicker, private val troopSelector: TroopSelector) {

    fun selectFightingStrategy(rounds: Int, type: TroopsType, frame: JFrame) {

        autoClicker.selectTroopsPage()

        when(type) {
            TroopsType.VULTURES7_M5_HERO -> troopSelector.vultures7Monsters5()
            TroopsType.G6_ALL -> troopSelector.griffins6All()
            TroopsType.VULTURES7_M5_LOGOS -> troopSelector.vultures7Monsters5Logos()
            TroopsType.VULTURES6_SEDLAK -> troopSelector.vultures6Sedlak()
        }

        autoClicker.march(rounds, frame)
        autoClicker.finishRound(rounds, frame)
    }
}
