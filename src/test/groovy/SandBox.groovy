package groovy

import groovy.inspect.swingui.AstNodeToScriptVisitor
import org.codehaus.groovy.ast.expr.EmptyExpression
import org.codehaus.groovy.ast.tools.GeneralUtils

class SandBox {

    static void main(String[] args) {
        new SandBox().visitArgumentlistExpressionExpressionLevel(1,2,3)
    }


    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitArgumentlistExpressionExpressionLevel(def a, def b, def c) {
        tst(a,b,c)
        1/0
    }

    void tst(def a, def b, def c) {
        System.out.println(a+b+c)
    }

    void z() {
    }
}
