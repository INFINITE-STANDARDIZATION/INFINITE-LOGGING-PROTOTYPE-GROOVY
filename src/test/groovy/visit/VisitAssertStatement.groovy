package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitAssertStatementNoneLevel() {
    assert 1==1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitAssertStatementMethodErrorLevel() {
    assert 1==1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitAssertStatementMethodLevel() {
    assert 1==1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitAssertStatementStatementLevel() {
    assert 1==1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitAssertStatementExpressionLevel() {
    assert 1==1
}