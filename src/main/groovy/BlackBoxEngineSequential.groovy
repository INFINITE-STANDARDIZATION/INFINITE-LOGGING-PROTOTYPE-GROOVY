package groovy

import groovy.util.logging.Slf4j

@Slf4j
class BlackBoxEngineSequential extends BlackBoxEngine {

    Integer depth = 0

    @Override
    void expressionExecutionOpen(String iExpressionName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, String iNodeSourceName) {
        super.expressionExecutionOpen(iExpressionName, iRestoredScriptCode, iColumnNumber, iLastColumnNumber, iLineNumber, iLastLineNumber, iNodeSourceName)
        depth++
        log.debug(":".padRight(depth) + "expressionExecutionOpen")
    }

    @Override
    void statementExecutionOpen(String iStatementName, String iRestoredScriptCode, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, String iNodeSourceName) {
        super.statementExecutionOpen(iStatementName, iRestoredScriptCode, iColumnNumber, iLastColumnNumber, iLineNumber, iLastLineNumber, iNodeSourceName)
        depth++
        log.debug(":".padRight(depth) + "statementExecutionOpen")
    }

    @Override
    void methodExecutionOpen(String iClassSimpleName, String iPackageName, String iMethodName, Integer iColumnNumber, Integer iLastColumnNumber, Integer iLineNumber, Integer iLastLineNumber, Map<String, Object> methodArgumentMap) {
        super.methodExecutionOpen(iClassSimpleName, iPackageName, iMethodName, iColumnNumber, iLastColumnNumber, iLineNumber, iLastLineNumber, methodArgumentMap)
        depth++
        log.debug(":".padRight(depth) + "methodExecutionOpen")
    }

    @Override
    void executionClose() {
        super.executionClose()
        log.debug(":".padRight(depth) + "executionClose")
        depth--
    }
}
