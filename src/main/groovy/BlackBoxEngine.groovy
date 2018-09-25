package groovy

import groovy.util.logging.Slf4j
import infinite_logging.prototype.groovy.*
import org.apache.commons.lang3.exception.ExceptionUtils
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.ConstructorCallExpression
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

    static Statement decorateMethod(MethodNode iMethodNode, BlackBoxLevel iBlackBoxLevel, AnnotationNode iAnnotationNode) {
        Statement methodCodeStatement = iMethodNode.getCode()
        Parameter[] methodParameters = iMethodNode.getParameters()
        BlockStatement decoratedBlockStatement = new BlockStatement()
        BlockStatement tryBlock = new BlockStatement()
        tryBlock.addStatement(methodCodeStatement)
        Statement finallyBlock
        if (iBlackBoxLevel.value() >= BlackBoxLevel.METHOD.value()) {
            decoratedBlockStatement.addStatement(text2statement("""automaticBlackBox.getInstance().methodExecutionOpen("${
                iMethodNode.getDeclaringClass().getNameWithoutPackage()
            }", "${iMethodNode.getDeclaringClass().getPackageName()}", "${
                iMethodNode.getName()
            }" %s)""", methodParameters))
            finallyBlock = text2statement("automaticBlackBox.getInstance().executionClose()")
            decoratedBlockStatement.addStatement(createTryCatch("automaticBlackBox.getInstance().exception(throwable)", tryBlock, finallyBlock, methodParameters, iAnnotationNode))
        } else if (iBlackBoxLevel == BlackBoxLevel.ERROR) {
            finallyBlock = new EmptyStatement()
            decoratedBlockStatement.addStatement(createTryCatch("automaticBlackBox.getInstance().exception(throwable)", tryBlock, finallyBlock, methodParameters, iAnnotationNode))
        } else {
            return methodCodeStatement
        }
        return decoratedBlockStatement
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
        getInstance().methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "decorateStatement", ["iCodeText": iCodeText, "iParameters": iParameters])
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

    void methodExecutionOpen(String iClassSimpleName, String iPackageName, String iMethodName, Map<String, Object> methodArgumentMap) {
        if (xmlExecution == null) {
            logOpen(iClassSimpleName, iPackageName, iMethodName)
        }
        XMLMethodExecution newXmlExecution = new XMLMethodExecution()
        newXmlExecution.parentExecution = xmlExecution
        newXmlExecution.setStartDateTime(getXMLGregorianCalendar())
        newXmlExecution.setMethodName(iMethodName)
        newXmlExecution.setClassName(iPackageName + "." + iClassSimpleName)
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
        if (xmlExecution.getException() == null) {
            xmlExecutionTrailer.setExecutionStatus(XMLExecutionStatus.NORMAL)
        } else {
            xmlExecutionTrailer.setExecutionStatus(XMLExecutionStatus.UNHANDLED_EXCEPTION)
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

    private void logOpen(String iClassSimpleName, String iPackageName, String iMethodName) {
        XMLLog xmlLog = new XMLLog()
        xmlExecution = xmlLog
        xmlLog.setCurrentLogName(iPackageName + "." + iClassSimpleName + "-" + iMethodName + ".log")
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
        XMLMethodException xmlMethodException = new XMLMethodException()
        xmlMethodException.setExceptionStackTrace(ExceptionUtils.getStackTrace(throwable))
        xmlMethodException.setMessage(ExceptionUtils.getMessage(throwable))
        xmlExecution.setException(xmlMethodException)
    }


    void stdout(String iMessage) {
        XMLStdout xmlStdout = new XMLStdout()
        xmlStdout.setMessage(iMessage)
        xmlStdout.setDateTime(getXMLGregorianCalendar())
        xmlExecution.getEvent().add(xmlStdout)
    }

}
