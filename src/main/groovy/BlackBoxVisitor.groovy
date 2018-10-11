package groovy

import groovy.transform.ToString
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.CodeVisitorSupport
import org.codehaus.groovy.ast.VariableScope
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.tools.GeneralUtils

@ToString(includeNames = true, includeFields = true)
class BlackBoxVisitor extends CodeVisitorSupport {


    final String PCLASSSIMPLENAME = this.getClass().getSimpleName()
    final String PPACKAGENAME = this.getClass().getPackage().getName()
    final BlackBoxEngine blackBoxEngine = BlackBoxEngine.getInstance()
    BlackBoxLevel blackBoxLevel
    AnnotationNode annotationNode
    BlackBoxTransformation blackBoxTransformation

    /*
    NOTE: TO SKIP LOGGING SPECIFIC STATEMENT/EXPRESSION - IT SHOULD BE EXCLUDED IN TRANSFORM METHOD
    (BlackBoxTransformation.transform...), AND NOT IN VISIT METHOD.
     */

    BlackBoxVisitor(BlackBoxLevel iBlackBoxLevel, AnnotationNode iAnnotationNode, BlackBoxTransformation iBlackBoxTransformation) {
        blackBoxLevel = iBlackBoxLevel
        annotationNode = iAnnotationNode
        blackBoxTransformation = iBlackBoxTransformation
    }

