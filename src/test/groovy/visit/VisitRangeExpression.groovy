package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitRangeExpressionNoneLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitRangeExpressionMethodErrorLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitRangeExpressionMethodLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitRangeExpressionStatementLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitRangeExpressionExpressionLevel() {
    for (i in 1..3) {
        System.out.println(i)
    }
}
