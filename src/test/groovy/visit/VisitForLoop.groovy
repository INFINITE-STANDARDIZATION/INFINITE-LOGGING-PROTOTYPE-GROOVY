package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitForLoopNoneLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitForLoopMethodErrorLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitForLoopMethodLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitForLoopStatementLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitForLoopExpressionLevel() {
    for (z in [1,2,3,4]) {
        System.out.println("Test")
    }
}
