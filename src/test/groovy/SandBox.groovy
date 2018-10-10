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
        groovy.BlackBoxEngine automaticBlackBox = new groovy.BlackBoxEngine().getInstance()
        automaticBlackBox.methodExecutionOpen('VisitStaticMethodCallExpression', 'groovy.visit', 'visitStaticMethodCallExpressionExpressionLevel', automaticBlackBox .NOARGSMAP, 1, 2, 30, 33)
        try {
            automaticBlackBox.expressionEvaluation('StaticMethodCallExpression', 'groovy.visit.VisitStaticMethodCallExpression.tst(\'1\', \'2\', \'3\')', 5, 21, 32, 32, {
                return groovy.visit.VisitStaticMethodCallExpression.tst(automaticBlackBox.expressionEvaluation('ConstantExpression', '\'1\'', 9, 12, 32, 32, {
                    return '1'
                }, 'ArgumentListExpression:getExpressions()') as String, automaticBlackBox.expressionEvaluation('ConstantExpression', '\'2\'', 13, 16, 32, 32, {
                    return '2'
                }, 'ArgumentListExpression:getExpressions()') as String, automaticBlackBox.expressionEvaluation('ConstantExpression', '\'3\'', 17, 20, 32, 32, {
                    return '3'
                }, 'ArgumentListExpression:getExpressions()') as String)
            }, 'iExpressionStatement.getExpression()')
        }
        catch (java.lang.Throwable automaticThrowable) {
            automaticBlackBox.exception(automaticThrowable)
            throw automaticThrowable
        }
        finally {
            automaticBlackBox.executionClose()
        }
    }

}
