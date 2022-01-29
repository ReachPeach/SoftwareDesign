package calculator.tokenizer

import calculator.tokenizer.source.CharSource
import calculator.tokenizer.state.EOF
import calculator.tokenizer.state.ErrorState
import calculator.tokenizer.state.GeneralState
import calculator.tokenizer.state.State
import calculator.tokenizer.token.Token

class Tokenizer(private val source: CharSource) {
    var currentState: State = GeneralState(this)

    fun tokenize(): List<Token> {
        val tokens = ArrayList<Token>()
        while (currentState !is EOF) {
            val currentToken = currentState.nextState(source.nextElement())
            if (currentState is ErrorState) {
                throw IllegalStateException("Unexpected char: ${(currentState as ErrorState).error} at position ${source.currentPosition()}")
            }
            if (currentToken != null) {
                tokens.add(currentToken)
            }
        }
        return tokens
    }
}