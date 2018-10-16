package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitBooleanExpressionNoneLevel() {
    if (true) false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitBooleanExpressionMethodErrorLevel() {
    if (true) false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitBooleanExpressionMethodLevel() {
    if (true) false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitBooleanExpressionStatementLevel() {
    if (true) false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitBooleanExpressionExpressionLevel() {
    if (true) false
}