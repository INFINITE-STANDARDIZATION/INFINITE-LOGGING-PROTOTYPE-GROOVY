package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitArrayExpressionNoneLevel() {
    def strArray = new String[3]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitArrayExpressionMethodErrorLevel() {
    def strArray = new String[3]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitArrayExpressionMethodLevel() {
    def strArray = new String[3]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitArrayExpressionStatementLevel() {
    def strArray = new String[3]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitArrayExpressionExpressionLevel() {
    def strArray = new String[3]
}