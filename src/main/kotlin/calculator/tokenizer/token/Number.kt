package calculator.tokenizer.token

import calculator.visitor.TokenVisitor

class Number(val value: Int) : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}