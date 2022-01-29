package calculator.tokenizer.state

import calculator.tokenizer.token.Token

interface State {
    fun nextState(char: Char): Token?
    fun createToken(): Token?
}