package calculator.tokenizer.source

interface CharSource {
    companion object {
        const val EOF = 0.toChar()
        fun isEOF(char: Char): Boolean {
            return char == EOF
        }
    }

    fun currentPosition(): Int
    fun nextElement(): Char
}