package cz.sg

import com.github.kwhat.jnativehook.GlobalScreen
import mu.KotlinLogging
import java.awt.Point
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object FileUtils {

    val APP_PATH = "${System.getProperty("user.home")}${File.separator}tmp${File.separator}tbapp"
    val EXTENSION = ".txt"

    fun loadPoints(path: String): MutableList<Point> {
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
            logger.warn { "Coordinates file is missing, it will be created in $path" }
            File(path).createNewFile()
            mutableListOf()
        }
    }

    fun savePoints(pointsPath: String, points: List<Point>) {

        logger.info { "Closing application" }
//        val coordsFile = File(pointsPath)
//        if (coordsFile.exists()) {
//            coordsFile.delete()
//            logger.info { "Deleting points configuration file $pointsPath" }
//        }
//        Files.createDirectories(Paths.get(pointsPath.removeSuffix("${File.separator}coords.txt")))
//        Files.write(Paths.get(pointsPath), points.map { it.toString() }, StandardOpenOption.CREATE_NEW)
//        logger.info { "Creating new points configuration file from ${points.size} points in $pointsPath" }

        GlobalScreen.unregisterNativeHook()
    }

    fun deleteCoordsFile(preset: Preset) {
        if(preset.presetFile.exists()) {
            preset.presetFile.delete()
        }
    }


    private val logger = KotlinLogging.logger {  }

}