package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitShortTernaryExpressionNoneLevel() {
    def location = true ?: "z"
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitShortTernaryExpressionMethodErrorLevel() {
    def location = true ?: "z"
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitShortTernaryExpressionMethodLevel() {
    def location = true ?: "z"
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitShortTernaryExpressionStatementLevel() {
    def location = true ?: "z"
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitShortTernaryExpressionExpressionLevel() {
    def location = true ?: "z"
}
