package calculator.tokenizer.state

import calculator.tokenizer.Tokenizer
import calculator.tokenizer.token.Subtract
import calculator.tokenizer.token.Token

class Subtract(tokenizer: Tokenizer) : GeneralState(tokenizer) {
    override fun createToken(): Token {
        return Subtract()
    }
}