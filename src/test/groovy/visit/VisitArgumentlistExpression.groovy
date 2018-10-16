package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitArgumentlistExpressionNoneLevel() {
    tst(1,2,3)
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitArgumentlistExpressionMethodErrorLevel() {
    tst(1,2,3)
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitArgumentlistExpressionMethodLevel() {
    tst(1,2,3)
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitArgumentlistExpressionStatementLevel() {
    tst(1,2,3)
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitArgumentlistExpressionExpressionLevel() {
    tst(1,2,3)
}

void tst(def a, def b, def c) {
    System.out.println(a+b+c)
}