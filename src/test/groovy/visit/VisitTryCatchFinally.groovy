package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitTryCatchFinallyNoneLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitTryCatchFinallyMethodErrorLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitTryCatchFinallyMethodLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitTryCatchFinallyStatementLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitTryCatchFinallyExpressionLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}
