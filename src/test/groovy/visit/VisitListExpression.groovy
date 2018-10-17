package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitListExpressionNoneLevel() {
    def (int a, int b) = [1,2]
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitListExpressionMethodErrorLevel() {
    def (int a, int b) = [1,2]
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitListExpressionMethodLevel() {
    def (int a, int b) = [1,2]
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitListExpressionStatementLevel() {
    def (int a, int b) = [1,2]
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitListExpressionExpressionLevel() {
    def strArray = new String[3]
}
