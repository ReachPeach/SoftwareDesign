package calculator.tokenizer.state

import calculator.tokenizer.Tokenizer
import calculator.tokenizer.token.Multiply
import calculator.tokenizer.token.Token

class Multiply(tokenizer: Tokenizer) : GeneralState(tokenizer) {
    override fun createToken(): Token {
        return Multiply()
    }
}