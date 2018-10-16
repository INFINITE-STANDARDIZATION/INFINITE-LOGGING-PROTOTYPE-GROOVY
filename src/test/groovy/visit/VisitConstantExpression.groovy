package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitConstantExpressionNoneLevel() {
    System.getProperty("z")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitConstantExpressionMethodErrorLevel() {
    System.getProperty("z")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitConstantExpressionMethodLevel() {
    System.getProperty("z")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitConstantExpressionStatementLevel() {
    System.getProperty("z")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitConstantExpressionExpressionLevel() {
    System.getProperty("z")
}