package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Point
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

class TotalBattleFrame(title: String, pointsPath: String, points: MutableList<Point>) : JFrame() {

    private val clicker = Clicker()
    private val pointsTextArea = JTextArea()

    init {
        this.title = title

        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isAlwaysOnTop = true
        this.size = Dimension(800, 450)
        this.isVisible = true
        this.setLocationRelativeTo(null) // center the application

        this.addWindowListener(WindowCloser(pointsPath, points))

        GlobalScreen.addNativeKeyListener(GlobalKeyListener(clicker, false, pointsPath, points))
        GlobalScreen.addNativeMouseListener(GlobalMouseListener(points, pointsTextArea))

        this.layout = BorderLayout()

        val controlPanel = JPanel()
        controlPanel.border = BorderFactory.createEmptyBorder(20, 40, 5, 40)
        controlPanel.layout = BorderLayout()
        val infoLabel = InfoLabel("Welcome Total Battle player")
        controlPanel.add(infoLabel, BorderLayout.WEST)
        val exitButton = GuiUtils.createExitButton(pointsPath, points)
        controlPanel.add(exitButton, BorderLayout.EAST)
        this.add(controlPanel, BorderLayout.NORTH)

        val tabsPanel = JPanel()
        val tabs = JTabbedPane()
        tabs.preferredSize = Dimension(760, 350)
        val autoClickPanel = AutoClickPanel(infoLabel, clicker)
        val cryptPanel = CryptPanel(infoLabel, clicker, points, pointsTextArea)
//        val testPanel = TestPanel()
        tabs.addTab("Auto click", autoClickPanel)
        tabs.addTab("Crypt maker", cryptPanel)
//        tabs.addTab("Testing", testPanel)
        tabs.selectedIndex = 1
        tabsPanel.add(tabs)

        this.add(tabsPanel, BorderLayout.CENTER)
    }

    class WindowCloser(private val pointsPath: String, private val points: List<Point>) : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            FileUtils.savePoints(pointsPath, points)
        }
    }
}
