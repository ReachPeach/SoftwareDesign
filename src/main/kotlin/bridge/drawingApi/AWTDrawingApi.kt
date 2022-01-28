package bridge.drawingApi

import java.awt.Dimension
import java.awt.Frame
import java.awt.Graphics
import java.awt.Graphics2D
import kotlin.math.roundToInt

class AWTDrawingApi(
    override val drawingAreaWidth: Int,
    override val drawingAreaHeight: Int
) : DrawingApi {
    private val circles = ArrayList<Circle>()
    private val lines = ArrayList<Line>()
    override fun drawCircle(circle: Circle) {
        circles.add(circle)
    }

    override fun drawLine(line: Line) {
        lines.add(line)
    }

    override fun showGraph() {
        AWTApplication(this).showGraph()
    }


    class AWTApplication(private val api: AWTDrawingApi) : DrawingApi.ApplicationApi, Frame() {
        override fun paint(g: Graphics?) {
            val graphics2D = graphics as Graphics2D
            graphics2D.color = java.awt.Color.GREEN
            for (circle in api.circles) {
                graphics2D.fillOval(
                    circle.center.x.roundToInt(),
                    circle.center.y.roundToInt(),
                    (2 * circle.radius).roundToInt(),
                    (2 * circle.radius).roundToInt()
                )
            }
            for (line in api.lines) {
                graphics2D.drawLine(
                    line.p1.x.roundToInt(), line.p1.y.roundToInt(),
                    line.p2.x.roundToInt(), line.p2.y.roundToInt()
                )
            }
        }

        override fun showGraph() {
            repaint()
            size = Dimension(api.drawingAreaWidth, api.drawingAreaHeight)
            title = "AWT visualization"
            isResizable = false
            isVisible = true
        }
    }

}