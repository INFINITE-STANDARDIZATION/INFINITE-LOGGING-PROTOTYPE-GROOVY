package groovy

import groovy.inspect.swingui.AstNodeToScriptVisitor
import groovy.util.logging.Slf4j
import infinite_logging.prototype.groovy.*
import org.apache.commons.lang3.exception.ExceptionUtils
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.tools.GeneralUtils
import org.codehaus.groovy.control.CompilePhase

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar
import java.lang.management.ManagementFactory

@Slf4j
class BlackBoxEngine {

    public static ThreadLocal<BlackBoxEngine> blackBoxEngineThreadLocal = new ThreadLocal<BlackBoxEngine>()

    XMLExecution xmlExecution

    ConfigObject configObject

    final static NOARGSMAP = new HashMap<String, Object>()


    final static String PCLASSSIMPLENAME = this.getClass().getSimpleName()
    final static String PPACKAGENAME = this.getClass().getPackage().getName()

    BlackBoxEngine() {
        PrintStream printStream = new PrintStream(System.out) {
            @Override
            void println(String string) {
                getInstance().stdout(string)
            }
        }
        System.setOut(printStream)
        addShutdownHook {
            synchronized (this) {
                while (xmlExecution != null) {
                    executionClose()
                }
            }
        }
        /*String blackBoxConfFileName = System.getProperty("blackBoxConfFileName")
        if (blackBoxConfFileName == null) {
            System.out.println("Prop missing")
            blackBoxConfFileName = "resources/BlackBox.config"
        }
        blackBoxEngine.configObject = new ConfigSlurper().parse(new File(blackBoxConfFileName).toURI().toURL())*/
    }

    static BlackBoxEngine getInstance() {
        BlackBoxEngine blackBoxEngine = blackBoxEngineThreadLocal.get(BlackBoxEngine.class)
        if (blackBoxEngine == null) {
            XMLExecution.getMetaClass().parentExecution = null
            XMLExecution.getMetaClass().isFailed = null
            ASTNode.getMetaClass().blackBoxVisited = false
            Throwable.getMetaClass().blackBoxLogged = false
            blackBoxEngine = new BlackBoxEngine()
            blackBoxEngineThreadLocal.set(blackBoxEngine)

        }
        return blackBoxEngine
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

    /*
    static tagOpen(XMLExecution iXmlExecution) {
        String iTagString = "<"
        switch (iXmlExecution) {
            case XMLLog: iTagString += """log xmlns="https://i-t.io/logging/groovy/2_x_x/Main" """; break
            case XMLMethodExecution: iTagString += """event xsi:type="MethodExecution" """; break
            case XMLStatementExecution: iTagString += """event xsi:type="StatementExecution" """; break
            case XMLExpressionEvaluation: iTagString += """event xsi:type=ExpressionEvaluation" """; break
        }
        for (k in iXmlExecution.getProperties().keySet()) {
            iTagString += """ $k="${XmlUtil.escapeXml(iXmlExecution.getProperties().get(k).toString())}" """
        }
        iTagString += ">"
        log.debug(iTagString)
    }*/

    static Expression decorateExpression(Expression iExpression, BlackBoxLevel iBlackBoxLevel, AnnotationNode iAnnotationNode) {
        getInstance().methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "decorateExpression", ["iExpression": iExpression, "iBlackBoxLevel": iBlackBoxLevel])
        try {
            String iOrigExpressionCode = codeString(iExpression)
            iExpression.visit(new BlackBoxVisitor(iBlackBoxLevel, iAnnotationNode))//<<<<<<<<<<<<<<
            if (iBlackBoxLevel.value() >= BlackBoxLevel.EXPRESSION.value()) {
                ReturnStatement returnStatement = new ReturnStatement(iExpression)
                returnStatement.setExpression(iExpression)
                ClosureExpression closureExpression = GeneralUtils.closureX(null as Parameter[], returnStatement)
                closureExpression.setVariableScope(new VariableScope())
                MethodCallExpression methodCallExpression = GeneralUtils.callX(
                        GeneralUtils.varX("automaticBlackBox"),
                        "expressionEvaluation",
                        new ArgumentListExpression(
                                GeneralUtils.constX(iExpression.getClass().getSimpleName()),
                                GeneralUtils.constX(iOrigExpressionCode),
                                GeneralUtils.constX(iExpression.getColumnNumber()),
                                GeneralUtils.constX(iExpression.getLastColumnNumber()),
                                GeneralUtils.constX(iExpression.getLineNumber()),
                                GeneralUtils.constX(iExpression.getLastLineNumber()),
                                closureExpression,
                                GeneralUtils.constX(iBlackBoxLevel.value()),
                        )
                )
                methodCallExpression.blackBoxVisited = true
                return getInstance().result("methodCallExpression", methodCallExpression) as Expression
            } else {
                iExpression.blackBoxVisited = true
                return getInstance().result("iExpression", iExpression) as Expression
            }
        } catch (Throwable throwable) {
            getInstance().exception(throwable)
            throw throwable
        } finally {
            getInstance().executionClose()
        }
    }

