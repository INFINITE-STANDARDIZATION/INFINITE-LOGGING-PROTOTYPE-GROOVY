package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitVariableExpressionNoneLevel() {
    def z = 1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitVariableExpressionMethodErrorLevel() {
    def z = 1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitVariableExpressionMethodLevel() {
    def z = 1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitVariableExpressionStatementLevel() {
    def z = 1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitVariableExpressionExpressionLevel() {
    def z = 1
}
