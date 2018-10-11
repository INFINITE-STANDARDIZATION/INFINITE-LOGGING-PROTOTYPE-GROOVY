package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitCastExpressionNoneLevel() {
    (Integer) "1"
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitCastExpressionMethodErrorLevel() {
    (Integer) "1"
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitCastExpressionMethodLevel() {
    (Integer) "1"
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitCastExpressionStatementLevel() {
    (Integer) "1"
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitCastExpressionExpressionLevel() {
    (Integer) "1"
}