    @Override
    void visitBlockStatement(BlockStatement iBlockStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitBlockStatement", ["iBlockStatement": iBlockStatement])
        try {
            transformStatementList(iBlockStatement.getStatements(), "iBlockStatement.getStatements()")
            blackBoxEngine.methodResult("iBlockStatement", iBlockStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitForLoop(ForStatement iForStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitForLoop", ["iForStatement": iForStatement])
        try {
            iForStatement.setCollectionExpression(blackBoxTransformation.transformExpression(iForStatement.getCollectionExpression(), blackBoxLevel, "iForStatement.getCollectionExpression()"))
            iForStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iForStatement.getLoopBlock(), blackBoxLevel, "iForStatement.getLoopBlock()")))
            blackBoxEngine.methodResult("iForStatement", iForStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitWhileLoop(WhileStatement iWhileStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitWhileLoop", ["iWhileStatement": iWhileStatement])
        try {
            iWhileStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.transformExpression(iWhileStatement.getBooleanExpression(), blackBoxLevel, "iWhileStatement.getBooleanExpression()")))
            iWhileStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iWhileStatement.getLoopBlock(), blackBoxLevel, "iWhileStatement.getLoopBlock()")))
            blackBoxEngine.methodResult("iWhileStatement", iWhileStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitDoWhileLoop(DoWhileStatement iDoWhileStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitDoWhileLoop", ["iDoWhileStatement": iDoWhileStatement])
        try {
            iDoWhileStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.transformExpression(iDoWhileStatement.getBooleanExpression(), blackBoxLevel, "iDoWhileStatement.getBooleanExpression()")))
            iDoWhileStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iDoWhileStatement.getLoopBlock(), blackBoxLevel, "iDoWhileStatement.getLoopBlock()")))
            blackBoxEngine.methodResult("iDoWhileStatement", iDoWhileStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitIfElse(IfStatement iIfStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitIfElse", ["iIfStatement": iIfStatement])
        try {
            iIfStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.transformExpression(iIfStatement.getBooleanExpression(), blackBoxLevel, "iIfStatement.getBooleanExpression()")))
            iIfStatement.setIfBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iIfStatement.getIfBlock(), blackBoxLevel, "iIfStatement.getIfBlock()")))
            iIfStatement.setElseBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iIfStatement.getElseBlock(), blackBoxLevel, "iIfStatement.getElseBlock()")))
            blackBoxEngine.methodResult("iIfStatement", iIfStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitExpressionStatement(ExpressionStatement iExpressionStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitExpressionStatement", ["iExpressionStatement": iExpressionStatement])
        try {
            iExpressionStatement.setExpression(blackBoxTransformation.transformExpression(iExpressionStatement.getExpression(), blackBoxLevel, "iExpressionStatement.getExpression()"))
            blackBoxEngine.methodResult("iExpressionStatement", iExpressionStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitReturnStatement(ReturnStatement iReturnStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitReturnStatement", ["iReturnStatement": iReturnStatement])
        try {
            iReturnStatement.setExpression(blackBoxTransformation.transformReturnStatementExpression(iReturnStatement.getExpression(), blackBoxLevel, "iReturnStatement.getExpression()", BlackBoxTransformation.codeString(iReturnStatement)))
            blackBoxEngine.methodResult("iReturnStatement", iReturnStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitAssertStatement(AssertStatement iAssertStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitAssertStatement", ["iAssertStatement": iAssertStatement])
        try {
            iAssertStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.transformExpression(iAssertStatement.getBooleanExpression(), blackBoxLevel, "iAssertStatement.getBooleanExpression()")))
            iAssertStatement.setMessageExpression(blackBoxTransformation.transformExpression(iAssertStatement.getMessageExpression(), blackBoxLevel, "iAssertStatement.getMessageExpression()"))
            blackBoxEngine.methodResult("iAssertStatement", iAssertStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitTryCatchFinally(TryCatchStatement iTryCatchStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitTryCatchFinally", ["iTryCatchStatement": iTryCatchStatement])
        try {
            iTryCatchStatement.setTryStatement(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iTryCatchStatement.getTryStatement(), blackBoxLevel, "iTryCatchStatement.getTryStatement()")))
            for (CatchStatement catchStatement : iTryCatchStatement.getCatchStatements()) {
                catchStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(catchStatement.getCode(), blackBoxLevel, "catchStatement.getCode()")))
            }
            iTryCatchStatement.setFinallyStatement(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iTryCatchStatement.getFinallyStatement(), blackBoxLevel, "iTryCatchStatement.getFinallyStatement()")))
            blackBoxEngine.methodResult("iTryCatchStatement", iTryCatchStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    protected void visitEmptyStatement(EmptyStatement iEmptyStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "void ", ["iEmptyStatement ": iEmptyStatement])
        try {
            super.visitEmptyStatement(iEmptyStatement)
            blackBoxEngine.methodResult("iEmptyStatement", iEmptyStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitSwitch(SwitchStatement iSwitchStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitSwitch", ["iSwitchStatement": iSwitchStatement])
        try {
            iSwitchStatement.setExpression(blackBoxTransformation.transformExpression(iSwitchStatement.getExpression(), blackBoxLevel, "iSwitchStatement.getExpression()"))
            for (CaseStatement caseStatement : iSwitchStatement.getCaseStatements()) {
                caseStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(caseStatement.getCode(), blackBoxLevel, "caseStatement.getCode()")))
            }
            iSwitchStatement.setDefaultStatement(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iSwitchStatement.getDefaultStatement(), blackBoxLevel, "iSwitchStatement.getDefaultStatement()")))
            blackBoxEngine.methodResult("iSwitchStatement", iSwitchStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitCaseStatement(CaseStatement iCaseStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitCaseStatement", ["iCaseStatement": iCaseStatement])
        try {
            iCaseStatement.setExpression(blackBoxTransformation.transformExpression(iCaseStatement.getExpression(), blackBoxLevel, "iCaseStatement.getExpression()"))
            iCaseStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iCaseStatement.getCode(), blackBoxLevel, "iCaseStatement.getCode()")))
            blackBoxEngine.methodResult("iCaseStatement", iCaseStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitBreakStatement(BreakStatement iBreakStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitBreakStatement", ["iBreakStatement": iBreakStatement])
        try {
            super.visitBreakStatement(iBreakStatement)
            blackBoxEngine.methodResult("iBreakStatement", iBreakStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitContinueStatement(ContinueStatement iContinueStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitContinueStatement", ["iContinueStatement": iContinueStatement])
        try {
            super.visitContinueStatement(iContinueStatement)
            blackBoxEngine.methodResult("iContinueStatement", iContinueStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitSynchronizedStatement(SynchronizedStatement iSynchronizedStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitSynchronizedStatement", ["iSynchronizedStatement": iSynchronizedStatement])
        try {
            iSynchronizedStatement.setExpression(blackBoxTransformation.transformExpression(iSynchronizedStatement.getExpression(), blackBoxLevel, "iSynchronizedStatement.getExpression()"))
            iSynchronizedStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iSynchronizedStatement.getCode(), blackBoxLevel, "iSynchronizedStatement.getCode()")))
            blackBoxEngine.methodResult("iSynchronizedStatement", iSynchronizedStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitThrowStatement(ThrowStatement iThrowStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitThrowStatement", ["iThrowStatement": iThrowStatement])
        try {
            iThrowStatement.setExpression(blackBoxTransformation.transformExpression(iThrowStatement.getExpression(), blackBoxLevel, "iThrowStatement.getExpression()"))
            blackBoxEngine.methodResult("iThrowStatement", iThrowStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitMethodCallExpression(MethodCallExpression iMethodCallExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitMethodCallExpression", ["iMethodCallExpression": iMethodCallExpression])
        try {
            iMethodCallExpression.setObjectExpression(blackBoxTransformation.transformExpression(iMethodCallExpression.getObjectExpression(), blackBoxLevel, "iMethodCallExpression.getObjectExpression()"))
            iMethodCallExpression.setArguments(blackBoxTransformation.transformExpression(iMethodCallExpression.getArguments(), blackBoxLevel, "iMethodCallExpression.getArguments()"))
            blackBoxEngine.methodResult("iMethodCallExpression", iMethodCallExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitStaticMethodCallExpression(StaticMethodCallExpression iStaticMethodCallExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitStaticMethodCallExpression", ["iStaticMethodCallExpression": iStaticMethodCallExpression])
        try {
            super.visitStaticMethodCallExpression(iStaticMethodCallExpression)
            //todo: improve Arguments handling (there is no setter method) (https://issues.apache.org/jira/browse/GROOVY-8834)
            blackBoxEngine.methodResult("iStaticMethodCallExpression", iStaticMethodCallExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitConstructorCallExpression(ConstructorCallExpression iConstructorCallExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitConstructorCallExpression", ["iConstructorCallExpression": iConstructorCallExpression])
        try {
            super.visitConstructorCallExpression(iConstructorCallExpression)
            //todo: improve Arguments handling (there is no setter method)
            blackBoxEngine.methodResult("iConstructorCallExpression", iConstructorCallExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitBinaryExpression(BinaryExpression iBinaryExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitBinaryExpression", ["iBinaryExpression": iBinaryExpression])
        try {
            //todo: enhance left expression logging
            iBinaryExpression.setRightExpression(blackBoxTransformation.transformExpression(iBinaryExpression.getRightExpression(), blackBoxLevel, "iBinaryExpression.getRightExpression()"))
            blackBoxEngine.methodResult("iBinaryExpression", iBinaryExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitTernaryExpression(TernaryExpression iTernaryExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitTernaryExpression", ["iTernaryExpression": iTernaryExpression])
        try {
            super.visitTernaryExpression(iTernaryExpression)
            //todo: log ternary expression (it's fields are final)
            blackBoxEngine.methodResult("iTernaryExpression", iTernaryExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitShortTernaryExpression(ElvisOperatorExpression iElvisOperatorExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitShortTernaryExpression", ["iElvisOperatorExpression": iElvisOperatorExpression])
        try {
            super.visitShortTernaryExpression(iElvisOperatorExpression)
            blackBoxEngine.methodResult("iElvisOperatorExpression", iElvisOperatorExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitPostfixExpression(PostfixExpression iPostfixExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitPostfixExpression", ["iPostfixExpression": iPostfixExpression])
        try {
            super.visitPostfixExpression(iPostfixExpression)
            blackBoxEngine.methodResult("iPostfixExpression", iPostfixExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitPrefixExpression(PrefixExpression iPrefixExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitPrefixExpression", ["iPrefixExpression": iPrefixExpression])
        try {
            super.visitPrefixExpression(iPrefixExpression)
            blackBoxEngine.methodResult("iPrefixExpression", iPrefixExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitBooleanExpression(BooleanExpression iBooleanExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitBooleanExpression", ["iBooleanExpression": iBooleanExpression])
        try {
            super.visitBooleanExpression(iBooleanExpression) //todo: enhance
            blackBoxEngine.methodResult("iBooleanExpression", iBooleanExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitNotExpression(NotExpression iNotExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitNotExpression", ["iNotExpression": iNotExpression])
        try {
            super.visitNotExpression(iNotExpression)
            blackBoxEngine.methodResult("iNotExpression", iNotExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitClosureExpression(ClosureExpression iClosureExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitClosureExpression", ["iClosureExpression": iClosureExpression])
        try {
            iClosureExpression.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iClosureExpression.getCode(), blackBoxLevel, "iClosureExpression.getCode()")))
            blackBoxEngine.methodResult("iClosureExpression", iClosureExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitTupleExpression(TupleExpression iTupleExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitTupleExpression", ["iTupleExpression": iTupleExpression])
        try {
            transformExpressionList(iTupleExpression.getExpressions(), "iTupleExpression.getExpressions()")
            blackBoxEngine.methodResult("iTupleExpression", iTupleExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitListExpression(ListExpression iListExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitListExpression", ["iListExpression": iListExpression])
        try {
            transformExpressionList(iListExpression.getExpressions(), "iListExpression.getExpressions()")
            blackBoxEngine.methodResult("iListExpression", iListExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitArrayExpression(ArrayExpression iArrayExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitArrayExpression", ["iArrayExpression": iArrayExpression])
        try {
            transformExpressionList(iArrayExpression.getExpressions(), "iArrayExpression.getExpressions()")
            transformExpressionList(iArrayExpression.getSizeExpression(), "iArrayExpression.getSizeExpression()")
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    void transformStatementList(List<Statement> statementList, String iNodeSourceName) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "transformStatementList", ["statementList": statementList])
        try {
            List<Statement> decoratedStatementsList = statementList.getClass().newInstance() as List<Statement>
            for (Statement statement : statementList) {
                decoratedStatementsList.addAll(blackBoxTransformation.transformStatement(statement, blackBoxLevel, iNodeSourceName))
            }
            statementList.clear()
            statementList.addAll(decoratedStatementsList)
            blackBoxEngine.methodResult("decoratedStatementsList", decoratedStatementsList)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    //todo: log return for all blackbox levels
    void transformExpressionList(List<Expression> expressionList, String iNodeSourceName) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "transformExpressionList", ["expressionList": expressionList])
        try {
            List<Expression> transformedExpressionList = expressionList.getClass().newInstance() as List<Expression>
            for (Expression expression : expressionList) {
                transformedExpressionList.addAll(blackBoxTransformation.transformExpression(expression, blackBoxLevel, iNodeSourceName))
            }
            expressionList.clear()
            expressionList.addAll(transformedExpressionList)
            blackBoxEngine.methodResult("transformedExpressionList", transformedExpressionList)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitMapExpression(MapExpression iMapExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitMapExpression", ["iMapExpression": iMapExpression])
        try {
            super.visitMapExpression(iMapExpression)//todo: not sure how to improve this (log MapEntryExpression)
            blackBoxEngine.methodResult("iMapExpression", iMapExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitMapEntryExpression(MapEntryExpression iMapEntryExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitMapEntryExpression", ["iMapEntryExpression": iMapEntryExpression])
        try {
            iMapEntryExpression.setKeyExpression(blackBoxTransformation.transformExpression(iMapEntryExpression.getKeyExpression(), blackBoxLevel, "iMapEntryExpression.getKeyExpression()"))
            iMapEntryExpression.setValueExpression(blackBoxTransformation.transformExpression(iMapEntryExpression.getValueExpression(), blackBoxLevel, "iMapEntryExpression.getValueExpression()"))
            blackBoxEngine.methodResult("iMapEntryExpression", iMapEntryExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitRangeExpression(RangeExpression iRangeExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitRangeExpression", ["iRangeExpression": iRangeExpression])
        try {
            super.visitRangeExpression(iRangeExpression) //todo: improve (from and to expressions are final)
            blackBoxEngine.methodResult("iRangeExpression", iRangeExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitSpreadExpression(SpreadExpression iSpreadExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitSpreadExpression", ["iSpreadExpression": iSpreadExpression])
        try {
            super.visitSpreadExpression(iSpreadExpression) //todo: improve (child expressions are final)
            blackBoxEngine.methodResult("iSpreadExpression", iSpreadExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }


    @Override
    void visitSpreadMapExpression(SpreadMapExpression iSpreadMapExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitSpreadMapExpression", ["iSpreadMapExpression": iSpreadMapExpression])
        try {
            super.visitSpreadMapExpression(iSpreadMapExpression) //todo: improve (child expressions are final)
            blackBoxEngine.methodResult("iSpreadMapExpression", iSpreadMapExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }


    @Override
    void visitMethodPointerExpression(MethodPointerExpression iMethodPointerExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitMethodPointerExpression", ["iMethodPointerExpression": iMethodPointerExpression])
        try {
            super.visitMethodPointerExpression(iMethodPointerExpression) //todo: improve (child expressions are final)
            blackBoxEngine.methodResult("iMethodPointerExpression", iMethodPointerExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitUnaryMinusExpression(UnaryMinusExpression iUnaryMinusExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitUnaryMinusExpression", ["iUnaryMinusExpression": iUnaryMinusExpression])
        try {
            super.visitUnaryMinusExpression(iUnaryMinusExpression) //todo: improve (child expressions are final)
            blackBoxEngine.methodResult("iUnaryMinusExpression", iUnaryMinusExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }


    @Override
    void visitUnaryPlusExpression(UnaryPlusExpression iUnaryPlusExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitUnaryPlusExpression", ["iUnaryPlusExpression": iUnaryPlusExpression])
        try {
            super.visitUnaryPlusExpression(iUnaryPlusExpression) //todo: improve (child expressions are final)
            blackBoxEngine.methodResult("iUnaryPlusExpression", iUnaryPlusExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitBitwiseNegationExpression(BitwiseNegationExpression iBitwiseNegationExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitBitwiseNegationExpression", ["iBitwiseNegationExpression": iBitwiseNegationExpression])
        try {
            super.visitBitwiseNegationExpression(iBitwiseNegationExpression) //todo: improve (child expressions are final)
            blackBoxEngine.methodResult("iBitwiseNegationExpression", iBitwiseNegationExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

       @Override
       void visitCastExpression(CastExpression iCastExpression) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitCastExpression", ["iCastExpression": iCastExpression])
           try {
               super.visitCastExpression(iCastExpression) //todo: improve (child expressions are final)
               blackBoxEngine.methodResult("iCastExpression", iCastExpression)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       void visitConstantExpression(ConstantExpression iConstantExpression) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitConstantExpression", ["iConstantExpression": iConstantExpression])
           try {
               super.visitConstantExpression(iConstantExpression)
               blackBoxEngine.methodResult("iConstantExpression", iConstantExpression)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       void visitClassExpression(ClassExpression iClassExpression) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitClassExpression", ["iClassExpression": iClassExpression])
           try {
               super.visitClassExpression(iClassExpression)
               blackBoxEngine.methodResult("iClassExpression", iClassExpression)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       void visitVariableExpression(VariableExpression iVariableExpression) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitVariableExpression", ["iVariableExpression": iVariableExpression])
           try {
               super.visitVariableExpression(iVariableExpression)
               blackBoxEngine.methodResult("iVariableExpression", iVariableExpression)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       void visitDeclarationExpression(DeclarationExpression iDeclarationExpression) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitDeclarationExpression", ["iDeclarationExpression": iDeclarationExpression])
           try {
               //todo: enhance left expression logging
               iDeclarationExpression.setRightExpression(blackBoxTransformation.transformExpression(iDeclarationExpression.getRightExpression(), blackBoxLevel, "iDeclarationExpression.getRightExpression()"))
               blackBoxEngine.methodResult("iDeclarationExpression", iDeclarationExpression)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       void visitPropertyExpression(PropertyExpression iPropertyExpression) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitPropertyExpression", ["iPropertyExpression": iPropertyExpression])
           try {
               iPropertyExpression.setObjectExpression(blackBoxTransformation.transformExpression(iPropertyExpression.getObjectExpression(), blackBoxLevel, "iPropertyExpression.getObjectExpression()"))
               iPropertyExpression.getProperty().visit(this)
               blackBoxEngine.methodResult("iPropertyExpression", iPropertyExpression)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       void visitAttributeExpression(AttributeExpression iAttributeExpression) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitAttributeExpression", ["iAttributeExpression": iAttributeExpression])
           try {
               iAttributeExpression.setObjectExpression(blackBoxTransformation.transformExpression(iAttributeExpression.getObjectExpression(), blackBoxLevel, "iAttributeExpression.getObjectExpression()"))
               iAttributeExpression.getProperty().visit(this)
               blackBoxEngine.methodResult("iAttributeExpression", iAttributeExpression)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       void visitFieldExpression(FieldExpression iFieldExpression) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitFieldExpression", ["iFieldExpression": iFieldExpression])
           try {
               super.visitFieldExpression(iFieldExpression)
               blackBoxEngine.methodResult("iFieldExpression", iFieldExpression)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       void visitGStringExpression(GStringExpression iGStringExpression) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitGStringExpression", ["iGStringExpression": iGStringExpression])
           try {
               //transformExpressionList(iGStringExpression.getStrings(), "iGStringExpression.getStrings()")
               /*TODO:
               java.lang.ClassCastException: org.codehaus.groovy.ast.expr.MethodCallExpression cannot be cast to org.codehaus.groovy.ast.expr.ConstantExpression
	at org.codehaus.groovy.classgen.ClassCompletionVerifier.visitGStringExpression(ClassCompletionVerifier.java:701)
	at org.codehaus.groovy.ast.expr.GStringExpression.visit(GStringExpression.java:54)
	at org.codehaus.groovy.ast.CodeVisitorSupport.visitReturnStatement(CodeVisitorSupport.java:126)
	at org.codehaus.groovy.ast.ClassCodeVisitorSupport.visitReturnStatement(ClassCodeVisitorSupport.java:212)
	at org.codehaus.groovy.ast.stmt.ReturnStatement.visit(ReturnStatement.java:49)
	at org.codehaus.groovy.ast.CodeVisitorSupport.visitClosureExpression(CodeVisitorSupport.java:227)
	at org.codehaus.groovy.ast.expr.ClosureExpression.visit(ClosureExpression.java:49)
	at org.codehaus.groovy.ast.CodeVisitorSupport.visitListOfExpressions(CodeVisitorSupport.java:326)
	at org.codehaus.groovy.ast.CodeVisitorSupport.visitTupleExpression(CodeVisitorSupport.java:231)
	at org.codehaus.groovy.ast.CodeVisitorSupport.visitArgumentlistExpression(CodeVisitorSupport.java:336)
                */
               transformExpressionList(iGStringExpression.getValues(), "iGStringExpression.getValues()")
               blackBoxEngine.methodResult("iGStringExpression", iGStringExpression)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       protected void visitListOfExpressions(List<? extends Expression> iExpressionList) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitListOfExpressions", ["iExpressionList": iExpressionList])
           try {
               transformExpressionList(iExpressionList, "iExpressionList")
               blackBoxEngine.methodResult("iExpressionList", iExpressionList)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       void visitCatchStatement(CatchStatement iCatchStatement) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitCatchStatement", ["iCatchStatement": iCatchStatement])
           try {
               iCatchStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iCatchStatement.getCode(), blackBoxLevel, "iCatchStatement.getCode()")))
               blackBoxEngine.methodResult("iCatchStatement", iCatchStatement)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       void visitArgumentlistExpression(ArgumentListExpression iArgumentListExpression) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitArgumentlistExpression", ["iArgumentListExpression": iArgumentListExpression])
           try {
               transformExpressionList(iArgumentListExpression.getExpressions(), "iArgumentListExpression.getExpressions()")
               blackBoxEngine.methodResult("iArgumentListExpression", iArgumentListExpression)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

       @Override
       void visitClosureListExpression(ClosureListExpression iClosureListExpression) {
           blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitClosureListExpression", ["iClosureListExpression": iClosureListExpression])
           try {
               transformExpressionList(iClosureListExpression.getExpressions(), "iClosureListExpression.getExpressions()")
               blackBoxEngine.methodResult("iClosureListExpression", iClosureListExpression)
           } catch (Throwable throwable) {
               blackBoxEngine.exception(throwable)
               throw throwable
           } finally {
               blackBoxEngine.executionClose()
           }
       }

}
