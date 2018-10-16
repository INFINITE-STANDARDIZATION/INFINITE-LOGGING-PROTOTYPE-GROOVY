package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitPrefixExpressionNoneLevel() {
    int z = 1
    ++z
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitPrefixExpressionMethodErrorLevel() {
    int z = 1
    ++z
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitPrefixExpressionMethodLevel() {
    int z = 1
    ++z
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitPrefixExpressionStatementLevel() {
    int z = 1
    ++z
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitPrefixExpressionExpressionLevel() {
    int z = 1
    ++z
}
