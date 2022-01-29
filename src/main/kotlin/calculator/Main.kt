package calculator

import ParserVisitor
import calculator.tokenizer.Tokenizer
import calculator.tokenizer.source.StringSource
import calculator.visitor.EvaluateVisitor
import calculator.visitor.PrintVisitor

fun main() {
    try {
        println("Write arithmetic expression on 1 line:")
        val sourceLine = readLine()!!
        val stringSource = StringSource(sourceLine)
        val tokens = Tokenizer(stringSource).tokenize()

        val printVisitor = PrintVisitor()
        print("Formatted expression: ")
        tokens.forEach { it.accept(printVisitor) }
        println()

        val parserVisitor = ParserVisitor()
        tokens.forEach { it.accept(parserVisitor) }
        val reversedPolishNotation = parserVisitor.getReversedPolishNotation()

        print("Formatted expression: ")
        reversedPolishNotation.forEach { it.accept(printVisitor) }
        println()


        val evalVisitor = EvaluateVisitor()
        reversedPolishNotation.forEach { it.accept(evalVisitor) }
        println("Result: ${evalVisitor.evaluate()}")
    } catch (e: Error) {
        println(e.message)
    }
}