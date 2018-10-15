package groovy

import groovy.inspect.swingui.AstNodeToScriptVisitor
import groovy.transform.ToString
import groovy.util.logging.Slf4j
import org.apache.commons.lang3.exception.ExceptionUtils
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.tools.GeneralUtils
import org.codehaus.groovy.classgen.VariableScopeVisitor
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.syntax.Token
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.codehaus.groovy.transform.sc.ListOfExpressionsExpression

@ToString(includeNames = true, includeFields = true, includePackage = false)
@GroovyASTTransformation(
        phase = CompilePhase.SEMANTIC_ANALYSIS
)
@Slf4j
class BlackBoxTransformation extends AbstractASTTransformation {

    BlackBoxVisitor blackBoxVisitor
    AnnotationNode annotationNode
    BlackBoxLevel blackBoxLevel

    void visit(ASTNode[] iAstNodeArray, SourceUnit iSourceUnit) {
        try {
            ASTNode.getMetaClass().origCodeString = null
            ASTNode.getMetaClass().isTransformed = null
            init(iAstNodeArray, iSourceUnit)
            MethodNode methodNode = iAstNodeArray[1] as MethodNode
            String methodName = methodNode.getName()
            String className = methodNode.getDeclaringClass().getNameWithoutPackage()
            Thread.currentThread().setName("Compilation_$className.$methodName")
            annotationNode = iAstNodeArray[0] as AnnotationNode
            blackBoxLevel = getBlackBoxLevel(annotationNode)
            blackBoxVisitor = new BlackBoxVisitor(blackBoxLevel, annotationNode, this)
            BlockStatement decoratedMethodNodeBlockStatement = new BlockStatement()
            decoratedMethodNodeBlockStatement.setSourcePosition(methodNode.getCode())
            decoratedMethodNodeBlockStatement.copyNodeMetaData(methodNode.getCode())
            decoratedMethodNodeBlockStatement.addStatement(createLoggerDeclaration())
            decoratedMethodNodeBlockStatement.addStatement(transformMethod(methodNode))
            methodNode.setCode(decoratedMethodNodeBlockStatement)
            new VariableScopeVisitor(sourceUnit, true).visitClass(methodNode.getDeclaringClass())//<<<<<<<<<
            log.debug(codeString(methodNode.getCode()))
        } catch (Throwable throwable) {
            log.error(ExceptionUtils.getStackTrace(throwable))
            throw throwable
        }
    }

    static BlackBoxLevel getBlackBoxLevel(ASTNode iAnnotationNode) {
        AnnotationNode annotationNode = iAnnotationNode as AnnotationNode
        PropertyExpression propertyExpression = annotationNode.getMember("blackBoxLevel") as PropertyExpression
        ConstantExpression constantExpression = propertyExpression.getProperty() as ConstantExpression
        return constantExpression.getValue() as BlackBoxLevel
    }

    Expression transformReturnStatementExpression(Expression iExpression, String iSourceNodeName, String iReturnStatementCodeString) {
        if (iExpression == null || iExpression instanceof EmptyExpression) {
            return iExpression
        } else if (blackBoxLevel.value() < BlackBoxLevel.EXPRESSION.value()) {
            return iExpression
        } else {
            ClosureExpression closureExpression = GeneralUtils.closureX(GeneralUtils.returnS(iExpression))
            closureExpression.setVariableScope(new VariableScope())
            MethodCallExpression methodCallExpression = GeneralUtils.callX(
                    GeneralUtils.varX("automaticBlackBox"),
                    "handleReturn",
                    GeneralUtils.args(
                            GeneralUtils.constX(iExpression.getClass().getSimpleName()),
                            GeneralUtils.constX(iExpression.origCodeString),
                            GeneralUtils.constX(iExpression.getColumnNumber()),
                            GeneralUtils.constX(iExpression.getLastColumnNumber()),
                            GeneralUtils.constX(iExpression.getLineNumber()),
                            GeneralUtils.constX(iExpression.getLastLineNumber()),
                            closureExpression,
                            GeneralUtils.constX(iSourceNodeName),
                            GeneralUtils.constX(iReturnStatementCodeString)
                    )
            )
            methodCallExpression.copyNodeMetaData(iExpression)
            methodCallExpression.setSourcePosition(iExpression)
            methodCallExpression.isTransformed = true
            return methodCallExpression
        }
    }

