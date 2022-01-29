package calculator.tokenizer.token

import calculator.visitor.TokenVisitor

class Multiply : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}