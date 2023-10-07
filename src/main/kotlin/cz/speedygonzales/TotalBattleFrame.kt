package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import mu.KotlinLogging
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Point
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import javax.swing.*

class TotalBattleFrame(title: String, pointsPath: String, points: MutableList<Point>) : JFrame() {

    private val clicker = Clicker()
    private val pointsTextArea = JTextArea()

    init {
        this.title = title

        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isAlwaysOnTop = true
        this.size = Dimension(600, 450)
        this.isVisible = true
        this.setLocationRelativeTo(null) // center the application

        this.addWindowListener(WindowCloser(pointsPath, points))

        GlobalScreen.addNativeKeyListener(GlobalKeyListener(clicker, false))
        GlobalScreen.addNativeMouseListener(GlobalMouseListener(points, pointsTextArea))

        this.layout = BorderLayout()

        val controlPanel = JPanel()
        controlPanel.border = BorderFactory.createEmptyBorder(20, 40, 5, 40)
        controlPanel.layout = BorderLayout()
        val infoLabel = InfoLabel("Welcome Total Battle player")
        controlPanel.add(infoLabel, BorderLayout.WEST)
        val exitButton = GuiUtils.createExitButton()
        controlPanel.add(exitButton, BorderLayout.EAST)
        this.add(controlPanel, BorderLayout.NORTH)

        val tabsPanel = JPanel()
        val tabs = JTabbedPane()
        val autoClickPanel = AutoClickPanel(infoLabel, clicker)
        val cryptPanel = CryptPanel(infoLabel, clicker, points, pointsTextArea)
//            val testPanel = TestPanel(frame)
        tabs.addTab("Auto click", autoClickPanel)
        tabs.addTab("Crypt maker", cryptPanel)
//            tabs.addTab("Testing", testPanel)
        tabs.selectedIndex = 1
        tabsPanel.add(tabs)

        this.add(tabsPanel, BorderLayout.CENTER)
    }

    fun showErrorMessage(msg: String) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE)
    }

    class WindowCloser(private val pointsPath: String, private val points: List<Point>) : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            logger.info { "Closing application" }
            val coordsFile = File(pointsPath)
            if (coordsFile.exists()) {
                coordsFile.delete()
                logger.info { "Deleting points configuration file $pointsPath" }
            }
            Files.createDirectories(Paths.get(pointsPath.removeSuffix("${File.separator}coords.txt")))
            Files.write(Paths.get(pointsPath), points.map { it.toString() }, StandardOpenOption.CREATE_NEW)
            logger.info { "Creating new points configuration file from ${points.size} points in $pointsPath" }

            GlobalScreen.unregisterNativeHook()
        }

        private val logger = KotlinLogging.logger { }
    }
}
