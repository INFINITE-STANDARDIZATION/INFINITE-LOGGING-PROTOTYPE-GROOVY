package groovy.tests

import groovy.BlackBox
import groovy.BlackBoxLevel

@BlackBox(blackBoxLevel = BlackBoxLevel.NONE)
void visitGStringExpressionNoneLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD_ERROR)
void visitGStringExpressionMethodErrorLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

@BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
void visitGStringExpressionMethodLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
void visitGStringExpressionStatementLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
void visitGStringExpressionExpressionLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}