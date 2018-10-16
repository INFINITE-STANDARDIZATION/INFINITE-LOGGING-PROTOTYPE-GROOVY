package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitMethodPointerExpressionNoneLevel() {
    this.&tst(1,2,3)
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitMethodPointerExpressionMethodErrorLevel() {
    this.&tst(1,2,3)
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitMethodPointerExpressionMethodLevel() {
    this.&tst(1,2,3)
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitMethodPointerExpressionStatementLevel() {
    this.&tst(1,2,3)
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitMethodPointerExpressionExpressionLevel() {
    this.&tst(1,2,3)
}


void tst(def a, def b, def c) {
    System.out.println(a+b+c)
}