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
    private val checkPathPanel = CheckPathPanel(points, pointsTextArea)

    private fun initGui() {

        GlobalScreen.addNativeKeyListener(GlobalKeyListener(clicker))
        GlobalScreen.addNativeMouseListener(GlobalMouseListener(points, pointsTextArea))

        val tabs = JTabbedPane()
        tabs.addTab("Auto click", autoClickPanel)
        tabs.addTab("Setup path", checkPathPanel)
        tabs.selectedIndex = 1

        frame = JFrame("TotalBattleApp")
        frame.add(tabs)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isAlwaysOnTop = true
        frame.size = Dimension(640, 480)
        frame.isVisible = true
//        frame.pack() //make frame as big as inner components
//        frame.setLocationRelativeTo(null) // center the application
        frame.setLocation(-1600, 250) //displays on main screen not on macbook screen
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

