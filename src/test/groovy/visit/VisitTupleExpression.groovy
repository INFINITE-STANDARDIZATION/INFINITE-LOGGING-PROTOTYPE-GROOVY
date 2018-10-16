package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitTupleExpressionNoneLevel() {
    def (int a, int b) = [1,2]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitTupleExpressionMethodErrorLevel() {
    def (int a, int b) = [1,2]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitTupleExpressionMethodLevel() {
    def (int a, int b) = [1,2]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitTupleExpressionStatementLevel() {
    def (int a, int b) = [1,2]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitTupleExpressionExpressionLevel() {
    def (int a, int b) = [1,2]
}