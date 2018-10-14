package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitBitwiseNegationExpressionNoneLevel() {
    //todo: unimplemented
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitBitwiseNegationExpressionMethodErrorLevel() {
    //todo: unimplemented
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitBitwiseNegationExpressionMethodLevel() {
    //todo: unimplemented
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitBitwiseNegationExpressionStatementLevel() {
    //todo: unimplemented
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitBitwiseNegationExpressionExpressionLevel() {
    (Integer) "1"
}
