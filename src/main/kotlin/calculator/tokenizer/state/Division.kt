package calculator.tokenizer.state

import calculator.tokenizer.Tokenizer
import calculator.tokenizer.token.Division
import calculator.tokenizer.token.Token

class Division(tokenizer: Tokenizer) : GeneralState(tokenizer) {
    override fun createToken(): Token {
        return Division()
    }
}