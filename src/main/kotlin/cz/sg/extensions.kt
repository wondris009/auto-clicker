package cz.sg

import java.awt.Component
import java.awt.event.KeyEvent
import javax.swing.JComponent

fun JComponent.addLeft(c: JComponent) {
    c.alignmentX = Component.LEFT_ALIGNMENT
    this.add(c)
}

fun String.toKeys(): List<Int> {
    fun convertCharacter(character: Char) = when (character) {
        '0' -> KeyEvent.VK_0
        '1' -> KeyEvent.VK_1
        '2' -> KeyEvent.VK_2
        '3' -> KeyEvent.VK_3
        '4' -> KeyEvent.VK_4
        '5' -> KeyEvent.VK_5
        '6' -> KeyEvent.VK_6
        '7' -> KeyEvent.VK_7
        '8' -> KeyEvent.VK_8
        '9' -> KeyEvent.VK_9
        else -> -1
    }

    return this.map {
        convertCharacter(it)
    }.toList()
}

fun Map<String, Preset>.getSelectedPreset() = this.values.first { it.selected }

