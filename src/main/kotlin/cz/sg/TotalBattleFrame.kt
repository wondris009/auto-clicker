package cz.sg

import com.github.kwhat.jnativehook.GlobalScreen
import java.awt.BorderLayout
import java.awt.Color
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*


class TotalBattleFrame(title: String) : JFrame() {

    init {
        UIManager.getLookAndFeelDefaults().putIfAbsent("Table.alternateRowColor", Color(240, 240, 240))

        this.title = title

        this.addWindowListener(WindowCloser())

        GlobalScreen.addNativeKeyListener(ExitButtonKeyListener())

        this.layout = BorderLayout()

        val controlPanel = JPanel()
        controlPanel.border = BorderFactory.createEmptyBorder(20, 40, 5, 40)
        controlPanel.layout = BorderLayout()
        val infoLabel = InfoLabel("Welcome Total Battle player")
        controlPanel.add(infoLabel, BorderLayout.WEST)
        val exitButton = GuiUtils.createExitButton()
        controlPanel.add(exitButton, BorderLayout.EAST)
        this.add(controlPanel, BorderLayout.NORTH)

        val tabs = JTabbedPane()

        val actionPanel = ActionPanel(infoLabel)
        val autoClickPanel = AutoClickPanel(infoLabel)
        val moveScreenPanel = MoveScreenPanel()
        tabs.addTab("Action panel", actionPanel)
        tabs.addTab("Auto click", autoClickPanel)
        tabs.addTab("Move screen", moveScreenPanel)
        tabs.selectedIndex = 0
        val tabsPanel = JPanel()
        tabsPanel.add(tabs)
        this.add(tabsPanel, BorderLayout.CENTER)

        this.defaultCloseOperation = EXIT_ON_CLOSE
        this.pack()
        this.setLocationRelativeTo(null)
        this.isVisible = true
    }

    class WindowCloser : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            GuiUtils.exit()
        }
    }
}
