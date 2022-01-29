package calculator.tokenizer.source

class StringSource(private val source: String) : CharSource {
    private var currentIndex = 0
    override fun currentPosition(): Int {
        return currentIndex
    }

    override fun nextElement(): Char {
        return if (currentIndex < source.length) {
            source[currentIndex++]
        } else {
            return CharSource.EOF
        }
    }
}