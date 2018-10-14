package groovy

import groovy.inspect.swingui.AstNodeToScriptVisitor
import org.codehaus.groovy.ast.expr.EmptyExpression
import org.codehaus.groovy.ast.tools.GeneralUtils

class SandBox {

    static void main(String[] args) {
        //Date q = new SandBox().someMethod(new Date())
        new SandBox().visitClosureExpressionExpressionLevel()
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

    void w() {

    }

}