    private static MethodCallExpression wrapExpressionIntoMethodCallExpression(Expression iExpression, iSourceNodeName) {
        ClosureExpression closureExpression = GeneralUtils.closureX(GeneralUtils.returnS(iExpression))
        closureExpression.setVariableScope(new VariableScope())
        MethodCallExpression methodCallExpression = GeneralUtils.callX(
                GeneralUtils.varX("automaticBlackBox"),
                "expressionEvaluation",
                GeneralUtils.args(
                        GeneralUtils.constX(iExpression.getClass().getSimpleName()),
                        GeneralUtils.constX(iExpression.origCodeString),
                        GeneralUtils.constX(iExpression.getColumnNumber()),
                        GeneralUtils.constX(iExpression.getLastColumnNumber()),
                        GeneralUtils.constX(iExpression.getLineNumber()),
                        GeneralUtils.constX(iExpression.getLastLineNumber()),
                        closureExpression,
                        GeneralUtils.constX(iSourceNodeName)
                )
        )
        methodCallExpression.copyNodeMetaData(iExpression)
        methodCallExpression.setSourcePosition(iExpression)
        methodCallExpression.isTransformed = true
        return methodCallExpression
    }

    private Statement transformMethod(MethodNode iMethodNode) {
                   Parameter[] methodParameters = iMethodNode.getParameters()
            BlockStatement decoratedBlockStatement = new BlockStatement()
            BlockStatement tryBlock = new BlockStatement()
            tryBlock.addStatement(iMethodNode.getCode())
            Statement finallyBlock
            if (blackBoxLevel.value() >= BlackBoxLevel.METHOD.value()) {
                decoratedBlockStatement.addStatement(text2statement("""automaticBlackBox.methodExecutionOpen("${
                    iMethodNode.getDeclaringClass().getNameWithoutPackage()
                }", "${iMethodNode.getDeclaringClass().getPackageName()}", "${
                    iMethodNode.getName()
                }" %s , ${
                    iMethodNode.getColumnNumber()
                }, ${
                    iMethodNode.getLastColumnNumber()
                }, ${
                    iMethodNode.getLineNumber()
                }, ${
                    iMethodNode.getLastLineNumber()
                })""", methodParameters))
                finallyBlock = text2statement("automaticBlackBox.executionClose()")
                decoratedBlockStatement.addStatement(createTryCatch(tryBlock, finallyBlock, methodParameters))
                decoratedBlockStatement.copyNodeMetaData(iMethodNode.getCode())
                decoratedBlockStatement.setSourcePosition(iMethodNode.getCode())
                iMethodNode.getCode().visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT<<<<<
                return decoratedBlockStatement
            } else if (blackBoxLevel == BlackBoxLevel.METHOD_ERROR) {
                finallyBlock = new EmptyStatement()
                decoratedBlockStatement.addStatement(createTryCatch(tryBlock, finallyBlock, methodParameters))
                decoratedBlockStatement.copyNodeMetaData(iMethodNode.getCode())
                decoratedBlockStatement.setSourcePosition(iMethodNode.getCode())
                return decoratedBlockStatement
            } else {
                return iMethodNode.getCode()
            }
    }

    private static Statement createLoggerDeclaration() {
        //see also: https://issues.apache.org/jira/browse/GROOVY-4927
        return GeneralUtils.declS(
                GeneralUtils.varX(
                        "automaticBlackBox",
                        ClassHelper.make(BlackBoxEngine.class)
                ),
                GeneralUtils.callX(
                        new ConstructorCallExpression(
                                new ClassNode(BlackBoxEngine),
                                new ArgumentListExpression()
                        ),
                        "getInstance"
                )
        )
    }

