package groovy


import groovy.util.logging.Slf4j
import infinite_logging.prototype.groovy.*
import org.apache.commons.lang3.exception.ExceptionUtils

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

@Slf4j
class BlackBoxEngine {

    public static ThreadLocal blackBoxEngineThreadLocal = new ThreadLocal()

    XMLASTNode astNode

    ConfigObject configObject

    BlackBoxVisitor blackBoxVisitor

    final static NOARGSMAP = new HashMap<String, Object>()


    final String PCLASSSIMPLENAME = this.getClass().getSimpleName()
    final String PPACKAGENAME = this.getClass().getPackage().getName()

    BlackBoxEngine() {
        addShutdownHook {
            synchronized (this) {
                while (astNode != null) {
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
            XMLASTNode.getMetaClass().parentAstNode = null
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
            case XMLMethodNode: iTagString += """event xsi:type="MethodExecution" """; break
            case XMLStatement: iTagString += """event xsi:type="StatementExecution" """; break
            case XMLExpression: iTagString += """event xsi:type=ExpressionEvaluation" """; break
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

    Object handleReturn(String iExpressionName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, Closure iClosure, String iNodeSourceName, String iReturnStatementCodeString) {
        XMLASTNode astNodeForChecking = astNode
        while (!(astNodeForChecking instanceof XMLMethodNode || (astNodeForChecking instanceof XMLExpression && astNodeForChecking.getExpressionName() == "ClosureExpression"))) {
            astNodeForChecking = astNodeForChecking.parentAstNode
        }
        Object expressionResult = expressionEvaluation(iExpressionName, iRestoredScriptCode, iColumnNumber, iLastColumnNumber, iLineNumber, iLastLineNumber, iClosure,iNodeSourceName)
        switch (astNodeForChecking) {
            case XMLMethodNode:
                astNodeForChecking.setMethodResult(TraceSerializer.createXMLTraceTrace(iReturnStatementCodeString, expressionResult))
                break
            case XMLExpression:
                astNodeForChecking.setExpressionResult(TraceSerializer.createXMLTraceTrace(iReturnStatementCodeString, expressionResult))
                break
        }
        return expressionResult
    }

    Object expressionEvaluation(String iExpressionName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, Closure iClosure, String iNodeSourceName) {
        XMLExpression xmlExpression = new XMLExpression()
        xmlExpression.parentAstNode = astNode
        xmlExpression.setAstNodeList(new XMLASTNodeList())
        xmlExpression.setStartDateTime(getXMLGregorianCalendar())
        xmlExpression.setExpressionName(iExpressionName)
        xmlExpression.setRestoredScriptCode(iRestoredScriptCode)
        xmlExpression.setColumnNumber(iColumnNumber as BigInteger)
        xmlExpression.setLastColumnNumber(iLastColumnNumber as BigInteger)
        xmlExpression.setLineNumber(iLineNumber as BigInteger)
        xmlExpression.setLastLineNumber(iLastLineNumber as BigInteger)
        xmlExpression.setSourceNodeName(iNodeSourceName)
        astNode.getAstNodeList().getAstNode().add(xmlExpression)
        astNode = xmlExpression
        try {
            Object evaluationResult = iClosure.call()
            //Avoid logging empty results such as for void method call expressions
            if (evaluationResult != null) {
                xmlExpression.setExpressionResult(TraceSerializer.createXMLTraceTrace(iRestoredScriptCode/*todo: parent*/, evaluationResult))
            }
            return evaluationResult
        } catch (Throwable throwable) {
            exception(throwable)
            throw throwable
        } finally {
            executionClose()
        }
    }

    void statementExecutionOpen(String iStatementName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, String iNodeSourceName) {
        XMLStatement xmlStatement = new XMLStatement()
        xmlStatement.parentAstNode = astNode
        xmlStatement.setAstNodeList(new XMLASTNodeList())
        xmlStatement.setStartDateTime(getXMLGregorianCalendar())
        xmlStatement.setStatementName(iStatementName)
        xmlStatement.setRestoredScriptCode(iRestoredScriptCode)
        xmlStatement.setColumnNumber(iColumnNumber as BigInteger)
        xmlStatement.setLastColumnNumber(iLastColumnNumber as BigInteger)
        xmlStatement.setLineNumber(iLineNumber as BigInteger)
        xmlStatement.setLastLineNumber(iLastLineNumber as BigInteger)
        xmlStatement.setSourceNodeName(iNodeSourceName)
        astNode.getAstNodeList().getAstNode().add(xmlStatement)
        astNode = xmlStatement
    }

    void methodExecutionOpen(String iClassSimpleName, String iPackageName, String iMethodName, Map<String, Object> methodArgumentMap, Integer iColumnNumber = null, Integer iLastColumnNumber = null, Integer iLineNumber = null, Integer iLastLineNumber = null) {
        //todo: "implicit" error logging - print log only when method has status failed, and save arguments
        if (astNode == null) {
            initRootAstNode()
        }
        XMLMethodNode xmlMethodNode = new XMLMethodNode()
        xmlMethodNode.parentAstNode = astNode
        xmlMethodNode.setAstNodeList(new XMLASTNodeList())
        xmlMethodNode.setStartDateTime(getXMLGregorianCalendar())
        xmlMethodNode.setMethodName(iMethodName)
        xmlMethodNode.setClassName(iPackageName + "." + iClassSimpleName)
        xmlMethodNode.setColumnNumber(iColumnNumber as BigInteger)
        xmlMethodNode.setLastColumnNumber(iLastColumnNumber as BigInteger)
        xmlMethodNode.setLineNumber(iLineNumber as BigInteger)
        xmlMethodNode.setLastLineNumber(iLastLineNumber as BigInteger)
        xmlMethodNode.setArgumentTraceList(new XMLTraceList())
        for (methodArgumentName in methodArgumentMap.keySet()) {
            XMLTrace xMLTrace = TraceSerializer.createXMLTraceTrace(methodArgumentName, methodArgumentMap.get(methodArgumentName))
            xmlMethodNode.getArgumentTraceList().getTrace().add(xMLTrace)
        }
        astNode.getAstNodeList().getAstNode().add(xmlMethodNode)
        astNode = xmlMethodNode
    }

    void executionClose() {
        if (astNode.parentAstNode == null) {
            JAXBContext lJAXBContext = JAXBContext.newInstance(astNode.getClass())
            Marshaller marshaller = lJAXBContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE)
            StringWriter stringWriter = new StringWriter()
            marshaller.marshal(new ObjectFactory().createRootAstNode(astNode), stringWriter)
            String xmlString = stringWriter.toString()
            log.debug(xmlString)
        }
        astNode = astNode.parentAstNode
    }

    void handleControlStatement(String iStatementName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, String iNodeSourceName) {
        statementExecutionOpen(iStatementName, iRestoredScriptCode, iColumnNumber, iLastColumnNumber, iLineNumber, iLastLineNumber, iNodeSourceName)
        executionClose()
        switch (iStatementName) {
            case "ReturnStatement":
                while (!(astNode instanceof XMLMethodNode || (astNode instanceof XMLExpression && astNode.getExpressionName() == "ClosureExpression"))) {
                    executionClose()
                }
                break
            case "BreakStatement":
                while (!(astNode instanceof XMLStatement && ["DoWhileStatement", "ForStatement", "WhileStatement", "SwitchStatement"].contains(astNode.getStatementName()))) {
                    executionClose()
                }
                break
            case "ContinueStatement":
                while (!(astNode instanceof XMLStatement && ["DoWhileStatement", "ForStatement", "WhileStatement"].contains(astNode.getStatementName()))) {
                    executionClose()
                }
                break
        }
    }

    void initRootAstNode() {
        astNode = new XMLASTNode()
        astNode.setAstNodeList(new XMLASTNodeList())
        astNode.setStartDateTime(getXMLGregorianCalendar())
    }

    Object methodResult(String iResultVariableName, Object iResult) {
        XMLTrace xMLTraceResult = TraceSerializer.createXMLTraceTrace(iResultVariableName, iResult)
        astNode.setMethodResult(xMLTraceResult)
        return iResult
    }

    void exception(Throwable throwable) {
        XMLException xmlException = new XMLException()
        xmlException.setExceptionStackTrace(ExceptionUtils.getStackTrace(throwable))
        xmlException.setExceptionDateTime(getXMLGregorianCalendar())
        while (!(astNode instanceof XMLMethodNode)) {
            executionClose()
        }
        ((XMLMethodNode)astNode).setException(xmlException)
    }

}
