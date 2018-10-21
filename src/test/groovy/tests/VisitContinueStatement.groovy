package groovy.tests

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitContinueStatementNoneLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
        if (true) {
            continue
        }
        System.out.println("Test")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitContinueStatementMethodErrorLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
        if (true) {
            continue
        }
        System.out.println("Test")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitContinueStatementMethodLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
        if (true) {
            continue
        }
        System.out.println("Test")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitContinueStatementStatementLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
        if (true) {
            continue
        }
        System.out.println("Test")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitContinueStatementExpressionLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
        if (true) {
            continue
        }
        System.out.println("Test")
    }
}
