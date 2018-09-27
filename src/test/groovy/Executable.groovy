package groovy

class Executable implements Runnable{


    @Override
    void run() {
        System.out.println("blackBoxConfFileName: " + System.getProperty("blackBoxConfFileName"))
        someMethod2()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void someMethod2() {
        for (i in [0,1]) {
            System.out.println("zzzzzzzzzz " + i)
        }
        //1/0
        //BlackBoxEngine.getInstance().executionClose()
    }

    void z() {
        java.lang.Object automaticBlackBox = new groovy.BlackBoxEngine()
        automaticBlackBox.getInstance().methodExecutionOpen('Executable', 'groovy', 'someMethod2', automaticBlackBox .NOARGSMAP)
        try {
            automaticBlackBox.getInstance().statementExecutionOpen('ForStatement', 'for (java.lang.Object i : automaticBlackBox.expressionEvaluation(\'ListExpression\', \'[0, 1]\', 19, 24, 14, 14, { \n    return [0, 1]\n})) {\n    automaticBlackBox.getInstance().statementExecutionOpen(\'BlockStatement\', \'automaticBlackBox.getInstance().statementExecutionOpen(\'ExpressionStatement\', \'java.lang.System.out.println(\'zzzzzzzzzz \' + i )\', 13, 50, 15, 15)\ntry {\n    java.lang.System.out.println(\'zzzzzzzzzz \' + i )\n} \ncatch (java.lang.Throwable throwable) {\n    automaticBlackBox.getInstance().exception(throwable)\n    throw throwable \n} \nfinally { \n    automaticBlackBox.getInstance().executionClose()\n} \n\', 26, 10, 14, 16)\n    try {\n        automaticBlackBox.getInstance().statementExecutionOpen(\'ExpressionStatement\', \'java.lang.System.out.println(\'zzzzzzzzzz \' + i )\', 13, 50, 15, 15)\n        try {\n            java.lang.System.out.println(\'zzzzzzzzzz \' + i )\n        } \n        catch (java.lang.Throwable throwable) {\n            automaticBlackBox.getInstance().exception(throwable)\n            throw throwable \n        } \n        finally { \n            automaticBlackBox.getInstance().executionClose()\n        } \n    } \n    catch (java.lang.Throwable throwable) {\n        automaticBlackBox.getInstance().exception(throwable)\n        throw throwable \n    } \n    finally { \n        automaticBlackBox.getInstance().executionClose()\n    } \n}\n', 9, 10, 14, 16)
            try {
                for (java.lang.Object i : automaticBlackBox.expressionEvaluation('ListExpression', '[0, 1]', 19, 24, 14, 14, {
                    return [0, 1]
                })) {
                    automaticBlackBox.getInstance().statementExecutionOpen('BlockStatement', 'automaticBlackBox.getInstance().statementExecutionOpen(\'ExpressionStatement\', \'java.lang.System.out.println(\'zzzzzzzzzz \' + i )\', 13, 50, 15, 15)\ntry {\n    java.lang.System.out.println(\'zzzzzzzzzz \' + i )\n} \ncatch (java.lang.Throwable throwable) {\n    automaticBlackBox.getInstance().exception(throwable)\n    throw throwable \n} \nfinally { \n    automaticBlackBox.getInstance().executionClose()\n} \n', 26, 10, 14, 16)
                    try {
                        automaticBlackBox.getInstance().statementExecutionOpen('ExpressionStatement', 'java.lang.System.out.println(\'zzzzzzzzzz \' + i )', 13, 50, 15, 15)
                        try {
                            java.lang.System.out.println('zzzzzzzzzz ' + i )
                        }
                        catch (java.lang.Throwable throwable) {
                            automaticBlackBox.getInstance().exception(throwable)
                            throw throwable
                        }
                        finally {
                            automaticBlackBox.getInstance().executionClose()
                        }
                    }
                    catch (java.lang.Throwable throwable) {
                        automaticBlackBox.getInstance().exception(throwable)
                        throw throwable
                    }
                    finally {
                        automaticBlackBox.getInstance().executionClose()
                    }
                }
            }
            catch (java.lang.Throwable throwable) {
                automaticBlackBox.getInstance().exception(throwable)
                throw throwable
            }
            finally {
                automaticBlackBox.getInstance().executionClose()
            }
        }
        catch (java.lang.Throwable throwable) {
            automaticBlackBox.getInstance().exception(throwable)
            throw throwable
        }
        finally {
            automaticBlackBox.getInstance().executionClose()
        }
    }

}
