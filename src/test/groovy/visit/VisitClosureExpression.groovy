package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitClosureExpressionNoneLevel() {
    Closure c = {
        System.out.println("z")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitClosureExpressionMethodErrorLevel() {
    Closure c = {
        System.out.println("z")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitClosureExpressionMethodLevel() {
    Closure c = {
        System.out.println("z")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitClosureExpressionStatementLevel() {
    Closure c = {
        System.out.println("z")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitClosureExpressionExpressionLevel() {
    Closure c = {
        System.out.println("z")
    }
}
