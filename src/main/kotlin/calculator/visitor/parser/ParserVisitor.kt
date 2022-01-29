import calculator.tokenizer.token.*
import calculator.tokenizer.token.Number
import calculator.visitor.TokenVisitor
import calculator.visitor.parser.state.CloseBrace
import calculator.visitor.parser.state.Numeric
import calculator.visitor.parser.state.StartState
import calculator.visitor.parser.state.ValidationState
import java.util.*

class ParserVisitor : TokenVisitor {
    private val operations = Stack<Token>()
    private val reversedPolishNotation = ArrayList<Token>()
    private var validationState: ValidationState = StartState()

    override fun visit(sum: Sum) {
        visitOperation(sum)
    }

    override fun visit(sub: Subtract) {
        visitOperation(sub)
    }

    override fun visit(mul: Multiply) {
        visitOperation(mul)
    }

    override fun visit(div: Division) {
        visitOperation(div)
    }

    override fun visit(num: Number) {
        nextState(num)
        reversedPolishNotation.add(num)
    }

    override fun visit(oBr: OpenBracket) {
        nextState(oBr)
        operations.push(oBr)
    }

    override fun visit(cBr: CloseBracket) {
        nextState(cBr)
        while (!operations.isEmpty() && operations.peek() !is OpenBracket) {
            reversedPolishNotation.add(operations.pop())
        }
        if (operations.isEmpty()) {
            throw IllegalStateException("Given expression doesnt match to correct bracket consequence")
        }
        operations.pop()
    }

    fun getReversedPolishNotation(): List<Token> {
        if (validationState !is Numeric && validationState !is CloseBrace) {
            throw IllegalStateException("Expected number of closed bracket at the end of the expression")
        }
        if (operations.firstOrNull { it is OpenBracket } != null) {
            throw IllegalStateException("Mismatched close bracket")
        }
        return reversedPolishNotation + operations.reversed()
    }

    private fun visitOperation(operation: Token) {
        nextState(operation)
        while (!operations.empty() && operations.peek() !is OpenBracket) {
            val previousOperation = operations.peek()
            if (previousOperation.getPriority() >= operation.getPriority()) {
                reversedPolishNotation.add(previousOperation)
                operations.pop()
            } else {
                break
            }
        }
        operations.push(operation)
    }

    private fun Token.getPriority() = when (this) {
        is Division, is Multiply -> 2
        is Subtract, is Sum -> 1
        else -> throw IllegalArgumentException("Non operation can't have priority")
    }

    private fun nextState(token: Token) {
        validationState = validationState.nextState(token)
    }
}