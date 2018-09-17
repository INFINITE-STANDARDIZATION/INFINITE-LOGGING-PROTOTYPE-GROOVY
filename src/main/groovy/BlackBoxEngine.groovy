package groovy


import groovy.inspect.swingui.AstNodeToScriptVisitor
import groovy.util.logging.Slf4j
import infinite_logging.prototype.groovy.*
import org.apache.commons.lang3.exception.ExceptionUtils
import org.codehaus.groovy.ast.ASTNode

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar
import java.lang.management.ManagementFactory

@Slf4j
class BlackBoxEngine {

    public static ThreadLocal<BlackBoxEngine> blackBoxEngineThreadLocal = new ThreadLocal<BlackBoxEngine>()

    Execution execution

    static BlackBoxEngine getInstance() {
        BlackBoxEngine blackBoxEngine = blackBoxEngineThreadLocal.get(BlackBoxEngine.class)
        if (blackBoxEngine == null) {
            blackBoxEngine = new BlackBoxEngine()
            blackBoxEngineThreadLocal.set(blackBoxEngine)
        }
        return blackBoxEngine
    }

    static XMLGregorianCalendar getXMLGregorianCalendar(Date date = new Date()) {
        GregorianCalendar lGregorianCalendar = new GregorianCalendar()
        lGregorianCalendar.setTime(date)
        XMLGregorianCalendar lXMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(lGregorianCalendar)
        return lXMLGregorianCalendar
    }

    String ast2text(ASTNode iAstNode) {
        StringWriter l_string_writer = new StringWriter()
        iAstNode.visit(new AstNodeToScriptVisitor(l_string_writer))
        return l_string_writer.getBuffer().toString().replace("\$", "")
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
            XMLObject xmlObject = TraceSerializer.createXMLObjectTrace(methodArgumentName, methodArgumentMap.get(methodArgumentName))
            xmlMethodExecution.getMethodArgument().add(xmlObject)
        }
        execution.getParentExecution().getXmlExecution().getEvent().add(xmlMethodExecution)
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
        xmlExecutionTrailer.setElapsedTime(execution.getEndDate().getTime()-execution.getStartDate().getTime() as BigInteger)
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

    void logMethodException(Throwable throwable) {
        XMLMethodException xmlMethodException = new XMLMethodException()
        //xmlMethodException.setExceptionStackTrace(ExceptionUtils.getRootCauseStackTrace(throwable).join("\n"))
        xmlMethodException.setExceptionStackTrace(ExceptionUtils.getStackTrace(throwable))
        xmlMethodException.setMessage(ExceptionUtils.getMessage(throwable))
        execution.getXmlExecution().getEvent().add(xmlMethodException)
        execution.setThrowable(throwable)
    }
}
