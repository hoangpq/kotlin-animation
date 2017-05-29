import java.awt.*
import java.awt.event.ActionListener
import javax.swing.JPanel
import javax.swing.ImageIcon
import java.util.Random
import java.util.Vector
import javax.swing.Timer


/**
 * Created by hoangpq on 5/27/2017.
 */

class Board: JPanel() {

    private var leafs: Vector<Leaf> = Vector()
    private var rd: Random = Random()
    private var timer: Timer? = null
    private var f: Font? = null
    private var background: Image? = null
    private val images = arrayOf("al.gif", "bl.gif", "cl.gif", "dl.gif", "el.gif", "fl.gif")

    init {
        leafs = Vector<Leaf>()
        rd = Random()
        try {
            val font = javaClass.getResourceAsStream("/font/brushtip.ttf")
            f = Font.createFont(Font.PLAIN, font).deriveFont(72f)
        } catch (e: FontFormatException) {
            e.printStackTrace()
        }

        // TODO: init leafs
        (0..49).forEach { randomLeaf() }

        // timer
        timer = Timer(10, ActionListener {
            update()
            leafs.forEach { it.update() }
            parent.repaint()
        })
        timer?.start()
        background = ImageIcon(javaClass.getResource("/images/background.jpg")).image
    }

    fun randomLeaf() {
        val x = rd.nextInt(500) * 1.0f
        val y = -rd.nextInt(550) * 1.0f
        val pos = rd.nextInt(5)
        val speed = rd.nextInt(5) + 2
        val img = ImageIcon(javaClass.getResource("/images/${images[pos]}")).image
        leafs.add(Leaf(x, y, speed, img))
    }

    private fun update() {
        for (i in 0..leafs.size - 1) {
            if (leafs[i].checkCollision()) {
                leafs.removeAt(i)
                randomLeaf()
            }
        }
    }

    public override fun paintComponent(g: Graphics) {
        val g2: Graphics2D? = g as Graphics2D
        g2!!.color = Color.PINK
        g2.clearRect(0, 0, width, height)
        g2.fillRect(0, 0, width, height)
        val text = "Hello, Kotlin"
        for (i in 0..leafs.size - 1) {
            leafs[i].draw(g)
        }
        g2.font = f
        val fontWidth = g2.fontMetrics.stringWidth(text)
        g2.color = Color.BLUE
        g2.drawString(text, 250 - fontWidth / 2, 250)
    }

}
