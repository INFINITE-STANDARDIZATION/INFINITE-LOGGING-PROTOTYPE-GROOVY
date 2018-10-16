package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitCastExpressionNoneLevel() {
    (Integer) "1"
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitCastExpressionMethodErrorLevel() {
    (Integer) "1"
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitCastExpressionMethodLevel() {
    (Integer) "1"
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitCastExpressionStatementLevel() {
    (Integer) "1"
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitCastExpressionExpressionLevel() {
    (Integer) "1"
}