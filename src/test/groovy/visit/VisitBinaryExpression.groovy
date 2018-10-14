package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitBinaryExpressionNoneLevel() {
    Object object = new Object()
    Object object2 = new Object()
    object = object2
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitBinaryExpressionMethodErrorLevel() {
    Object object = new Object()
    Object object2 = new Object()
    object = object2
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitBinaryExpressionMethodLevel() {
    Object object = new Object()
    Object object2 = new Object()
    object = object2
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitBinaryExpressionStatementLevel() {
    Object object = new Object()
    Object object2 = new Object()
    object = object2
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitBinaryExpressionExpressionLevel() {
    Object object = new Object()
    Object object2 = new Object()
    object = object2
}
