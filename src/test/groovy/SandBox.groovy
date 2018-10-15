package groovy

import groovy.inspect.swingui.AstNodeToScriptVisitor
import org.codehaus.groovy.ast.expr.EmptyExpression
import org.codehaus.groovy.ast.tools.GeneralUtils

class SandBox implements Runnable {

    static void main2(String[] args) {
        new SandBox().visitArgumentlistExpressionExpressionLevel()
    }


    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitArgumentlistExpressionExpressionLevel() {
        tst(1,2,3)
    }

    void tst(def a, def b, def c) {
        System.out.println(a+b+c)
    }


    void z() {
        groovy.BlackBoxEngine automaticBlackBox = new groovy.BlackBoxEngine().getInstance()
        automaticBlackBox.methodExecutionOpen('SandBox', 'groovy', 'visitArgumentlistExpressionExpressionLevel', automaticBlackBox .NOARGSMAP, 5, 6, 14, 17)
        try {
            automaticBlackBox.expressionEvaluation('MethodCallExpression', 'this.tst(1, 2, 3)', 9, 19, 16, 16, {
                return this.tst(automaticBlackBox.expressionEvaluation('MethodCallExpression', null, 13, 14, 16, 16, {
                    return automaticBlackBox.expressionEvaluation('ConstantExpression', '1', 13, 14, 16, 16, {
                        return 1
                    }, 'iTupleExpression.getExpressions()')
                }, 'iArgumentListExpression.getExpressions()'), automaticBlackBox.expressionEvaluation('MethodCallExpression', null, 15, 16, 16, 16, {
                    return automaticBlackBox.expressionEvaluation('ConstantExpression', '2', 15, 16, 16, 16, {
                        return 2
                    }, 'iTupleExpression.getExpressions()')
                }, 'iArgumentListExpression.getExpressions()'), automaticBlackBox.expressionEvaluation('MethodCallExpression', null, 17, 18, 16, 16, {
                    return automaticBlackBox.expressionEvaluation('ConstantExpression', '3', 17, 18, 16, 16, {
                        return 3
                    }, 'iTupleExpression.getExpressions()')
                }, 'iArgumentListExpression.getExpressions()'))
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

    @Override
    void run() {
        visitArgumentlistExpressionExpressionLevel()
    }
}
