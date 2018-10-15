package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitRangeExpressionNoneLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitRangeExpressionMethodErrorLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitRangeExpressionMethodLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitRangeExpressionStatementLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

//@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitRangeExpressionExpressionLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}
