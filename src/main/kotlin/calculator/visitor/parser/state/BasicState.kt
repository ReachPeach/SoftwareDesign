package calculator.visitor.parser.state

import calculator.tokenizer.token.CloseBracket
import calculator.tokenizer.token.Number
import calculator.tokenizer.token.OpenBracket
import calculator.tokenizer.token.Token

open class BasicState : ValidationState {
    override fun nextState(token: Token): ValidationState {
        return when (token) {
            is CloseBracket -> CloseBrace()
            is OpenBracket -> OpenBrace()
            is Number -> Numeric()
            else -> Operation()
        }
    }
}