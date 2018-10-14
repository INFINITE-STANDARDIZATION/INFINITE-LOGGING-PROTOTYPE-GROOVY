package groovy

import groovy.inspect.swingui.AstNodeToScriptVisitor
import org.codehaus.groovy.ast.expr.EmptyExpression
import org.codehaus.groovy.ast.tools.GeneralUtils

class SandBox implements Runnable{

    static void main2(String[] args) {
        //Date q = new SandBox().someMethod(new Date())
        //new SandBox().visitClosureExpressionExpressionLevel()
        new SandBox().visitBinaryExpressionExpressionLevel()
    }

    //@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    Date someMethod(Date iDate) {
        System.out.println(iDate)
        return iDate
    }

    //@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitClosureExpressionExpressionLevel() {
        Closure c = {
            System.out.println("z")
            return 2
        }
        c.call()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitBinaryExpressionExpressionLevel() {
        Integer a = 2
        Integer b = 3
        if (a<b) {
            a=b
        }
    }

    @Override
    void run() {
        new SandBox().visitBinaryExpressionExpressionLevel()
    }
}
