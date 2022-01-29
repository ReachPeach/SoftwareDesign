package calculator.tokenizer.token

import calculator.visitor.TokenVisitor

class Division : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}