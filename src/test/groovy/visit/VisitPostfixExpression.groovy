package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitPostfixExpressionNoneLevel() {
    int z = 1
    z++
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitPostfixExpressionMethodErrorLevel() {
    int z = 1
    z++
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitPostfixExpressionMethodLevel() {
    int z = 1
    z++
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitPostfixExpressionStatementLevel() {
    int z = 1
    z++
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitPostfixExpressionExpressionLevel() {
    int z = 1
    z++
}