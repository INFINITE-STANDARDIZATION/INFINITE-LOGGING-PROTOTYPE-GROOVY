package groovy


import infinite_logging.prototype.groovy.*
import org.apache.commons.lang3.exception.ExceptionUtils

import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

class BlackBoxEngine {

    public static ThreadLocal blackBoxEngineThreadLocal = new ThreadLocal()

    XMLASTNode astNode

    BlackBoxEngine() {
        addShutdownHook {
            //todo: possibly need to set this thread name to parent thread name for proper sifting appender file selection
            Thread.currentThread().setName("BlackBoxEngine Shutdown Hook " + Thread.currentThread().getId())
            while (astNode != null) {
                executionClose()
            }
        }
    }

    static BlackBoxEngine getInstance() {
        BlackBoxEngine blackBoxEngine = blackBoxEngineThreadLocal.get() as BlackBoxEngine
        if (blackBoxEngine == null) {
            XMLASTNode.getMetaClass().parentAstNode = null
            Throwable.getMetaClass().isLoggedByBlackBox = null
            if (System.getProperty("blackBox.mode") == BlackBoxMode.SEQUENTIAL.value()) {
                blackBoxEngine = new BlackBoxEngineSequential()
            } else if (System.getProperty("blackBox.mode") == BlackBoxMode.HIERARCHICAL.value()) {
                blackBoxEngine = new BlackBoxEngineHierarchical()
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
                XMLObject xmlObject = new XMLObject()
                xmlObject.setClassName(evaluationResult.getClass().getCanonicalName())
                xmlObject.setValue(evaluationResult.toString())
                astNode.setExpressionValue(xmlObject)
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
            case "ThrowStatement":
                while (!(astNode instanceof XMLMethodNode || (astNode instanceof XMLStatement && astNode.getStatementClassName() == "TryCatchStatement"))) {
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
        XMLObject xmlMethodResult = new XMLObject()
        xmlMethodResult.setValue(methodResult.toString())
        xmlMethodResult.setClassName(methodResult.getClass().getCanonicalName())
        ((XMLMethodNode) astNode).setMethodResult(xmlMethodResult)
        return methodResult
    }

    void exception(Throwable throwable) {
        //todo: log only 1 time
        //todo: log.error for exceptions
        XMLException xmlException = new XMLException()
        xmlException.setExceptionStackTrace(ExceptionUtils.getStackTrace(throwable))
        xmlException.setExceptionDateTime(getXMLGregorianCalendar())
        while (!(astNode instanceof XMLMethodNode)) {
            executionClose()
        }
        ((XMLMethodNode) astNode).setException(xmlException)
    }

}
