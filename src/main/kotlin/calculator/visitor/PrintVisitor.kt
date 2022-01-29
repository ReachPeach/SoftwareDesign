package calculator.visitor

import calculator.tokenizer.token.*
import calculator.tokenizer.token.Number

class PrintVisitor : TokenVisitor {
    override fun visit(sum: Sum) {
        print("+")
    }

    override fun visit(sub: Subtract) {
        print("-")
    }

    override fun visit(mul: Multiply) {
        print("*")
    }

    override fun visit(div: Division) {
        print("/")
    }

    override fun visit(num: Number) {
        print(num.value)
    }

    override fun visit(oBr: OpenBracket) {
        print("(")
    }

    override fun visit(cBr: CloseBracket) {
        print(")")
    }
}