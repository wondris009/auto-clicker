package cz.sg

import com.github.kwhat.jnativehook.GlobalScreen
import javax.swing.SwingUtilities

class TotalBattleApp {

    private fun initGui() {
        try {
            TotalBattleFrame("TotalBattleApp - !!! Press CTRL + F1 to exit application !!!")
        } catch (e: Exception) {
            GuiUtils.exit()
        }
    }

    companion object {

        const val WAIT_BEFORE_SECONDS = 5

        @JvmStatic
        fun main(args: Array<String>) {
            GlobalScreen.registerNativeHook()

            SwingUtilities.invokeAndWait {
                TotalBattleApp().initGui()
            }
        }
    }
}


