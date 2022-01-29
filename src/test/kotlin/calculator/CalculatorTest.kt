package calculator

import ParserVisitor
import calculator.tokenizer.Tokenizer
import calculator.tokenizer.source.StringSource
import calculator.visitor.EvaluateVisitor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    private val testExpression1 = "(1 + 1) - 1"
    private val testExpression2 = "1 - (1 + 2 * 0) + (0 / 1000000)"
    private val testExpression3 = "1 + (1 + (1 + (1 + 1))) - ((((1 + 1) + 1) + 1) + 1)"
    private lateinit var parser: ParserVisitor
    private lateinit var evaluator: EvaluateVisitor

    @Before
    fun `init parsers`() {
        parser = ParserVisitor()
        evaluator = EvaluateVisitor()
    }

    @Test
    fun `test eval1`() {
        val tokens1 = Tokenizer(StringSource(testExpression1)).tokenize()

        tokens1.forEach { it.accept(parser) }
        val reversedPolishNotation = parser.getReversedPolishNotation()

        reversedPolishNotation.forEach { it.accept(evaluator) }

        assertEquals(evaluator.evaluate(), 1)
    }

    @Test
    fun `test eval2`() {
        val tokens1 = Tokenizer(StringSource(testExpression2)).tokenize()

        tokens1.forEach { it.accept(parser) }
        val reversedPolishNotation = parser.getReversedPolishNotation()

        reversedPolishNotation.forEach { it.accept(evaluator) }

        assertEquals(evaluator.evaluate(), 0)
    }

    @Test
    fun `test eval3`() {
        val tokens1 = Tokenizer(StringSource(testExpression3)).tokenize()

        tokens1.forEach { it.accept(parser) }
        val reversedPolishNotation = parser.getReversedPolishNotation()

        reversedPolishNotation.forEach { it.accept(evaluator) }

        assertEquals(evaluator.evaluate(), 0)
    }
}