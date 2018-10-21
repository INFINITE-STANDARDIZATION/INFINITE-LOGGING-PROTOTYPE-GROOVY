package groovy.io.infinite.blackbox.tests

import groovy.io.infinite.blackbox.BlackBox
import groovy.io.infinite.blackbox.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitTernaryExpressionNoneLevel() {
    true?true:false
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitTernaryExpressionMethodErrorLevel() {
    true?true:false
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitTernaryExpressionMethodLevel() {
    true?true:false
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitTernaryExpressionStatementLevel() {
    true?true:false
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitTernaryExpressionExpressionLevel() {
    true?true:false
}
