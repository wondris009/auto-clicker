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

        val controlsPanel = JPanel()
        controlsPanel.layout = BoxLayout(controlsPanel, BoxLayout.Y_AXIS)

        val checkBox = JCheckBox("Enable / disable screen moving", enableScreenMove)
        checkBox.addActionListener {
            enableScreenMove = !enableScreenMove

            if(enableScreenMove) {
                GlobalScreen.addNativeKeyListener(moveScreenKeyListener)
            } else {
                GlobalScreen.removeNativeKeyListener(moveScreenKeyListener)
            }

        }
        controlsPanel.add(checkBox)

        this.add(controlsPanel)
    }
}

