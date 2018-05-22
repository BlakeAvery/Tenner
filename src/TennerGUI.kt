import java.awt.*
import java.util.*
import javax.swing.*
import ufoproducts.util.*
import ufoproducts.order.*
import java.io.*
import java.net.*

/**
 * TennerGUI: As the class title says, Tenner with a GUI. Implemented mainly in
 * Swing, because JavaFX seems scary. Swing also is guaranteed to be included in
 * all systems, I'm sure. (JavaFX on Linux seems to require the installation of
 * openjfx packages, which may be kind of annoying to managers who want to keep
 * packages down.)
 */
object TennerGUI {
    //TODO: Actually learn swing, and by extension GUI programming
    private val VER = 0.9f
    private val GUI_VER = VER.toString() + "gui0.1"
    var pos = POS()
    private var hmm = JFrame("Tenner Point-of-sale v$GUI_VER")
    private var tennerLog = File("logs" + System.getProperty("file.separator") + "log.txt")
    @JvmStatic
    fun main(args: Array<String>) {
        hmm.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        JFrame.setDefaultLookAndFeelDecorated(true)
        //!!BAD MEME BAD MEME!!
        val pane = hmm.contentPane
        val emptyLabel = Label("Welcome to Tenner v$GUI_VER.")
        val clickMeDaddy = JButton("Click me to break!")
        clickMeDaddy.addActionListener(ActionHandler())
        val typeShite = JFormattedTextField("login:")
        pane.add(clickMeDaddy, BorderLayout.SOUTH)
        pane.add(emptyLabel, BorderLayout.NORTH)
        pane.add(typeShite, BorderLayout.CENTER)
        val menuBar = MenuBar()
        menuBar.add(Menu("File"))
        menuBar.getMenu(0).add(MenuItem("Exit"))
        hmm.menuBar = menuBar
        hmm.pack()
        hmm.isVisible = true
        hmm.setSize(Toolkit.getDefaultToolkit().screenSize.getWidth().toInt(), Toolkit.getDefaultToolkit().screenSize.getHeight().toInt() - 24)
        typeShite.setSize(240, 24)
    }
}
