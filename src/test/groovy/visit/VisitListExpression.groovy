package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitListExpressionNoneLevel() {
    def (int a, int b) = [1,2]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitListExpressionMethodErrorLevel() {
    def (int a, int b) = [1,2]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitListExpressionMethodLevel() {
    def (int a, int b) = [1,2]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitListExpressionStatementLevel() {
    def (int a, int b) = [1,2]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitListExpressionExpressionLevel() {
    def strArray = new String[3]
}
