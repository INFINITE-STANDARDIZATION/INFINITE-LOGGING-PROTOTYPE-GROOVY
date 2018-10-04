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
        automaticBlackBox.methodExecutionOpen('SandBox', 'groovy', 'someMethod', automaticBlackBox .NOARGSMAP, 5, 6, 9, 19)
        try {
            java.lang.String rslt = automaticBlackBox.expressionEvaluation('ConstantExpression', '\'\'', 23, 25, 11, 11, {
                return ''
            })
            automaticBlackBox.statementExecutionOpen('WhileStatement', 'while (rslt.length() < 100) {\n    if (rslt.length() > 10) {\n        break\n    }\n    rslt += \'z\'\n}\n', 9, 10, 12, 17)
            while (automaticBlackBox.expressionEvaluation('BooleanExpression', 'rslt.length() < 100', 15, 32, 12, 12, {
                return rslt.length() < automaticBlackBox.expressionEvaluation('ConstantExpression', '100', 29, 32, 12, 12, {
                    return 100
                })
            })) {
                automaticBlackBox.statementExecutionOpen('IfStatement', 'if (rslt.length() > 10) {\n    break\n}\n', 13, 14, 13, 15)
                if (automaticBlackBox.expressionEvaluation('BooleanExpression', 'rslt.length() > 10', 17, 35, 13, 13, {
                    return rslt.length() > automaticBlackBox.expressionEvaluation('ConstantExpression', '10', 33, 35, 13, 13, {
                        return 10
                    })
                })) {
                    automaticBlackBox.handleControlStatement('BreakStatement', 'break\n', 17, 22, 14, 14)
                    break
                } else {
                }
                automaticBlackBox.executionClose()
                automaticBlackBox.expressionEvaluation('BinaryExpression', 'rslt += \'z\'', 13, 24, 16, 16, {
                    return rslt += automaticBlackBox.expressionEvaluation('ConstantExpression', '\'z\'', 21, 24, 16, 16, {
                        return 'z'
                    })
                })
            }
            automaticBlackBox.executionClose()
            automaticBlackBox.handleControlStatement('ReturnStatement', '\nreturn rslt \n', 9, 20, 18, 18)
            return automaticBlackBox.expressionEvaluation('VariableExpression', 'rslt ', 16, 20, 18, 18, {
                return rslt
            })
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
