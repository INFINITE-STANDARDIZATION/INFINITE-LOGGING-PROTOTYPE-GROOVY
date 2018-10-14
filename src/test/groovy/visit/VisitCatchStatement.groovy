package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitCatchStatementNoneLevel() {
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
void visitCatchStatementMethodErrorLevel() {
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
void visitCatchStatementMethodLevel() {
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
void visitCatchStatementStatementLevel() {
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
void visitCatchStatementExpressionLevel() {
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