    static Statement decorateStatement(Statement iStatement, BlackBoxLevel iBlackBoxLevel, AnnotationNode iAnnotationNode) {
        getInstance().methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "decorateStatement", ["iStatement": iStatement, "iBlackBoxLevel": iBlackBoxLevel])
        try {
            String iOrigStatementCode = codeString(iStatement)
            iStatement.visit(new BlackBoxVisitor(iBlackBoxLevel, iAnnotationNode))//<<<<<<<<<<<<<<
            BlockStatement decoratedBlockStatement = new BlockStatement()
            BlockStatement tryBlock = new BlockStatement()
            tryBlock.addStatement(iStatement)
            Statement finallyBlock
            if (iBlackBoxLevel.value() >= BlackBoxLevel.STATEMENT.value()) {
                decoratedBlockStatement.addStatement(text2statement("""automaticBlackBox.getInstance().statementExecutionOpen("${
                    iStatement.getClass().getSimpleName()
                }", \"\"\"$iOrigStatementCode\"\"\", ${iStatement.getColumnNumber()}, ${
                    iStatement.getLastColumnNumber()
                }, ${
                    iStatement.getLineNumber()
                }, ${iStatement.getLastLineNumber()})"""))
                finallyBlock = text2statement("automaticBlackBox.getInstance().executionClose()")
                decoratedBlockStatement.addStatement(createTryCatch("automaticBlackBox.getInstance().exception(throwable)", tryBlock, finallyBlock, null, iAnnotationNode))
            } else if (iBlackBoxLevel == BlackBoxLevel.STATEMENT_ERROR) {
                finallyBlock = new EmptyStatement()
                decoratedBlockStatement.addStatement(createTryCatch("automaticBlackBox.getInstance().exception(throwable)", tryBlock, finallyBlock, null, iAnnotationNode))
            } else {
                iStatement.blackBoxVisited = true
                return getInstance().result("iStatement", iStatement) as Statement
            }
            decoratedBlockStatement.blackBoxVisited = true
            return getInstance().result("decoratedBlockStatement", decoratedBlockStatement) as Statement
        } catch (Throwable throwable) {
            getInstance().exception(throwable)
            throw throwable
        } finally {
            getInstance().executionClose()
        }
    }

    static Statement decorateMethod(MethodNode iMethodNode, BlackBoxLevel iBlackBoxLevel, AnnotationNode iAnnotationNode) {
        getInstance().methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "decorateMethod", ["iMethodNode.getCode()": iMethodNode.getCode(), "iBlackBoxLevel": iBlackBoxLevel])
        try {
            Statement methodCodeStatement = iMethodNode.getCode()
            Parameter[] methodParameters = iMethodNode.getParameters()
            BlockStatement decoratedBlockStatement = new BlockStatement()
            BlockStatement tryBlock = new BlockStatement()
            tryBlock.addStatement(methodCodeStatement)
            Statement finallyBlock
            methodCodeStatement.visit(new BlackBoxVisitor(iBlackBoxLevel, iAnnotationNode))//<<<<<<<<<<<<<<<<<<<
            if (iBlackBoxLevel.value() >= BlackBoxLevel.METHOD.value()) {
                decoratedBlockStatement.addStatement(text2statement("""automaticBlackBox.getInstance().methodExecutionOpen("${
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
                finallyBlock = text2statement("automaticBlackBox.getInstance().executionClose()")
                decoratedBlockStatement.addStatement(createTryCatch("automaticBlackBox.getInstance().exception(throwable)", tryBlock, finallyBlock, methodParameters, iAnnotationNode))
            } else if (iBlackBoxLevel == BlackBoxLevel.METHOD_ERROR) {
                finallyBlock = new EmptyStatement()
                decoratedBlockStatement.addStatement(createTryCatch("automaticBlackBox.getInstance().exception(throwable)", tryBlock, finallyBlock, methodParameters, iAnnotationNode))
            } else {
                methodCodeStatement.blackBoxVisited = true
                return getInstance().result("methodCodeStatement", methodCodeStatement) as Statement
            }
            decoratedBlockStatement.blackBoxVisited = true
            return getInstance().result("decoratedBlockStatement", decoratedBlockStatement) as Statement
        } catch (Throwable throwable) {
            getInstance().exception(throwable)
            throw throwable
        } finally {
            getInstance().executionClose()
        }
    }

    static Statement createLoggerDeclaration() {
        //Workaround of: https://issues.apache.org/jira/browse/GROOVY-4927
        return GeneralUtils.declS(GeneralUtils.varX("automaticBlackBox"), new ConstructorCallExpression(new ClassNode(BlackBoxEngine), new ArgumentListExpression()))
    }

    static TryCatchStatement createTryCatch(String iLogErrorCodeLine, BlockStatement iMainBlock, Statement iFinallyBlock, Parameter[] iParameters, AnnotationNode iAnnotationNode) {
        TryCatchStatement tryCatchStatement = new TryCatchStatement(iMainBlock, iFinallyBlock)
        BlockStatement throwBlock = new BlockStatement()
        throwBlock.addStatement(text2statement(iLogErrorCodeLine, iParameters))
        throwBlock.addStatement(createThrowStatement(iAnnotationNode))
        tryCatchStatement.addCatch(GeneralUtils.catchS(GeneralUtils.param(ClassHelper.make(Throwable.class), "throwable"), throwBlock))
        return tryCatchStatement
    }

    static Statement createThrowStatement(AnnotationNode iAnnotationNode) {
        ThrowStatement throwStatement = GeneralUtils.throwS(GeneralUtils.varX("throwable"))
        iAnnotationNode.setSourcePosition(iAnnotationNode)
        return throwStatement
    }

    static Statement text2statement(String iCodeText, Parameter[] iParameters) {
        getInstance().methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "text2statement", ["iCodeText": iCodeText, "iParameters": iParameters])
        try {
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
            List<ASTNode> resultingStatements = new AstBuilder().buildFromString(CompilePhase.SEMANTIC_ANALYSIS, statementCode)
            return getInstance().result("resultingStatements.first()", resultingStatements.first()) as Statement
        } catch (Throwable throwable) {
            getInstance().exception(throwable)
            throw throwable
        } finally {
            getInstance().executionClose()
        }

    }

    static XMLGregorianCalendar getXMLGregorianCalendar(Date date = new Date()) {
        GregorianCalendar lGregorianCalendar = new GregorianCalendar()
        lGregorianCalendar.setTime(date)
        XMLGregorianCalendar lXMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(lGregorianCalendar)
        return lXMLGregorianCalendar
    }

    static String codeString(ASTNode iAstNode) {
        StringWriter stringWriter = new StringWriter()
        iAstNode.visit(new AstNodeToScriptVisitor(stringWriter))
        return stringWriter.getBuffer().toString().replace("\$", "")
    }

    Object expressionEvaluation(String iExpressionName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, Closure iClosure, Integer iBlackBoxLevelValue) {
        if (iBlackBoxLevelValue >= BlackBoxLevel.EXPRESSION.value()) {
            XMLExpressionEvaluation xmlExpressionEvaluation = new XMLExpressionEvaluation()
            xmlExpressionEvaluation.parentExecution = xmlExecution
            xmlExpressionEvaluation.setStartDateTime(getXMLGregorianCalendar())
            xmlExpressionEvaluation.setExpressionName(iExpressionName)
            xmlExpressionEvaluation.setRestoredScriptCode(iRestoredScriptCode)
            xmlExpressionEvaluation.setColumnNumber(iColumnNumber as BigInteger)
            xmlExpressionEvaluation.setLastColumnNumber(iLastColumnNumber as BigInteger)
            xmlExpressionEvaluation.setLineNumber(iLineNumber as BigInteger)
            xmlExpressionEvaluation.setLastLineNumber(iLastLineNumber as BigInteger)
            xmlExecution.getEvent().add(xmlExpressionEvaluation)
            xmlExecution = xmlExpressionEvaluation
            try {
                Object evaluationResult = iClosure.call()
                xmlExpressionEvaluation.setResult(TraceSerializer.createXMLTraceTrace("evaluationResult", evaluationResult))
                return evaluationResult
            } catch (Throwable throwable) {
                exception(throwable)
                throw throwable
            } finally {
                executionClose()
            }
        } else if (iBlackBoxLevelValue >= BlackBoxLevel.EXPRESSION_ERROR.value()) {
            try {
                return iClosure.call()
            } catch (Throwable throwable) {
                exception(throwable)
                throw throwable
            }
        } else {
            return iClosure.call()
        }
/*        XMLExecutionTrailer xmlExecutionTrailer = new XMLExecutionTrailer()
        xmlExecutionTrailer.setEndDateTime(getXMLGregorianCalendar())
        xmlExecutionTrailer.setElapsedTime(xmlExecutionTrailer.getEndDateTime().toGregorianCalendar().getTimeInMillis() - xmlExecution.getStartDateTime().toGregorianCalendar().getTimeInMillis() as BigInteger)
        xmlExecution.setExecutionTrailer(xmlExecutionTrailer)
        xmlExecution = xmlExecution.parentExecution*/
    }

    void statementExecutionOpen(String iStatementName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber) {
        XMLStatementExecution newXmlStatementExecution = new XMLStatementExecution()
        newXmlStatementExecution.parentExecution = xmlExecution
        newXmlStatementExecution.setStartDateTime(getXMLGregorianCalendar())
        newXmlStatementExecution.setStatementName(iStatementName)
        newXmlStatementExecution.setRestoredScriptCode(iRestoredScriptCode)
        newXmlStatementExecution.setColumnNumber(iColumnNumber as BigInteger)
        newXmlStatementExecution.setLastColumnNumber(iLastColumnNumber as BigInteger)
        newXmlStatementExecution.setLineNumber(iLineNumber as BigInteger)
        newXmlStatementExecution.setLastLineNumber(iLastLineNumber as BigInteger)
        xmlExecution.getEvent().add(newXmlStatementExecution)
        xmlExecution = newXmlStatementExecution
    }

    void methodExecutionOpen(String iClassSimpleName, String iPackageName, String iMethodName, Map<String, Object> methodArgumentMap, Integer iColumnNumber = null, Integer iLastColumnNumber = null, Integer iLineNumber = null, Integer iLastLineNumber = null) {
        if (xmlExecution == null) {
            logOpen()
        }
        XMLMethodExecution newXmlExecution = new XMLMethodExecution()
        newXmlExecution.parentExecution = xmlExecution
        newXmlExecution.setStartDateTime(getXMLGregorianCalendar())
        newXmlExecution.setMethodName(iMethodName)
        newXmlExecution.setClassName(iPackageName + "." + iClassSimpleName)
        newXmlExecution.setColumnNumber(iColumnNumber as BigInteger)
        newXmlExecution.setLastColumnNumber(iLastColumnNumber as BigInteger)
        newXmlExecution.setLineNumber(iLineNumber as BigInteger)
        newXmlExecution.setLastLineNumber(iLastLineNumber as BigInteger)
        for (methodArgumentName in methodArgumentMap.keySet()) {
            XMLTrace xMLTrace = TraceSerializer.createXMLTraceTrace(methodArgumentName, methodArgumentMap.get(methodArgumentName))
            newXmlExecution.getArgument().add(xMLTrace)
        }
        xmlExecution.getEvent().add(newXmlExecution)
        xmlExecution = newXmlExecution
    }

    void executionClose() {
        XMLExecutionTrailer xmlExecutionTrailer = new XMLExecutionTrailer()
        xmlExecutionTrailer.setEndDateTime(getXMLGregorianCalendar())
        if (xmlExecution.isFailed == true) {
            xmlExecutionTrailer.setExecutionStatus(XMLExecutionStatus.UNHANDLED_EXCEPTION)
        } else {
            xmlExecutionTrailer.setExecutionStatus(XMLExecutionStatus.NORMAL)
        }
        xmlExecutionTrailer.setElapsedTime(xmlExecutionTrailer.getEndDateTime().toGregorianCalendar().getTimeInMillis() - xmlExecution.getStartDateTime().toGregorianCalendar().getTimeInMillis() as BigInteger)
        xmlExecution.setExecutionTrailer(xmlExecutionTrailer)
        if (xmlExecution.parentExecution == null) {
            XMLLog xmlLog = getXmlExecution() as XMLLog
            XMLLogTrailer xmlLogTrailer = new XMLLogTrailer()
            xmlLogTrailer.setNextLogName(xmlLog.getCurrentLogName())
            xmlLogTrailer.setLogStatus(XMLLogStatus.STOPPED)
            xmlLog.setLogTrailer(xmlLogTrailer)
            xmlLogTrailer.setDateTime(getXMLGregorianCalendar())
            JAXBContext lJAXBContext = JAXBContext.newInstance(XMLLog.class)
            Marshaller marshaller = lJAXBContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE)
            StringWriter stringWriter = new StringWriter()
            marshaller.marshal(new ObjectFactory().createLog(xmlLog), stringWriter)
            String xmlString = stringWriter.toString()
            log.debug(xmlString)
        }
        xmlExecution = xmlExecution.parentExecution
    }

    private void logOpen() {
        XMLLog xmlLog = new XMLLog()
        xmlExecution = xmlLog
        xmlLog.setCurrentLogName(Thread.currentThread().getName())
        xmlLog.setPreviousLogName(xmlLog.getCurrentLogName())
        xmlLog.setProcessId(ManagementFactory.getRuntimeMXBean().getName())
        xmlLog.setProgramName("INFINITE-LOGGING-PROTOTYPE-GROOVY")
        xmlLog.setProgramVersion("2.0.0.PRE")
        xmlLog.setThreadId(Thread.currentThread().getId() as BigInteger)
        xmlLog.setThreadName(Thread.currentThread().getName())
        xmlLog.setStartDateTime(getXMLGregorianCalendar())
    }

    Object result(String iResultVariableName, Object iResult) {
        XMLTrace xMLTraceResult = TraceSerializer.createXMLTraceTrace(iResultVariableName, iResult)
        xmlExecution.setResult(xMLTraceResult)
        return iResult
    }

    void exception(Throwable throwable) {
        XMLException xmlException = new XMLException()
        if (throwable.blackBoxLogged == false) {
            xmlException.setExceptionStackTrace(ExceptionUtils.getStackTrace(throwable))
            throwable.blackBoxLogged = true
        } else {
            xmlException.setExceptionStackTrace("Exception rethrown and stacktrace logged previously")
        }
        xmlException.setMessage(ExceptionUtils.getMessage(throwable))
        xmlException.setExceptionClassName(throwable.getClass().getCanonicalName())
        xmlException.setDateTime(getXMLGregorianCalendar())
        xmlExecution.getEvent().add(xmlException)
        xmlExecution.isFailed = true
    }

    void stdout(String iMessage) {
        XMLStdout xmlStdout = new XMLStdout()
        xmlStdout.setMessage(iMessage)
        xmlStdout.setDateTime(getXMLGregorianCalendar())
        xmlExecution.getEvent().add(xmlStdout)
    }

}
