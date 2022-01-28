package bridge.graph

import bridge.drawingApi.DrawingApi
import bridge.drawingApi.Line

class AdjacencyGraph : Graph {
    private val adjacencyMatrix: ArrayList<ArrayList<Boolean>>

    constructor(drawingApi: DrawingApi) : super(drawingApi) {
        adjacencyMatrix = ArrayList()
        initializeFromConsole()
    }

    constructor(drawingApi: DrawingApi, matrix: List<ArrayList<Boolean>>) : super(drawingApi) {
        vertexCount = matrix.size
        adjacencyMatrix = ArrayList(matrix)
    }


    override fun drawVertexes() {
        for (v in 1..vertexCount) {
            drawingApi.drawCircle(getVertexMiddledCircle(getCircleOfVertex(v)))
        }
    }

    override fun drawEdges() {
        for (i in 0 until vertexCount) {
            for (j in i until vertexCount) {
                if (adjacencyMatrix[i][j]) {
                    drawingApi.drawLine(
                        Line(
                            getCircleOfVertex(i + 1).center,
                            getCircleOfVertex(j + 1).center
                        )
                    )
                }
            }
        }
    }

    override fun initializeFromConsole() {
        print("Enter number of vertexes in graph:")
        vertexCount = readLine()!!.toInt()
        adjacencyMatrix.addAll(ArrayList(List(vertexCount) { ArrayList(List(vertexCount) { false }) }))

        println("\nEnter adjacency matrix, where matrix is a [n x n] where each row is <x1,x2...xn>:xi=0/1 - 1 if there is an edge and 0 otherwise)")
        for (i in 1..vertexCount) {
            val edgesIndicators = readLine()!!.split(",").map { it.trim().toInt() }
            for (j in edgesIndicators.indices) {
                adjacencyMatrix[i][j] = edgesIndicators[j] == 1
            }
        }
    }
}