package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitDeclarationExpressionNoneLevel() {
    def z = 1
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitDeclarationExpressionMethodErrorLevel() {
    def z = 1
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitDeclarationExpressionMethodLevel() {
    def z = 1
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitDeclarationExpressionStatementLevel() {
    def z = 1
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitDeclarationExpressionExpressionLevel() {
    def z = 1
}