    private TryCatchStatement createTryCatch(BlockStatement iMainBlock, Statement iFinallyBlock, Parameter[] iParameters) {
        TryCatchStatement tryCatchStatement = new TryCatchStatement(iMainBlock, iFinallyBlock)
        BlockStatement throwBlock = new BlockStatement()
        throwBlock.addStatement(text2statement("automaticBlackBox.exception(automaticThrowable)", iParameters))
        throwBlock.addStatement(createThrowStatement())
        tryCatchStatement.addCatch(GeneralUtils.catchS(GeneralUtils.param(ClassHelper.make(Throwable.class), "automaticThrowable"), throwBlock))
        return tryCatchStatement
    }

    private Statement createThrowStatement() {
        ThrowStatement throwStatement = GeneralUtils.throwS(GeneralUtils.varX("automaticThrowable"))
        throwStatement.setSourcePosition(annotationNode)
        return throwStatement
    }

    static Statement text2statement(String iCodeText, Parameter[] iParameters) {
        String statementCode
        if (methodArgumentsPresent(iParameters)) {
            ArrayList<String> serializedParameters = new ArrayList<String>()
            for (parameter in iParameters) {
                serializedParameters.add(""""${parameter.getName()}": ${parameter.getName()}""")
            }
            statementCode = String.format(iCodeText, """, [${serializedParameters.join(",")}]""")
        } else {
            statementCode = String.format(iCodeText, ", automaticBlackBox.NOARGSMAP")
        }
        List<ASTNode> resultingStatements = new AstBuilder().buildFromString(CompilePhase.SEMANTIC_ANALYSIS, true, statementCode)
        return resultingStatements.first() as Statement
    }

    static String codeString(ASTNode iAstNode) {
        StringWriter stringWriter = new StringWriter()
        iAstNode.visit(new AstNodeToScriptVisitor(stringWriter))
        return stringWriter.getBuffer().toString().replace("\$", "")
    }

    private static final Boolean methodArgumentsPresent(Object iArgs) {
        if (iArgs != null) {
            if (iArgs instanceof Collection) {
                return iArgs.size() > 0
            } else if (iArgs instanceof Object[]) {
                return iArgs.length > 0
            } else {
                return false
            }
        } else {
            return false
        }
    }

    private static BlockStatement transformControlStatement(Statement iStatement, String iSourceNodeName) {
        BlockStatement blockStatement = GeneralUtils.block(new VariableScope())
        blockStatement.addStatement(text2statement("""automaticBlackBox.handleControlStatement("${
            iStatement.getClass().getSimpleName()
        }", \"\"\"${iStatement.origCodeString}\"\"\", ${iStatement.getColumnNumber()}, ${
            iStatement.getLastColumnNumber()
        }, ${
            iStatement.getLineNumber()
        }, ${iStatement.getLastLineNumber()}, "${iSourceNodeName}")"""))
        blockStatement.addStatement(iStatement)
        blockStatement.copyNodeMetaData(iStatement)
        blockStatement.setSourcePosition(iStatement)
        blockStatement.isTransformed = true
        return blockStatement
    }

    Statement transformStatement(Statement iStatement, String iSourceNodeName) {
        if (iStatement == null || iStatement instanceof EmptyStatement || iStatement.isTransformed == true) {
            return iStatement
        }
        if (blackBoxLevel.value() < BlackBoxLevel.STATEMENT.value() || iStatement instanceof BlockStatement || iStatement instanceof ExpressionStatement) {
            return iStatement
        }
        if (iStatement instanceof ReturnStatement || iStatement instanceof ContinueStatement || iStatement instanceof BreakStatement) {
            return transformControlStatement(iStatement, iSourceNodeName)
        }
        BlockStatement blockStatement = GeneralUtils.block(new VariableScope())
        blockStatement.addStatement(text2statement("""automaticBlackBox.statementExecutionOpen("${
            iStatement.getClass().getSimpleName()
        }", \"\"\"${iStatement.origCodeString}\"\"\", ${iStatement.getColumnNumber()}, ${
            iStatement.getLastColumnNumber()
        }, ${
            iStatement.getLineNumber()
        }, ${iStatement.getLastLineNumber()}, "${iSourceNodeName}")"""))
        blockStatement.addStatement(iStatement)
        blockStatement.addStatement(text2statement("automaticBlackBox.executionClose()"))
        blockStatement.copyNodeMetaData(iStatement)
        blockStatement.setSourcePosition(iStatement)
        blockStatement.isTransformed = true
        return blockStatement
    }

