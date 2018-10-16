package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitPropertyExpressionNoneLevel() {
    System.out
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitPropertyExpressionMethodErrorLevel() {
    System.out
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitPropertyExpressionMethodLevel() {
    System.out
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitPropertyExpressionStatementLevel() {
    System.out
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitPropertyExpressionExpressionLevel() {
    System.out
}
