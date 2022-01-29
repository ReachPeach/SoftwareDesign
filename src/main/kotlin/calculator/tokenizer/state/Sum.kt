package calculator.tokenizer.state

import calculator.tokenizer.Tokenizer
import calculator.tokenizer.token.Sum
import calculator.tokenizer.token.Token

class Sum(tokenizer: Tokenizer) : GeneralState(tokenizer) {
    override fun createToken(): Token {
        return Sum()
    }
}