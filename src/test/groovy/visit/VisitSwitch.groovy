package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitSwitchNoneLevel() {
    switch (1) {
        case 1:
            System.out.println("test")
            System.out.println("test")
            break
        case 2:
            System.out.println("test")
            System.out.println("test")
            break
        default:
            System.out.println("test")
            System.out.println("test")
            break
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitSwitchMethodErrorLevel() {
    switch (1) {
        case 1:
            System.out.println("test")
            System.out.println("test")
            break
        case 2:
            System.out.println("test")
            System.out.println("test")
            break
        default:
            System.out.println("test")
            System.out.println("test")
            break
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitSwitchMethodLevel() {
    switch (1) {
        case 1:
            System.out.println("test")
            System.out.println("test")
            break
        case 2:
            System.out.println("test")
            System.out.println("test")
            break
        default:
            System.out.println("test")
            System.out.println("test")
            break
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitSwitchStatementLevel() {
    switch (1) {
        case 1:
            System.out.println("test")
            System.out.println("test")
            break
        case 2:
            System.out.println("test")
            System.out.println("test")
            break
        default:
            System.out.println("test")
            System.out.println("test")
            break
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitSwitchExpressionLevel() {
    switch (1) {
        case 1:
            System.out.println("test")
            System.out.println("test")
            break
        case 2:
            System.out.println("test")
            System.out.println("test")
            break
        default:
            System.out.println("test")
            System.out.println("test")
            break
    }
}