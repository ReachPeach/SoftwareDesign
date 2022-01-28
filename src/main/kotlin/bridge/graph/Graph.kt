package bridge.graph

import bridge.drawingApi.Circle
import bridge.drawingApi.DrawingApi
import bridge.drawingApi.Point
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt

abstract class Graph(internal val drawingApi: DrawingApi) {
    data class Edge(val v1: Int, val v2: Int)

    var vertexCount: Int = 0
    fun drawGraph() {
        drawVertexes()
        drawEdges()
        drawingApi.showGraph()
    }

    protected fun getVertexMiddledCircle(circle: Circle): Circle {
        return Circle(Point(circle.center.x - circle.radius, circle.center.y - circle.radius), circle.radius)
    }


    protected fun getCircleOfVertex(vertexIndex: Int): Circle {
        val outerCircle = getOuterCircle()
        val radius = min(
            10.0,
            sqrt((1 - cos(2 * Math.PI / vertexCount)) * outerCircle.radius * outerCircle.radius / 3)
        )
        return Circle(
            Point(
                outerCircle.center.x + outerCircle.radius * cos(2 * Math.PI * vertexIndex / vertexCount),
                outerCircle.center.y + outerCircle.radius * sin(2 * Math.PI * vertexIndex / vertexCount),
            ), radius
        )
    }

    protected abstract fun drawVertexes()
    protected abstract fun drawEdges()
    protected abstract fun initializeFromConsole()

    private fun getOuterCircle(): Circle {
        return Circle(
            Point(
                drawingApi.drawingAreaWidth / 2.0,
                drawingApi.drawingAreaHeight / 2.0
            ), min(drawingApi.drawingAreaWidth, drawingApi.drawingAreaHeight) / 2.0 - 50
        )
    }

}