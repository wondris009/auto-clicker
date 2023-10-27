package cz.sg

import java.awt.Point
import java.io.File

class Preset(val presetName: String, var selected: Boolean = false) {

    var presetFile: File

    var pointsAndAmounts = mutableListOf<PointAmount>()

    init {
        val presetFilePath = "${FileUtils.APP_PATH}${File.separator}$presetName${FileUtils.EXTENSION}"
        presetFile = File(presetFilePath)
        if (!presetFile.exists()) {
            presetFile.createNewFile()
        }
    }

    fun getPath(): String = presetFile.absolutePath

    fun clear() {
        pointsAndAmounts = mutableListOf()
    }

    fun toCsv(): List<String> {
        return pointsAndAmounts.map { row ->
            "${row.point};${row.amount}"
        }.toList()
    }

    fun add(pointAmount: PointAmount) {
        pointsAndAmounts.add(pointAmount)
    }
}

class PointAmount(val point: Point, var amount: String = "")