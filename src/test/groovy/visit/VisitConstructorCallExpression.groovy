package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitConstructorCallExpressionNoneLevel() {
    Object object = new Object()
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitConstructorCallExpressionMethodErrorLevel() {
    Object object = new Object()
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitConstructorCallExpressionMethodLevel() {
    Object object = new Object()
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitConstructorCallExpressionStatementLevel() {
    Object object = new Object()
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitConstructorCallExpressionExpressionLevel() {
    Object object = new Object()
}
