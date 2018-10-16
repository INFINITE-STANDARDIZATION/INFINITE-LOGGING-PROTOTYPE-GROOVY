package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
String visitReturnStatementNoneLevel() {
    if (true) {
        if (false) {
            return "Test"
        } else {
            return "Test"
        }
    } else {
        return "Test"
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
String visitReturnStatementMethodErrorLevel() {
    if (true) {
        if (false) {
            return "Test"
        } else {
            return "Test"
        }
    } else {
        return "Test"
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
String visitReturnStatementMethodLevel() {
    if (true) {
        if (false) {
            return "Test"
        } else {
            return "Test"
        }
    } else {
        return "Test"
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
String visitReturnStatementStatementLevel() {
    if (true) {
        if (false) {
            return "Test"
        } else {
            return "Test"
        }
    } else {
        return "Test"
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
String visitReturnStatementExpressionLevel() {
    if (true) {
        if (false) {
            return "Test"
        } else {
            return "Test"
        }
    } else {
        return "Test"
    }
}