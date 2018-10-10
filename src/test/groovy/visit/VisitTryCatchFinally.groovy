package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
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

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
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

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
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

//@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
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

//@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
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
