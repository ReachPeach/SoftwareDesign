package calculator.tokenizer.state

import calculator.tokenizer.Tokenizer
import calculator.tokenizer.source.CharSource
import calculator.tokenizer.token.Token

open class GeneralState(private val tokenizer: Tokenizer) : State {
    override fun nextState(char: Char): Token? {
        tokenizer.currentState = when (char) {
            '(' -> OpenBracket(tokenizer)
            ')' -> CloseBracket(tokenizer)
            '+' -> Sum(tokenizer)
            '-' -> Subtract(tokenizer)
            '*' -> Multiply(tokenizer)
            '/' -> Division(tokenizer)
            else -> when {
                char.isWhitespace() -> GeneralState(tokenizer)
                char.isDigit() -> Digit(tokenizer, char)
                CharSource.isEOF(char) -> EOF()
                else -> ErrorState(char)
            }
        }
        return createToken()
    }

    override fun createToken(): Token? {
        return null
    }

}