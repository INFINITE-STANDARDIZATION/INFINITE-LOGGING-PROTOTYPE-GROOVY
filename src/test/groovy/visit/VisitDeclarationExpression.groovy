package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitDeclarationExpressionNoneLevel() {
    def z = 1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitDeclarationExpressionMethodErrorLevel() {
    def z = 1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitDeclarationExpressionMethodLevel() {
    def z = 1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitDeclarationExpressionStatementLevel() {
    def z = 1
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitDeclarationExpressionExpressionLevel() {
    def z = 1
}
