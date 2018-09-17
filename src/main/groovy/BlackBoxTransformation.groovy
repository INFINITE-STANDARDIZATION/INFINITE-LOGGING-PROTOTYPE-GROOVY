package groovy

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@ToString(includeNames = true, includeFields = true, includePackage = false)
@GroovyASTTransformation(
        phase = CompilePhase.SEMANTIC_ANALYSIS
)
class BlackBoxTransformation extends AbstractASTTransformation {

    final String PCLASSSIMPLENAME= this.getClass().getSimpleName()
    final String PPACKAGENAME= this.getClass().getPackage().getName()
    final BlackBoxEngine blackBoxEngine = BlackBoxEngine.getInstance()

    void visit(ASTNode[] iAstNodeArray, SourceUnit iSourceUnit) {
        final String LMETHODNAME = "visit"
        try {
            MethodNode
            ASTNode methodNode = iAstNodeArray[1]
            String methodName = methodNode.getName()
            String className = methodNode.getDeclaringClass().getNameWithoutPackage()
            blackBoxEngine.logOpen(className, methodName)
            blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, LMETHODNAME, ["className":className, "methodName":methodName, "methodNode.getCode()": methodNode.getCode()])
            BlackBoxVisitor blackBoxVisitor = new BlackBoxVisitor()
            methodNode.getCode().visit(blackBoxVisitor)
        } catch (Throwable throwable) {
            blackBoxEngine.logMethodException(throwable)
            throw throwable
        } finally {
            blackBoxEngine.methodExecutionClose()
            blackBoxEngine.logClose()
        }
    }

}
