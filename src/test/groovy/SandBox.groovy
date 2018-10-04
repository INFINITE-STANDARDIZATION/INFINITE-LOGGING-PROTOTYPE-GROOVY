package groovy

class SandBox implements Runnable{
    @Override
    void run() {
        String q = tester()
    }

    //@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    String someMethod() {
        String rslt = ""
        while(rslt.length()<100) {
            if (rslt.length() > 10) {
                break
            }
            rslt += "z"
        }
        return rslt
    }

    String tester() {
        groovy.BlackBoxEngine automaticBlackBox = new groovy.BlackBoxEngine().getInstance()
        automaticBlackBox.methodExecutionOpen('SandBox', 'groovy', 'misc', automaticBlackBox .NOARGSMAP, 5, 6, 24, 42)
        try {
            java.lang.Integer wwww = automaticBlackBox.expressionEvaluation('ConstantExpression', '0', 20, 21, 26, 26, {
                return 0
            }, 'iBinaryExpression.getRightExpression()')
            automaticBlackBox.statementExecutionOpen('WhileStatement', 'while ( wwww < 3) {\n    try {\n        java.lang.System.out.println(\'try\')\n        java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )\n        ( wwww )++\n    } \n    catch (java.lang.Throwable z) {\n        java.lang.System.out.println(\'Catched \' + z )\n        java.lang.System.out.println(\'Catch\')\n    } \n    finally { \n        java.lang.System.out.println(\'Finally1 \' + wwww )\n        java.lang.System.out.println(\'Finally2 \' + wwww )\n    } \n}\n', 9, 10, 27, 39, iBlockStatement.getStatements())
            while (automaticBlackBox.expressionEvaluation('BooleanExpression', 'wwww < 3', 16, 24, 27, 27, {
                return wwww < automaticBlackBox.expressionEvaluation('ConstantExpression', '3', 23, 24, 27, 27, {
                    return 3
                }, 'iBinaryExpression.getRightExpression()')
            }, 'iWhileStatement.getBooleanExpression()')) {
                automaticBlackBox.statementExecutionOpen('TryCatchStatement', 'try {\n    java.lang.System.out.println(\'try\')\n    java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )\n    ( wwww )++\n} \ncatch (java.lang.Throwable z) {\n    java.lang.System.out.println(\'Catched \' + z )\n    java.lang.System.out.println(\'Catch\')\n} \nfinally { \n    java.lang.System.out.println(\'Finally1 \' + wwww )\n    java.lang.System.out.println(\'Finally2 \' + wwww )\n} \n', 13, 14, 28, 38, iBlockStatement.getStatements())
                try {
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'try\')', 17, 42, 29, 29, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 29, 29, {
                            return java.lang.System.out
                        }, 'iMethodCallExpression.getObjectExpression()').println('try')
                    }, 'iExpressionStatement.getExpression()')
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )', 17, 57, 30, 30, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 30, 30, {
                            return java.lang.System.out
                        }, 'iMethodCallExpression.getObjectExpression()').println('zzzzzzzzzz ' + wwww )
                    }, 'iExpressionStatement.getExpression()')
                    automaticBlackBox.expressionEvaluation('PostfixExpression', '( wwww )++', 17, 23, 31, 31, {
                        return  wwww++
                    }, 'iExpressionStatement.getExpression()')
                }
                catch (java.lang.Throwable z) {
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Catched \' + z )', 17, 51, 33, 33, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 33, 33, {
                            return java.lang.System.out
                        }, 'iMethodCallExpression.getObjectExpression()').println('Catched ' + z )
                    }, 'iExpressionStatement.getExpression()')
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Catch\')', 17, 44, 34, 34, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 34, 34, {
                            return java.lang.System.out
                        }, 'iMethodCallExpression.getObjectExpression()').println('Catch')
                    }, 'iExpressionStatement.getExpression()')
                }
                finally {
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Finally1 \' + wwww )', 17, 55, 36, 36, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 36, 36, {
                            return java.lang.System.out
                        }, 'iMethodCallExpression.getObjectExpression()').println('Finally1 ' + wwww )
                    }, 'iExpressionStatement.getExpression()')
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Finally2 \' + wwww )', 17, 55, 37, 37, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 37, 37, {
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

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void misc() {
        int wwww = 0
        while (wwww < 3) {
            try {
                System.out.println("try")
                System.out.println("zzzzzzzzzz " + wwww)
                wwww++
            } catch (Throwable z) {
                System.out.println("Catched " + z)
                System.out.println("Catch")
            } finally {
                System.out.println("Finally1 " + wwww)
                System.out.println("Finally2 " + wwww)
            }
        }
        //1/0
        //BlackBoxEngine.getInstance().executionClose()
    }

}
