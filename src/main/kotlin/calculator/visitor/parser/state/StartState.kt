package calculator.visitor.parser.state

import calculator.tokenizer.token.Number
import calculator.tokenizer.token.OpenBracket
import calculator.tokenizer.token.Token

class StartState : BasicState() {
    override fun nextState(token: Token): ValidationState {
        if (token !is Number && token !is OpenBracket) {
            throw IllegalStateException("Expression should start with number of open bracket")
        }
        return super.nextState(token)
    }
}