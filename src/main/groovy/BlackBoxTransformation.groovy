package groovy

import groovy.inspect.swingui.AstNodeToScriptVisitor
import groovy.transform.ToString
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
import org.codehaus.groovy.syntax.Types
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.codehaus.groovy.transform.sc.ListOfExpressionsExpression

@ToString(includeNames = true, includeFields = true, includePackage = false)
@GroovyASTTransformation(
        phase = CompilePhase.SEMANTIC_ANALYSIS
)
class BlackBoxTransformation extends AbstractASTTransformation {

    final String PCLASSSIMPLENAME = this.getClass().getSimpleName()
    final String PPACKAGENAME = this.getClass().getPackage().getName()
    final BlackBoxEngine blackBoxEngine = BlackBoxEngine.getInstance()
    BlackBoxVisitor blackBoxVisitor

    void visit(ASTNode[] iAstNodeArray, SourceUnit iSourceUnit) {
        final String LMETHODNAME = "visit"
        try {
            ////////////////
            //IMPORTANT: AT NO POINT OF TIME NEW BLOCK STATEMENTS SHOULD BE CREATED AS IT AFFECTS VARIABLE SCOPE
            //IN GROOVY BLOCK STATEMENT SHOULD BE DONE ABSTRACT WITH CONCRETE IMPLEMENTATIONS FOR CLASSES LIKE LOOP BLOCK ETC
            //VARIABLE SCOPE OF DECLARATIONS WITHIN BLOCK STATEMENT IS LOCAL AND CAN NOT BE CHANGED!
            ////////////////
            init(iAstNodeArray, iSourceUnit)
            MethodNode methodNode = iAstNodeArray[1] as MethodNode
            String methodName = methodNode.getName()
            String className = methodNode.getDeclaringClass().getNameWithoutPackage()
            Thread.currentThread().setName("Compilation_$className.$methodName")
            blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, LMETHODNAME, ["className": className, "methodName": methodName, "methodNode.getCode()": methodNode.getCode()])
            AnnotationNode annotationNode = iAstNodeArray[0] as AnnotationNode
            BlackBoxLevel blackBoxLevel = getBlackBoxLevel(annotationNode)
            blackBoxVisitor = new BlackBoxVisitor(blackBoxLevel, annotationNode, this)
            BlockStatement decoratedMethodNodeBlockStatement = new BlockStatement()
            decoratedMethodNodeBlockStatement.setSourcePosition(methodNode.getCode())
            decoratedMethodNodeBlockStatement.copyNodeMetaData(methodNode.getCode())
            decoratedMethodNodeBlockStatement.addStatement(createLoggerDeclaration())
            decoratedMethodNodeBlockStatement.addStatement(transformMethod(methodNode, blackBoxLevel, annotationNode))
            methodNode.setCode(decoratedMethodNodeBlockStatement)
            new VariableScopeVisitor(sourceUnit, true).visitClass(methodNode.getDeclaringClass())//<<<<<<<<<
            blackBoxEngine.methodResult("methodNode.getCode()", methodNode.getCode())
        } catch (Throwable throwable) {
            System.out.println(ExceptionUtils.getStackTrace(throwable))
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    static BlackBoxLevel getBlackBoxLevel(ASTNode iAnnotationNode) {
        AnnotationNode annotationNode = iAnnotationNode as AnnotationNode
        PropertyExpression propertyExpression = annotationNode.getMember("blackBoxLevel") as PropertyExpression
        ConstantExpression constantExpression = propertyExpression.getProperty() as ConstantExpression
        return constantExpression.getValue() as BlackBoxLevel
    }

    Expression transformReturnStatementExpression(Expression iExpression, BlackBoxLevel iBlackBoxLevel, String iSourceNodeName, String iReturnStatementCodeString) {
        blackBoxEngine.methodExecutionOpen(blackBoxEngine.PCLASSSIMPLENAME, blackBoxEngine.PPACKAGENAME, "transformExpression", ["iExpression": iExpression, "iBlackBoxLevel": iBlackBoxLevel])
        try {
            if (iExpression == null || iExpression instanceof EmptyExpression) {
                return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
            }
            if (!(iExpression instanceof DeclarationExpression)) {
                if (iBlackBoxLevel.value() < BlackBoxLevel.EXPRESSION.value()) {
                    iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT
                    return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
                }
                String iOrigExpressionCode = codeString(iExpression)
                ClosureExpression closureExpression = GeneralUtils.closureX(GeneralUtils.returnS(iExpression))
                closureExpression.setVariableScope(new VariableScope())
                MethodCallExpression methodCallExpression = GeneralUtils.callX(
                        GeneralUtils.varX("automaticBlackBox"),
                        "handleReturn",
                        GeneralUtils.args(
                                GeneralUtils.constX(iExpression.getClass().getSimpleName()),
                                GeneralUtils.constX(iOrigExpressionCode),
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
                iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT
                return blackBoxEngine.methodResult("methodCallExpression", methodCallExpression) as Expression
            } else {
                iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT
                return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
            }
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }
    
    private ListOfExpressionsExpression transformAssignmentExpression(BinaryExpression iExpression, BlackBoxLevel iBlackBoxLevel, String iSourceNodeName) {
        ListOfExpressionsExpression listOfExpressionsExpression = new ListOfExpressionsExpression()
        //<<<<<<<<<<<<<<VISIT
        Expression transformedRightExpression = transformExpression(iExpression.rightExpression as Expression, iBlackBoxLevel, "DeclarationExpression:rightExpression")
        DeclarationExpression transformedDeclarationExpression = new DeclarationExpression(iExpression.leftExpression as Expression, iExpression.operation as Token, transformedRightExpression)
        String iOrigExpressionCode = codeString(iExpression)
        MethodCallExpression expressionExecutionOpenMethodCallExpression = GeneralUtils.callX(
                GeneralUtils.varX("automaticBlackBox"),
                "expressionExecutionOpen",
                GeneralUtils.args(
                        GeneralUtils.constX(iExpression.getClass().getSimpleName()),
                        GeneralUtils.constX(iOrigExpressionCode),
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
        transformedDeclarationExpression.copyNodeMetaData(iExpression)
        transformedDeclarationExpression.setSourcePosition(iExpression)
        return blackBoxEngine.methodResult("listOfExpressionsExpression", listOfExpressionsExpression) as ListOfExpressionsExpression
    }

    Expression transformExpression(Expression iExpression, BlackBoxLevel iBlackBoxLevel, String iSourceNodeName) {
        /*See also: https://issues.apache.org/jira/browse/GROOVY-8834*/
        /*To debug the compilation process: use class implementing Runnable interface*/
        blackBoxEngine.methodExecutionOpen(blackBoxEngine.PCLASSSIMPLENAME, blackBoxEngine.PPACKAGENAME, "transformExpression", ["iExpression": iExpression, "iBlackBoxLevel": iBlackBoxLevel])
        try {
            if (codeString(iExpression).contains("automaticBlackBox")) {
                throw new Exception("Recursive transformation detected")
            }
            //See also: https://issues.apache.org/jira/projects/GROOVY/issues/GROOVY-8834
            switch (iExpression) {
            //IN EACH CASE EITHER "iExpression.visit" OR "transform(iExpression.childExpression)" SHOULD BE CALLED OTHERWISE TRAVERSING WILL TERMINATE EARLY
                case null:
                    return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
                case EmptyExpression:
                    return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
                case iBlackBoxLevel.value() < BlackBoxLevel.EXPRESSION.value():
                    iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT
                    return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
                case StaticMethodCallExpression:
                    StaticMethodCallExpression staticMethodCallExpression = new StaticMethodCallExpression(
                            iExpression.getOwnerType() as ClassNode,
                            iExpression.getMethod() as String,
                            transformExpression(iExpression.getArguments() as ArgumentListExpression, iBlackBoxLevel, "StaticMethodCallExpression:getArguments()")//<<<<<<<<<<<<<<VISIT
                    )
                    return blackBoxEngine.methodResult(
                            "wrapExpressionIntoMethodCallExpression(iExpression, staticMethodCallExpression, iSourceNodeName)",
                            wrapExpressionIntoMethodCallExpression(iExpression, staticMethodCallExpression, iSourceNodeName)
                    ) as Expression
                case ArgumentListExpression:
                    blackBoxVisitor.transformExpressionList(iExpression.getExpressions() as List, "ArgumentListExpression:getExpressions()")
                    return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
                case MapEntryExpression:
                    iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT
                    return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
                case DeclarationExpression:
                    return blackBoxEngine.methodResult("listOfExpressionsExpression", transformAssignmentExpression(iExpression as DeclarationExpression, iBlackBoxLevel, iSourceNodeName)) as Expression
                case BinaryExpression:
                    Expression transformedRightExpression = transformExpression(iExpression.rightExpression as Expression, iBlackBoxLevel, "BinaryExpression:rightExpression")
                    Expression transformedLeftExpression
                    if (iExpression.operation == Types.ASSIGN) {//todo: this does not work
                        transformedLeftExpression = iExpression.leftExpression
                    } else {
                        transformedLeftExpression = transformExpression(iExpression.leftExpression as Expression, iBlackBoxLevel, "BinaryExpression:leftExpression")
                    }
                    BinaryExpression transformedBinaryExpression = new BinaryExpression(transformedLeftExpression as Expression, iExpression.operation as Token, transformedRightExpression)
                    transformedBinaryExpression.copyNodeMetaData(iExpression)
                    transformedBinaryExpression.setSourcePosition(iExpression)
                    Expression transformedExpression = wrapExpressionIntoMethodCallExpression(iExpression, transformedBinaryExpression, iSourceNodeName)
                    iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT
                    return blackBoxEngine.methodResult("transformedExpression", transformedExpression) as Expression
                default:
                    Expression transformedExpression = wrapExpressionIntoMethodCallExpression(iExpression, iExpression, iSourceNodeName)
                    iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT
                    return blackBoxEngine.methodResult("transformedExpression", transformedExpression) as Expression
            }
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    private MethodCallExpression wrapExpressionIntoMethodCallExpression(Expression iOriginalExpression, Expression iTransformedExpression, String iSourceNodeName) {
        blackBoxEngine.methodExecutionOpen(blackBoxEngine.PCLASSSIMPLENAME, blackBoxEngine.PPACKAGENAME, "wrapExpressionIntoMethodCallExpression", ["iOriginalExpression": iOriginalExpression, "iTransformedExpression": iTransformedExpression, "iSourceNodeName": iSourceNodeName])
        try {
            String origExpressionCode = codeString(iOriginalExpression)
            ClosureExpression closureExpression = GeneralUtils.closureX(GeneralUtils.returnS(iTransformedExpression))
            closureExpression.setVariableScope(new VariableScope())
            MethodCallExpression methodCallExpression = GeneralUtils.callX(
                    GeneralUtils.varX("automaticBlackBox"),
                    "expressionEvaluation",
                    GeneralUtils.args(
                            GeneralUtils.constX(iOriginalExpression.getClass().getSimpleName()),
                            GeneralUtils.constX(origExpressionCode),
                            GeneralUtils.constX(iOriginalExpression.getColumnNumber()),
                            GeneralUtils.constX(iOriginalExpression.getLastColumnNumber()),
                            GeneralUtils.constX(iOriginalExpression.getLineNumber()),
                            GeneralUtils.constX(iOriginalExpression.getLastLineNumber()),
                            closureExpression,
                            GeneralUtils.constX(iSourceNodeName)
                    )
            )
            methodCallExpression.copyNodeMetaData(iOriginalExpression)
            methodCallExpression.setSourcePosition(iOriginalExpression)
            return methodCallExpression
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    private List<Statement> transformControlStatement(Statement iStatement, String iSourceNodeName) {
        blackBoxEngine.methodExecutionOpen(blackBoxEngine.PCLASSSIMPLENAME, blackBoxEngine.PPACKAGENAME, "transformStatement", ["iStatement": iStatement])
        try {
            String iOrigStatementCode = codeString(iStatement)
            List<Statement> decoratedStatements = new BlockStatement().getStatements().getClass().newInstance() as List<Statement>
            decoratedStatements.add(text2statement("""automaticBlackBox.handleControlStatement("${
                iStatement.getClass().getSimpleName()
            }", \"\"\"$iOrigStatementCode\"\"\", ${iStatement.getColumnNumber()}, ${
                iStatement.getLastColumnNumber()
            }, ${
                iStatement.getLineNumber()
            }, ${iStatement.getLastLineNumber()}, "${iSourceNodeName}")"""))
            decoratedStatements.add(iStatement)
            iStatement.visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT
            return blackBoxEngine.methodResult("decoratedStatements", decoratedStatements) as List<Statement>
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    List<Statement> transformStatement(Statement iStatement, BlackBoxLevel iBlackBoxLevel, String iSourceNodeName) {
        blackBoxEngine.methodExecutionOpen(blackBoxEngine.PCLASSSIMPLENAME, blackBoxEngine.PPACKAGENAME, "transformStatement", ["iStatement": iStatement, "iBlackBoxLevel": iBlackBoxLevel])
        try {
            if (codeString(iStatement).contains("automaticBlackBox")) {
                throw new Exception("Recursive transformation detected")
            }
            if (iStatement == null || iStatement instanceof EmptyStatement) {
                return blackBoxEngine.methodResult("[iStatement]", [iStatement]) as List<Statement>
            }
            if (iBlackBoxLevel.value() < BlackBoxLevel.STATEMENT.value() || iStatement instanceof BlockStatement || iStatement instanceof ExpressionStatement) {
                iStatement.visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT
                return blackBoxEngine.methodResult("[iStatement]", [iStatement]) as List<Statement>
            }
            if (iStatement instanceof ReturnStatement || iStatement instanceof ContinueStatement || iStatement instanceof BreakStatement) {
                return blackBoxEngine.methodResult("transformControlStatement(iStatement)", transformControlStatement(iStatement, iSourceNodeName)) as List<Statement>
            }
            String iOrigStatementCode = codeString(iStatement)
            List<Statement> decoratedStatements = new BlockStatement().getStatements().getClass().newInstance() as List<Statement>
            decoratedStatements.add(text2statement("""automaticBlackBox.statementExecutionOpen("${
                iStatement.getClass().getSimpleName()
            }", \"\"\"$iOrigStatementCode\"\"\", ${iStatement.getColumnNumber()}, ${
                iStatement.getLastColumnNumber()
            }, ${
                iStatement.getLineNumber()
            }, ${iStatement.getLastLineNumber()}, "${iSourceNodeName}")"""))
            decoratedStatements.add(iStatement)
            decoratedStatements.add(text2statement("automaticBlackBox.executionClose()"))
            iStatement.visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT
            return blackBoxEngine.methodResult("decoratedStatements", decoratedStatements) as List<Statement>
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    private Statement transformMethod(MethodNode iMethodNode, BlackBoxLevel iBlackBoxLevel, AnnotationNode iAnnotationNode) {
        blackBoxEngine.methodExecutionOpen(blackBoxEngine.PCLASSSIMPLENAME, blackBoxEngine.PPACKAGENAME, "transformMethod", ["iMethodNode.getCode()": iMethodNode.getCode(), "iBlackBoxLevel": iBlackBoxLevel])
        try {
            Parameter[] methodParameters = iMethodNode.getParameters()
            BlockStatement decoratedBlockStatement = new BlockStatement()
            BlockStatement tryBlock = new BlockStatement()
            tryBlock.addStatement(iMethodNode.getCode())
            Statement finallyBlock
            if (iBlackBoxLevel.value() >= BlackBoxLevel.METHOD.value()) {
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
                decoratedBlockStatement.addStatement(createTryCatch("automaticBlackBox.exception(automaticThrowable)", tryBlock, finallyBlock, methodParameters, iAnnotationNode))
            } else if (iBlackBoxLevel == BlackBoxLevel.METHOD_ERROR) {
                finallyBlock = new EmptyStatement()
                decoratedBlockStatement.addStatement(createTryCatch("automaticBlackBox.exception(automaticThrowable)", tryBlock, finallyBlock, methodParameters, iAnnotationNode))
            } else {
                iMethodNode.getCode().visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT<<<<<
                return blackBoxEngine.methodResult("iMethodNode.getCode()", iMethodNode.getCode()) as Statement
            }
            decoratedBlockStatement.copyNodeMetaData(iMethodNode.getCode())
            decoratedBlockStatement.setSourcePosition(iMethodNode.getCode())
            iMethodNode.getCode().visit(blackBoxVisitor)//<<<<<<<<<<<<<<VISIT<<<<<
            return blackBoxEngine.methodResult("decoratedBlockStatement", decoratedBlockStatement) as Statement
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
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

    private TryCatchStatement createTryCatch(String iLogErrorCodeLine, BlockStatement iMainBlock, Statement iFinallyBlock, Parameter[] iParameters, AnnotationNode iAnnotationNode) {
        TryCatchStatement tryCatchStatement = new TryCatchStatement(iMainBlock, iFinallyBlock)
        BlockStatement throwBlock = new BlockStatement()
        throwBlock.addStatement(text2statement(iLogErrorCodeLine, iParameters))
        throwBlock.addStatement(createThrowStatement(iAnnotationNode))
        tryCatchStatement.addCatch(GeneralUtils.catchS(GeneralUtils.param(ClassHelper.make(Throwable.class), "automaticThrowable"), throwBlock))
        return tryCatchStatement
    }

    private static Statement createThrowStatement(AnnotationNode iAnnotationNode) {
        ThrowStatement throwStatement = GeneralUtils.throwS(GeneralUtils.varX("automaticThrowable"))
        iAnnotationNode.setSourcePosition(iAnnotationNode)
        return throwStatement
    }

    Statement text2statement(String iCodeText, Parameter[] iParameters) {
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
        return blackBoxEngine.methodResult("resultingStatements.first()", resultingStatements.first()) as Statement
    }

    static String codeString(ASTNode iAstNode) {
        StringWriter stringWriter = new StringWriter()
        iAstNode.visit(new AstNodeToScriptVisitor(stringWriter))
        return stringWriter.getBuffer().toString().replace("\$", "")
    }

    static final Boolean methodArgumentsPresent(Object iArgs) {
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

}
