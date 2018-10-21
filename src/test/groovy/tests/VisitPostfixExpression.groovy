package groovy.tests

import groovy.BlackBox
import groovy.BlackBoxLevel

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