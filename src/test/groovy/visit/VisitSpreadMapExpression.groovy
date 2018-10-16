package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitSpreadMapExpressionNoneLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitSpreadMapExpressionMethodErrorLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitSpreadMapExpressionMethodLevel() {
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitSpreadMapExpressionStatementLevel() {

    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitSpreadMapExpressionExpressionLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}