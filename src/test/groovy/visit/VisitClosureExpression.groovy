package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitClosureExpressionNoneLevel() {
    Closure c = {
        System.out.println("z")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitClosureExpressionMethodErrorLevel() {
    Closure c = {
        System.out.println("z")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitClosureExpressionMethodLevel() {
    Closure c = {
        System.out.println("z")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitClosureExpressionStatementLevel() {
    Closure c = {
        System.out.println("z")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitClosureExpressionExpressionLevel() {
    Closure c = {
        System.out.println("z")
    }
}
