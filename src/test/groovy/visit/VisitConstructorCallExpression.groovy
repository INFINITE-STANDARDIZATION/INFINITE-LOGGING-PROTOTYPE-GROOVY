package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitConstructorCallExpressionNoneLevel() {
    Object object = new Object()
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitConstructorCallExpressionMethodErrorLevel() {
    Object object = new Object()
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitConstructorCallExpressionMethodLevel() {
    Object object = new Object()
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitConstructorCallExpressionStatementLevel() {
    Object object = new Object()
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitConstructorCallExpressionExpressionLevel() {
    Object object = new Object()
}
