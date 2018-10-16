package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitTernaryExpressionNoneLevel() {
    true?true:false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitTernaryExpressionMethodErrorLevel() {
    true?true:false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitTernaryExpressionMethodLevel() {
    true?true:false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitTernaryExpressionStatementLevel() {
    true?true:false
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitTernaryExpressionExpressionLevel() {
    true?true:false
}
