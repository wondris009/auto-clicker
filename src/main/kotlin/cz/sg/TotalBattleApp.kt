package cz.sg

import com.github.kwhat.jnativehook.GlobalScreen
import java.awt.Point
import java.io.File
import javax.swing.SwingUtilities

class TotalBattleApp {

    private fun initGui(pointsPath: String) {

        try {
            val points = mutableListOf<Point>()
            TotalBattleFrame("TotalBattleApp - !!! Press CTRL + F1 to exit application !!!", pointsPath, points)
        } catch (e: LoadPointsException) {
            GuiUtils.exit()
        }
    }

    companion object {

        const val WAIT_BEFORE_SECONDS = 5

        @JvmStatic
        fun main(args: Array<String>) {
            GlobalScreen.registerNativeHook()

            SwingUtilities.invokeAndWait {

                var fileName = "coords.txt"
                if (ScreenHelper.hasTwoScreens()) {
                    fileName = "coords-second-screen.txt"
                }

                val pointPaths =
                    "${System.getProperty("user.home")}${File.separator}tmp${File.separator}tbapp${File.separator}$fileName"
                TotalBattleApp().initGui(pointPaths)
            }
        }
    }
}

class LoadPointsException : Throwable()

