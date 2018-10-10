package groovy

import groovy.visit.*

class TestCasesNormal {

    public static void main(String[] args) {
        new TestCasesNormal().run()
    }

    void run() {
        BlackBoxEngine.getInstance().initRootAstNode() //just removing compilation data from log
        misc()
        new VisitBlockStatement().visitBlockStatementNoneLevel()
        new VisitBlockStatement().visitBlockStatementMethodErrorLevel()
        new VisitBlockStatement().visitBlockStatementMethodLevel()
        new VisitBlockStatement().visitBlockStatementStatementLevel()
        new VisitBlockStatement().visitBlockStatementExpressionLevel()
        new VisitForLoop().visitForLoopNoneLevel()
        new VisitForLoop().visitForLoopMethodErrorLevel()
        new VisitForLoop().visitForLoopMethodLevel()
        new VisitForLoop().visitForLoopStatementLevel()
        new VisitForLoop().visitForLoopExpressionLevel()
        new VisitWhileLoop().visitWhileLoopNoneLevel()
        new VisitWhileLoop().visitWhileLoopMethodErrorLevel()
        new VisitWhileLoop().visitWhileLoopMethodLevel()
        new VisitWhileLoop().visitWhileLoopStatementLevel()
        new VisitWhileLoop().visitWhileLoopExpressionLevel()
        new VisitDoWhileLoop().visitDoWhileLoopNoneLevel()
        new VisitDoWhileLoop().visitDoWhileLoopMethodErrorLevel()
        new VisitDoWhileLoop().visitDoWhileLoopMethodLevel()
        new VisitDoWhileLoop().visitDoWhileLoopStatementLevel()
        new VisitDoWhileLoop().visitDoWhileLoopExpressionLevel()
        new VisitIfElse().visitIfElseNoneLevel()
        new VisitIfElse().visitIfElseMethodErrorLevel()
        new VisitIfElse().visitIfElseMethodLevel()
        new VisitIfElse().visitIfElseStatementLevel()
        new VisitIfElse().visitIfElseExpressionLevel()
        new VisitExpressionStatement().visitExpressionStatementNoneLevel()
        new VisitExpressionStatement().visitExpressionStatementMethodErrorLevel()
        new VisitExpressionStatement().visitExpressionStatementMethodLevel()
        new VisitExpressionStatement().visitExpressionStatementStatementLevel()
        new VisitExpressionStatement().visitExpressionStatementExpressionLevel()
        new VisitReturnStatement().visitReturnStatementNoneLevel()
        new VisitReturnStatement().visitReturnStatementMethodErrorLevel()
        new VisitReturnStatement().visitReturnStatementMethodLevel()
        new VisitReturnStatement().visitReturnStatementStatementLevel()
        new VisitReturnStatement().visitReturnStatementExpressionLevel()
        new VisitAssertStatement().visitAssertStatementNoneLevel()
        new VisitAssertStatement().visitAssertStatementMethodErrorLevel()
        new VisitAssertStatement().visitAssertStatementMethodLevel()
        new VisitAssertStatement().visitAssertStatementStatementLevel()
        new VisitAssertStatement().visitAssertStatementExpressionLevel()
        new VisitTryCatchFinally().visitTryCatchFinallyNoneLevel()
        new VisitTryCatchFinally().visitTryCatchFinallyMethodErrorLevel()
        new VisitTryCatchFinally().visitTryCatchFinallyMethodLevel()
        new VisitTryCatchFinally().visitTryCatchFinallyStatementLevel()
        new VisitTryCatchFinally().visitTryCatchFinallyExpressionLevel()
        new VisitEmptyStatement().visitEmptyStatementNoneLevel()
        new VisitEmptyStatement().visitEmptyStatementMethodErrorLevel()
        new VisitEmptyStatement().visitEmptyStatementMethodLevel()
        new VisitEmptyStatement().visitEmptyStatementStatementLevel()
        new VisitEmptyStatement().visitEmptyStatementExpressionLevel()
        new VisitSwitch().visitSwitchNoneLevel()
        new VisitSwitch().visitSwitchMethodErrorLevel()
        new VisitSwitch().visitSwitchMethodLevel()
        new VisitSwitch().visitSwitchStatementLevel()
        new VisitSwitch().visitSwitchExpressionLevel()
        new VisitCaseStatement().visitCaseStatementNoneLevel()
        new VisitCaseStatement().visitCaseStatementMethodErrorLevel()
        new VisitCaseStatement().visitCaseStatementMethodLevel()
        new VisitCaseStatement().visitCaseStatementStatementLevel()
        new VisitCaseStatement().visitCaseStatementExpressionLevel()
        new VisitBreakStatement().visitBreakStatementNoneLevel()
        new VisitBreakStatement().visitBreakStatementMethodErrorLevel()
        new VisitBreakStatement().visitBreakStatementMethodLevel()
        new VisitBreakStatement().visitBreakStatementStatementLevel()
        new VisitBreakStatement().visitBreakStatementExpressionLevel()
        new VisitContinueStatement().visitContinueStatementNoneLevel()
        new VisitContinueStatement().visitContinueStatementMethodErrorLevel()
        new VisitContinueStatement().visitContinueStatementMethodLevel()
        new VisitContinueStatement().visitContinueStatementStatementLevel()
        new VisitContinueStatement().visitContinueStatementExpressionLevel()
        new VisitSynchronizedStatement().visitSynchronizedStatementNoneLevel()
        new VisitSynchronizedStatement().visitSynchronizedStatementMethodErrorLevel()
        new VisitSynchronizedStatement().visitSynchronizedStatementMethodLevel()
        new VisitSynchronizedStatement().visitSynchronizedStatementStatementLevel()
        new VisitSynchronizedStatement().visitSynchronizedStatementExpressionLevel()
        new VisitThrowStatement().visitThrowStatementNoneLevel()
        new VisitThrowStatement().visitThrowStatementMethodErrorLevel()
        new VisitThrowStatement().visitThrowStatementMethodLevel()
        new VisitThrowStatement().visitThrowStatementStatementLevel()
        new VisitThrowStatement().visitThrowStatementExpressionLevel()
        new VisitMethodCallExpression().visitMethodCallExpressionNoneLevel()
        new VisitMethodCallExpression().visitMethodCallExpressionMethodErrorLevel()
        new VisitMethodCallExpression().visitMethodCallExpressionMethodLevel()
        new VisitMethodCallExpression().visitMethodCallExpressionStatementLevel()
        new VisitMethodCallExpression().visitMethodCallExpressionExpressionLevel()
        new VisitStaticMethodCallExpression().visitStaticMethodCallExpressionNoneLevel()
        new VisitStaticMethodCallExpression().visitStaticMethodCallExpressionMethodErrorLevel()
        new VisitStaticMethodCallExpression().visitStaticMethodCallExpressionMethodLevel()
        new VisitStaticMethodCallExpression().visitStaticMethodCallExpressionStatementLevel()
        new VisitStaticMethodCallExpression().visitStaticMethodCallExpressionExpressionLevel()
        new VisitConstructorCallExpression().visitConstructorCallExpressionNoneLevel()
        new VisitConstructorCallExpression().visitConstructorCallExpressionMethodErrorLevel()
        new VisitConstructorCallExpression().visitConstructorCallExpressionMethodLevel()
        new VisitConstructorCallExpression().visitConstructorCallExpressionStatementLevel()
        new VisitConstructorCallExpression().visitConstructorCallExpressionExpressionLevel()
        new VisitBinaryExpression().visitBinaryExpressionNoneLevel()
        new VisitBinaryExpression().visitBinaryExpressionMethodErrorLevel()
        new VisitBinaryExpression().visitBinaryExpressionMethodLevel()
        new VisitBinaryExpression().visitBinaryExpressionStatementLevel()
        new VisitBinaryExpression().visitBinaryExpressionExpressionLevel()
        new VisitTernaryExpression().visitTernaryExpressionNoneLevel()
        new VisitTernaryExpression().visitTernaryExpressionMethodErrorLevel()
        new VisitTernaryExpression().visitTernaryExpressionMethodLevel()
        new VisitTernaryExpression().visitTernaryExpressionStatementLevel()
        new VisitTernaryExpression().visitTernaryExpressionExpressionLevel()
        new VisitShortTernaryExpression().visitShortTernaryExpressionNoneLevel()
        new VisitShortTernaryExpression().visitShortTernaryExpressionMethodErrorLevel()
        new VisitShortTernaryExpression().visitShortTernaryExpressionMethodLevel()
        new VisitShortTernaryExpression().visitShortTernaryExpressionStatementLevel()
        new VisitShortTernaryExpression().visitShortTernaryExpressionExpressionLevel()
        new VisitPostfixExpression().visitPostfixExpressionNoneLevel()
        new VisitPostfixExpression().visitPostfixExpressionMethodErrorLevel()
        new VisitPostfixExpression().visitPostfixExpressionMethodLevel()
        new VisitPostfixExpression().visitPostfixExpressionStatementLevel()
        new VisitPostfixExpression().visitPostfixExpressionExpressionLevel()
        new VisitPrefixExpression().visitPrefixExpressionNoneLevel()
        new VisitPrefixExpression().visitPrefixExpressionMethodErrorLevel()
        new VisitPrefixExpression().visitPrefixExpressionMethodLevel()
        new VisitPrefixExpression().visitPrefixExpressionStatementLevel()
        new VisitPrefixExpression().visitPrefixExpressionExpressionLevel()
        new VisitBooleanExpression().visitBooleanExpressionNoneLevel()
        new VisitBooleanExpression().visitBooleanExpressionMethodErrorLevel()
        new VisitBooleanExpression().visitBooleanExpressionMethodLevel()
        new VisitBooleanExpression().visitBooleanExpressionStatementLevel()
        new VisitBooleanExpression().visitBooleanExpressionExpressionLevel()
        new VisitNotExpression().visitNotExpressionNoneLevel()
        new VisitNotExpression().visitNotExpressionMethodErrorLevel()
        new VisitNotExpression().visitNotExpressionMethodLevel()
        new VisitNotExpression().visitNotExpressionStatementLevel()
        new VisitNotExpression().visitNotExpressionExpressionLevel()
        new VisitClosureExpression().visitClosureExpressionNoneLevel()
        new VisitClosureExpression().visitClosureExpressionMethodErrorLevel()
        new VisitClosureExpression().visitClosureExpressionMethodLevel()
        new VisitClosureExpression().visitClosureExpressionStatementLevel()
        new VisitClosureExpression().visitClosureExpressionExpressionLevel()
        new VisitTupleExpression().visitTupleExpressionNoneLevel()
        new VisitTupleExpression().visitTupleExpressionMethodErrorLevel()
        new VisitTupleExpression().visitTupleExpressionMethodLevel()
        new VisitTupleExpression().visitTupleExpressionStatementLevel()
        new VisitTupleExpression().visitTupleExpressionExpressionLevel()
        new VisitListExpression().visitListExpressionNoneLevel()
        new VisitListExpression().visitListExpressionMethodErrorLevel()
        new VisitListExpression().visitListExpressionMethodLevel()
        new VisitListExpression().visitListExpressionStatementLevel()
        new VisitListExpression().visitListExpressionExpressionLevel()
        new VisitArrayExpression().visitArrayExpressionNoneLevel()
        new VisitArrayExpression().visitArrayExpressionMethodErrorLevel()
        new VisitArrayExpression().visitArrayExpressionMethodLevel()
        new VisitArrayExpression().visitArrayExpressionStatementLevel()
        new VisitArrayExpression().visitArrayExpressionExpressionLevel()
        new VisitMapExpression().visitMapExpressionNoneLevel()
        new VisitMapExpression().visitMapExpressionMethodErrorLevel()
        new VisitMapExpression().visitMapExpressionMethodLevel()
        new VisitMapExpression().visitMapExpressionStatementLevel()
        new VisitMapExpression().visitMapExpressionExpressionLevel()
        new VisitMapEntryExpression().visitMapEntryExpressionNoneLevel()
        new VisitMapEntryExpression().visitMapEntryExpressionMethodErrorLevel()
        new VisitMapEntryExpression().visitMapEntryExpressionMethodLevel()
        new VisitMapEntryExpression().visitMapEntryExpressionStatementLevel()
        new VisitMapEntryExpression().visitMapEntryExpressionExpressionLevel()
        new VisitRangeExpression().visitRangeExpressionNoneLevel()
        new VisitRangeExpression().visitRangeExpressionMethodErrorLevel()
        new VisitRangeExpression().visitRangeExpressionMethodLevel()
        new VisitRangeExpression().visitRangeExpressionStatementLevel()
        new VisitRangeExpression().visitRangeExpressionExpressionLevel()
        new VisitSpreadExpression().visitSpreadExpressionNoneLevel()
        new VisitSpreadExpression().visitSpreadExpressionMethodErrorLevel()
        new VisitSpreadExpression().visitSpreadExpressionMethodLevel()
        new VisitSpreadExpression().visitSpreadExpressionStatementLevel()
        new VisitSpreadExpression().visitSpreadExpressionExpressionLevel()
        new VisitSpreadMapExpression().visitSpreadMapExpressionNoneLevel()
        new VisitSpreadMapExpression().visitSpreadMapExpressionMethodErrorLevel()
        new VisitSpreadMapExpression().visitSpreadMapExpressionMethodLevel()
        new VisitSpreadMapExpression().visitSpreadMapExpressionStatementLevel()
        new VisitSpreadMapExpression().visitSpreadMapExpressionExpressionLevel()
        new VisitMethodPointerExpression().visitMethodPointerExpressionNoneLevel()
        new VisitMethodPointerExpression().visitMethodPointerExpressionMethodErrorLevel()
        new VisitMethodPointerExpression().visitMethodPointerExpressionMethodLevel()
        new VisitMethodPointerExpression().visitMethodPointerExpressionStatementLevel()
        new VisitMethodPointerExpression().visitMethodPointerExpressionExpressionLevel()
        new VisitUnaryMinusExpression().visitUnaryMinusExpressionNoneLevel()
        new VisitUnaryMinusExpression().visitUnaryMinusExpressionMethodErrorLevel()
        new VisitUnaryMinusExpression().visitUnaryMinusExpressionMethodLevel()
        new VisitUnaryMinusExpression().visitUnaryMinusExpressionStatementLevel()
        new VisitUnaryMinusExpression().visitUnaryMinusExpressionExpressionLevel()
        new VisitUnaryPlusExpression().visitUnaryPlusExpressionNoneLevel()
        new VisitUnaryPlusExpression().visitUnaryPlusExpressionMethodErrorLevel()
        new VisitUnaryPlusExpression().visitUnaryPlusExpressionMethodLevel()
        new VisitUnaryPlusExpression().visitUnaryPlusExpressionStatementLevel()
        new VisitUnaryPlusExpression().visitUnaryPlusExpressionExpressionLevel()
        new VisitBitwiseNegationExpression().visitBitwiseNegationExpressionNoneLevel()
        new VisitBitwiseNegationExpression().visitBitwiseNegationExpressionMethodErrorLevel()
        new VisitBitwiseNegationExpression().visitBitwiseNegationExpressionMethodLevel()
        new VisitBitwiseNegationExpression().visitBitwiseNegationExpressionStatementLevel()
        new VisitBitwiseNegationExpression().visitBitwiseNegationExpressionExpressionLevel()
        new VisitCastExpression().visitCastExpressionNoneLevel()
        new VisitCastExpression().visitCastExpressionMethodErrorLevel()
        new VisitCastExpression().visitCastExpressionMethodLevel()
        new VisitCastExpression().visitCastExpressionStatementLevel()
        new VisitCastExpression().visitCastExpressionExpressionLevel()
        new VisitConstantExpression().visitConstantExpressionNoneLevel()
        new VisitConstantExpression().visitConstantExpressionMethodErrorLevel()
        new VisitConstantExpression().visitConstantExpressionMethodLevel()
        new VisitConstantExpression().visitConstantExpressionStatementLevel()
        new VisitConstantExpression().visitConstantExpressionExpressionLevel()
        new VisitClassExpression().visitClassExpressionNoneLevel()
        new VisitClassExpression().visitClassExpressionMethodErrorLevel()
        new VisitClassExpression().visitClassExpressionMethodLevel()
        new VisitClassExpression().visitClassExpressionStatementLevel()
        new VisitClassExpression().visitClassExpressionExpressionLevel()
        new VisitVariableExpression().visitVariableExpressionNoneLevel()
        new VisitVariableExpression().visitVariableExpressionMethodErrorLevel()
        new VisitVariableExpression().visitVariableExpressionMethodLevel()
        new VisitVariableExpression().visitVariableExpressionStatementLevel()
        new VisitVariableExpression().visitVariableExpressionExpressionLevel()
        new VisitDeclarationExpression().visitDeclarationExpressionNoneLevel()
        new VisitDeclarationExpression().visitDeclarationExpressionMethodErrorLevel()
        new VisitDeclarationExpression().visitDeclarationExpressionMethodLevel()
        new VisitDeclarationExpression().visitDeclarationExpressionStatementLevel()
        new VisitDeclarationExpression().visitDeclarationExpressionExpressionLevel()
        new VisitPropertyExpression().visitPropertyExpressionNoneLevel()
        new VisitPropertyExpression().visitPropertyExpressionMethodErrorLevel()
        new VisitPropertyExpression().visitPropertyExpressionMethodLevel()
        new VisitPropertyExpression().visitPropertyExpressionStatementLevel()
        new VisitPropertyExpression().visitPropertyExpressionExpressionLevel()
        new VisitAttributeExpression().visitAttributeExpressionNoneLevel()
        new VisitAttributeExpression().visitAttributeExpressionMethodErrorLevel()
        new VisitAttributeExpression().visitAttributeExpressionMethodLevel()
        new VisitAttributeExpression().visitAttributeExpressionStatementLevel()
        new VisitAttributeExpression().visitAttributeExpressionExpressionLevel()
        new VisitFieldExpression().visitFieldExpressionNoneLevel()
        new VisitFieldExpression().visitFieldExpressionMethodErrorLevel()
        new VisitFieldExpression().visitFieldExpressionMethodLevel()
        new VisitFieldExpression().visitFieldExpressionStatementLevel()
        new VisitFieldExpression().visitFieldExpressionExpressionLevel()
        new VisitGStringExpression().visitGStringExpressionNoneLevel()
        new VisitGStringExpression().visitGStringExpressionMethodErrorLevel()
        new VisitGStringExpression().visitGStringExpressionMethodLevel()
        new VisitGStringExpression().visitGStringExpressionStatementLevel()
        new VisitGStringExpression().visitGStringExpressionExpressionLevel()
        new VisitListOfExpressions().visitListOfExpressionsNoneLevel()
        new VisitListOfExpressions().visitListOfExpressionsMethodErrorLevel()
        new VisitListOfExpressions().visitListOfExpressionsMethodLevel()
        new VisitListOfExpressions().visitListOfExpressionsStatementLevel()
        new VisitListOfExpressions().visitListOfExpressionsExpressionLevel()
        new VisitCatchStatement().visitCatchStatementNoneLevel()
        new VisitCatchStatement().visitCatchStatementMethodErrorLevel()
        new VisitCatchStatement().visitCatchStatementMethodLevel()
        new VisitCatchStatement().visitCatchStatementStatementLevel()
        new VisitCatchStatement().visitCatchStatementExpressionLevel()
        new VisitArgumentlistExpression().visitArgumentlistExpressionNoneLevel()
        new VisitArgumentlistExpression().visitArgumentlistExpressionMethodErrorLevel()
        new VisitArgumentlistExpression().visitArgumentlistExpressionMethodLevel()
        new VisitArgumentlistExpression().visitArgumentlistExpressionStatementLevel()
        new VisitArgumentlistExpression().visitArgumentlistExpressionExpressionLevel()
        new VisitClosureListExpression().visitClosureListExpressionNoneLevel()
        new VisitClosureListExpression().visitClosureListExpressionMethodErrorLevel()
        new VisitClosureListExpression().visitClosureListExpressionMethodLevel()
        new VisitClosureListExpression().visitClosureListExpressionStatementLevel()
        new VisitClosureListExpression().visitClosureListExpressionExpressionLevel()
    }

    ////////////////////////////

    //@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
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
    }

}

