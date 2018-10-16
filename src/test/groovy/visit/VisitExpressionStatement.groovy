package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitExpressionStatementNoneLevel() {
    int z
    z = z
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitExpressionStatementMethodErrorLevel() {
    int z
    z = z
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitExpressionStatementMethodLevel() {
    int z
    z = z
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitExpressionStatementStatementLevel() {
    int z
    z = z
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitExpressionStatementExpressionLevel() {
    int z
    z = z
}