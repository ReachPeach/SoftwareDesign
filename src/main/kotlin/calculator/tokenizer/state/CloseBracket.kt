package calculator.tokenizer.state

import calculator.tokenizer.Tokenizer
import calculator.tokenizer.token.CloseBracket
import calculator.tokenizer.token.Token

class CloseBracket(tokenizer: Tokenizer) : GeneralState(tokenizer) {
    override fun createToken(): Token {
        return CloseBracket()
    }
}