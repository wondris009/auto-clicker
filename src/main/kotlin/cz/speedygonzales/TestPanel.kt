package cz.speedygonzales

import cz.speedygonzales.GuiUtils.createButton
import java.awt.BorderLayout
import javax.swing.*


class TestPanel(
    frame: TotalBattleFrame
) : JPanel() {

    init {
        this.layout = BorderLayout()

        val controlsPanel = JPanel()
        controlsPanel.layout = BoxLayout(controlsPanel, BoxLayout.Y_AXIS)

        val tesTButton = createButton("Test") {
            frame.showErrorMessage("Eggs are not supposed to be green.")
        }
        controlsPanel.add(tesTButton)
        this.add(controlsPanel)
    }
}

