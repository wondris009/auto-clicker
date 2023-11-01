package cz.sg

import com.github.kwhat.jnativehook.GlobalScreen
import mu.KotlinLogging
import javax.swing.SwingUtilities

class TotalBattleApp {

    private fun initGui() {
        try {
            TotalBattleFrame("TotalBattleApp - !!! Press CTRL + F1 to exit application !!!")
        } catch (e: Exception) {
            logger.error(e) {}
            GuiUtils.exit()
        }
    }

    private val logger = KotlinLogging.logger {  }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            GlobalScreen.registerNativeHook()

            SwingUtilities.invokeLater {
                TotalBattleApp().initGui()
            }
        }
    }
}


