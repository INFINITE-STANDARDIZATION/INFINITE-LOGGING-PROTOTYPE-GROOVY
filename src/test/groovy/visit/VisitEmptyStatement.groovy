package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitEmptyStatementNoneLevel() {
    //skipped
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitEmptyStatementMethodErrorLevel() {
    //skipped
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitEmptyStatementMethodLevel() {
    //skipped
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitEmptyStatementStatementLevel() {
    //skipped
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitEmptyStatementExpressionLevel() {
    //skipped
}