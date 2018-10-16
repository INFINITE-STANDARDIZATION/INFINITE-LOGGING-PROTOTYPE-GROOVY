package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitThrowStatementNoneLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
        throw new Exception("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitThrowStatementMethodErrorLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
        throw new Exception("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitThrowStatementMethodLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
        throw new Exception("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitThrowStatementStatementLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
        throw new Exception("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitThrowStatementExpressionLevel() {
    try {
        System.out.println("test")
        System.out.println("test")
        throw new Exception("test")
    } catch (Throwable throwable) {
        System.out.println("test")
        System.out.println("test")
    } finally {
        System.out.println("test")
        System.out.println("test")
    }
}