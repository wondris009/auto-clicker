package cz.sg

import java.awt.Component
import javax.swing.JComponent

fun JComponent.addLeft(c: JComponent) {
    c.alignmentX = Component.LEFT_ALIGNMENT
    this.add(c)
}