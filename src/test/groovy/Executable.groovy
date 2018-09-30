package groovy

class Executable implements Runnable {


    @Override
    void run() {
        System.out.println("blackBoxConfFileName: " + System.getProperty("blackBoxConfFileName"))
        someMethod2()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    //@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void someMethod2() {
        for (i in [0, 1]) {
            System.out.println("iiiiiiiiii " + i)
        }
        int z = 0
        while (z < 2) {
            System.out.println("zzzzzzzzzz " + z)
            z++
        }
        //1/0
        //BlackBoxEngine.getInstance().executionClose()
    }

    void z() {
        groovy.BlackBoxEngine automaticBlackBox = new groovy.BlackBoxEngine().getInstance()
        automaticBlackBox.methodExecutionOpen('Executable', 'groovy', 'someMethod2', automaticBlackBox.NOARGSMAP, 5, 6, 12, 25)
        try {
            automaticBlackBox.statementExecutionOpen('ForStatement', 'for (java.lang.Object i : [0, 1]) {\n    java.lang.System.out.println(\'iiiiiiiiii \' + i )\n}\n', 9, 10, 15, 17)
            try {
                for (java.lang.Object i : automaticBlackBox.expressionEvaluation('ListExpression', '[0, 1]', 19, 25, 15, 15, {
                    return [0, 1]
                }, 7)) {
                    automaticBlackBox.statementExecutionOpen('BlockStatement', 'java.lang.System.out.println(\'iiiiiiiiii \' + i )\n', 27, 10, 15, 17)
                    try {
                        automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.System.out.println(\'iiiiiiiiii \' + i )', 13, 50, 16, 16)
                        try {
                            java.lang.System.out.println('iiiiiiiiii ' + i)
                        }
                        catch (java.lang.Throwable throwable) {
                            automaticBlackBox.exception(throwable)
                            throw throwable
                        }
                        finally {
                            automaticBlackBox.executionClose()
                        }
                    }
                    catch (java.lang.Throwable throwable) {
                        automaticBlackBox.exception(throwable)
                        throw throwable
                    }
                    finally {
                        automaticBlackBox.executionClose()
                    }
                }
            }
            catch (java.lang.Throwable throwable) {
                automaticBlackBox.exception(throwable)
                throw throwable
            }
            finally {
                automaticBlackBox.executionClose()
            }
            automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.Integer z = 0', 9, 18, 18, 18)
            try {
                java.lang.Integer z = 0
            }
            catch (java.lang.Throwable throwable) {
                automaticBlackBox.exception(throwable)
                throw throwable
            }
            finally {
                automaticBlackBox.executionClose()
            }
            automaticBlackBox.statementExecutionOpen('WhileStatement', 'while ( z < 2) {\n    java.lang.System.out.println(\'zzzzzzzzzz \' + z )\n    ( z )++\n}\n', 9, 10, 19, 22)
            try {
                while (automaticBlackBox.expressionEvaluation('BooleanExpression', 'z < 2', 16, 21, 19, 19, {
                    return z < 2
                }, 7)) {
                    automaticBlackBox.statementExecutionOpen('BlockStatement', 'java.lang.System.out.println(\'zzzzzzzzzz \' + z )\n( z )++\n', 23, 10, 19, 22)
                    try {
                        automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.System.out.println(\'zzzzzzzzzz \' + z )', 13, 50, 20, 20)
                        try {
                            java.lang.System.out.println('zzzzzzzzzz ' + z)
                        }
                        catch (java.lang.Throwable throwable) {
                            automaticBlackBox.exception(throwable)
                            throw throwable
                        }
                        finally {
                            automaticBlackBox.executionClose()
                        }
                        automaticBlackBox.statementExecutionOpen('ExpressionStatement', '( z )++', 13, 17, 21, 21)
                        try {
                            (z) ++
                        }
                        catch (java.lang.Throwable throwable) {
                            automaticBlackBox.exception(throwable)
                            throw throwable
                        }
                        finally {
                            automaticBlackBox.executionClose()
                        }
                    }
                    catch (java.lang.Throwable throwable) {
                        automaticBlackBox.exception(throwable)
                        throw throwable
                    }
                    finally {
                        automaticBlackBox.executionClose()
                    }
                }
            }
            catch (java.lang.Throwable throwable) {
                automaticBlackBox.exception(throwable)
                throw throwable
            }
            finally {
                automaticBlackBox.executionClose()
            }
        }
        catch (java.lang.Throwable throwable) {
            automaticBlackBox.exception(throwable)
            throw throwable
        }
        finally {
            automaticBlackBox.executionClose()
        }
    }

}
