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

    public static ThreadLocal blackBoxEngineThreadLocal = new ThreadLocal()

    XMLExecution xmlExecution

    ConfigObject configObject

    BlackBoxVisitor blackBoxVisitor

    final static NOARGSMAP = new HashMap<String, Object>()


    final String PCLASSSIMPLENAME = this.getClass().getSimpleName()
    final String PPACKAGENAME = this.getClass().getPackage().getName()

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
        BlackBoxEngine blackBoxEngine = blackBoxEngineThreadLocal.get(BlackBoxEngine.class) as BlackBoxEngine
        if (blackBoxEngine == null) {
            XMLExecution.getMetaClass().parentExecution = null
            XMLExecution.getMetaClass().isFailed = null
            blackBoxEngine = new BlackBoxEngine()
            blackBoxEngineThreadLocal.set(blackBoxEngine)
        }
        return blackBoxEngine
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

    static XMLGregorianCalendar getXMLGregorianCalendar(Date date = new Date()) {
        GregorianCalendar lGregorianCalendar = new GregorianCalendar()
        lGregorianCalendar.setTime(date)
        XMLGregorianCalendar lXMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(lGregorianCalendar)
        return lXMLGregorianCalendar
    }

    Object expressionEvaluation(String iExpressionName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, Closure iClosure) {
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
        //todo: "implicit" error logging - print log only when method has status failed, and save arguments
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
        xmlException.setExceptionStackTrace(ExceptionUtils.getStackTrace(throwable))
        xmlException.setMessage(ExceptionUtils.getMessage(throwable))
        xmlException.setExceptionClassName(throwable.getClass().getCanonicalName())
        xmlException.setDateTime(getXMLGregorianCalendar())
        while (!(xmlExecution instanceof XMLMethodExecution)) {
            xmlExecution.getEvent().add(xmlException)
            xmlExecution.isFailed = true
            executionClose()
        }
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
