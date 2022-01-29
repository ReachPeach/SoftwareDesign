package calculator.tokenizer.state

import calculator.tokenizer.token.Token

class ErrorState(val error: Char) : State {
    override fun nextState(char: Char): Token? {
        return null
    }

    override fun createToken(): Token? {
        return null
    }
}