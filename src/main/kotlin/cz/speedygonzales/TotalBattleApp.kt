package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import java.awt.Dimension
import java.awt.Point
import javax.swing.*


class TotalBattleApp {

    private lateinit var frame: JFrame

    private val clicker = Clicker()
    private val troopSelector = TroopSelector(clicker)
    private val points = mutableListOf<Point>()

//    private val strategies = Strategies(clicker, troopSelector)

    private val pointsTextArea = JTextArea()

    private val autoClickPanel = AutoClickPanel(clicker)
    private val checkPathPanel = CheckPathPanel(points, pointsTextArea)

    private fun initGui() {

        GlobalScreen.addNativeKeyListener(GlobalKeyListener(clicker))
        GlobalScreen.addNativeMouseListener(GlobalMouseListener(points, pointsTextArea))

        val tabs = JTabbedPane()
        tabs.addTab("Auto click", autoClickPanel)
        tabs.addTab("Check path", checkPathPanel)

        frame = JFrame("TotalBattleApp")
        frame.add(tabs)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isAlwaysOnTop = true
        frame.size = Dimension(320, 200)
//        frame.setLocationRelativeTo(null) // center the application
        frame.isVisible = true
        frame.pack() //make frame as big as inner components
        frame.setLocation(-1000, 400) //displays on main screen not on macbook screen
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

