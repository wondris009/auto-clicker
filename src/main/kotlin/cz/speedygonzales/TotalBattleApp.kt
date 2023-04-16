package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import java.awt.Dimension
import java.awt.Point
import javax.swing.JFrame
import javax.swing.JTabbedPane
import javax.swing.JTextArea
import javax.swing.SwingUtilities


class TotalBattleApp {

    private lateinit var frame: JFrame

    private val clicker = Clicker()
    private val points = mutableListOf<Point>()

    private val pointsTextArea = JTextArea()

    private val autoClickPanel = AutoClickPanel(clicker)
    private val setupPathPanel = SetupPathPanel(clicker, points, pointsTextArea)

    private fun initGui() {

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
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            GlobalScreen.registerNativeHook()

            SwingUtilities.invokeAndWait {
                TotalBattleApp().initGui()
            }
        }
    }
}

