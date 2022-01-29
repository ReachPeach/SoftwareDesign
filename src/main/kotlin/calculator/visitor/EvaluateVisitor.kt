package calculator.visitor

import calculator.tokenizer.token.*
import calculator.tokenizer.token.Number
import java.util.*

class EvaluateVisitor : TokenVisitor {
    private val stack = Stack<Int>()
    override fun visit(sum: Sum) {
        applyOperation { arg1, arg2 -> arg1 + arg2 }
    }

    override fun visit(sub: Subtract) {
        applyOperation { reduced, subtracted -> reduced - subtracted }
    }

    override fun visit(mul: Multiply) {
        applyOperation { arg1, arg2 -> arg1 * arg2 }
    }

    override fun visit(div: Division) {
        applyOperation { divisible, divisor ->
            if (divisor == 0) throw IllegalArgumentException("Division by 0")
            divisible / divisor
        }
    }

    override fun visit(num: Number) {
        stack.push(num.value)
    }

    override fun visit(oBr: OpenBracket) {
        throw IllegalArgumentException("Illegal reversed polish notation given!")
    }

    override fun visit(cBr: CloseBracket) {
        throw IllegalArgumentException("Illegal reversed polish notation given!")
    }

    private fun applyOperation(operation: (Int, Int) -> Int) {
        if (stack.size < 2) {
            throw IllegalArgumentException("Illegal reversed polish notation given!")
        }

        val secondArg = stack.pop()
        val firstArg = stack.pop()
        val evaluatedRes = operation.invoke(firstArg, secondArg)
        stack.push(evaluatedRes)
    }

    fun evaluate(): Int? {
        if (stack.size != 1) {
            throw IllegalStateException("Incorrect reversed polish notation given")
        }
        return stack.peek()
    }
}