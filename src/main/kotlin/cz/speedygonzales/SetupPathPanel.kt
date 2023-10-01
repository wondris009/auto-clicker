package cz.speedygonzales

import cz.speedygonzales.GuiUtils.createButton
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Point
import javax.swing.*


class SetupPathPanel(
    clicker: Clicker,
    private val points: MutableList<Point>,
    pointsTextArea: JTextArea,
    frame: TotalBattleFrame
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

        val numberOfClicks = JTextField("10")
        controlsPanel.add(numberOfClicks)

        val infoLabel = JLabel("Ready for crypting madness ???")
        infoLabel.foreground = Color.RED
        controlsPanel.add(infoLabel)

        val goButton = createButton("Go CRYPTING !!!") {
            Thread(Crypter(clicker, points, numberOfClicks.text.toInt(), infoLabel)).start()
        }
        controlsPanel.add(goButton)

        this.add(controlsPanel, BorderLayout.NORTH)
        this.add(scrollPane, BorderLayout.CENTER)

        val tesTButton = createButton("Test") {
            frame.showErrorMessage("Eggs are not supposed to be green.")
        }
        controlsPanel.add(tesTButton)
    }

    class Crypter(
        private val clicker: Clicker,
        private val points: MutableList<Point>,
        private val rounds: Int,
        private val label: JLabel
    ) : Runnable {

        override fun run() {

            for (i in 3 downTo 1) {
                setText("There will be $rounds of rounds. Start crypting in $i")
                Thread.sleep(1_000)
            }

            for (round in rounds downTo 1) {
                setText("Round $round")
                clicker.mouseMove(points[0].x, points[0].y)
                clicker.clickLeftMouse()
                Thread.sleep(2_800)

                clicker.mouseMove(points[1].x, points[1].y)
                clicker.clickLeftMouse()
                Thread.sleep(2_800)

                clicker.mouseMove(points[2].x, points[2].y)
                clicker.clickLeftMouse()
                Thread.sleep(2_800)

                clicker.mouseMove(points[3].x, points[3].y)
                clicker.clickLeftMouse()
                Thread.sleep(2_800)

                clicker.mouseMove(points[4].x, points[4].y)
                clicker.clickLeftMouse()
                Thread.sleep(2_800)

                clicker.speedUp(points)
                clicker.waitAfterSpeedUps(round, this)
            }
            setText("$rounds crypts finished")
        }

        fun setText(text: String) {
            label.text = text
        }
    }
}

