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
        /*for (i in [0, 1]) {
            System.out.println("iiiiiiiiii " + i)
        }*/
        int wwww = 0
        while (wwww < 2) {
            System.out.println("zzzzzzzzzz " + wwww)
            wwww++
        }
        //1/0
        //BlackBoxEngine.getInstance().executionClose()
    }

    void z() {
        groovy.BlackBoxEngine automaticBlackBox = new groovy.BlackBoxEngine().getInstance()
        automaticBlackBox.methodExecutionOpen('Executable', 'groovy', 'someMethod2', automaticBlackBox .NOARGSMAP, 5, 6, 12, 25)
        try {
            automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.Integer wwww = 0', 9, 21, 18, 18)
            java.lang.Integer wwww = 0
            automaticBlackBox.executionClose()
            automaticBlackBox.statementExecutionOpen('WhileStatement', 'while ( wwww &lt; 2) {\n    java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )\n    return null\n}\n', 9, 10, 19, 22)
            while (automaticBlackBox.expressionEvaluation('BooleanExpression', 'wwww &lt; 2', 16, 24, 19, 19, {
                return wwww < 2
            })) {
                automaticBlackBox.statementExecutionOpen('BlockStatement', 'java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )\nreturn null\n', 25, 10, 19, 22)
                automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )', 13, 53, 20, 20)
                java.lang.System.out.println('zzzzzzzzzz ' + wwww )
                automaticBlackBox.executionClose()
                automaticBlackBox.statementExecutionOpen('ReturnStatement', '\nreturn null\n', 13, 19, 21, 21)
                return
                automaticBlackBox.executionClose()
                automaticBlackBox.executionClose()
            }
            automaticBlackBox.executionClose()
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

