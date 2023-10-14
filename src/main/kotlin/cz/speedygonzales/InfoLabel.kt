package cz.speedygonzales

import java.awt.Color
import java.awt.Font
import javax.swing.JLabel

class InfoLabel(text: String) : JLabel(text) {

    init {
        this.foreground = Color.BLUE
        this.setFont(Font("Verdana", Font.BOLD, 18))
    }

    constructor(text: String, textColor: Color, font: String, fontType: Int, size: Int) : this(text) {
        this.foreground = textColor
        this.setFont(Font(font, fontType, size))
    }

}