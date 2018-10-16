package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel


//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitWhileLoopNoneLevel() {
    int z = 0
    while (z < 3) {
        System.out.println("Test")
        z++
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitWhileLoopMethodErrorLevel() {
    int z = 0
    while (z < 3) {
        System.out.println("Test")
        z++
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitWhileLoopMethodLevel() {
    int z = 0
    while (z < 3) {
        System.out.println("Test")
        z++
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitWhileLoopStatementLevel() {
    int z = 0
    while (z < 3) {
        System.out.println("Test")
        z++
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitWhileLoopExpressionLevel() {
    int z = 0
    while (z < 3) {
        System.out.println("Test")
        z++
    }
}