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

    private val presetNameFileNameL = JLabel()
    private var presetNameTF: JTextField
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

        val presetP = FlowLeftPanel()
        val presetNameL = JLabel("Preset name")
        presetNameL.border = BorderFactory.createEmptyBorder()
        presetP.addLeft(presetNameL)
        presetNameTF = JTextField("", 20)
        reactOnPresetNameChange {
            transformPresetNameToFileName()
        }
        presetP.addLeft(presetNameTF)

        val addPresetB = createButton(buttonLabel = "+") {
            if (presetNameTF.text.isNotEmpty()) {
                val presetName = presetNameFileNameL.text.replace(PREFIX, "").replace(FileUtils.EXTENSION, "")
                if (!getPresetNames().contains(presetName)) {
                    addPreset(presetName, infoLabel)
                } else {
                    showError(infoLabel, "Preset already exists")
                }
            } else {
                showError(infoLabel, "Set preset name")
            }
        }
        presetP.addLeft(addPresetB)

        val removePresetB = createButton(buttonLabel = "-") {
            if (presetTable.selectedRow != -1) {
                removePreset(infoLabel)
            } else {
                infoLabel.text = "Select preset from the table"
            }
        }
        presetP.addLeft(removePresetB)

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
        val presetTableSP = JScrollPane(
            presetTable,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        )
        presetTableSP.preferredSize = Dimension(presetTableSP.width, 120)

        val presetControlsP = FlowLeftPanel()

        val removeLastValueB = createButton(buttonLabel = "Remove last value") {
            if (points.isNotEmpty()) {
                points.removeLast()
                val linesCount = pointsTextArea.lineCount
                val start = pointsTextArea.getLineStartOffset(linesCount - 2)
                val end = pointsTextArea.getLineEndOffset(linesCount - 1)
                pointsTextArea.replaceRange("", start, end)
                FileUtils.savePoints(presets.values.first { it.selected }, points)
            }
        }
        presetControlsP.addLeft(removeLastValueB)

        val clearAllPointsB = createButton(buttonLabel = "Clear all") {
            points.clear()
            pointsTextArea.text = ""
            FileUtils.savePoints(presets.values.first { it.selected }, points)
        }
        presetControlsP.addLeft(clearAllPointsB)

        val actionP = FlowLeftPanel()
        val numberOfRoundsL = JLabel("Number of rounds")
        actionP.addLeft(numberOfRoundsL)
        val numberOfRoundsTF = JTextField("10", 4)
        actionP.addLeft(numberOfRoundsTF)

        val waitAfterActionL = JLabel("Seconds after action")
        actionP.addLeft(waitAfterActionL)
        val waitAfterSpeedUpsTF = JTextField("40", 4)
        actionP.addLeft(waitAfterSpeedUpsTF)

        val goB = createButton(color = Color.RED, buttonLabel = "Go common/epic") {

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
                    Thread(CryptMarcher(points, numberOfRoundsTF.text.toInt(), waitAfterSpeedUpsTF.text.toInt(), rare, infoLabel)).start()
                }
            }
        }
        val rareCryptCB = JCheckBox("Rare crypt?", rare)
        rareCryptCB.addActionListener {
            rare = !rare
            if (rare) {
                goB.text = "Go rare"
            } else {
                goB.text = "Go common/epic"
            }

        }
        actionP.addLeft(rareCryptCB)
        actionP.addLeft(goB)

        this.addLeft(presetNameFileNameL)
        this.addLeft(presetP)
        this.addLeft(presetTableSP)
        this.addLeft(presetControlsP)
        this.addLeft(actionP)

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
        presetNameTF.border = JTextField().border
        presetNameTF.background = JTextField().background
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
        presetNameTF.document.addDocumentListener(object : DocumentListener {
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
        setFileNameLabel(presetNameTF.text.replace(" ".toRegex(), "-").lowercase())
    }

    private fun showError(infoLabel: InfoLabel, errorMessage: String) {
        infoLabel.text = errorMessage
        presetNameTF.background = Color(254, 204, 204)
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
        presetNameFileNameL.text = "${PREFIX}coords-$text${FileUtils.EXTENSION}"
        presetNameFileNameL.border = BorderFactory.createLineBorder(Color.DARK_GRAY)
    }

    private val logger = KotlinLogging.logger { }
}

