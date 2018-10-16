package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitClassExpressionNoneLevel() {
    System.getProperty("z")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitClassExpressionMethodErrorLevel() {
    System.getProperty("z")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitClassExpressionMethodLevel() {
    System.getProperty("z")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitClassExpressionStatementLevel() {
    System.getProperty("z")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitClassExpressionExpressionLevel() {
    System.getProperty("z")
}
