package bridge

import bridge.drawingApi.AWTDrawingApi
import bridge.drawingApi.JavaFXDrawingApi
import bridge.graph.AdjacencyGraph
import bridge.graph.EdgesGraph
import bridge.graph.Graph
import org.junit.Test
import java.lang.Thread.sleep


class VisualizersTest {
    @Test
    fun `Swing visualizer by edges test`() {
        val api = AWTDrawingApi(drawingAreaWidth = 600, drawingAreaHeight = 600)
        val edges = arrayListOf(
            Graph.Edge(1, 2),
            Graph.Edge(1, 3),
            Graph.Edge(1, 4),
            Graph.Edge(2, 3),
            Graph.Edge(2, 4),
            Graph.Edge(3, 4),
        )
        val graph = EdgesGraph(api, edges)
        graph.drawGraph()
        sleep(10000)
    }

    @Test
    fun `Swing visualizer by matrix test`() {
        val api = AWTDrawingApi(drawingAreaHeight = 600, drawingAreaWidth = 600)
        val matrix = arrayListOf(
            arrayListOf(false, true, true, true),
            arrayListOf(true, false, true, true),
            arrayListOf(true, true, false, true),
            arrayListOf(true, true, true, false),
        )
        val graph = AdjacencyGraph(api, matrix)
        graph.drawGraph()
        sleep(10000)
    }

    @Test
    fun `JavaFX visualizer by edges test`() {
        val api = JavaFXDrawingApi(600, 600)
        val edges = arrayListOf(
            Graph.Edge(1, 2),
            Graph.Edge(1, 3),
            Graph.Edge(1, 4),
            Graph.Edge(2, 3),
            Graph.Edge(2, 4),
            Graph.Edge(3, 4),
        )
        val graph = EdgesGraph(api, edges)
        graph.drawGraph()
        sleep(10000)
    }

    @Test
    fun `JavaFX visualizer by matrix test`() {
        val api = JavaFXDrawingApi(600, 600)
        val matrix = arrayListOf(
            arrayListOf(false, true, true, true),
            arrayListOf(true, false, true, true),
            arrayListOf(true, true, false, true),
            arrayListOf(true, true, true, false),
        )
        val graph = AdjacencyGraph(api, matrix)
        graph.drawGraph()
        sleep(10000)
    }

    @Test
    fun `Swing large graph test`() {
        val api = AWTDrawingApi(600, 600)
        val edges = arrayListOf(
            Graph.Edge(100, 10),
        )
        val graph = EdgesGraph(api, edges)
        graph.drawGraph()
        sleep(10000)
    }

    @Test
    fun `JavaFX large graph test`() {
        val api = JavaFXDrawingApi(600, 600)
        val edges = arrayListOf(
            Graph.Edge(500, 250),
        )
        val graph = EdgesGraph(api, edges)
        graph.drawGraph()
    }
}