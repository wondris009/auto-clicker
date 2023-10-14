package cz.speedygonzales

import cz.speedygonzales.GuiUtils.createButton
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import java.awt.Point
import javax.swing.*


class CryptPanel(
    infoLabel: InfoLabel,
    clicker: Clicker,
    private val points: MutableList<Point>,
    pointsTextArea: JTextArea
) : JPanel() {

    init {
        //can't see setEnabled
        pointsTextArea.enable(false)

        points.forEach { pointsTextArea.append("$it\n") }

        val scrollPane = JScrollPane(
            pointsTextArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        )

        this.layout = BorderLayout()

        val controlsPanel = JPanel()
        controlsPanel.layout = BoxLayout(controlsPanel, BoxLayout.Y_AXIS)

        val removeLastValueButton = createButton("Remove last value") {
            if (points.isNotEmpty()) {
                points.removeLast()
                val linesCount = pointsTextArea.lineCount
                val start = pointsTextArea.getLineStartOffset(linesCount - 2)
                val end = pointsTextArea.getLineEndOffset(linesCount - 1)
                pointsTextArea.replaceRange("", start, end)
            }
        }
        controlsPanel.add(removeLastValueButton)

        val clearAllPointsButton = createButton("Clear all") {
            points.clear()
            pointsTextArea.text = ""
        }
        controlsPanel.add(clearAllPointsButton)

        val simulatePathButton = createButton("Simulate") {
            Thread.sleep(3_000)
            points.forEach {
                clicker.mouseMove(it.x, it.y)
                Thread.sleep(Constants.SECOND.toLong())
            }
        }
        controlsPanel.add(simulatePathButton)

        val numberOfRounds = JTextField("10")
        val numberOfRoundsPanel = GuiUtils.getInputRow("Number of rounds", numberOfRounds)
        controlsPanel.add(numberOfRoundsPanel)

        val goButton = createButton("Make Common or Epic CRYPTS !!!") {

            if(points.size != 6) {
                val errorMsg = InfoLabel(
                    "<html>You need to specify exactly 6 points." +
                            "<br/><br/>" +
                            "1> Watchtower icon location." +
                            "<br/>" +
                            "2> Go button with selected crypt" +
                            "<br/>" +
                            "3> Crypt position on the map" +
                            "<br/>" +
                            "4> Explore button" +
                            "<br/>" +
                            "5> Speedup button" +
                            "<br/>" +
                            "6> Use speedup button" +
                            "</html>",
                    Color.BLUE,
                    "Verdana",
                    Font.PLAIN,
                    16
                )

                GuiUtils.showErrorMessage(this, errorMsg)
            } else {
                val warningMsg = InfoLabel(
                    "Are you sure you set your watchtower on correct level of crypts before you go?",
                    Color.BLUE,
                    "Verdana",
                    Font.PLAIN,
                    16
                )
                val yesNoResult = GuiUtils.showConfirmDialog(this, warningMsg)
                if(yesNoResult == JOptionPane.YES_OPTION) {
                    Thread(CryptMarcher(clicker, points, numberOfRounds.text.toInt(), infoLabel)).start()
                }
            }
        }
        controlsPanel.add(goButton)

        this.add(controlsPanel, BorderLayout.NORTH)
        this.add(scrollPane, BorderLayout.CENTER)
    }
}

