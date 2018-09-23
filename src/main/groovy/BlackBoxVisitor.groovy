package groovy

import groovy.transform.ToString
import org.codehaus.groovy.ast.CodeVisitorSupport
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.ArrayExpression
import org.codehaus.groovy.ast.expr.AttributeExpression
import org.codehaus.groovy.ast.expr.BinaryExpression
import org.codehaus.groovy.ast.expr.BitwiseNegationExpression
import org.codehaus.groovy.ast.expr.BooleanExpression
import org.codehaus.groovy.ast.expr.CastExpression
import org.codehaus.groovy.ast.expr.ClassExpression
import org.codehaus.groovy.ast.expr.ClosureExpression
import org.codehaus.groovy.ast.expr.ClosureListExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.ConstructorCallExpression
import org.codehaus.groovy.ast.expr.DeclarationExpression
import org.codehaus.groovy.ast.expr.ElvisOperatorExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.ast.expr.FieldExpression
import org.codehaus.groovy.ast.expr.GStringExpression
import org.codehaus.groovy.ast.expr.ListExpression
import org.codehaus.groovy.ast.expr.MapEntryExpression
import org.codehaus.groovy.ast.expr.MapExpression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.expr.MethodPointerExpression
import org.codehaus.groovy.ast.expr.NotExpression
import org.codehaus.groovy.ast.expr.PostfixExpression
import org.codehaus.groovy.ast.expr.PrefixExpression
import org.codehaus.groovy.ast.expr.PropertyExpression
import org.codehaus.groovy.ast.expr.RangeExpression
import org.codehaus.groovy.ast.expr.SpreadExpression
import org.codehaus.groovy.ast.expr.SpreadMapExpression
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression
import org.codehaus.groovy.ast.expr.TernaryExpression
import org.codehaus.groovy.ast.expr.TupleExpression
import org.codehaus.groovy.ast.expr.UnaryMinusExpression
import org.codehaus.groovy.ast.expr.UnaryPlusExpression
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.ast.stmt.AssertStatement
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.BreakStatement
import org.codehaus.groovy.ast.stmt.CaseStatement
import org.codehaus.groovy.ast.stmt.CatchStatement
import org.codehaus.groovy.ast.stmt.ContinueStatement
import org.codehaus.groovy.ast.stmt.DoWhileStatement
import org.codehaus.groovy.ast.stmt.EmptyStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.ForStatement
import org.codehaus.groovy.ast.stmt.IfStatement
import org.codehaus.groovy.ast.stmt.ReturnStatement
import org.codehaus.groovy.ast.stmt.SwitchStatement
import org.codehaus.groovy.ast.stmt.SynchronizedStatement
import org.codehaus.groovy.ast.stmt.ThrowStatement
import org.codehaus.groovy.ast.stmt.TryCatchStatement
import org.codehaus.groovy.ast.stmt.WhileStatement
import org.codehaus.groovy.classgen.BytecodeExpression

@ToString(includeNames = true, includeFields = true)
class BlackBoxVisitor extends CodeVisitorSupport {


    final static String PCLASSSIMPLENAME= this.getClass().getSimpleName()
    final static String PPACKAGENAME= this.getClass().getPackage().getName()
    final BlackBoxEngine blackBoxEngine = BlackBoxEngine.getInstance()

