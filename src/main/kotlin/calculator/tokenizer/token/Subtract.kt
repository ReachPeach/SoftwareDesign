package calculator.tokenizer.token

import calculator.visitor.TokenVisitor

class Subtract : Token{
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}