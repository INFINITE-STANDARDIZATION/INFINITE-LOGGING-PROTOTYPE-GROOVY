package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitIfElseNoneLevel() {
    if (true) {
        if (false) {
            System.out.println("Test")
        } else {
            System.out.println("Test")
        }
    } else {
        System.out.println("Test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitIfElseMethodErrorLevel() {
    if (true) {
        if (false) {
            System.out.println("Test")
        } else {
            System.out.println("Test")
        }
    } else {
        System.out.println("Test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitIfElseMethodLevel() {
    if (true) {
        if (false) {
            System.out.println("Test")
        } else {
            System.out.println("Test")
        }
    } else {
        System.out.println("Test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitIfElseStatementLevel() {
    if (true) {
        if (false) {
            System.out.println("Test")
        } else {
            System.out.println("Test")
        }
    } else {
        System.out.println("Test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitIfElseExpressionLevel() {
    if (true) {
        if (false) {
            System.out.println("Test")
        } else {
            System.out.println("Test")
        }
    } else {
        System.out.println("Test")
    }
}