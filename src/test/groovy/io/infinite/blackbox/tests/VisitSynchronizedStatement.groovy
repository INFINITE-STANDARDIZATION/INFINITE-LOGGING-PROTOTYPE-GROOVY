package groovy.io.infinite.blackbox.tests

import groovy.io.infinite.blackbox.BlackBox
import groovy.io.infinite.blackbox.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitSynchronizedStatementNoneLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitSynchronizedStatementMethodErrorLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitSynchronizedStatementMethodLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitSynchronizedStatementStatementLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitSynchronizedStatementExpressionLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}