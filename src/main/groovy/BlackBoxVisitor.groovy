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

    BlackBoxLevel blackBoxLevel
    AnnotationNode annotationNode
    BlackBoxTransformation blackBoxTransformation

    BlackBoxVisitor(BlackBoxLevel iBlackBoxLevel, AnnotationNode iAnnotationNode, BlackBoxTransformation iBlackBoxTransformation) {
        blackBoxLevel = iBlackBoxLevel
        annotationNode = iAnnotationNode
        blackBoxTransformation = iBlackBoxTransformation
    }

    void transformStatementList(List<Statement> iStatementListToTransform, String iSourceNodeName) {
        List<Statement> transformedStatementList = iStatementListToTransform.getClass().newInstance() as List<Statement>
        for (Statement statementToTransform : iStatementListToTransform) {
            transformedStatementList.add(blackBoxTransformation.transformStatement(statementToTransform, iSourceNodeName))
        }
        iStatementListToTransform.clear()
        iStatementListToTransform.addAll(transformedStatementList)
    }

    //todo: log return for all blackbox levels
    void transformExpressionList(List<Expression> iExpressionListToTransform, String iSourceNodeName) {
        List<Expression> transformedExpressionList = iExpressionListToTransform.getClass().newInstance() as List<Expression>
        for (Expression expressionToTransform : iExpressionListToTransform) {
            transformedExpressionList.addAll(blackBoxTransformation.transformExpression(expressionToTransform, iSourceNodeName))
        }
        iExpressionListToTransform.clear()
        iExpressionListToTransform.addAll(transformedExpressionList)
    }

    @Override
    void visitBlockStatement(BlockStatement iBlockStatement) {
        iBlockStatement.origCodeString = blackBoxTransformation.codeString(iBlockStatement)
        super.visitBlockStatement(iBlockStatement)
        transformStatementList(iBlockStatement.getStatements(), "iBlockStatement.getStatements()")
    }

    @Override
    void visitForLoop(ForStatement iForStatement) {
        iForStatement.origCodeString = blackBoxTransformation.codeString(iForStatement)
        super.visitForLoop(iForStatement)
        iForStatement.setCollectionExpression(blackBoxTransformation.transformExpression(iForStatement.getCollectionExpression(), "iForStatement.getCollectionExpression()"))
        iForStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iForStatement.getLoopBlock(), "iForStatement.getLoopBlock()")))
    }

    @Override
    void visitWhileLoop(WhileStatement iWhileStatement) {
        iWhileStatement.origCodeString = blackBoxTransformation.codeString(iWhileStatement)
        super.visitWhileLoop(iWhileStatement)
        iWhileStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.transformExpression(iWhileStatement.getBooleanExpression(), "iWhileStatement.getBooleanExpression()")))
        iWhileStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iWhileStatement.getLoopBlock(), "iWhileStatement.getLoopBlock()")))
    }

    @Override
    void visitDoWhileLoop(DoWhileStatement iDoWhileStatement) {
        iDoWhileStatement.origCodeString = blackBoxTransformation.codeString(iDoWhileStatement)
        super.visitDoWhileLoop(iDoWhileStatement)
        iDoWhileStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.transformExpression(iDoWhileStatement.getBooleanExpression(), "iDoWhileStatement.getBooleanExpression()")))
        iDoWhileStatement.setLoopBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iDoWhileStatement.getLoopBlock(), "iDoWhileStatement.getLoopBlock()")))
    }

    @Override
    void visitIfElse(IfStatement iIfStatement) {
        iIfStatement.origCodeString = blackBoxTransformation.codeString(iIfStatement)
        super.visitIfElse(iIfStatement)
        iIfStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.transformExpression(iIfStatement.getBooleanExpression(), "iIfStatement.getBooleanExpression()")))
        iIfStatement.setIfBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iIfStatement.getIfBlock(), "iIfStatement.getIfBlock()")))
        iIfStatement.setElseBlock(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iIfStatement.getElseBlock(), "iIfStatement.getElseBlock()")))
    }

    @Override
    void visitExpressionStatement(ExpressionStatement iExpressionStatement) {
        iExpressionStatement.origCodeString = blackBoxTransformation.codeString(iExpressionStatement)
        super.visitExpressionStatement(iExpressionStatement)
        iExpressionStatement.setExpression(blackBoxTransformation.transformExpression(iExpressionStatement.getExpression(), "iExpressionStatement.getExpression()"))
    }

    @Override
    void visitReturnStatement(ReturnStatement iReturnStatement) {
        iReturnStatement.origCodeString = blackBoxTransformation.codeString(iReturnStatement)
        super.visitReturnStatement(iReturnStatement)
        iReturnStatement.setExpression(blackBoxTransformation.transformReturnStatementExpression(iReturnStatement.getExpression(), "iReturnStatement.getExpression()", iReturnStatement.origCodeString as String))
    }

    @Override
    void visitAssertStatement(AssertStatement iAssertStatement) {
        iAssertStatement.origCodeString = blackBoxTransformation.codeString(iAssertStatement)
        super.visitAssertStatement(iAssertStatement)
        iAssertStatement.setBooleanExpression(new BooleanExpression(blackBoxTransformation.transformExpression(iAssertStatement.getBooleanExpression(), "iAssertStatement.getBooleanExpression()")))
        iAssertStatement.setMessageExpression(blackBoxTransformation.transformExpression(iAssertStatement.getMessageExpression(), "iAssertStatement.getMessageExpression()"))
    }

    @Override
    void visitTryCatchFinally(TryCatchStatement iTryCatchStatement) {
        iTryCatchStatement.origCodeString = blackBoxTransformation.codeString(iTryCatchStatement)
        super.visitTryCatchFinally(iTryCatchStatement)
        iTryCatchStatement.setTryStatement(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iTryCatchStatement.getTryStatement(), "iTryCatchStatement.getTryStatement()")))
        for (CatchStatement catchStatement : iTryCatchStatement.getCatchStatements()) {
            catchStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(catchStatement.getCode(), "catchStatement.getCode()")))
        }
        iTryCatchStatement.setFinallyStatement(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iTryCatchStatement.getFinallyStatement(), "iTryCatchStatement.getFinallyStatement()")))
    }

    @Override
    protected void visitEmptyStatement(EmptyStatement iEmptyStatement) {
        iEmptyStatement.origCodeString = blackBoxTransformation.codeString(iEmptyStatement)
        super.visitEmptyStatement(iEmptyStatement)

    }

    @Override
    void visitSwitch(SwitchStatement iSwitchStatement) {
        iSwitchStatement.origCodeString = blackBoxTransformation.codeString(iSwitchStatement)
        super.visitSwitch(iSwitchStatement)
        iSwitchStatement.setExpression(blackBoxTransformation.transformExpression(iSwitchStatement.getExpression(), "iSwitchStatement.getExpression()"))
        for (CaseStatement caseStatement : iSwitchStatement.getCaseStatements()) {
            caseStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(caseStatement.getCode(), "caseStatement.getCode()")))
        }
        iSwitchStatement.setDefaultStatement(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iSwitchStatement.getDefaultStatement(), "iSwitchStatement.getDefaultStatement()")))
    }

    @Override
    void visitCaseStatement(CaseStatement iCaseStatement) {
        iCaseStatement.origCodeString = blackBoxTransformation.codeString(iCaseStatement)
        super.visitCaseStatement(iCaseStatement)
        iCaseStatement.setExpression(blackBoxTransformation.transformExpression(iCaseStatement.getExpression(), "iCaseStatement.getExpression()"))
        iCaseStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iCaseStatement.getCode(), "iCaseStatement.getCode()")))
    }

    @Override
    void visitBreakStatement(BreakStatement iBreakStatement) {
        iBreakStatement.origCodeString = blackBoxTransformation.codeString(iBreakStatement)
        super.visitBreakStatement(iBreakStatement)

    }

    @Override
    void visitContinueStatement(ContinueStatement iContinueStatement) {
        iContinueStatement.origCodeString = blackBoxTransformation.codeString(iContinueStatement)
        super.visitContinueStatement(iContinueStatement)

    }

    @Override
    void visitSynchronizedStatement(SynchronizedStatement iSynchronizedStatement) {
        iSynchronizedStatement.origCodeString = blackBoxTransformation.codeString(iSynchronizedStatement)
        super.visitSynchronizedStatement(iSynchronizedStatement)
        iSynchronizedStatement.setExpression(blackBoxTransformation.transformExpression(iSynchronizedStatement.getExpression(), "iSynchronizedStatement.getExpression()"))
        iSynchronizedStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iSynchronizedStatement.getCode(), "iSynchronizedStatement.getCode()")))
    }

    @Override
    void visitThrowStatement(ThrowStatement iThrowStatement) {
        iThrowStatement.origCodeString = blackBoxTransformation.codeString(iThrowStatement)
        super.visitThrowStatement(iThrowStatement)
        iThrowStatement.setExpression(blackBoxTransformation.transformExpression(iThrowStatement.getExpression(), "iThrowStatement.getExpression()"))
    }

    @Override
    void visitMethodCallExpression(MethodCallExpression iMethodCallExpression) {
        iMethodCallExpression.origCodeString = blackBoxTransformation.codeString(iMethodCallExpression)
        super.visitMethodCallExpression(iMethodCallExpression)
        iMethodCallExpression.setObjectExpression(blackBoxTransformation.transformExpression(iMethodCallExpression.getObjectExpression(), "iMethodCallExpression.getObjectExpression()"))
        iMethodCallExpression.setArguments(blackBoxTransformation.transformExpression(iMethodCallExpression.getArguments(), "iMethodCallExpression.getArguments()"))
    }

    @Override
    void visitStaticMethodCallExpression(StaticMethodCallExpression iStaticMethodCallExpression) {
        iStaticMethodCallExpression.origCodeString = blackBoxTransformation.codeString(iStaticMethodCallExpression)
        super.visitStaticMethodCallExpression(iStaticMethodCallExpression)
    }

    @Override
    void visitConstructorCallExpression(ConstructorCallExpression iConstructorCallExpression) {
        iConstructorCallExpression.origCodeString = blackBoxTransformation.codeString(iConstructorCallExpression)
        super.visitConstructorCallExpression(iConstructorCallExpression)
    }

    @Override
    void visitBinaryExpression(BinaryExpression iBinaryExpression) {
        iBinaryExpression.origCodeString = blackBoxTransformation.codeString(iBinaryExpression)
        super.visitBinaryExpression(iBinaryExpression)
    }

    @Override
    void visitTernaryExpression(TernaryExpression iTernaryExpression) {
        iTernaryExpression.origCodeString = blackBoxTransformation.codeString(iTernaryExpression)
        super.visitTernaryExpression(iTernaryExpression)
    }

    @Override
    void visitShortTernaryExpression(ElvisOperatorExpression iElvisOperatorExpression) {
        iElvisOperatorExpression.origCodeString = blackBoxTransformation.codeString(iElvisOperatorExpression)
        super.visitShortTernaryExpression(iElvisOperatorExpression)
    }

    @Override
    void visitPostfixExpression(PostfixExpression iPostfixExpression) {
        iPostfixExpression.origCodeString = blackBoxTransformation.codeString(iPostfixExpression)
        super.visitPostfixExpression(iPostfixExpression)
    }

    @Override
    void visitPrefixExpression(PrefixExpression iPrefixExpression) {
        iPrefixExpression.origCodeString = blackBoxTransformation.codeString(iPrefixExpression)
        super.visitPrefixExpression(iPrefixExpression)

    }

    @Override
    void visitBooleanExpression(BooleanExpression iBooleanExpression) {
        iBooleanExpression.origCodeString = blackBoxTransformation.codeString(iBooleanExpression)
        super.visitBooleanExpression(iBooleanExpression)
    }

    @Override
    void visitNotExpression(NotExpression iNotExpression) {
        iNotExpression.origCodeString = blackBoxTransformation.codeString(iNotExpression)
        super.visitNotExpression(iNotExpression)
    }

    @Override
    void visitClosureExpression(ClosureExpression iClosureExpression) {
        iClosureExpression.origCodeString = blackBoxTransformation.codeString(iClosureExpression)
        super.visitClosureExpression(iClosureExpression)
        iClosureExpression.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iClosureExpression.getCode(), "iClosureExpression.getCode()")))
    }

    @Override
    void visitTupleExpression(TupleExpression iTupleExpression) {
        iTupleExpression.origCodeString = blackBoxTransformation.codeString(iTupleExpression)
        super.visitTupleExpression(iTupleExpression)
        transformExpressionList(iTupleExpression.getExpressions(), "iTupleExpression.getExpressions()")
    }

    @Override
    void visitListExpression(ListExpression iListExpression) {
        iListExpression.origCodeString = blackBoxTransformation.codeString(iListExpression)
        super.visitListExpression(iListExpression)
        transformExpressionList(iListExpression.getExpressions(), "iListExpression.getExpressions()")
    }

    @Override
    void visitArrayExpression(ArrayExpression iArrayExpression) {
        iArrayExpression.origCodeString = blackBoxTransformation.codeString(iArrayExpression)
        super.visitArrayExpression(iArrayExpression)
        transformExpressionList(iArrayExpression.getExpressions(), "iArrayExpression.getExpressions()")
        transformExpressionList(iArrayExpression.getSizeExpression(), "iArrayExpression.getSizeExpression()")
    }

    @Override
    void visitMapExpression(MapExpression iMapExpression) {
        iMapExpression.origCodeString = blackBoxTransformation.codeString(iMapExpression)
        super.visitMapExpression(iMapExpression)
    }

    @Override
    void visitMapEntryExpression(MapEntryExpression iMapEntryExpression) {
        iMapEntryExpression.origCodeString = blackBoxTransformation.codeString(iMapEntryExpression)
        super.visitMapEntryExpression(iMapEntryExpression)
        iMapEntryExpression.setKeyExpression(blackBoxTransformation.transformExpression(iMapEntryExpression.getKeyExpression(), "iMapEntryExpression.getKeyExpression()"))
        iMapEntryExpression.setValueExpression(blackBoxTransformation.transformExpression(iMapEntryExpression.getValueExpression(), "iMapEntryExpression.getValueExpression()"))
    }

    @Override
    void visitRangeExpression(RangeExpression iRangeExpression) {
        iRangeExpression.origCodeString = blackBoxTransformation.codeString(iRangeExpression)
        super.visitRangeExpression(iRangeExpression)
    }

    @Override
    void visitSpreadExpression(SpreadExpression iSpreadExpression) {
        iSpreadExpression.origCodeString = blackBoxTransformation.codeString(iSpreadExpression)
        super.visitSpreadExpression(iSpreadExpression)
    }


    @Override
    void visitSpreadMapExpression(SpreadMapExpression iSpreadMapExpression) {
        iSpreadMapExpression.origCodeString = blackBoxTransformation.codeString(iSpreadMapExpression)
        super.visitSpreadMapExpression(iSpreadMapExpression)
    }


    @Override
    void visitMethodPointerExpression(MethodPointerExpression iMethodPointerExpression) {
        iMethodPointerExpression.origCodeString = blackBoxTransformation.codeString(iMethodPointerExpression)
        super.visitMethodPointerExpression(iMethodPointerExpression)
    }

    @Override
    void visitUnaryMinusExpression(UnaryMinusExpression iUnaryMinusExpression) {
        iUnaryMinusExpression.origCodeString = blackBoxTransformation.codeString(iUnaryMinusExpression)
        super.visitUnaryMinusExpression(iUnaryMinusExpression)
    }


    @Override
    void visitUnaryPlusExpression(UnaryPlusExpression iUnaryPlusExpression) {
        iUnaryPlusExpression.origCodeString = blackBoxTransformation.codeString(iUnaryPlusExpression)
        super.visitUnaryPlusExpression(iUnaryPlusExpression)
    }

    @Override
    void visitBitwiseNegationExpression(BitwiseNegationExpression iBitwiseNegationExpression) {
        iBitwiseNegationExpression.origCodeString = blackBoxTransformation.codeString(iBitwiseNegationExpression)
        super.visitBitwiseNegationExpression(iBitwiseNegationExpression)
    }

    @Override
    void visitCastExpression(CastExpression iCastExpression) {
        iCastExpression.origCodeString = blackBoxTransformation.codeString(iCastExpression)
        super.visitCastExpression(iCastExpression)
    }

    @Override
    void visitConstantExpression(ConstantExpression iConstantExpression) {
        iConstantExpression.origCodeString = blackBoxTransformation.codeString(iConstantExpression)
        super.visitConstantExpression(iConstantExpression)
    }

    @Override
    void visitClassExpression(ClassExpression iClassExpression) {
        iClassExpression.origCodeString = blackBoxTransformation.codeString(iClassExpression)
        super.visitClassExpression(iClassExpression)
    }

    @Override
    void visitVariableExpression(VariableExpression iVariableExpression) {
        iVariableExpression.origCodeString = blackBoxTransformation.codeString(iVariableExpression)
        super.visitVariableExpression(iVariableExpression)
    }

    @Override
    void visitDeclarationExpression(DeclarationExpression iDeclarationExpression) {
        iDeclarationExpression.origCodeString = blackBoxTransformation.codeString(iDeclarationExpression)
        super.visitDeclarationExpression(iDeclarationExpression)
    }

    @Override
    void visitPropertyExpression(PropertyExpression iPropertyExpression) {
        iPropertyExpression.origCodeString = blackBoxTransformation.codeString(iPropertyExpression)
        super.visitPropertyExpression(iPropertyExpression)
    }

    @Override
    void visitAttributeExpression(AttributeExpression iAttributeExpression) {
        iAttributeExpression.origCodeString = blackBoxTransformation.codeString(iAttributeExpression)
        super.visitAttributeExpression(iAttributeExpression)
    }

    @Override
    void visitFieldExpression(FieldExpression iFieldExpression) {
        iFieldExpression.origCodeString = blackBoxTransformation.codeString(iFieldExpression)
        super.visitFieldExpression(iFieldExpression)
    }

    @Override
    void visitGStringExpression(GStringExpression iGStringExpression) {
        iGStringExpression.origCodeString = blackBoxTransformation.codeString(iGStringExpression)
        super.visitGStringExpression(iGStringExpression)
        transformExpressionList(iGStringExpression.getValues(), "iGStringExpression.getValues()")
    }

    @Override
    void visitCatchStatement(CatchStatement iCatchStatement) {
        iCatchStatement.origCodeString = blackBoxTransformation.codeString(iCatchStatement)
        super.visitCatchStatement(iCatchStatement)
        iCatchStatement.setCode(GeneralUtils.block(new VariableScope(), blackBoxTransformation.transformStatement(iCatchStatement.getCode(), "iCatchStatement.getCode()")))
    }

    @Override
    void visitArgumentlistExpression(ArgumentListExpression iArgumentListExpression) {
        iArgumentListExpression.origCodeString = blackBoxTransformation.codeString(iArgumentListExpression)
        super.visitArgumentlistExpression(iArgumentListExpression)
        transformExpressionList(iArgumentListExpression.getExpressions(), "iArgumentListExpression.getExpressions()")
    }

    @Override
    void visitClosureListExpression(ClosureListExpression iClosureListExpression) {
        iClosureListExpression.origCodeString = blackBoxTransformation.codeString(iClosureListExpression)
        super.visitClosureListExpression(iClosureListExpression)
        transformExpressionList(iClosureListExpression.getExpressions(), "iClosureListExpression.getExpressions()")
    }

}
