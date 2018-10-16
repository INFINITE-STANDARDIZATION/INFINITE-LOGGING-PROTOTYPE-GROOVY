package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitMethodCallExpressionNoneLevel() {
    System.out.println("test")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitMethodCallExpressionMethodErrorLevel() {
    System.out.println("test")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitMethodCallExpressionMethodLevel() {
    System.out.println("test")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitMethodCallExpressionStatementLevel() {
    System.out.println("test")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitMethodCallExpressionExpressionLevel() {
    System.out.println("test")
}