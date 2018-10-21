package groovy.io.infinite.blackbox.tests

import groovy.io.infinite.blackbox.BlackBox
import groovy.io.infinite.blackbox.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitShortTernaryExpressionNoneLevel() {
    def location = true ?: "z"
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitShortTernaryExpressionMethodErrorLevel() {
    def location = true ?: "z"
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitShortTernaryExpressionMethodLevel() {
    def location = true ?: "z"
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitShortTernaryExpressionStatementLevel() {
    def location = true ?: "z"
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitShortTernaryExpressionExpressionLevel() {
    def location = true ?: "z"
}
