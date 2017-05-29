import java.awt.*
import javax.swing.*
import io.reactivex.*
import io.reactivex.schedulers.Schedulers

class KotlinFrame: javax.swing.JFrame() {

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
        Flowable.just("Hello, World").subscribe(::println)


        Flowable.fromCallable {
            Thread.sleep(1000)
            "Done"
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(::println, Throwable::printStackTrace)

        Thread.sleep(2000)


        val frame = KotlinFrame()
        frame.isVisible = true
        SwingUtilities.updateComponentTreeUI(frame)
    }
}
