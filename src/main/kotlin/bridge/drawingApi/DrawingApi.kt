package bridge.drawingApi

interface DrawingApi {
    val drawingAreaWidth: Int
    val drawingAreaHeight: Int
    fun drawCircle(circle: Circle)
    fun drawLine(line : Line)
    fun showGraph()

    interface ApplicationApi {
        fun showGraph()
    }
}