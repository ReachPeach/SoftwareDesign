package bridge.graph

import bridge.drawingApi.DrawingApi
import bridge.drawingApi.Line
import java.lang.Integer.max

class EdgesGraph : Graph {
    private val edges: ArrayList<Edge>

    constructor(drawingApi: DrawingApi) : super(drawingApi) {
        edges = ArrayList()
        initializeFromConsole()
    }

    constructor(drawingApi: DrawingApi, edgesList: List<Edge>) : super(drawingApi) {
        edges = ArrayList(edgesList)

        vertexCount = edges.maxOf { max(it.v1, it.v2) }
    }



    override fun drawVertexes() {
        for (v in 1..vertexCount) {
            drawingApi.drawCircle(getVertexMiddledCircle(getCircleOfVertex(v)))
        }
    }

    override fun drawEdges() {
        for (edge in edges) {
            drawingApi.drawLine(
                Line(
                    getCircleOfVertex(edge.v1).center,
                    getCircleOfVertex(edge.v2).center
                )
            )
        }
    }

    override fun initializeFromConsole() {
        print("Enter number of vertexes in graph:")
        vertexCount = readLine()!!.toInt()
        print("Enter number of edges in graph:")
        val edgesCount = readLine()!!.toInt()
        println("Enter $edgesCount edges, where edge is a pair of integers like <x,y>:1<=x,y<=$vertexCount")
        for (i in 1..edgesCount) {
            val vertexPair = readLine()!!.split(",").map { it.trim().toInt() }
            edges.add(Edge(vertexPair[0], vertexPair[1]))
        }
    }
}