package bridge

import bridge.drawingApi.AWTDrawingApi
import bridge.graph.AdjacencyGraph
import bridge.graph.EdgesGraph

private enum class APIName {
    Swing, JavaFX
}

private enum class GraphType {
    Adjacency, Edges
}


private fun printInformation() {
    println("Welcome to graph visualizer!")
    println("To visualize graph, you should choose drawing API (Swing or JavaFX) and enter graph for visualizing")
}

private fun printUsage() {

}

private fun readApiName(): APIName {
    print("Enter name of the API that would be used (Swing or JavaFX):")

    var enteredName = readLine()!!.trim()
    val typesList = APIName.values().toList()
    var currentType = typesList.find { it.name.equals(enteredName, ignoreCase = true) }
    while (currentType == null) {
        print("Enter one of available types: Swing or JavaFX")
        enteredName = readLine()!!.trim()
        currentType = typesList.find { it.name.equals(enteredName, ignoreCase = true) }
    }

    return currentType
}


private fun readGraphType(): GraphType {
    print("Enter graph's type that would be used (adjacency or edges):")

    var enteredName = readLine()!!.trim()
    val typesList = GraphType.values().toList()
    var currentType = typesList.find { it.name.equals(enteredName, ignoreCase = true) }
    while (currentType == null) {
        print("Enter one of available types: adjacency or edges")
        enteredName = readLine()!!.trim()
        currentType = typesList.find { it.name.equals(enteredName, ignoreCase = true) }
    }

    return currentType
}

fun main() {
    printInformation()
    printUsage()

    val apiType = when (readApiName()) {
        APIName.Swing -> AWTDrawingApi(drawingAreaHeight = 600, drawingAreaWidth = 600)
        APIName.JavaFX -> AWTDrawingApi(drawingAreaHeight = 600, drawingAreaWidth = 600)//JavaFXDrawingApi()
    }

    val graph = when (readGraphType()) {
        GraphType.Adjacency -> AdjacencyGraph(apiType)
        GraphType.Edges -> EdgesGraph(apiType)
    }

    graph.drawGraph()
}
