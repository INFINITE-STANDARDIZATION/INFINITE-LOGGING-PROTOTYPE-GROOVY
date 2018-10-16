package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitNotExpressionNoneLevel() {
    if (!true) false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitNotExpressionMethodErrorLevel() {
    if (!true) false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitNotExpressionMethodLevel() {
    if (!true) false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitNotExpressionStatementLevel() {
    if (!true) false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitNotExpressionExpressionLevel() {
    if (!true) false
}