    @Override
    void visitBlockStatement(BlockStatement iBlockStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitBlockStatement", ["block":iBlockStatement])
        try {
            /*List<Statement> statements = iBlockStatement.getStatements().getClass().newInstance() as List<Statement>
            for (Statement statement : iBlockStatement.getStatements()) {
                statements.add(blackBoxEngine.decorateStatement(statement))
            }
            iBlockStatement.getStatements().clear()
            iBlockStatement.getStatements().addAll(statements)*/
            blackBoxEngine.result("iBlockStatement", iBlockStatement)
            super.visitBlockStatement(iBlockStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitForLoop(ForStatement iForStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitForLoop", ["forLoop": iForStatement])
        try {
            blackBoxEngine.result("iForStatement", iForStatement)
            super.visitForLoop(iForStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    @Override
    void visitWhileLoop(WhileStatement iWhileStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitWhileLoop", ["loop": iWhileStatement])
        try {
            blackBoxEngine.result("iWhileStatement", iWhileStatement)
            super.visitWhileLoop(iWhileStatement)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
        blackBoxEngine.result("iWhileStatement", iWhileStatement)
        super.visitWhileLoop(iWhileStatement)
    }

    @Override
    void visitDoWhileLoop(DoWhileStatement iDoWhileStatement) {
        blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "visitDoWhileLoop", ["loop": iDoWhileStatement])
        try {
            blackBoxEngine.result("iDoWhileStatement", iDoWhileStatement)
            super.visitDoWhileLoop(iDoWhileStatement)
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
            blackBoxEngine.result("iIfStatement", iIfStatement)
            super.visitIfElse(iIfStatement)
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
            blackBoxEngine.result("iExpressionStatement", iExpressionStatement)
            super.visitExpressionStatement(iExpressionStatement)
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
            blackBoxEngine.result("iReturnStatement", iReturnStatement)
            super.visitReturnStatement(iReturnStatement)
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
            blackBoxEngine.result("iAssertStatement", iAssertStatement)
            super.visitAssertStatement(iAssertStatement)
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
            blackBoxEngine.result("iTryCatchStatement", iTryCatchStatement)
            super.visitTryCatchFinally(iTryCatchStatement)
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
            blackBoxEngine.result("iEmptyStatement", iEmptyStatement)
            super.visitEmptyStatement(iEmptyStatement)
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
            blackBoxEngine.result("iSwitchStatement", iSwitchStatement)
            super.visitSwitch(iSwitchStatement)
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
            blackBoxEngine.result("iCaseStatement", iCaseStatement)
            super.visitCaseStatement(iCaseStatement)
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
            blackBoxEngine.result("iBreakStatement", iBreakStatement)
            super.visitBreakStatement(iBreakStatement)
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
            blackBoxEngine.result("iContinueStatement", iContinueStatement)
            super.visitContinueStatement(iContinueStatement)
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
            blackBoxEngine.result("iSynchronizedStatement", iSynchronizedStatement)
            super.visitSynchronizedStatement(iSynchronizedStatement)
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
            blackBoxEngine.result("iThrowStatement", iThrowStatement)
            super.visitThrowStatement(iThrowStatement)
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
            blackBoxEngine.result("iMethodCallExpression", iMethodCallExpression)
            super.visitMethodCallExpression(iMethodCallExpression)
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
            blackBoxEngine.result("iStaticMethodCallExpression", iStaticMethodCallExpression)
            super.visitStaticMethodCallExpression(iStaticMethodCallExpression)
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
            blackBoxEngine.result("iConstructorCallExpression", iConstructorCallExpression)
            super.visitConstructorCallExpression(iConstructorCallExpression)
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
            blackBoxEngine.result("iBinaryExpression", iBinaryExpression)
            super.visitBinaryExpression(iBinaryExpression)
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
            blackBoxEngine.result("iTernaryExpression", iTernaryExpression)
            super.visitTernaryExpression(iTernaryExpression)
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
            blackBoxEngine.result("iElvisOperatorExpression", iElvisOperatorExpression)
            super.visitShortTernaryExpression(iElvisOperatorExpression)
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
            blackBoxEngine.result("iPostfixExpression", iPostfixExpression)
            super.visitPostfixExpression(iPostfixExpression)
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
            blackBoxEngine.result("iPrefixExpression", iPrefixExpression)
            super.visitPrefixExpression(iPrefixExpression)
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
            blackBoxEngine.result("iBooleanExpression", iBooleanExpression)
            super.visitBooleanExpression(iBooleanExpression)
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
            blackBoxEngine.result("iNotExpression", iNotExpression)
            super.visitNotExpression(iNotExpression)
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
            blackBoxEngine.result("iClosureExpression", iClosureExpression)
            super.visitClosureExpression(iClosureExpression)
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
            blackBoxEngine.result("iTupleExpression", iTupleExpression)
            super.visitTupleExpression(iTupleExpression)
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
            blackBoxEngine.result("iListExpression", iListExpression)
            super.visitListExpression(iListExpression)
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
            blackBoxEngine.result("iArrayExpression", iArrayExpression)
            super.visitArrayExpression(iArrayExpression)
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
            blackBoxEngine.result("iMapExpression", iMapExpression)
            super.visitMapExpression(iMapExpression)
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
            blackBoxEngine.result("iMapEntryExpression", iMapEntryExpression)
            super.visitMapEntryExpression(iMapEntryExpression)
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
            blackBoxEngine.result("iRangeExpression", iRangeExpression)
            super.visitRangeExpression(iRangeExpression)
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
            super.visitSpreadExpression(iSpreadExpression)
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
            super.visitSpreadMapExpression(iSpreadMapExpression)
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
            super.visitMethodPointerExpression(iMethodPointerExpression)
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
            super.visitUnaryMinusExpression(iUnaryMinusExpression)
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
            super.visitUnaryPlusExpression(iUnaryPlusExpression)
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
            super.visitBitwiseNegationExpression(iBitwiseNegationExpression)
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
            super.visitCastExpression(iCastExpression)
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
            super.visitConstantExpression(iConstantExpression)
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
            super.visitClassExpression(iClassExpression)
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
            super.visitVariableExpression(iVariableExpression)
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
            super.visitDeclarationExpression(iDeclarationExpression)
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
            super.visitPropertyExpression(iPropertyExpression)
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
            super.visitAttributeExpression(iAttributeExpression)
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
            super.visitFieldExpression(iFieldExpression)
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
            super.visitGStringExpression(iGStringExpression)
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
            super.visitListOfExpressions(iExpressionList)
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
            super.visitCatchStatement(iCatchStatement)
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
            super.visitArgumentlistExpression(iArgumentListExpression)
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
            super.visitClosureListExpression(iClosureListExpression)
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
            super.visitBytecodeExpression(iBytecodeExpression)
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }
}
