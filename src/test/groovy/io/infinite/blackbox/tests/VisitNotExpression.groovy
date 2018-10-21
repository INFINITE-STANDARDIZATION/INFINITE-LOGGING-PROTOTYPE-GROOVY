package groovy.io.infinite.blackbox.tests

import groovy.io.infinite.blackbox.BlackBox
import groovy.io.infinite.blackbox.BlackBoxLevel

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
