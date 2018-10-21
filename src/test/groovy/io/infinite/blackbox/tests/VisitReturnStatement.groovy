package groovy.io.infinite.blackbox.tests

import groovy.io.infinite.blackbox.BlackBox
import groovy.io.infinite.blackbox.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
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

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
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

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
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

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
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

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
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
