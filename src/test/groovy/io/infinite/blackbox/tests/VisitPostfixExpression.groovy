package groovy.io.infinite.blackbox.tests

import groovy.io.infinite.blackbox.BlackBox
import groovy.io.infinite.blackbox.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitPostfixExpressionNoneLevel() {
    int z = 1
    z++
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitPostfixExpressionMethodErrorLevel() {
    int z = 1
    z++
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitPostfixExpressionMethodLevel() {
    int z = 1
    z++
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitPostfixExpressionStatementLevel() {
    int z = 1
    z++
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitPostfixExpressionExpressionLevel() {
    int z = 1
    z++
}