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
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

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

    Expression transformReturnStatementExpression(Expression iExpression, BlackBoxLevel iBlackBoxLevel, String iNodeSourceName, String iReturnStatementCodeString) {
        blackBoxEngine.methodExecutionOpen(blackBoxEngine.PCLASSSIMPLENAME, blackBoxEngine.PPACKAGENAME, "transformExpression", ["iExpression": iExpression, "iBlackBoxLevel": iBlackBoxLevel])
        try {
            if (iExpression == null || iExpression instanceof EmptyExpression) {
                return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
            }
            if (!(iExpression instanceof DeclarationExpression)) {
                if (iBlackBoxLevel.value() < BlackBoxLevel.EXPRESSION.value()) {
                    iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<
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
                                GeneralUtils.constX(iNodeSourceName),
                                GeneralUtils.constX(iReturnStatementCodeString)
                        )
                )
                methodCallExpression.copyNodeMetaData(iExpression)
                methodCallExpression.setSourcePosition(iExpression)
                iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<
                return blackBoxEngine.methodResult("methodCallExpression", methodCallExpression) as Expression
            } else {
                iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<
                return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
            }
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    Expression transformExpression(Expression iExpression, BlackBoxLevel iBlackBoxLevel, String iNodeSourceName) {
        blackBoxEngine.methodExecutionOpen(blackBoxEngine.PCLASSSIMPLENAME, blackBoxEngine.PPACKAGENAME, "transformExpression", ["iExpression": iExpression, "iBlackBoxLevel": iBlackBoxLevel])
        try {
            //See also: https://issues.apache.org/jira/projects/GROOVY/issues/GROOVY-8834
            switch (iExpression) {
                case null:
                    return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
                case EmptyExpression:
                    return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
                case iBlackBoxLevel.value() < BlackBoxLevel.EXPRESSION.value():
                    iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<
                    return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
                case MapEntryExpression:
                    iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<
                    return blackBoxEngine.methodResult("iExpression", iExpression) as Expression
                case DeclarationExpression:
                    Expression transformedRightExpression = transformExpression(iExpression.rightExpression, iBlackBoxLevel, "iExpression.rightExpression")
                    DeclarationExpression transformedDeclarationExpression = new DeclarationExpression(iExpression.leftExpression, iExpression.operation, transformedRightExpression)
                    return blackBoxEngine.methodResult("transformedDeclarationExpression", transformedDeclarationExpression) as Expression
                case BinaryExpression:
                    Expression transformedRightExpression = transformExpression(iExpression.rightExpression, iBlackBoxLevel, "iExpression.rightExpression")
                    BinaryExpression transformedBinaryExpression = new BinaryExpression(iExpression.leftExpression, iExpression.operation, transformedRightExpression)
                    return blackBoxEngine.methodResult("transformedBinaryExpression", transformedBinaryExpression) as Expression
                default:
                    String iOrigExpressionCode = codeString(iExpression)
                    ClosureExpression closureExpression = GeneralUtils.closureX(GeneralUtils.returnS(iExpression))
                    closureExpression.setVariableScope(new VariableScope())
                    MethodCallExpression methodCallExpression = GeneralUtils.callX(
                            GeneralUtils.varX("automaticBlackBox"),
                            "expressionEvaluation",
                            GeneralUtils.args(
                                    GeneralUtils.constX(iExpression.getClass().getSimpleName()),
                                    GeneralUtils.constX(iOrigExpressionCode),
                                    GeneralUtils.constX(iExpression.getColumnNumber()),
                                    GeneralUtils.constX(iExpression.getLastColumnNumber()),
                                    GeneralUtils.constX(iExpression.getLineNumber()),
                                    GeneralUtils.constX(iExpression.getLastLineNumber()),
                                    closureExpression,
                                    GeneralUtils.constX(iNodeSourceName)
                            )
                    )
                    methodCallExpression.copyNodeMetaData(iExpression)
                    methodCallExpression.setSourcePosition(iExpression)
                    iExpression.visit(blackBoxVisitor)//<<<<<<<<<<<<<<
                    return blackBoxEngine.methodResult("methodCallExpression", methodCallExpression) as Expression
            }
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    List<Statement> transformControlStatement(Statement iStatement, String iNodeSourceName) {
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
            }, ${iStatement.getLastLineNumber()}, "${iNodeSourceName}")"""))
            decoratedStatements.add(iStatement)
            iStatement.visit(blackBoxVisitor)//<<<<<<<<<<<<<<
            return blackBoxEngine.methodResult("decoratedStatements", decoratedStatements) as List<Statement>
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    List<Statement> transformStatement(Statement iStatement, BlackBoxLevel iBlackBoxLevel, String iNodeSourceName) {
        blackBoxEngine.methodExecutionOpen(blackBoxEngine.PCLASSSIMPLENAME, blackBoxEngine.PPACKAGENAME, "transformStatement", ["iStatement": iStatement, "iBlackBoxLevel": iBlackBoxLevel])
        try {
            if (iStatement == null || iStatement instanceof EmptyStatement) {
                return blackBoxEngine.methodResult("[iStatement]", [iStatement]) as List<Statement>
            }
            if (iBlackBoxLevel.value() < BlackBoxLevel.STATEMENT.value() || iStatement instanceof BlockStatement || iStatement instanceof ExpressionStatement) {
                iStatement.visit(blackBoxVisitor)//<<<<<<<<<<<<<<
                return blackBoxEngine.methodResult("[iStatement]", [iStatement]) as List<Statement>
            }
            if (iStatement instanceof ReturnStatement || iStatement instanceof ContinueStatement || iStatement instanceof BreakStatement) {
                return blackBoxEngine.methodResult("transformControlStatement(iStatement)", transformControlStatement(iStatement, iNodeSourceName)) as List<Statement>
            }
            String iOrigStatementCode = codeString(iStatement)
            List<Statement> decoratedStatements = new BlockStatement().getStatements().getClass().newInstance() as List<Statement>
            decoratedStatements.add(text2statement("""automaticBlackBox.statementExecutionOpen("${
                iStatement.getClass().getSimpleName()
            }", \"\"\"$iOrigStatementCode\"\"\", ${iStatement.getColumnNumber()}, ${
                iStatement.getLastColumnNumber()
            }, ${
                iStatement.getLineNumber()
            }, ${iStatement.getLastLineNumber()}, "${iNodeSourceName}")"""))
            decoratedStatements.add(iStatement)
            decoratedStatements.add(text2statement("automaticBlackBox.executionClose()"))
            iStatement.visit(blackBoxVisitor)//<<<<<<<<<<<<<<
            return blackBoxEngine.methodResult("decoratedStatements", decoratedStatements) as List<Statement>
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    Statement transformMethod(MethodNode iMethodNode, BlackBoxLevel iBlackBoxLevel, AnnotationNode iAnnotationNode) {
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
                iMethodNode.getCode().visit(blackBoxVisitor)//<<<<<<<<<<<<<<<<<<<
                return blackBoxEngine.methodResult("iMethodNode.getCode()", iMethodNode.getCode()) as Statement
            }
            decoratedBlockStatement.copyNodeMetaData(iMethodNode.getCode())
            decoratedBlockStatement.setSourcePosition(iMethodNode.getCode())
            iMethodNode.getCode().visit(blackBoxVisitor)//<<<<<<<<<<<<<<<<<<<
            return blackBoxEngine.methodResult("decoratedBlockStatement", decoratedBlockStatement) as Statement
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
        }
    }

    static Statement createLoggerDeclaration() {
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

    TryCatchStatement createTryCatch(String iLogErrorCodeLine, BlockStatement iMainBlock, Statement iFinallyBlock, Parameter[] iParameters, AnnotationNode iAnnotationNode) {
        TryCatchStatement tryCatchStatement = new TryCatchStatement(iMainBlock, iFinallyBlock)
        BlockStatement throwBlock = new BlockStatement()
        throwBlock.addStatement(text2statement(iLogErrorCodeLine, iParameters))
        throwBlock.addStatement(createThrowStatement(iAnnotationNode))
        tryCatchStatement.addCatch(GeneralUtils.catchS(GeneralUtils.param(ClassHelper.make(Throwable.class), "automaticThrowable"), throwBlock))
        return tryCatchStatement
    }

    static Statement createThrowStatement(AnnotationNode iAnnotationNode) {
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
