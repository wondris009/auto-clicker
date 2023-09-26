package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import java.awt.Dimension
import java.awt.Point
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import javax.swing.JFrame
import javax.swing.JTabbedPane
import javax.swing.JTextArea
import javax.swing.SwingUtilities

private const val DEFAULT_PATH = "~/tmp/tbapp/coords.txt"

class TotalBattleApp {

    private lateinit var frame: JFrame

    private val clicker = Clicker()
    private val pointsTextArea = JTextArea()

    private fun initGui(pointsPath: String) {

        val points = loadPositions(pointsPath)

        val autoClickPanel = AutoClickPanel(clicker)
        val setupPathPanel = SetupPathPanel(clicker, points, pointsTextArea)

        //can't see setEnabled
        pointsTextArea.enable(false)

        GlobalScreen.addNativeKeyListener(GlobalKeyListener(clicker))
        GlobalScreen.addNativeMouseListener(GlobalMouseListener(points, pointsTextArea))

        val tabs = JTabbedPane()
        tabs.addTab("Auto click", autoClickPanel)
        tabs.addTab("Setup path", setupPathPanel)
        tabs.selectedIndex = 1

        frame = JFrame("TotalBattleApp - !!! Press CTRL + ESC to exit application !!!")
        frame.add(tabs)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isAlwaysOnTop = true
        frame.size = Dimension(640, 480)
        frame.isVisible = true
        frame.setLocationRelativeTo(null) // center the application

        frame.addWindowListener(WindowCloser(pointsPath, points))
    }

    private fun loadPositions(path: String): MutableList<Point> {

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

    class WindowCloser(private val pointsPath: String, private val points: List<Point>) : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            File(pointsPath).delete()
            Files.write(Paths.get(pointsPath), points.map { it.toString() }, StandardOpenOption.CREATE_NEW)
        }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            GlobalScreen.registerNativeHook()

            SwingUtilities.invokeAndWait {

                var pointPaths = args.first()

                if (pointPaths.isEmpty()) {
                    pointPaths = DEFAULT_PATH
                }

                TotalBattleApp().initGui(pointPaths)
            }
        }
    }
}

