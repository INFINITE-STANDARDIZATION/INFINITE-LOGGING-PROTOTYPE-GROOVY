package groovy.tests

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitVariableExpressionNoneLevel() {
    def z = 1
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitVariableExpressionMethodErrorLevel() {
    def z = 1
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitVariableExpressionMethodLevel() {
    def z = 1
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitVariableExpressionStatementLevel() {
    def z = 1
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitVariableExpressionExpressionLevel() {
    def z = 1
}
