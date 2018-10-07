package groovy

class SandBox {

    static void main(String[] args) {
        new SandBox().someMethod()
    }

    //@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void someMethod() {
        groovy.BlackBoxEngine automaticBlackBox = new groovy.BlackBoxEngine().getInstance()
        automaticBlackBox.methodExecutionOpen('TestCasesNormal', 'groovy', 'misc', automaticBlackBox .NOARGSMAP, 5, 6, 1458, 1476)
        try {
            java.lang.Integer wwww = automaticBlackBox.expressionEvaluation('ConstantExpression', '0', 20, 21, 1460, 1460, {
                return 0
            }, 'iBinaryExpression.getRightExpression()')
            automaticBlackBox.statementExecutionOpen('WhileStatement', 'while ( wwww &lt; 3) {\n    try {\n        java.lang.System.out.println(\'try\')\n        java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )\n        ( wwww )++\n    } \n    catch (java.lang.Throwable z) {\n        java.lang.System.out.println(\'Catched \' + z )\n        java.lang.System.out.println(\'Catch\')\n    } \n    finally { \n        java.lang.System.out.println(\'Finally1 \' + wwww )\n        java.lang.System.out.println(\'Finally2 \' + wwww )\n    } \n}\n', 9, 10, 1461, 1473, 'iBlockStatement.getStatements()')
            while (automaticBlackBox.expressionEvaluation('BooleanExpression', 'wwww &lt; 3', 16, 24, 1461, 1461, {
                return wwww < automaticBlackBox.expressionEvaluation('ConstantExpression', '3', 23, 24, 1461, 1461, {
                    return 3
                }, 'iBinaryExpression.getRightExpression()')
            }, 'iWhileStatement.getBooleanExpression()')) {
                automaticBlackBox.statementExecutionOpen('TryCatchStatement', 'try {\n    java.lang.System.out.println(\'try\')\n    java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )\n    ( wwww )++\n} \ncatch (java.lang.Throwable z) {\n    java.lang.System.out.println(\'Catched \' + z )\n    java.lang.System.out.println(\'Catch\')\n} \nfinally { \n    java.lang.System.out.println(\'Finally1 \' + wwww )\n    java.lang.System.out.println(\'Finally2 \' + wwww )\n} \n', 13, 14, 1462, 1472, 'iBlockStatement.getStatements()')
                try {
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'try\')', 17, 42, 1463, 1463, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 1463, 1463, {
                            return java.lang.System.out
                        }, 'iMethodCallExpression.getObjectExpression()').println('try')
                    }, 'iExpressionStatement.getExpression()')
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )', 17, 57, 1464, 1464, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 1464, 1464, {
                            return java.lang.System.out
                        }, 'iMethodCallExpression.getObjectExpression()').println('zzzzzzzzzz ' + wwww )
                    }, 'iExpressionStatement.getExpression()')
                    automaticBlackBox.expressionEvaluation('PostfixExpression', '( wwww )++', 17, 23, 1465, 1465, {
                        return  wwww ++
                    }, 'iExpressionStatement.getExpression()')
                }
                catch (java.lang.Throwable z) {
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Catched \' + z )', 17, 51, 1467, 1467, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 1467, 1467, {
                            return java.lang.System.out
                        }, 'iMethodCallExpression.getObjectExpression()').println('Catched ' + z )
                    }, 'iExpressionStatement.getExpression()')
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Catch\')', 17, 44, 1468, 1468, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 1468, 1468, {
                            return java.lang.System.out
                        }, 'iMethodCallExpression.getObjectExpression()').println('Catch')
                    }, 'iExpressionStatement.getExpression()')
                }
                finally {
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Finally1 \' + wwww )', 17, 55, 1470, 1470, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 1470, 1470, {
                            return java.lang.System.out
                        }, 'iMethodCallExpression.getObjectExpression()').println('Finally1 ' + wwww )
                    }, 'iExpressionStatement.getExpression()')
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Finally2 \' + wwww )', 17, 55, 1471, 1471, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 1471, 1471, {
                            return java.lang.System.out
                        }, 'iMethodCallExpression.getObjectExpression()').println('Finally2 ' + wwww )
                    }, 'iExpressionStatement.getExpression()')
                }
                automaticBlackBox.executionClose()
            }
            automaticBlackBox.executionClose()
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
