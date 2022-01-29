package calculator.visitor.parser.state

import calculator.tokenizer.token.Token

interface ValidationState {
    fun nextState(token: Token): ValidationState
}