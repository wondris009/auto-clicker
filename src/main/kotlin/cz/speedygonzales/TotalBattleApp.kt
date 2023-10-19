package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import mu.KotlinLogging
import java.awt.Point
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.*

class TotalBattleApp {

    private fun initGui(pointsPath: String) {

        try {
            val points = loadPositions(pointsPath)
            TotalBattleFrame("TotalBattleApp - !!! Press CTRL + F1 to exit application !!!", pointsPath, points)
        } catch (e: LoadPointsException) {
            GuiUtils.exit()
        }
    }

    private fun loadPositions(path: String): MutableList<Point> {
        logger.info { "Loading configured points from $path" }
        return try {
            val lines = Files.readAllLines(Paths.get(path))
            lines.map {
                val coordinates = it
                    .replace("java.awt.Point[x=", "")
                    .replace("]", "")
                    .replace("x", "")
                    .replace("y", "")
                    .replace("=", "")
                    .split(",")
                val x = coordinates[0].trim()
                val y = coordinates[1].trim()

                Point(x.toInt(), y.toInt())
            }.toMutableList()
        } catch (e: Exception) {
            logger.warn { "Coordinates file is missing, it will be created in HOME_DIR/tmp/tbapp/coords.txt" }
            mutableListOf()
        }
    }

    companion object {

        const val WAIT_BEFORE_SECONDS = 5

        @JvmStatic
        fun main(args: Array<String>) {
            GlobalScreen.registerNativeHook()

            SwingUtilities.invokeAndWait {

                var fileName = "coords.txt"
                if(ScreenHelper.hasTwoScreens()) {
                    fileName = "coords-second-screen.txt"
                }

                val pointPaths = "${System.getProperty("user.home")}${File.separator}tmp${File.separator}tbapp${File.separator}$fileName"
                TotalBattleApp().initGui(pointPaths)
            }
        }
    }

    private val logger = KotlinLogging.logger {  }
}

class LoadPointsException : Throwable()

