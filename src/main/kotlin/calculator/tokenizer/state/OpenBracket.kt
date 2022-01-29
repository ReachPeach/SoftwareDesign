package calculator.tokenizer.state

import calculator.tokenizer.Tokenizer
import calculator.tokenizer.token.OpenBracket
import calculator.tokenizer.token.Token

class OpenBracket(tokenizer: Tokenizer) : GeneralState(tokenizer) {
    override fun createToken(): Token {
        return OpenBracket()
    }
}