    private ListOfExpressionsExpression transformDeclarationExpression(DeclarationExpression iExpression, String iSourceNodeName) {
        ListOfExpressionsExpression listOfExpressionsExpression = new ListOfExpressionsExpression()
        Expression transformedRightExpression = transformExpression(iExpression.rightExpression as Expression, "DeclarationExpression:rightExpression")
        DeclarationExpression transformedDeclarationExpression = new DeclarationExpression(iExpression.leftExpression as Expression, iExpression.operation as Token, transformedRightExpression)
        MethodCallExpression expressionExecutionOpenMethodCallExpression = GeneralUtils.callX(
                GeneralUtils.varX("automaticBlackBox"),
                "expressionExecutionOpen",
                GeneralUtils.args(
                        GeneralUtils.constX(iExpression.getClass().getSimpleName()),
                        GeneralUtils.constX(iExpression.origCodeString),
                        GeneralUtils.constX(iExpression.getColumnNumber()),
                        GeneralUtils.constX(iExpression.getLastColumnNumber()),
                        GeneralUtils.constX(iExpression.getLineNumber()),
                        GeneralUtils.constX(iExpression.getLastLineNumber()),
                        GeneralUtils.constX(iSourceNodeName)
                )
        )
        MethodCallExpression expressionExecutionCloseMethodCallExpression = GeneralUtils.callX(
                GeneralUtils.varX("automaticBlackBox"),
                "executionClose"
        )
        listOfExpressionsExpression.addExpression(expressionExecutionOpenMethodCallExpression)
        listOfExpressionsExpression.addExpression(transformedDeclarationExpression)
        listOfExpressionsExpression.addExpression(expressionExecutionCloseMethodCallExpression)
        listOfExpressionsExpression.copyNodeMetaData(iExpression)
        listOfExpressionsExpression.setSourcePosition(iExpression)
        listOfExpressionsExpression.isTransformed = true
        return listOfExpressionsExpression
    }

