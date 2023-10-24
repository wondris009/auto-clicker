package cz.sg

import com.github.kwhat.jnativehook.GlobalScreen
import cz.sg.GuiUtils.createButton
import mu.KotlinLogging
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import javax.swing.table.DefaultTableModel


private const val PREFIX = "File name will be: "

class ActionPanel(infoLabel: InfoLabel) : JPanel() {

    private val presets = mutableMapOf<String, Preset>()
    private val points = mutableListOf<Point>()

    private val presetNameFileNameLabel = JLabel()
    private var presetNameTextField: JTextField
    private val presetTable = JTable()
    private val presetTableModel = object : DefaultTableModel(0, 0) {
        override fun isCellEditable(row: Int, column: Int): Boolean {
            return false
        }
    }
    private val pointsTextArea = JTextArea()

    private var rare = false

    init {
        GlobalScreen.addNativeMouseListener(AddPointMouseListener(presets, points, pointsTextArea))

        this.layout = BoxLayout(this, BoxLayout.Y_AXIS)
        this.preferredSize = Dimension(800, 400)
        @Suppress("DEPRECATION")
        pointsTextArea.enable(false)

        loadPresets()

        setFileNameLabel()

        val presetPanel = FlowLeftPanel()
        val presetNameLabel = JLabel("Preset name")
        presetNameLabel.border = BorderFactory.createEmptyBorder()
        presetPanel.addLeft(presetNameLabel)
        presetNameTextField = JTextField("", 20)
        reactOnPresetNameChange {
            transformPresetNameToFileName()
        }
        presetPanel.addLeft(presetNameTextField)

        val addPresetButton = createButton(buttonLabel = "+") {
            if (presetNameTextField.text.isNotEmpty()) {
                val presetName = presetNameFileNameLabel.text.replace(PREFIX, "").replace(FileUtils.EXTENSION, "")
                if (!getPresetNames().contains(presetName)) {
                    addPreset(presetName, infoLabel)
                } else {
                    showError(infoLabel, "Preset already exists")
                }
            } else {
                showError(infoLabel, "Set preset name")
            }
        }
        presetPanel.addLeft(addPresetButton)

        val removePresetButton = createButton(buttonLabel = "-") {
            if (presetTable.selectedRow != -1) {
                removePreset(infoLabel)
            } else {
                infoLabel.text = "Select preset from the table"
            }
        }
        presetPanel.addLeft(removePresetButton)

        presetTableModel.setColumnIdentifiers(arrayOf("Preset name"))
        presetTable.model = presetTableModel
        presetTable.tableHeader.isOpaque = false
        presetTable.tableHeader.background = Color(204, 229, 255)
        getPresetNames().forEach {
            presetTableModel.addRow(arrayOf(it))
        }
        presetTable.addMouseListener(object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent) {
                if (e.clickCount == 2 && presetTable.selectedRow != -1) {
                    val presetName = presetTable.model.getValueAt(presetTable.selectedRow, 0)
                    presets[presetName]!!.selected = true
                    loadPoints(File("${FileUtils.APP_PATH}${File.separator}$presetName.txt"))
                }
            }
        })
        val presetTableScrollPane = JScrollPane(
            presetTable,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        )
        presetTableScrollPane.preferredSize = Dimension(presetTableScrollPane.width, 120)

        val presetControlsPanel = FlowLeftPanel()

        val removeLastValueButton = createButton(buttonLabel = "Remove last value") {
            if (points.isNotEmpty()) {
                points.removeLast()
                val linesCount = pointsTextArea.lineCount
                val start = pointsTextArea.getLineStartOffset(linesCount - 2)
                val end = pointsTextArea.getLineEndOffset(linesCount - 1)
                pointsTextArea.replaceRange("", start, end)
                FileUtils.savePoints(presets.values.first { it.selected }, points)
            }
        }
        presetControlsPanel.addLeft(removeLastValueButton)

        val clearAllPointsButton = createButton(buttonLabel = "Clear all") {
            points.clear()
            pointsTextArea.text = ""
            FileUtils.savePoints(presets.values.first { it.selected }, points)
        }
        presetControlsPanel.addLeft(clearAllPointsButton)

        val actionPanel = FlowLeftPanel()
        val numberOfRoundsLabel = JLabel("Number of rounds")
        actionPanel.addLeft(numberOfRoundsLabel)
        val numberOfRoundsTextField = JTextField("10", 4)
        actionPanel.addLeft(numberOfRoundsTextField)

        val waitAfterActionLabel = JLabel("Seconds after action")
        actionPanel.addLeft(waitAfterActionLabel)
        val waitAfterActionTextField = JTextField("40", 4)
        actionPanel.addLeft(waitAfterActionTextField)

        val goButton = createButton(color = Color.RED, buttonLabel = "Go common/epic") {

            if (points.size != 7) {
                val errorMsg = InfoLabel(
                    "<html>You need to specify exactly 7 points." +
                            "<br/><br/>" +
                            "1> Watchtower icon location." +
                            "<br/>" +
                            "2> Go button with selected crypt" +
                            "<br/>" +
                            "3> Crypt position on the map" +
                            "<br/>" +
                            "4> Open crypt - define and control crypt type with checkbox (for non rare, point skipped) " +
                            "<br/>" +
                            "5> Explore button" +
                            "<br/>" +
                            "6> Speedup button" +
                            "<br/>" +
                            "7> Use speedup button" +
                            "</html>",
                    Color.BLUE,
                    "Verdana",
                    Font.PLAIN,
                    14
                )

                GuiUtils.showErrorMessage(this, errorMsg)
            } else {
                val warningMsg = InfoLabel(
                    "Are you sure you set your watchtower on correct level of crypts before you go?",
                    Color.BLUE,
                    "Verdana",
                    Font.PLAIN,
                    14
                )
                val yesNoResult = GuiUtils.showConfirmDialog(this, warningMsg)
                if (yesNoResult == JOptionPane.YES_OPTION) {
                    logger.info { "passing $rare" }
                    Thread(CryptMarcher(points, numberOfRoundsTextField.text.toInt(), waitAfterActionTextField.text.toInt(), rare, infoLabel)).start()
                }
            }
        }
        val rareCryptCheckBox = JCheckBox("Rare crypt?", rare)
        rareCryptCheckBox.addActionListener {
            rare = !rare
            if (rare) {
                goButton.text = "Go rare"
            } else {
                goButton.text = "Go common/epic"
            }

        }
        actionPanel.addLeft(rareCryptCheckBox)
        actionPanel.addLeft(goButton)

        this.addLeft(presetNameFileNameLabel)
        this.addLeft(presetPanel)
        this.addLeft(presetTableScrollPane)
        this.addLeft(presetControlsPanel)
        this.addLeft(actionPanel)

        val scrollPane = JScrollPane(
            pointsTextArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        )
        scrollPane.preferredSize = Dimension(scrollPane.width, 150)
        this.addLeft(scrollPane)

        if (presets.isNotEmpty()) {
            presetTable.setRowSelectionInterval(0, 0)
        }
    }

    private fun addPreset(presetName: String, infoLabel: InfoLabel) {
        logger.info { "Adding preset with name: $presetName" }
        presetTableModel.addRow(arrayOf(presetName))
        presetNameTextField.border = JTextField().border
        presetNameTextField.background = JTextField().background
        infoLabel.text = "Preset $presetName created"
        presets[presetName] = Preset(presetName, presets.isEmpty())
    }

    private fun removePreset(infoLabel: InfoLabel) {
        val presetName = getPresetNameFromTable(presetTable.selectedRow)
        logger.info { "Removing preset with name: $presetName" }
        val preset = presets[presetName]!!
        if (preset.selected) {
            points.clear()
            pointsTextArea.text = ""
        }
        presets.remove(presetName)
        infoLabel.text = "Preset $presetName deleted"
        FileUtils.deleteCoordsFile(preset)
        presetTableModel.removeRow(presetTable.selectedRow)
    }

    private fun reactOnPresetNameChange(fn: () -> Unit) {
        presetNameTextField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                fn()
            }

            override fun removeUpdate(e: DocumentEvent?) {
                fn()
            }

            override fun changedUpdate(e: DocumentEvent?) {
                fn()
            }
        })
    }

    private fun transformPresetNameToFileName() {
        setFileNameLabel(presetNameTextField.text.replace(" ".toRegex(), "-").lowercase())
    }

    private fun showError(infoLabel: InfoLabel, errorMessage: String) {
        infoLabel.text = errorMessage
        presetNameTextField.background = Color(254, 204, 204)
    }

    private fun loadPresets() {
        val firstUsageOfApp = File(FileUtils.APP_PATH)
            .walkTopDown()
            .none { it.name.startsWith("coords") }

        if (firstUsageOfApp) {
            return
        }

        File(FileUtils.APP_PATH)
            .walkTopDown()
            .filter { it.name.startsWith("coords") }
            .sorted()
            .forEachIndexed { index, file ->
                var selected = false
                if (index == 0) {
                    loadPoints(file)
                    selected = true
                }
                val presetName = file.name.removeSuffix(FileUtils.EXTENSION)
                presets[presetName] = Preset(presetName, selected)
            }
    }

    private fun getPresetNames() = presets.values.map { it.presetName }

    private fun getPresetNameFromTable(selectedRow: Int) = presetTable.model.getValueAt(selectedRow, 0).toString()

    private fun loadPoints(file: File) {
        points.clear()
        points.addAll(FileUtils.loadPoints(file.absolutePath))
        pointsTextArea.text = ""
        if (points.isNotEmpty()) pointsTextArea.text = points.joinToString(separator = "\n", postfix = "\n")
    }

    private fun setFileNameLabel(text: String = "") {
        presetNameFileNameLabel.text = "${PREFIX}coords-$text${FileUtils.EXTENSION}"
        presetNameFileNameLabel.border = BorderFactory.createLineBorder(Color.DARK_GRAY)
    }

    private val logger = KotlinLogging.logger { }
}

