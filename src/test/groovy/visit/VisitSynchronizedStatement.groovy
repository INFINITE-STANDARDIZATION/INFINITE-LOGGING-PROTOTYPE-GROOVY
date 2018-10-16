package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitSynchronizedStatementNoneLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitSynchronizedStatementMethodErrorLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitSynchronizedStatementMethodLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitSynchronizedStatementStatementLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitSynchronizedStatementExpressionLevel() {
    Object object = new Object()
    synchronized (object) {
        object = new Object()
        System.out.println(object)
    }
}