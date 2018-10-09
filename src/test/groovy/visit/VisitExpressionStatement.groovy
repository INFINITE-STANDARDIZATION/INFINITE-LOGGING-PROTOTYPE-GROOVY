package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitExpressionStatementNoneLevel() {
    int z
    z = z
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitExpressionStatementMethodErrorLevel() {
    int z
    z = z
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitExpressionStatementMethodLevel() {
    int z
    z = z
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitExpressionStatementStatementLevel() {
    int z
    z = z
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitExpressionStatementExpressionLevel() {
    int z
    z = z
}