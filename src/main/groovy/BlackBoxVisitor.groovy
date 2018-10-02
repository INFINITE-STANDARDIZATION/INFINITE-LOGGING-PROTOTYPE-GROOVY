package groovy

import groovy.transform.ToString
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.CodeVisitorSupport
import org.codehaus.groovy.ast.VariableScope
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.tools.GeneralUtils
import org.codehaus.groovy.classgen.BytecodeExpression

@ToString(includeNames = true, includeFields = true)
class BlackBoxVisitor extends CodeVisitorSupport {


    final String PCLASSSIMPLENAME = this.getClass().getSimpleName()
    final String PPACKAGENAME = this.getClass().getPackage().getName()
    final BlackBoxEngine blackBoxEngine = BlackBoxEngine.getInstance()
    BlackBoxLevel blackBoxLevel
    AnnotationNode annotationNode
    BlackBoxTransformation blackBoxTransformation

    BlackBoxVisitor(BlackBoxLevel iBlackBoxLevel, AnnotationNode iAnnotationNode, BlackBoxTransformation iBlackBoxTransformation) {
        blackBoxLevel = iBlackBoxLevel
        annotationNode = iAnnotationNode
        blackBoxTransformation = iBlackBoxTransformation
    }

    @Override
    void visitBlockStatement(BlockStatement iBlockStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitBlockStatement", ["iBlockStatement": iBlockStatement])
        try {
            decorateStatementList(iBlockStatement.getStatements())
            blackBoxEngine.result("iBlockStatement", iBlockStatement)
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
            iForStatement.setCollectionExpression(blackBoxTransformation.decorateExpression(iForStatement.getCollectionExpression(), blackBoxLevel))
            iForStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(iForStatement.getLoopBlock(), blackBoxLevel)))
            blackBoxEngine.result("iForStatement", iForStatement)
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
            iWhileStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.decorateExpression(iWhileStatement.getBooleanExpression(), blackBoxLevel)))
            iWhileStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(iWhileStatement.getLoopBlock(), blackBoxLevel)))
            blackBoxEngine.result("iWhileStatement", iWhileStatement)
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
            iDoWhileStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.decorateExpression(iDoWhileStatement.getBooleanExpression(), blackBoxLevel)))
            iDoWhileStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(iDoWhileStatement.getLoopBlock(), blackBoxLevel)))
            blackBoxEngine.result("iDoWhileStatement", iDoWhileStatement)
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
            iIfStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.decorateExpression(iIfStatement.getBooleanExpression(), blackBoxLevel)))
            iIfStatement.setIfBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(iIfStatement.getIfBlock(), blackBoxLevel)))
            iIfStatement.setElseBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(iIfStatement.getElseBlock(), blackBoxLevel)))
            blackBoxEngine.result("iIfStatement", iIfStatement)
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
            iExpressionStatement.setExpression(blackBoxTransformation.decorateExpression(iExpressionStatement.getExpression(), blackBoxLevel))
            blackBoxEngine.result("iExpressionStatement", iExpressionStatement)
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
            iReturnStatement.setExpression(blackBoxTransformation.decorateExpression(iReturnStatement.getExpression(), blackBoxLevel))
            blackBoxEngine.result("iReturnStatement", iReturnStatement)
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
            iAssertStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.decorateExpression(iAssertStatement.getBooleanExpression(), blackBoxLevel)))
            iAssertStatement.setMessageExpression(blackBoxTransformation.decorateExpression(iAssertStatement.getMessageExpression(), blackBoxLevel))
            blackBoxEngine.result("iAssertStatement", iAssertStatement)
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
            iTryCatchStatement.setTryStatement(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(iTryCatchStatement.getTryStatement(), blackBoxLevel)))
            for (CatchStatement catchStatement : iTryCatchStatement.getCatchStatements()) {
                catchStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(catchStatement.getCode(), blackBoxLevel)))
            }
            iTryCatchStatement.setFinallyStatement(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(iTryCatchStatement.getFinallyStatement(), blackBoxLevel)))
            blackBoxEngine.result("iTryCatchStatement", iTryCatchStatement)
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
            blackBoxEngine.result("iEmptyStatement", iEmptyStatement)
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
            iSwitchStatement.setExpression(blackBoxTransformation.decorateExpression(iSwitchStatement.getExpression(), blackBoxLevel))
            for (CaseStatement caseStatement : iSwitchStatement.getCaseStatements()) {
                caseStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(caseStatement.getCode(), blackBoxLevel)))
            }
            iSwitchStatement.setDefaultStatement(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(iSwitchStatement.getDefaultStatement(), blackBoxLevel)))
            blackBoxEngine.result("iSwitchStatement", iSwitchStatement)
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
            iCaseStatement.setExpression(blackBoxTransformation.decorateExpression(iCaseStatement.getExpression(), blackBoxLevel))
            iCaseStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(iCaseStatement.getCode(), blackBoxLevel)))
            blackBoxEngine.result("iCaseStatement", iCaseStatement)
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
            blackBoxEngine.result("iBreakStatement", iBreakStatement)
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
            blackBoxEngine.result("iContinueStatement", iContinueStatement)
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
            iSynchronizedStatement.setExpression(blackBoxTransformation.decorateExpression(iSynchronizedStatement.getExpression(), blackBoxLevel))
            iSynchronizedStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(iSynchronizedStatement.getCode(), blackBoxLevel)))
            blackBoxEngine.result("iSynchronizedStatement", iSynchronizedStatement)
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
            iThrowStatement.setExpression(blackBoxTransformation.decorateExpression(iThrowStatement.getExpression(), blackBoxLevel))
            blackBoxEngine.result("iThrowStatement", iThrowStatement)
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
            iMethodCallExpression.setObjectExpression(blackBoxTransformation.decorateExpression(iMethodCallExpression.getObjectExpression(), blackBoxLevel))
            //iMethodCallExpression.setArguments(blackBoxTransformation.decorateExpression(iMethodCallExpression.getArguments(), blackBoxLevel))
            blackBoxEngine.result("iMethodCallExpression", iMethodCallExpression)
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
//todo: improve Arguments handling (there is no setter method)
            blackBoxEngine.result("iStaticMethodCallExpression", iStaticMethodCallExpression)
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
            blackBoxEngine.result("iConstructorCallExpression", iConstructorCallExpression)
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
            iBinaryExpression.setRightExpression(blackBoxTransformation.decorateExpression(iBinaryExpression.getRightExpression(), blackBoxLevel))
            blackBoxEngine.result("iBinaryExpression", iBinaryExpression)
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
            blackBoxEngine.result("iTernaryExpression", iTernaryExpression)
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
            blackBoxEngine.result("iElvisOperatorExpression", iElvisOperatorExpression)
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
            blackBoxEngine.result("iPostfixExpression", iPostfixExpression)
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
            blackBoxEngine.result("iPrefixExpression", iPrefixExpression)
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
            blackBoxEngine.result("iBooleanExpression", iBooleanExpression)
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
            blackBoxEngine.result("iNotExpression", iNotExpression)
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
            iClosureExpression.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.decorateStatement(iClosureExpression.getCode(), blackBoxLevel)))
            blackBoxEngine.result("iClosureExpression", iClosureExpression)
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
            decorateExpressionList(iTupleExpression.getExpressions())
            blackBoxEngine.result("iTupleExpression", iTupleExpression)
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
            decorateExpressionList(iListExpression.getExpressions())
            blackBoxEngine.result("iListExpression", iListExpression)
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
            decorateExpressionList(iArrayExpression.getExpressions())
            decorateExpressionList(iArrayExpression.getSizeExpression())c
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    void decorateStatementList(List<Statement> statementList) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "decorateStatementList", ["statementList": statementList])
        try {
            List<Statement> decoratedStatementsList = statementList.getClass().newInstance() as List<Statement>
            for (Statement statement : statementList) {
                decoratedStatementsList.addAll(blackBoxTransformation.decorateStatement(statement, blackBoxLevel))
            }
            statementList.clear()
            statementList.addAll(decoratedStatementsList)
            blackBoxEngine.result("decoratedStatementsList", decoratedStatementsList)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    void decorateExpressionList(List<Expression> expressionList) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "decorateExpressionList", ["expressionList": expressionList])
        try {
            List<Expression> decoratedExpressionsList = expressionList.getClass().newInstance() as List<Expression>
            for (Expression expression : expressionList) {
                decoratedExpressionsList.addAll(blackBoxTransformation.decorateExpression(expression, blackBoxLevel))
            }
            expressionList.clear()
            expressionList.addAll(decoratedExpressionsList)
            blackBoxEngine.result("decoratedExpressionsList", decoratedExpressionsList)
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
            super.visitMapExpression(iMapExpression) //todo: improve
            blackBoxEngine.result("iMapExpression", iMapExpression)
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
            iMapEntryExpression.setKeyExpression(blackBoxTransformation.decorateExpression(iMapEntryExpression.getKeyExpression(), blackBoxLevel))
            iMapEntryExpression.setValueExpression(blackBoxTransformation.decorateExpression(iMapEntryExpression.getValueExpression(), blackBoxLevel))
            blackBoxEngine.result("iMapEntryExpression", iMapEntryExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

 /*
    @Override
    void visitRangeExpression(RangeExpression iRangeExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitRangeExpression", ["iRangeExpression": iRangeExpression])
        try {
            blackBoxEngine.result("iRangeExpression", iRangeExpression)
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
            blackBoxEngine.result("iSpreadExpression", iSpreadExpression)
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
            blackBoxEngine.result("iSpreadMapExpression", iSpreadMapExpression)
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
            blackBoxEngine.result("iMethodPointerExpression", iMethodPointerExpression)
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
            blackBoxEngine.result("iUnaryMinusExpression", iUnaryMinusExpression)
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
            blackBoxEngine.result("iUnaryPlusExpression", iUnaryPlusExpression)
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
            blackBoxEngine.result("iBitwiseNegationExpression", iBitwiseNegationExpression)
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
            blackBoxEngine.result("iCastExpression", iCastExpression)
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
            blackBoxEngine.result("iConstantExpression", iConstantExpression)
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
            blackBoxEngine.result("iClassExpression", iClassExpression)
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
            blackBoxEngine.result("iVariableExpression", iVariableExpression)
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
            blackBoxEngine.result("iDeclarationExpression", iDeclarationExpression)
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
            blackBoxEngine.result("iPropertyExpression", iPropertyExpression)
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
            blackBoxEngine.result("iAttributeExpression", iAttributeExpression)
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
            blackBoxEngine.result("iFieldExpression", iFieldExpression)
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
            blackBoxEngine.result("iGStringExpression", iGStringExpression)
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
            blackBoxEngine.result("iExpressionList", iExpressionList)
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
            blackBoxEngine.result("iCatchStatement", iCatchStatement)
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
            blackBoxEngine.result("iArgumentListExpression", iArgumentListExpression)
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
            blackBoxEngine.result("iClosureListExpression", iClosureListExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitBytecodeExpression(BytecodeExpression iBytecodeExpression) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitBytecodeExpression", ["iBytecodeExpression": iBytecodeExpression])
        try {
            blackBoxEngine.result("iBytecodeExpression", iBytecodeExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    */
}
