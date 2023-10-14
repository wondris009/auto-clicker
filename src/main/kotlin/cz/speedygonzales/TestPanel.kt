//package cz.speedygonzales
//
//import cz.speedygonzales.GuiUtils.createButton
//import java.awt.BorderLayout
//import java.awt.Color
//import java.awt.Font
//import javax.swing.BoxLayout
//import javax.swing.JLabel
//import javax.swing.JPanel
//
//
//class TestPanel : JPanel() {
//
//    init {
//        this.layout = BorderLayout()
//
//        val controlsPanel = JPanel()
//        controlsPanel.layout = BoxLayout(controlsPanel, BoxLayout.Y_AXIS)
//
//        val tesTButton = createButton("Show error message dialog window") {
//
//            val errorMsg = InfoLabel(
//                "<html>You need to specify exactly 6 points." +
//                        "<br/><br/>" +
//                        "1> Watchtower icon location." +
//                        "<br/>" +
//                        "2> Go button with selected crypt" +
//                        "<br/>" +
//                        "3> Crypt position on the map" +
//                        "<br/>" +
//                        "4> Explore button" +
//                        "<br/>" +
//                        "5> Speedup button" +
//                        "<br/>" +
//                        "6> Use speedup button" +
//                "</html>",
//                Color.BLUE,
//                "Verdana",
//                Font.PLAIN,
//                16
//            )
//
//            GuiUtils.showErrorMessage(this, errorMsg)
//        }
//        controlsPanel.add(tesTButton)
//        this.add(controlsPanel)
//    }
//}
//
