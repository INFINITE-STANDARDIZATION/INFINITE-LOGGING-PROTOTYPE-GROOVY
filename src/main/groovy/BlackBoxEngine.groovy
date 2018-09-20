package groovy


import groovy.util.logging.Slf4j
import infinite_logging.prototype.groovy.*
import org.apache.commons.lang3.exception.ExceptionUtils
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.EmptyStatement
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.ast.stmt.ThrowStatement
import org.codehaus.groovy.ast.stmt.TryCatchStatement
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

    Execution execution

    ConfigObject configObject

    final static NOARGSMAP = new HashMap<String, Object>()


    final static String PCLASSSIMPLENAME = this.getClass().getSimpleName()
    final static String PPACKAGENAME = this.getClass().getPackage().getName()

    static BlackBoxEngine getInstance() {
        BlackBoxEngine blackBoxEngine = blackBoxEngineThreadLocal.get(BlackBoxEngine.class)
        if (blackBoxEngine == null) {
            blackBoxEngine = new BlackBoxEngine()
            String blackBoxConfFileName = System.getProperty("blackBoxConfFileName")
            if (blackBoxConfFileName == null) {
                blackBoxConfFileName = "src/main/resources/groovy/BlackBox.config"
            }
            blackBoxEngine.configObject = new ConfigSlurper().parse(new File(blackBoxConfFileName).toURI().toURL())
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

    static Statement decorateMethod(MethodNode iMethodNode, BlackBoxLevel iBlackBoxLevel) {
        Statement methodCodeStatement = iMethodNode.getCode()
        Parameter[] methodParameters = iMethodNode.getParameters()
        BlockStatement decoratedBlockStatement = new BlockStatement()
        BlockStatement tryBlock = new BlockStatement()
        tryBlock.addStatement(methodCodeStatement)
        Statement finallyBlock
        if (iBlackBoxLevel.value() >= BlackBoxLevel.METHOD.value()) {
            decoratedBlockStatement.addStatement(text2statement("""groovy.BlackBoxEngine.getInstance().methodExecutionOpen("${iMethodNode.getDeclaringClass().getNameWithoutPackage()}", "${iMethodNode.getDeclaringClass().getPackageName()}", "${iMethodNode.getName()}" %s)""", methodParameters))
            finallyBlock = text2statement("groovy.BlackBoxEngine.getInstance().methodExecutionClose()")
            decoratedBlockStatement.addStatement(createTryCatch("groovy.BlackBoxEngine.getInstance().methodException(throwable)", tryBlock, finallyBlock, methodParameters))
        } else if (iBlackBoxLevel == BlackBoxLevel.ERROR) {
            finallyBlock = new EmptyStatement()
            decoratedBlockStatement.addStatement(createTryCatch("groovy.BlackBoxEngine.getInstance().methodException(throwable)", tryBlock, finallyBlock, methodParameters))
        } else {
            return methodCodeStatement
        }
        return decoratedBlockStatement
    }

    static TryCatchStatement createTryCatch(String iLogErrorCodeLine, BlockStatement iMainBlock, Statement iFinallyBlock, Parameter[] iParameters) {
        TryCatchStatement tryCatchStatement = new TryCatchStatement(iMainBlock, iFinallyBlock)
        BlockStatement throwBlock = new BlockStatement()
        throwBlock.addStatement(text2statement(iLogErrorCodeLine, iParameters))
        throwBlock.addStatement(createRethrow())
        tryCatchStatement.addCatch(GeneralUtils.catchS(GeneralUtils.param(ClassHelper.make(Throwable.class), "throwable"), throwBlock))
        return tryCatchStatement
    }

    static Statement createRethrow() {
        ThrowStatement l_throw_statement = GeneralUtils.throwS(GeneralUtils.varX("throwable"))
        //l_throw_statement.setSourcePosition(p_annotation_node) TODO
        return l_throw_statement
    }

    static Statement text2statement(String iCodeText, Parameter[] iParameters) {
        getInstance().methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "decorateStatement", ["iCodeText": iCodeText, "i_parameters": iParameters])
        try {
            String statementCode
            if (methodArgumentsPresent(iParameters)) {
            ArrayList<String> serializedParameters = new ArrayList<String>()
                for (parameter in iParameters) {
                    serializedParameters.add(""""${parameter.getName()}": ${parameter.getName()}""")
                }
                statementCode = String.format(iCodeText, """, [${serializedParameters.join(",")}]""")
            } else {
                statementCode = String.format(iCodeText, ", groovy.BlackBoxEngine.NOARGSMAP")
            }
            List<ASTNode> resultingStatements = new AstBuilder().buildFromString(CompilePhase.SEMANTIC_ANALYSIS, statementCode)
            return getInstance().methodResult("resultingStatements.first()", resultingStatements.first()) as Statement
        } catch (Throwable throwable) {
            getInstance().methodException(throwable)
            throw throwable
        } finally {
            getInstance().methodExecutionClose()
        }

    }

    static Statement decorateStatement(Statement iStatement) {
        getInstance().methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, "decorateStatement", ["iStatement": iStatement])
        try {
            BlockStatement blockStatement = new BlockStatement()
            blockStatement.addStatement(text2statement("groovy.BlackBoxEngine.getInstance().statementExecutionOpen()"))
            blockStatement.addStatement(iStatement)
            return getInstance().methodResult("iStatement", iStatement) as Statement
        } catch (Throwable throwable) {
            getInstance().methodException(throwable)
            throw throwable
        } finally {
            getInstance().methodExecutionClose()
        }
    }

    static XMLGregorianCalendar getXMLGregorianCalendar(Date date = new Date()) {
        GregorianCalendar lGregorianCalendar = new GregorianCalendar()
        lGregorianCalendar.setTime(date)
        XMLGregorianCalendar lXMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(lGregorianCalendar)
        return lXMLGregorianCalendar
    }

    void statementExecutionOpen(String iClassSimpleName, String iPackageName, String iMethodName, Map<String, Object> methodArgumentMap) {
        XMLMethodExecution xmlMethodExecution = new XMLMethodExecution()
        execution = new Execution(xmlMethodExecution, execution)
        xmlMethodExecution.setStartDateTime(getXMLGregorianCalendar(execution.getStartDate()))
        XMLMethodNode xmlMethodNode = new XMLMethodNode()
        xmlMethodNode.setName(iMethodName)
        XMLClassNode xmlClassNode = new XMLClassNode()
        xmlClassNode.setGetNameWithoutPackage(iClassSimpleName)
        xmlClassNode.setGetPackageName(iPackageName)
        xmlMethodNode.setDeclaringClass(xmlClassNode)
        xmlMethodExecution.setMethodNode(xmlMethodNode)
        for (methodArgumentName in methodArgumentMap.keySet()) {
            XMLTrace xMLTrace = TraceSerializer.createXMLTraceTrace(methodArgumentName, methodArgumentMap.get(methodArgumentName))
            xmlMethodExecution.getMethodArgument().add(xMLTrace)
        }
        execution.getParentExecution().getXmlExecution().getEvent().add(xmlMethodExecution)
        execution.setXmlMethodExecution(xmlMethodExecution)
    }

    void methodExecutionOpen(String iClassSimpleName, String iPackageName, String iMethodName, Map<String, Object> methodArgumentMap) {
        XMLMethodExecution xmlMethodExecution = new XMLMethodExecution()
        execution = new Execution(xmlMethodExecution, execution)
        xmlMethodExecution.setStartDateTime(getXMLGregorianCalendar(execution.getStartDate()))
        XMLMethodNode xmlMethodNode = new XMLMethodNode()
        xmlMethodNode.setName(iMethodName)
        XMLClassNode xmlClassNode = new XMLClassNode()
        xmlClassNode.setGetNameWithoutPackage(iClassSimpleName)
        xmlClassNode.setGetPackageName(iPackageName)
        xmlMethodNode.setDeclaringClass(xmlClassNode)
        xmlMethodExecution.setMethodNode(xmlMethodNode)
        for (methodArgumentName in methodArgumentMap.keySet()) {
            XMLTrace xMLTrace = TraceSerializer.createXMLTraceTrace(methodArgumentName, methodArgumentMap.get(methodArgumentName))
            xmlMethodExecution.getMethodArgument().add(xMLTrace)
        }
        execution.getParentExecution().getXmlExecution().getEvent().add(xmlMethodExecution)
        execution.setXmlMethodExecution(xmlMethodExecution)
    }

    void executionClose() {
        Date endDate = new Date()
        execution.setEndDate(endDate)
        XMLExecutionTrailer xmlExecutionTrailer = new XMLExecutionTrailer()
        xmlExecutionTrailer.setEndDateTime(getXMLGregorianCalendar(endDate))
        if (execution.getThrowable() == null) {
            xmlExecutionTrailer.setExecutionStatus(XMLExecutionStatus.NORMAL)
        } else {
            xmlExecutionTrailer.setExecutionStatus(XMLExecutionStatus.UNHANDLED_EXCEPTION)
        }
        xmlExecutionTrailer.setElapsedTime(execution.getEndDate().getTime() - execution.getStartDate().getTime() as BigInteger)
        execution.getXmlExecution().setExecutionTrailer(xmlExecutionTrailer)
        execution = execution.getParentExecution()
    }

    void methodExecutionClose() {
        executionClose()
    }

    void logOpen(String iClassName, String iMethodName) {
        XMLLog xmlLog = new XMLLog()
        execution = new Execution(xmlLog, execution)
        xmlLog.setCurrentLogName(iClassName + "-" + iMethodName + ".log")
        xmlLog.setPreviousLogName(xmlLog.getCurrentLogName())
        xmlLog.setProcessId(ManagementFactory.getRuntimeMXBean().getName())
        xmlLog.setProgramName("INFINITE-LOGGING-PROTOTYPE-GROOVY")
        xmlLog.setProgramVersion("2.0.0.PRE")
        xmlLog.setThreadId(Thread.currentThread().getId() as BigInteger)
        xmlLog.setThreadName(Thread.currentThread().getName())
        xmlLog.setStartDateTime(getXMLGregorianCalendar(execution.getStartDate()))
    }

    void logClose() {
        XMLLog xmlLog = execution.getXmlExecution() as XMLLog
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
        executionClose()
    }

    Object methodResult(String iResultVariableName, Object iResult) {
        XMLTrace xMLTraceResult = TraceSerializer.createXMLTraceTrace(iResultVariableName, iResult)
        execution.getXmlMethodExecution().setMethodResult(xMLTraceResult)
        return iResult
    }

    void methodException(Throwable throwable) {
        XMLMethodException xmlMethodException = new XMLMethodException()
        xmlMethodException.setExceptionStackTrace(ExceptionUtils.getStackTrace(throwable))
        xmlMethodException.setMessage(ExceptionUtils.getMessage(throwable))
        execution.getXmlMethodExecution().setMethodException(xmlMethodException)
        execution.setThrowable(throwable)
    }
}
