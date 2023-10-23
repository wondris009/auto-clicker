package cz.sg

import java.awt.FlowLayout
import javax.swing.JPanel

open class FlowLeftPanel : JPanel() {
    init {
        this.layout = FlowLayout(FlowLayout.LEFT)
    }
}