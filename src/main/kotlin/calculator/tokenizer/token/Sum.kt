package calculator.tokenizer.token

import calculator.visitor.TokenVisitor

class Sum : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}