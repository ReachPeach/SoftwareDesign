package calculator.visitor

import calculator.tokenizer.token.*
import calculator.tokenizer.token.Number

interface TokenVisitor {
    fun visit(sum: Sum)
    fun visit(sub: Subtract)
    fun visit(mul: Multiply)
    fun visit(div: Division)
    fun visit(num: Number)
    fun visit(oBr: OpenBracket)
    fun visit(cBr: CloseBracket)
}