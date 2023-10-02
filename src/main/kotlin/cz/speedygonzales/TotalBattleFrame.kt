package cz.speedygonzales

import mu.KotlinLogging
import java.awt.Dimension
import java.awt.Point
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import javax.swing.JFrame
import javax.swing.JOptionPane

class TotalBattleFrame(title: String, pointsPath: String, points: List<Point>) : JFrame() {

    init {
        this.title = title

        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.isAlwaysOnTop = true
        this.size = Dimension(640, 480)
        this.isVisible = true
        this.setLocationRelativeTo(null) // center the application

        this.addWindowListener(WindowCloser(pointsPath, points))
    }

    fun showErrorMessage(msg: String) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE)
    }

    class WindowCloser(private val pointsPath: String, private val points: List<Point>) : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            logger.info { "Closing application" }
            File(pointsPath).delete()
            logger.info { "Deleting points configuration file $pointsPath" }
            Files.write(Paths.get(pointsPath), points.map { it.toString() }, StandardOpenOption.CREATE_NEW)
            logger.info { "Creating new points configuration file from ${points.size} points in $pointsPath" }
        }

        private val logger = KotlinLogging.logger { }
    }
}
