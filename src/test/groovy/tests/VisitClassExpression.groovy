package groovy.tests

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitClassExpressionNoneLevel() {
    System.getProperty("z")
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitClassExpressionMethodErrorLevel() {
    System.getProperty("z")
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitClassExpressionMethodLevel() {
    System.getProperty("z")
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitClassExpressionStatementLevel() {
    System.getProperty("z")
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitClassExpressionExpressionLevel() {
    System.getProperty("z")
}
