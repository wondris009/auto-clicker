package cz.speedygonzales

import com.github.kwhat.jnativehook.GlobalScreen
import mu.KotlinLogging
import java.awt.Point
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

object FileUtils {

    fun savePoints(pointsPath: String, points: List<Point>) {

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

    private val logger = KotlinLogging.logger {  }

}