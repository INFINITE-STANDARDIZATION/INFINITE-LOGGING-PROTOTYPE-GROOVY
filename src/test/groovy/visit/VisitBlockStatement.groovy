package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitBlockStatementNoneLevel() {
    System.out.println("Test")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitBlockStatementMethodErrorLevel() {
    System.out.println("Test")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitBlockStatementMethodLevel() {
    System.out.println("Test")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitBlockStatementStatementLevel() {
    System.out.println("Test")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitBlockStatementExpressionLevel() {
    System.out.println("Test")
}