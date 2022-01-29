package calculator.tokenizer.token

import calculator.visitor.TokenVisitor

interface Token {
    fun accept(visitor: TokenVisitor)
}