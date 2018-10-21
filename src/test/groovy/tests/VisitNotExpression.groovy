package groovy.tests

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitNotExpressionNoneLevel() {
    if (!true) false
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitNotExpressionMethodErrorLevel() {
    if (!true) false
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitNotExpressionMethodLevel() {
    if (!true) false
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitNotExpressionStatementLevel() {
    if (!true) false
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitNotExpressionExpressionLevel() {
    if (!true) false
}
