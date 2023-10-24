package cz.sg

import com.github.kwhat.jnativehook.GlobalScreen
import java.awt.BorderLayout
import javax.swing.BoxLayout
import javax.swing.JCheckBox
import javax.swing.JPanel


class MoveScreenPanel : JPanel() {

    private var enableScreenMove = false

    private var moveScreenKeyListener = MoveScreenKeyListener()

    init {
        this.layout = BorderLayout()

        val controlsP = JPanel()
        controlsP.layout = BoxLayout(controlsP, BoxLayout.Y_AXIS)

        val cb = JCheckBox("Enable / disable screen moving", enableScreenMove)
        cb.addActionListener {
            enableScreenMove = !enableScreenMove

            if(enableScreenMove) {
                GlobalScreen.addNativeKeyListener(moveScreenKeyListener)
            } else {
                GlobalScreen.removeNativeKeyListener(moveScreenKeyListener)
            }

        }
        controlsP.add(cb)

        this.add(controlsP)
    }
}

