package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitSpreadExpressionNoneLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitSpreadExpressionMethodErrorLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitSpreadExpressionMethodLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitSpreadExpressionStatementLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitSpreadExpressionExpressionLevel() {
    def map = [name: 'mrhaki', blog: true]
    assert [name: 'mrhaki', blog: true, subject: 'Groovy Goodness'] == [subject: 'Groovy Goodness', *:map]
}
