package calculator.visitor.parser.state

import calculator.tokenizer.token.Number
import calculator.tokenizer.token.OpenBracket
import calculator.tokenizer.token.Token

class Numeric : BasicState() {
    override fun nextState(token: Token): ValidationState {
        if (token is Number || token is OpenBracket) {
            throw IllegalStateException("Expected operation or close bracket after number")
        }
        return super.nextState(token)
    }
}