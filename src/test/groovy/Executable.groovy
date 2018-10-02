package groovy

class Executable implements Runnable {


    @Override
    void run() {
        System.out.println("blackBoxConfFileName: " + System.getProperty("blackBoxConfFileName"))
        someMethod2()//someMethod2()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    //@BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void someMethod2() {
        /*for (i in [0, 1]) {
            System.out.println("iiiiiiiiii " + i)
        }*/
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

    void z() {
        groovy.BlackBoxEngine automaticBlackBox = new groovy.BlackBoxEngine().getInstance()
        automaticBlackBox.methodExecutionOpen('Executable', 'groovy', 'someMethod2', automaticBlackBox .NOARGSMAP, 5, 6, 12, 34)
        try {
            automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.Integer wwww = 0', 9, 21, 18, 18)
            java.lang.Integer wwww = automaticBlackBox.expressionEvaluation('ConstantExpression', '0', 20, 21, 18, 18, {
                return 0
            })
            automaticBlackBox.executionClose()
            automaticBlackBox.statementExecutionOpen('WhileStatement', 'while ( wwww < 3) {\n    try {\n        java.lang.System.out.println(\'try\')\n        java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )\n        ( wwww )++\n    } \n    catch (java.lang.Throwable z) {\n        java.lang.System.out.println(\'Catched \' + z )\n        java.lang.System.out.println(\'Catch\')\n    } \n    finally { \n        java.lang.System.out.println(\'Finally1 \' + wwww )\n        java.lang.System.out.println(\'Finally2 \' + wwww )\n    } \n}\n', 9, 10, 19, 31)
            while (automaticBlackBox.expressionEvaluation('BooleanExpression', 'wwww < 3', 16, 24, 19, 19, {
                return wwww < automaticBlackBox.expressionEvaluation('ConstantExpression', '3', 23, 24, 19, 19, {
                    return 3
                })
            })) {
                automaticBlackBox.statementExecutionOpen('BlockStatement', 'try {\n    java.lang.System.out.println(\'try\')\n    java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )\n    ( wwww )++\n} \ncatch (java.lang.Throwable z) {\n    java.lang.System.out.println(\'Catched \' + z )\n    java.lang.System.out.println(\'Catch\')\n} \nfinally { \n    java.lang.System.out.println(\'Finally1 \' + wwww )\n    java.lang.System.out.println(\'Finally2 \' + wwww )\n} \n', 26, 10, 19, 31)
                automaticBlackBox.statementExecutionOpen('TryCatchStatement', 'try {\n    java.lang.System.out.println(\'try\')\n    java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )\n    ( wwww )++\n} \ncatch (java.lang.Throwable z) {\n    java.lang.System.out.println(\'Catched \' + z )\n    java.lang.System.out.println(\'Catch\')\n} \nfinally { \n    java.lang.System.out.println(\'Finally1 \' + wwww )\n    java.lang.System.out.println(\'Finally2 \' + wwww )\n} \n', 13, 14, 20, 30)
                try {
                    automaticBlackBox.statementExecutionOpen('BlockStatement', 'java.lang.System.out.println(\'try\')\njava.lang.System.out.println(\'zzzzzzzzzz \' + wwww )\n( wwww )++\n', 17, 15, 20, 24)
                    automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.System.out.println(\'try\')', 17, 42, 21, 21)
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'try\')', 17, 42, 21, 21, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 21, 21, {
                            return java.lang.System.out
                        }).println(automaticBlackBox.expressionEvaluation('ArgumentListExpression', '(\'try\')', 36, 41, 21, 21, {
                            return (automaticBlackBox.expressionEvaluation('ConstantExpression', '\'try\'', 36, 41, 21, 21, {
                                return 'try'
                            }))
                        }))
                    })
                    automaticBlackBox.executionClose()
                    automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )', 17, 57, 22, 22)
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'zzzzzzzzzz \' + wwww )', 17, 57, 22, 22, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 22, 22, {
                            return java.lang.System.out
                        }).println(automaticBlackBox.expressionEvaluation('ArgumentListExpression', '(\'zzzzzzzzzz \' + wwww )', 36, 56, 22, 22, {
                            return (automaticBlackBox.expressionEvaluation('BinaryExpression', '\'zzzzzzzzzz \' + wwww ', 36, 56, 22, 22, {
                                return 'zzzzzzzzzz ' + automaticBlackBox.expressionEvaluation('VariableExpression', 'wwww ', 52, 56, 22, 22, {
                                    return wwww
                                })
                            }))
                        }))
                    })
                    automaticBlackBox.executionClose()
                    automaticBlackBox.statementExecutionOpen('ExpressionStatement', '( wwww )++', 17, 23, 23, 23)
                    automaticBlackBox.expressionEvaluation('PostfixExpression', '( wwww )++', 17, 23, 23, 23, {
                        return  wwww++
                    })
                    automaticBlackBox.executionClose()
                    automaticBlackBox.executionClose()
                }
                catch (java.lang.Throwable z) {
                    automaticBlackBox.statementExecutionOpen('BlockStatement', 'java.lang.System.out.println(\'Catched \' + z )\njava.lang.System.out.println(\'Catch\')\n', 35, 15, 24, 27)
                    automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.System.out.println(\'Catched \' + z )', 17, 51, 25, 25)
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Catched \' + z )', 17, 51, 25, 25, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 25, 25, {
                            return java.lang.System.out
                        }).println(automaticBlackBox.expressionEvaluation('ArgumentListExpression', '(\'Catched \' + z )', 36, 50, 25, 25, {
                            return (automaticBlackBox.expressionEvaluation('BinaryExpression', '\'Catched \' + z ', 36, 50, 25, 25, {
                                return 'Catched ' + automaticBlackBox.expressionEvaluation('VariableExpression', 'z ', 49, 50, 25, 25, {
                                    return z
                                })
                            }))
                        }))
                    })
                    automaticBlackBox.executionClose()
                    automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.System.out.println(\'Catch\')', 17, 44, 26, 26)
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Catch\')', 17, 44, 26, 26, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 26, 26, {
                            return java.lang.System.out
                        }).println(automaticBlackBox.expressionEvaluation('ArgumentListExpression', '(\'Catch\')', 36, 43, 26, 26, {
                            return (automaticBlackBox.expressionEvaluation('ConstantExpression', '\'Catch\'', 36, 43, 26, 26, {
                                return 'Catch'
                            }))
                        }))
                    })
                    automaticBlackBox.executionClose()
                    automaticBlackBox.executionClose()
                }
                finally {
                    automaticBlackBox.statementExecutionOpen('BlockStatement', 'java.lang.System.out.println(\'Finally1 \' + wwww )\njava.lang.System.out.println(\'Finally2 \' + wwww )\n', 15, 14, 27, 30)
                    automaticBlackBox.statementExecutionOpen('BlockStatement', 'java.lang.System.out.println(\'Finally1 \' + wwww )\njava.lang.System.out.println(\'Finally2 \' + wwww )\n', 23, 14, 27, 30)
                    automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.System.out.println(\'Finally1 \' + wwww )', 17, 55, 28, 28)
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Finally1 \' + wwww )', 17, 55, 28, 28, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 28, 28, {
                            return java.lang.System.out
                        }).println(automaticBlackBox.expressionEvaluation('ArgumentListExpression', '(\'Finally1 \' + wwww )', 36, 54, 28, 28, {
                            return (automaticBlackBox.expressionEvaluation('BinaryExpression', '\'Finally1 \' + wwww ', 36, 54, 28, 28, {
                                return 'Finally1 ' + automaticBlackBox.expressionEvaluation('VariableExpression', 'wwww ', 50, 54, 28, 28, {
                                    return wwww
                                })
                            }))
                        }))
                    })
                    automaticBlackBox.executionClose()
                    automaticBlackBox.statementExecutionOpen('ExpressionStatement', 'java.lang.System.out.println(\'Finally2 \' + wwww )', 17, 55, 29, 29)
                    automaticBlackBox.expressionEvaluation('MethodCallExpression', 'java.lang.System.out.println(\'Finally2 \' + wwww )', 17, 55, 29, 29, {
                        return automaticBlackBox.expressionEvaluation('PropertyExpression', 'java.lang.System.out', 17, 27, 29, 29, {
                            return java.lang.System.out
                        }).println(automaticBlackBox.expressionEvaluation('ArgumentListExpression', '(\'Finally2 \' + wwww )', 36, 54, 29, 29, {
                            return (automaticBlackBox.expressionEvaluation('BinaryExpression', '\'Finally2 \' + wwww ', 36, 54, 29, 29, {
                                return 'Finally2 ' + automaticBlackBox.expressionEvaluation('VariableExpression', 'wwww ', 50, 54, 29, 29, {
                                    return wwww
                                })
                            }))
                        }))
                    })
                    automaticBlackBox.executionClose()
                    automaticBlackBox.executionClose()
                    automaticBlackBox.executionClose()
                }
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

