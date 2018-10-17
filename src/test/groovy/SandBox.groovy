package groovy

import groovy.inspect.swingui.AstNodeToScriptVisitor
import org.codehaus.groovy.ast.expr.EmptyExpression
import org.codehaus.groovy.ast.tools.GeneralUtils

class SandBox {

    static void main(String[] args) {
        //System.setProperty("blackBox.mode", BlackBoxMode.EMERGENCY.value())
        System.setProperty("blackBox.mode", BlackBoxMode.SEQUENTIAL.value())
        //System.setProperty("blackBox.mode", BlackBoxMode.HIERARCHICAL.value())
        //new SandBox().visitArgumentlistExpressionExpressionLevel(1,2,3)
        new SandBox().visitThrowStatementExpressionLevel()
    }


    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    String visitArgumentlistExpressionExpressionLevel(def a, def b, def c) {
        tst(a,b,c)
        //1/0
        return "zzzz"
    }

    void tst(def a, def b, def c) {
        System.out.println(a+b+c)
    }

    void z() {
        groovy.BlackBoxEngine automaticBlackBox = groovy.BlackBoxEngine.getInstance()
        automaticBlackBox.methodExecutionOpen('SandBox', 'groovy', 'visitThrowStatementExpressionLevel', 5, 6, 32, 45, [:])
        try {
            automaticBlackBox.statementExecutionOpen('TryCatchStatement', 'try {\n    java.lang.System.out.println(\'test1\')\n    java.lang.System.out.println(\'test2\')\n    throw new java.lang.Exception(\'test3\')\n} \ncatch (java.lang.Throwable throwable) {\n    java.lang.System.out.println(\'test4\')\n    java.lang.System.out.println(\'test5\')\n} \nfinally { \n    java.lang.System.out.println(\'test6\')\n    java.lang.System.out.println(\'test7\')\n} \n', 9, 10, 34, 44, 'BlockStatement:statements')
            try {
                automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'test1\')', 13, 40, 35, 35, {
                    return automaticBlackBox.expressionEvaluation('PropertyExpression', null, 13, 23, 35, 35, {
                        return automaticBlackBox.expressionEvaluation('ClassExpression', 'java.lang.System', 13, 19, 35, 35, {
                            return java.lang.System
                        }, 'PropertyExpression:objectExpression').automaticBlackBox.expressionEvaluation('ConstantExpression', '\'out\'', 20, 23, 35, 35, {
                            return 'out'
                        }, 'PropertyExpression:property')
                    }, 'MethodCallExpression:objectExpression').println(automaticBlackBox.expressionEvaluation('ConstantExpression', '\'test1\'', 32, 39, 35, 35, {
                        return 'test1'
                    }, 'ArgumentListExpression:expressions'))
                }, 'ExpressionStatement:expression')
                automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'test2\')', 13, 40, 36, 36, {
                    return automaticBlackBox.expressionEvaluation('PropertyExpression', null, 13, 23, 36, 36, {
                        return automaticBlackBox.expressionEvaluation('ClassExpression', 'java.lang.System', 13, 19, 36, 36, {
                            return java.lang.System
                        }, 'PropertyExpression:objectExpression').automaticBlackBox.expressionEvaluation('ConstantExpression', '\'out\'', 20, 23, 36, 36, {
                            return 'out'
                        }, 'PropertyExpression:property')
                    }, 'MethodCallExpression:objectExpression').println(automaticBlackBox.expressionEvaluation('ConstantExpression', '\'test2\'', 32, 39, 36, 36, {
                        return 'test2'
                    }, 'ArgumentListExpression:expressions'))
                }, 'ExpressionStatement:expression')
                automaticBlackBox.statementExecutionOpen('ThrowStatement', 'throw new java.lang.Exception(\'test3\')\n', 13, 41, 37, 37, 'BlockStatement:statements')
                throw automaticBlackBox.expressionEvaluation('ConstructorCallExpression', null, 19, 41, 37, 37, {
                    return new java.lang.Exception(automaticBlackBox.expressionEvaluation('ConstantExpression', '\'test3\'', 33, 40, 37, 37, {
                        return 'test3'
                    }, 'ArgumentListExpression:expressions'))
                }, 'ThrowStatement:expression')
                automaticBlackBox.executionClose()
            }
            catch (java.lang.Throwable throwable) {
                automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'test4\')', 13, 40, 39, 39, {
                    return automaticBlackBox.expressionEvaluation('PropertyExpression', null, 13, 23, 39, 39, {
                        return automaticBlackBox.expressionEvaluation('ClassExpression', 'java.lang.System', 13, 19, 39, 39, {
                            return java.lang.System
                        }, 'PropertyExpression:objectExpression').automaticBlackBox.expressionEvaluation('ConstantExpression', '\'out\'', 20, 23, 39, 39, {
                            return 'out'
                        }, 'PropertyExpression:property')
                    }, 'MethodCallExpression:objectExpression').println(automaticBlackBox.expressionEvaluation('ConstantExpression', '\'test4\'', 32, 39, 39, 39, {
                        return 'test4'
                    }, 'ArgumentListExpression:expressions'))
                }, 'ExpressionStatement:expression')
                automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'test5\')', 13, 40, 40, 40, {
                    return automaticBlackBox.expressionEvaluation('PropertyExpression', null, 13, 23, 40, 40, {
                        return automaticBlackBox.expressionEvaluation('ClassExpression', 'java.lang.System', 13, 19, 40, 40, {
                            return java.lang.System
                        }, 'PropertyExpression:objectExpression').automaticBlackBox.expressionEvaluation('ConstantExpression', '\'out\'', 20, 23, 40, 40, {
                            return 'out'
                        }, 'PropertyExpression:property')
                    }, 'MethodCallExpression:objectExpression').println(automaticBlackBox.expressionEvaluation('ConstantExpression', '\'test5\'', 32, 39, 40, 40, {
                        return 'test5'
                    }, 'ArgumentListExpression:expressions'))
                }, 'ExpressionStatement:expression')
            }
            finally {
                automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'test6\')', 13, 40, 42, 42, {
                    return automaticBlackBox.expressionEvaluation('PropertyExpression', null, 13, 23, 42, 42, {
                        return automaticBlackBox.expressionEvaluation('ClassExpression', 'java.lang.System', 13, 19, 42, 42, {
                            return java.lang.System
                        }, 'PropertyExpression:objectExpression').automaticBlackBox.expressionEvaluation('ConstantExpression', '\'out\'', 20, 23, 42, 42, {
                            return 'out'
                        }, 'PropertyExpression:property')
                    }, 'MethodCallExpression:objectExpression').println(automaticBlackBox.expressionEvaluation('ConstantExpression', '\'test6\'', 32, 39, 42, 42, {
                        return 'test6'
                    }, 'ArgumentListExpression:expressions'))
                }, 'ExpressionStatement:expression')
                automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'test7\')', 13, 40, 43, 43, {
                    return automaticBlackBox.expressionEvaluation('PropertyExpression', null, 13, 23, 43, 43, {
                        return automaticBlackBox.expressionEvaluation('ClassExpression', 'java.lang.System', 13, 19, 43, 43, {
                            return java.lang.System
                        }, 'PropertyExpression:objectExpression').automaticBlackBox.expressionEvaluation('ConstantExpression', '\'out\'', 20, 23, 43, 43, {
                            return 'out'
                        }, 'PropertyExpression:property')
                    }, 'MethodCallExpression:objectExpression').println(automaticBlackBox.expressionEvaluation('ConstantExpression', '\'test7\'', 32, 39, 43, 43, {
                        return 'test7'
                    }, 'ArgumentListExpression:expressions'))
                }, 'ExpressionStatement:expression')
            }
            automaticBlackBox.executionClose()
        }
        catch (java.lang.Throwable automaticThrowable) {
            automaticBlackBox.exception(automaticThrowable)
            throw automaticThrowable
        }
        finally {
            automaticBlackBox.executionClose()}
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitThrowStatementExpressionLevel() {
        try {
            System.out.println("test1")
            System.out.println("test2")
            throw new Exception("test3")
        } catch (Throwable throwable) {
            System.out.println("test4")
            System.out.println("test5")
        } finally {
            System.out.println("test6")
            System.out.println("test7")
        }
    }
}
