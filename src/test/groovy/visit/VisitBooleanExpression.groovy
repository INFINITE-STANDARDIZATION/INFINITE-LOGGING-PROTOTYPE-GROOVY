package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitBooleanExpressionNoneLevel() {
    if (true) false
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitBooleanExpressionMethodErrorLevel() {
    if (true) false
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitBooleanExpressionMethodLevel() {
    if (true) false
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitBooleanExpressionStatementLevel() {
    if (true) false
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitBooleanExpressionExpressionLevel() {
    if (true) false
}