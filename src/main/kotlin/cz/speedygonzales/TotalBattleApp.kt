package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import mu.KotlinLogging
import java.awt.Point
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.*

class TotalBattleApp {

    private lateinit var frame: TotalBattleFrame

    private val clicker = Clicker()
    private val pointsTextArea = JTextArea()

    private fun initGui(pointsPath: String) {

        try {
            val points = loadPositions(pointsPath)

            frame = TotalBattleFrame("TotalBattleApp - !!! Press CTRL + ESC to exit application !!!", pointsPath, points)

            val autoClickPanel = AutoClickPanel(clicker)
            val cryptPanel = CryptPanel(clicker, points, pointsTextArea)
//            val testPanel = TestPanel(frame)

            GlobalScreen.addNativeKeyListener(GlobalKeyListener(clicker, false))
            GlobalScreen.addNativeMouseListener(GlobalMouseListener(points, pointsTextArea))

            val tabs = JTabbedPane()
            tabs.addTab("Auto click", autoClickPanel)
            tabs.addTab("Crypt maker", cryptPanel)
//            tabs.addTab("Testing", testPanel)
            tabs.selectedIndex = 1
            frame.add(tabs)
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

        @JvmStatic
        fun main(args: Array<String>) {
            GlobalScreen.registerNativeHook()

            SwingUtilities.invokeAndWait {

                val pointPaths = "${System.getProperty("user.home")}${File.separator}tmp${File.separator}tbapp${File.separator}coords.txt"
                TotalBattleApp().initGui(pointPaths)
            }
        }
    }

    private val logger = KotlinLogging.logger {  }
}

class LoadPointsException : Throwable()

