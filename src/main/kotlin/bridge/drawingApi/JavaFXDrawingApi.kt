package bridge.drawingApi

import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.stage.Stage


class JavaFXDrawingApi(
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
        JavaFxApplication.showGraph(this)
    }

    class JavaFxApplication : DrawingApi.ApplicationApi, javafx.application.Application() {
        override fun showGraph() {
            launch()
        }

        companion object {
            fun showGraph(api: JavaFXDrawingApi) {
                this.api = api
                launch(JavaFxApplication::class.java)
            }

            lateinit var api: JavaFXDrawingApi
        }

        override fun start(primaryStage: Stage?) {
            primaryStage!!.title = "JavaFX visualization"
            val root = Group()
            val canvas = Canvas(api.drawingAreaWidth.toDouble(), api.drawingAreaHeight.toDouble())
            val gc: GraphicsContext = canvas.graphicsContext2D
            gc.fill = Color.GREEN
            for (circle in api.circles) {
                gc.fillOval(circle.center.x, circle.center.y, 2 * circle.radius, 2 * circle.radius)
            }
            for (line in api.lines) {
                gc.strokeLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y)
            }

            root.children.add(canvas)
            primaryStage.scene = Scene(root)
            primaryStage.show()
        }

    }
}