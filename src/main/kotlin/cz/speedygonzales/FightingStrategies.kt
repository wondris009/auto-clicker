package cz.speedygonzales

import javax.swing.JFrame

class FightingStrategies(private val autoClicker: Clicker, private val troopSelector: TroopSelector) {

    fun selectFightingStrategy(rounds: Int, type: Troops, frame: JFrame) {

        autoClicker.selectTroopsPage()

        when(type) {
            Troops.VULTURES7_M5_HERO -> troopSelector.vultures7Monsters5()
            Troops.G6_ALL -> troopSelector.griffins6All()
            Troops.VULTURES7_M5_LOGOS -> troopSelector.vultures7Monsters5Logor()
        }

        autoClicker.march(rounds, frame)
        autoClicker.finishRound(rounds, frame)
    }
}
