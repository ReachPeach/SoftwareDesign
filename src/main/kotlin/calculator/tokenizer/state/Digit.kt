package calculator.tokenizer.state

import calculator.tokenizer.Tokenizer
import calculator.tokenizer.token.Number
import calculator.tokenizer.token.Token

class Digit(tokenizer: Tokenizer, char: Char) : GeneralState(tokenizer) {
    private val currentDigits = StringBuilder().append(char)

    override fun nextState(char: Char): Token? {
        return if (char.isDigit()) {
            currentDigits.append(char)
            null
        } else {
            super.nextState(char)
        }
    }

    override fun createToken(): Token {
        return Number(currentDigits.toString().toInt())
    }
}