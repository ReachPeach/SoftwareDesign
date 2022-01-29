package calculator.tokenizer.token

import calculator.visitor.TokenVisitor

class CloseBracket : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}