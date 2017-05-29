/**
 * Created by hoangpq on 5/27/2017.
 */

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Image

import javax.swing.ImageIcon

class Leaf(private var x: Float, private var y: Float, private val speed: Int, _leafImg: Image) {

    private var angle = 0f
    private var directionX = 1
    private val leafImg: ImageIcon = ImageIcon(_leafImg)

    fun draw(g: Graphics) {
        val g2 = g as Graphics2D
        leafImg.paintIcon(null, g2, x.toInt(), y.toInt())
    }

    fun update() {
        angle += (directionX * speed).toFloat()
        x += (speed * Math.sin(Math.toRadians(angle.toDouble()))).toFloat()
        y += speed.toFloat()
    }

    fun checkCollision(): Boolean {
        if (y >= 500 - leafImg.iconHeight) {
            return true
        }
        return false
    }
}
