package calculator.visitor.parser.state

import calculator.tokenizer.token.OpenBracket
import calculator.tokenizer.token.Token

class CloseBrace : BasicState() {
    override fun nextState(token: Token): ValidationState {
        if (token is Number || token is OpenBracket) {
            throw IllegalStateException("Expected operation after close bracket")
        }
        return super.nextState(token)
    }
}