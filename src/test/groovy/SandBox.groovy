package groovy

import groovy.inspect.swingui.AstNodeToScriptVisitor
import org.codehaus.groovy.ast.expr.EmptyExpression
import org.codehaus.groovy.ast.tools.GeneralUtils

class SandBox {

    static void main(String[] args) {
        new SandBox().someMethod()
    }

    //@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void someMethod() {
        StringWriter stringWriter = new StringWriter()
        GeneralUtils.declS(GeneralUtils.varX("someVariableName"), new EmptyExpression()).visit(new AstNodeToScriptVisitor(stringWriter))
        System.out.println("Output: "+stringWriter.getBuffer().toString().replace("\$", ""))
    }

}
