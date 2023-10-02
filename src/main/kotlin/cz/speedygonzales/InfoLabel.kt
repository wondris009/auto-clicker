package cz.speedygonzales

import java.awt.Color
import java.awt.Font
import javax.swing.JLabel

class InfoLabel(text: String) : JLabel(text) {

    init {
        this.foreground = Color.RED
        this.setFont(Font("Verdana", Font.BOLD, 18))
    }

}