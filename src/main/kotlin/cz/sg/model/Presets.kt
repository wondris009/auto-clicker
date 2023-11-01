package cz.sg.model

import cz.sg.Preset

class Presets(val presets: MutableMap<String, Preset>) {

    fun getByName(presetName: String): Preset? {
        return presets[presetName]
    }

    fun removePreset(presetName: String) {
        presets.remove(presetName)
    }

    fun getPresetNames() = presets.values.map { it.presetName }


}