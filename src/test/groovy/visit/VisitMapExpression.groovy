package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitMapExpressionNoneLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitMapExpressionMethodErrorLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitMapExpressionMethodLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitMapExpressionStatementLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitMapExpressionExpressionLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitMapEntryExpressionNoneLevel() {
    def map = ["abcd": 1234, "tdgf": 55436]
}