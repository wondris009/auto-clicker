package cz.sg

import java.io.File

class Preset(val presetName: String) {

    var presetFile: File

    init {
        val filePath = "${FileUtils.APP_PATH}${File.separator}$presetName${FileUtils.EXTENSION}"
        presetFile = File(filePath)
        if (!presetFile.exists()) {
            presetFile.createNewFile()
        }
    }
}