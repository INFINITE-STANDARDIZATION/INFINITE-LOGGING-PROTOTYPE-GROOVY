package groovy


import groovy.util.logging.Slf4j
import infinite_logging.prototype.groovy.*
import org.apache.commons.lang3.exception.ExceptionUtils

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

class BlackBoxEngine {

    public static ThreadLocal blackBoxEngineThreadLocal = new ThreadLocal()

    XMLASTNode astNode

    BlackBoxEngine() {
        addShutdownHook {
            synchronized (this) {
                while (astNode != null) {
                    executionClose()
                }
            }
        }
    }

    static BlackBoxEngine getInstance() {
        BlackBoxEngine blackBoxEngine = blackBoxEngineThreadLocal.get(BlackBoxEngine.class) as BlackBoxEngine
        if (blackBoxEngine == null) {
            XMLASTNode.getMetaClass().parentAstNode = null
            Throwable.getMetaClass().isLoggedByBlackBox = null
            if (System.getProperty("blackBox.mode") == BlackBoxMode.SEQUENTIAL.value()) {
                blackBoxEngine = new BlackBoxEngineSequential()
            } else {
                blackBoxEngine = new BlackBoxEngineEmergency()
            }
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

    void expressionExecutionOpen(String iExpressionName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, String iNodeSourceName) {
        XMLExpression xmlExpression = new XMLExpression()
        xmlExpression.parentAstNode = astNode
        xmlExpression.setAstNodeList(new XMLASTNodeList())
        xmlExpression.setStartDateTime(getXMLGregorianCalendar())
        xmlExpression.setExpressionClassName(iExpressionName)
        xmlExpression.setRestoredScriptCode(iRestoredScriptCode)
        xmlExpression.setColumnNumber(iColumnNumber as BigInteger)
        xmlExpression.setLastColumnNumber(iLastColumnNumber as BigInteger)
        xmlExpression.setLineNumber(iLineNumber as BigInteger)
        xmlExpression.setLastLineNumber(iLastLineNumber as BigInteger)
        xmlExpression.setSourceNodeName(iNodeSourceName)
        astNode.getAstNodeList().getAstNode().add(xmlExpression)
        astNode = xmlExpression
    }

    Object expressionEvaluation(String iExpressionName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, Closure iClosure, String iNodeSourceName) {
        expressionExecutionOpen(iExpressionName, iRestoredScriptCode, iColumnNumber, iLastColumnNumber, iLineNumber, iLastLineNumber, iNodeSourceName)
        try {
            Object evaluationResult = iClosure.call()
            //Avoid logging empty results such as for void method call expressions
            if (evaluationResult != null) {
                astNode.setExpressionValue(evaluationResult.toString())
            }
            return evaluationResult
        } catch (Throwable throwable) {
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
        xmlStatement.setStatementClassName(iStatementName)
        xmlStatement.setRestoredScriptCode(iRestoredScriptCode)
        xmlStatement.setColumnNumber(iColumnNumber as BigInteger)
        xmlStatement.setLastColumnNumber(iLastColumnNumber as BigInteger)
        xmlStatement.setLineNumber(iLineNumber as BigInteger)
        xmlStatement.setLastLineNumber(iLastLineNumber as BigInteger)
        xmlStatement.setSourceNodeName(iNodeSourceName)
        astNode.getAstNodeList().getAstNode().add(xmlStatement)
        astNode = xmlStatement
    }

    void methodExecutionOpen(
            String iClassSimpleName,
            String iPackageName,
            String iMethodName,
            Integer iColumnNumber,
            Integer iLastColumnNumber,
            Integer iLineNumber,
            Integer iLastLineNumber,
            Map<String, Object> methodArgumentMap
    ) {
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
        xmlMethodNode.setArgumentList(new XMLArgumentList())
        for (methodArgumentName in methodArgumentMap.keySet()) {
            XMLArgument xmlArgument = new XMLArgument()
            xmlArgument.setArgumentClassName(methodArgumentMap.get(methodArgumentName).getClass().getCanonicalName())
            xmlArgument.setArgumentName(methodArgumentName)
            xmlArgument.setArgumentValue(methodArgumentMap.get(methodArgumentName).toString())
            xmlMethodNode.getArgumentList().getArgument().add(xmlArgument)
        }
        astNode.getAstNodeList().getAstNode().add(xmlMethodNode)
        astNode = xmlMethodNode
    }

    void executionClose() {
        astNode = astNode.parentAstNode
    }

    void handleControlStatement(String iStatementName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, String iNodeSourceName) {
        statementExecutionOpen(iStatementName, iRestoredScriptCode, iColumnNumber, iLastColumnNumber, iLineNumber, iLastLineNumber, iNodeSourceName)
        executionClose()
        switch (iStatementName) {
            case "ReturnStatement":
                while (!(astNode instanceof XMLMethodNode || (astNode instanceof XMLExpression && astNode.getExpressionClassName() == "ClosureExpression"))) {
                    executionClose()
                }
                break
            case "BreakStatement":
                while (!(astNode instanceof XMLStatement && ["DoWhileStatement", "ForStatement", "WhileStatement", "SwitchStatement"].contains(astNode.getStatementClassName()))) {
                    executionClose()
                }
                break
            case "ContinueStatement":
                while (!(astNode instanceof XMLStatement && ["DoWhileStatement", "ForStatement", "WhileStatement"].contains(astNode.getStatementClassName()))) {
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

    Object executeMethod(Closure iMethodClosure) {
        Object methodResult = iMethodClosure.call()
        XMLMethodResult xmlMethodResult = new XMLMethodResult()
        xmlMethodResult.setMethodResultValue(methodResult.toString())
        xmlMethodResult.setMethodResultClassName(methodResult.getClass().getCanonicalName())
        ((XMLMethodNode) astNode).setMethodResult(xmlMethodResult)
        return methodResult
    }

    void exception(Throwable throwable) {
        //todo: log only 1 time
        XMLException xmlException = new XMLException()
        xmlException.setExceptionStackTrace(ExceptionUtils.getStackTrace(throwable))
        xmlException.setExceptionDateTime(getXMLGregorianCalendar())
        while (!(astNode instanceof XMLMethodNode)) {
            executionClose()
        }
        ((XMLMethodNode) astNode).setException(xmlException)
    }

}
