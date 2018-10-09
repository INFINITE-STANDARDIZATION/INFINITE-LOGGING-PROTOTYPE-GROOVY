package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitForLoopNoneLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitForLoopMethodErrorLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitForLoopMethodLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitForLoopStatementLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitForLoopExpressionLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}
