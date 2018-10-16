package groovy.visit

import groovy.BlackBox
import groovy.BlackBoxLevel

//@BlackBox(blackBoxMode = BlackBoxLevel.NONE)
void visitGStringExpressionNoneLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD_ERROR)
void visitGStringExpressionMethodErrorLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.METHOD)
void visitGStringExpressionMethodLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.STATEMENT)
void visitGStringExpressionStatementLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}

//@BlackBox(blackBoxMode = BlackBoxLevel.EXPRESSION)
void visitGStringExpressionExpressionLevel() {
    System.out.println("test gstring ${new Date()} this is test ${Integer.valueOf("123").toString()}")
}