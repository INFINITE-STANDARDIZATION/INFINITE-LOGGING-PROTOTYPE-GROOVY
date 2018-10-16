package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitMapEntryExpressionNoneLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitMapEntryExpressionMethodErrorLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitMapEntryExpressionMethodLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitMapEntryExpressionStatementLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitMapEntryExpressionExpressionLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}
