package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitStaticMethodCallExpressionNoneLevel() {
    System.getProperty("test")
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitStaticMethodCallExpressionMethodErrorLevel() {
    System.getProperty("test")
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitStaticMethodCallExpressionMethodLevel() {
    System.getProperty("test")
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitStaticMethodCallExpressionStatementLevel() {
    System.getProperty("test")
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitStaticMethodCallExpressionExpressionLevel() {
    System.getProperty("test")
}