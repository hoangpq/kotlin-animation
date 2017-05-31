import java.awt.*
import javax.swing.*
import io.reactivex.*
import io.reactivex.schedulers.Schedulers

class KotlinFrame: JFrame() {

    init { initUI() }

    private fun initPanel() {
        add(Board(), BorderLayout.CENTER)
    }

    private fun initUI() {
        title = "Kotlin Frame"
        setSize(500, 500)
        setLocationRelativeTo(null)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        layout = java.awt.BorderLayout()
        initPanel()
    }

}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")
        } catch (e: Exception) {
            println(e.message)
        }
        val frame = KotlinFrame()
        frame.isVisible = true
        SwingUtilities.updateComponentTreeUI(frame)
    }
}
