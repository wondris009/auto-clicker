package cz.sg

import cz.sg.GuiUtils.createButton
import mu.KotlinLogging
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import javax.swing.table.DefaultTableModel


private const val PREFIX = "File name will be: "

class FightPanel(
    infoLabel: InfoLabel,
    clicker: Clicker,
) : JPanel() {

    private val pointsTextArea = JTextArea()

    private val presets = mutableMapOf<String, Preset>()

    private val selectedPoints = mutableListOf<Point>()

    private val presetNameFileNameLabel = JLabel()
    private lateinit var presetNameTextField: JTextField

    private val presetTable = JTable()
    private val presetTableModel = object : DefaultTableModel(0, 0) {
        override fun isCellEditable(row: Int, column: Int): Boolean {
            return false
        }
    }

    init {
        UIManager.getLookAndFeelDefaults().putIfAbsent("Table.alternateRowColor", Color(240, 240, 240))

        this.layout = BorderLayout()
        @Suppress("DEPRECATION")
        pointsTextArea.enable(false)

        loadPresets()

        val controlsPanel = JPanel()
        controlsPanel.layout = BoxLayout(controlsPanel, BoxLayout.Y_AXIS)

        setFileNameLabel()
        controlsPanel.add(presetNameFileNameLabel)

        presetNameTextField = JTextField()
        val numberOfRoundsPanel = GuiUtils.getInputRow("Preset name", presetNameTextField)
        controlsPanel.add(numberOfRoundsPanel)
        reactOnPresetNameChange(presetNameTextField)
        controlsPanel.add(numberOfRoundsPanel)

        val presetControlsPanel = JPanel()
        presetControlsPanel.layout = BoxLayout(presetControlsPanel, BoxLayout.X_AXIS)
        presetControlsPanel.alignmentX = Component.LEFT_ALIGNMENT

        val addPresetButton = createButton(buttonLabel = "+") {
            if (presetNameTextField.text.isNotEmpty()) {
                val presetName = presetNameFileNameLabel.text.replace(PREFIX, "").replace(FileUtils.EXTENSION, "")
                if (!getPresetNames().contains(presetName)) {
                    addPreset(presetName, infoLabel)
                } else {
                    showError(infoLabel, "Set preset name")
                }
            } else {
                showError(infoLabel, "Preset already exists")
            }
        }
        presetControlsPanel.add(addPresetButton)

        val removePresetButton = createButton(buttonLabel = "-") {
            if (presetTable.selectedRow != -1) {
                removePreset()
            } else {
                infoLabel.text = "Select preset from the table"
            }
        }
        presetControlsPanel.add(removePresetButton)
        controlsPanel.add(presetControlsPanel)

        presetTableModel.setColumnIdentifiers(arrayOf("Preset"))
        presetTable.model = presetTableModel
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
        controlsPanel.add(presetTable)

        this.add(controlsPanel, BorderLayout.NORTH)

        val scrollPane = JScrollPane(
            pointsTextArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        )
        this.add(scrollPane, BorderLayout.CENTER)

        presetTable.setRowSelectionInterval(0, 0)
    }

    private fun addPreset(presetName: String, infoLabel: InfoLabel) {
        logger.info { "Adding preset with name: $presetName" }
        presetTableModel.addRow(arrayOf(presetName))
        presetNameTextField.border = JTextField().border
        infoLabel.text = "Preset $presetName created"
        presets[presetName] = Preset(presetName)
    }

    private fun removePreset() {
        val presetName = getPresetNameFromTable(presetTable.selectedRow)
        logger.info { "Removing preset with name: $presetName" }
        val preset = presets[presetName]!!
        if (preset.selected) {
            selectedPoints.clear()
            pointsTextArea.text = ""
        }
        presets.remove(presetName)
        FileUtils.deleteCoordsFile(preset)
        presetTableModel.removeRow(presetTable.selectedRow)
    }


    private fun reactOnPresetNameChange(presetNameTextField: JTextField) {
        presetNameTextField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                transformPresetNameToFileName()
            }

            override fun removeUpdate(e: DocumentEvent?) {
                transformPresetNameToFileName()
            }

            override fun changedUpdate(e: DocumentEvent?) {
                transformPresetNameToFileName()
            }

            private fun transformPresetNameToFileName() {
                setFileNameLabel(presetNameTextField.text.replace(" ".toRegex(), "-").lowercase())
            }
        })
    }

    private fun showError(infoLabel: InfoLabel, errorMessage: String) {
        infoLabel.text = errorMessage
        presetNameTextField.background = Color.RED
    }

    private fun loadPresets() {
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
        selectedPoints.clear()
        selectedPoints.addAll(FileUtils.loadPoints(file.absolutePath))
        pointsTextArea.text = ""
        selectedPoints.joinToString(separator = "\n")
        pointsTextArea.text = selectedPoints.joinToString(separator = "\n")
    }

    private fun setFileNameLabel(text: String = "") {
        presetNameFileNameLabel.text = "${PREFIX}coords-$text${FileUtils.EXTENSION}"
//        presetNameFileNameLabel.border = BorderFactory.createLineBorder(Color.DARK_GRAY)
    }

    private val logger = KotlinLogging.logger { }
}

