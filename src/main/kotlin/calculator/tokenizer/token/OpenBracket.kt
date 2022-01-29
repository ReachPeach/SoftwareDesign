package calculator.tokenizer.token

import calculator.visitor.TokenVisitor

class OpenBracket : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}