    Expression transformExpression(Expression iExpression, String iSourceNodeName) {
        //see also: https://issues.apache.org/jira/browse/GROOVY-8834
        Expression transformedExpression = iExpression
        if (iExpression == null ||
                blackBoxLevel.value() < BlackBoxLevel.EXPRESSION.value() ||
                iExpression instanceof EmptyExpression ||
                iExpression instanceof MapEntryExpression ||
                iExpression instanceof VariableExpression ||
                iExpression instanceof ArgumentListExpression ||
                iExpression.isTransformed == true
        ) {
            return iExpression
        } else if (iExpression.getClass() == DeclarationExpression.class) {
            return transformDeclarationExpression(iExpression as DeclarationExpression, iSourceNodeName)
        } else if (iExpression.getClass() == BinaryExpression.class) {
            Expression transformedRightExpression = transformExpression(iExpression.rightExpression as Expression, "BinaryExpression:rightExpression")
            Expression transformedLeftExpression
            if (iExpression.operation.text == "=") {
                transformedLeftExpression = iExpression.leftExpression
            } else {
                transformedLeftExpression = transformExpression(iExpression.leftExpression as Expression, "BinaryExpression:leftExpression")
            }
            transformedExpression = new BinaryExpression(transformedLeftExpression as Expression, iExpression.operation as Token, transformedRightExpression)
        } else if (iExpression.getClass() == BitwiseNegationExpression.class) {
            transformedExpression = new BitwiseNegationExpression(transformExpression(iExpression.getExpression() as Expression, "BitwiseNegationExpression:expression"))
        } else if (iExpression.getClass() == NotExpression.class) {
            transformedExpression = new NotExpression(transformExpression(iExpression.getExpression() as Expression, "NotExpression:expression"))
        } else if (iExpression.getClass() == BooleanExpression.class) {
            transformedExpression = new BooleanExpression(transformExpression(iExpression.getExpression() as Expression, "BooleanExpression:expression"))
        } else if (iExpression.getClass() == CastExpression.class) {
            transformedExpression = new CastExpression(iExpression.getType(), transformExpression(iExpression.getExpression() as Expression, "ClassExpression:expression"))
        } else if (iExpression.getClass() == ConstructorCallExpression.class) {
            transformedExpression = new ConstructorCallExpression(iExpression.getType(), transformExpression(iExpression.getArguments() as Expression, "ConstructorCallExpression:arguments"))
        } else if (iExpression.getClass() == MethodPointerExpression.class) {
            transformedExpression = new MethodPointerExpression(transformExpression(iExpression.getExpression() as Expression, "MethodPointerExpression:expression"),
                    transformExpression(iExpression.getMethodName() as Expression, "MethodPointerExpression:methodName"))
        } else if (iExpression.getClass() == AttributeExpression.class) {
            transformedExpression = new AttributeExpression(transformExpression(iExpression.getObjectExpression() as Expression, "AttributeExpression:objectExpression"),
                    transformExpression(iExpression.getProperty() as Expression, "PropertyExpression:property"))
        } else if (iExpression.getClass() == PropertyExpression.class) {
            transformedExpression = new PropertyExpression(transformExpression(iExpression.getObjectExpression() as Expression, "PropertyExpression:objectExpression"),
                    transformExpression(iExpression.getProperty() as Expression, "PropertyExpression:property"))
        } else if (iExpression.getClass() == RangeExpression.class) {
            transformedExpression = new RangeExpression(transformExpression(iExpression.getFrom() as Expression, "RangeExpression:from"),
                    transformExpression(iExpression.getTo() as Expression, "RangeExpression:to"), iExpression.isInclusive() as Boolean)
        } else if (iExpression.getClass() == SpreadExpression.class) {
            transformedExpression = new SpreadExpression(transformExpression(iExpression.getExpression() as Expression, "SpreadExpression:expression"))
        } else if (iExpression.getClass() == SpreadMapExpression.class) {
            transformedExpression = new SpreadMapExpression(transformExpression(iExpression.getExpression() as Expression, "SpreadExpression:expression"))
        } else if (iExpression.getClass() == StaticMethodCallExpression.class) {
            transformedExpression = new StaticMethodCallExpression(iExpression.getOwnerType() as ClassNode, iExpression.getMethod() as String,
                    transformExpression(iExpression.getArguments() as Expression, "StaticMethodCallExpression:arguments"))
        } else if (iExpression.getClass() == ElvisOperatorExpression.class) {
            transformedExpression = new ElvisOperatorExpression(
                    transformExpression(iExpression.getTrueExpression() as Expression, "ElvisOperatorExpression:trueExpression"),
                    transformExpression(iExpression.getFalseExpression() as Expression, "ElvisOperatorExpression:falseExpression")
            )
        } else if (iExpression.getClass() == TernaryExpression.class) {
            transformedExpression = new TernaryExpression(
                    new BooleanExpression(transformExpression(iExpression.getBooleanExpression() as Expression, "TernaryExpression:booleanExpression")),
                    transformExpression(iExpression.getTrueExpression() as Expression, "TernaryExpression:trueExpression"),
                    transformExpression(iExpression.getFalseExpression() as Expression, "TernaryExpression:falseExpression")
            )
        } else if (iExpression.getClass() == UnaryMinusExpression.class) {
            transformedExpression = new UnaryMinusExpression(transformExpression(iExpression.getExpression() as Expression, "UnaryMinusExpression:expression"))
        } else if (iExpression.getClass() == UnaryPlusExpression.class) {
            transformedExpression = new UnaryPlusExpression(transformExpression(iExpression.getExpression() as Expression, "UnaryPlusExpression:expression"))
        }
        transformedExpression.isTransformed = true
        transformedExpression.copyNodeMetaData(iExpression)
        transformedExpression.setSourcePosition(iExpression)
        return wrapExpressionIntoMethodCallExpression(transformedExpression, iSourceNodeName)
    }

}
