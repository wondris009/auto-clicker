package cz.sg

import com.github.kwhat.jnativehook.GlobalScreen
import java.awt.BorderLayout
import javax.swing.*


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

            if (enableScreenMove) {
                GlobalScreen.addNativeKeyListener(moveScreenKeyListener)
            } else {
                GlobalScreen.removeNativeKeyListener(moveScreenKeyListener)
            }

        }
        controlsP.add(cb)

        val info = JLabel(
            """
                <html>
                    Use arrows to move map using keys. 
                    <br/>
                    Sometimes it goes little but further, not just one move but multiple moves.
                    <br/>
                    Just use opposite arrow to get back a bit.
                </html>
            """.trimIndent()
        )
        info.border = BorderFactory.createEmptyBorder(20, 30, 0, 0)
        controlsP.add(info)

        this.add(controlsP)
    }
}

