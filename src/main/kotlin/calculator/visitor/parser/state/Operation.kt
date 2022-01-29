package calculator.visitor.parser.state

import calculator.tokenizer.token.Number
import calculator.tokenizer.token.OpenBracket
import calculator.tokenizer.token.Token

class Operation : BasicState() {
    override fun nextState(token: Token): ValidationState {
        if (token !is Number && token !is OpenBracket) {
            throw IllegalStateException("Expected number or open bracket after operation symbol")
        }
        return super.nextState(token)
    }
}