package groovy

import groovy.inspect.swingui.AstNodeToScriptVisitor
import org.codehaus.groovy.ast.expr.EmptyExpression
import org.codehaus.groovy.ast.tools.GeneralUtils

class SandBox {

    static void main(String[] args) {
        //Date q = new SandBox().someMethod(new Date())
        //new SandBox().visitClosureExpressionExpressionLevel()
        new SandBox().visitBinaryExpressionExpressionLevel()
    }

    //@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    Date someMethod(Date iDate) {
        System.out.println(iDate)
        return iDate
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitClosureExpressionExpressionLevel() {
        Closure c = {
            System.out.println("z")
            return 2
        }
        c.call()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitBinaryExpressionExpressionLevel() {
        Object object = new Object()
        Object object2 = new Object()
        object = object2
    }
/*
    @Override
    void run() {
        new SandBox().visitBinaryExpressionExpressionLevel()
    }*/
}
