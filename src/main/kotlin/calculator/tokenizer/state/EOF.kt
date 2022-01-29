package calculator.tokenizer.state

import calculator.tokenizer.token.Token

class EOF : State {
    override fun nextState(char: Char): Token? {
        return null
    }

    override fun createToken(): Token? {
        return null
    }
}