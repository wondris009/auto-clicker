package cz.sg

import mu.KotlinLogging
import java.awt.Point
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

object FileUtils {

    val APP_PATH = "${System.getProperty("user.home")}${File.separator}tmp${File.separator}tbapp"
    val EXTENSION = ".txt"

    fun loadPoints(path: String): MutableList<PointAmount> {
        logger.info { "Loading configured points from $path" }
        return try {
            val lines = Files.readAllLines(Paths.get(path))
            lines.filter { it.isNotEmpty() }.map {
                val columns = it.split(";")
                val point = columns.getOrElse(0) { "" }
                val amount = columns.getOrElse(1) { "" }

                val coordinates = point
                    .replace("java.awt.Point[x=", "")
                    .replace("]", "")
                    .replace("x", "")
                    .replace("y", "")
                    .replace("=", "")
                    .split(",")
                val x = coordinates[0].trim()
                val y = coordinates[1].trim()

                PointAmount(Point(x.toInt(), y.toInt()), amount)
            }.toMutableList()
        } catch (e: Exception) {
            logger.warn(e) { "Coordinates file is missing, it will be created in $path" }
            File(path).createNewFile()
            mutableListOf()
        }
    }

    fun savePoints(preset: Preset) {
        val pointsPath = preset.getPath()
        Files.write(Paths.get(pointsPath), preset.toCsv(), StandardOpenOption.TRUNCATE_EXISTING)
        logger.info { "Writing ${preset.pointsAndAmounts.size} rows in $pointsPath" }
    }

    fun deleteCoordsFile(preset: Preset) {
        if (preset.presetFile.exists()) {
            preset.presetFile.delete()
        }
    }

    private val logger = KotlinLogging.logger { }
}