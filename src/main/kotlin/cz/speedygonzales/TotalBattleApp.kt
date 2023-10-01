package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import mu.KotlinLogging
import java.awt.Point
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.*

private const val DEFAULT_PATH = "/Users/vondracek/tmp/tbapp/coords.txt"

class TotalBattleApp {

    private lateinit var frame: TotalBattleFrame

    private val clicker = Clicker()
    private val pointsTextArea = JTextArea()

    private fun initGui(pointsPath: String) {

        val points = loadPositions(pointsPath)

        frame = TotalBattleFrame("TotalBattleApp - !!! Press CTRL + ESC to exit application !!!", pointsPath, points)

        val autoClickPanel = AutoClickPanel(clicker)
        val setupPathPanel = SetupPathPanel(clicker, points, pointsTextArea, frame)
        val testPanel = TestPanel(frame)

        GlobalScreen.addNativeKeyListener(GlobalKeyListener(clicker))
        GlobalScreen.addNativeMouseListener(GlobalMouseListener(points, pointsTextArea))

        val tabs = JTabbedPane()
        tabs.addTab("Auto click", autoClickPanel)
        tabs.addTab("Setup path", setupPathPanel)
        tabs.addTab("Testing", testPanel)
        tabs.selectedIndex = 1
        frame.add(tabs)
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
            mutableListOf()
        }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            GlobalScreen.registerNativeHook()

            SwingUtilities.invokeAndWait {

                var pointPaths = args.firstOrNull()

                if (pointPaths.isNullOrEmpty()) {
                    pointPaths = DEFAULT_PATH
                }

                TotalBattleApp().initGui(pointPaths)
            }
        }
    }

    private val logger = KotlinLogging.logger {  }
}

