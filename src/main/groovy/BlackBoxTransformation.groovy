package groovy


import groovy.transform.ToString
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.PropertyExpression
import org.codehaus.groovy.ast.stmt.BlockStatement
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
            MethodNode methodNode = iAstNodeArray[1] as MethodNode
            String methodName = methodNode.getName()
            String className = methodNode.getDeclaringClass().getNameWithoutPackage()
            blackBoxEngine.methodExecutionOpen(PCLASSSIMPLENAME, PPACKAGENAME, LMETHODNAME, ["className":className, "methodName":methodName, "methodNode.getCode()": methodNode.getCode()])
            BlackBoxVisitor blackBoxVisitor = new BlackBoxVisitor()
            methodNode.getCode().visit(blackBoxVisitor)
            AnnotationNode annotationNode = iAstNodeArray[0] as AnnotationNode
            BlackBoxLevel blackBoxLevel = getBlackBoxLevel(annotationNode)
            BlockStatement decoratedMethodNodeBlockStatement = new BlockStatement()
            decoratedMethodNodeBlockStatement.setSourcePosition(methodNode.getCode())
            decoratedMethodNodeBlockStatement.copyNodeMetaData(methodNode.getCode())
            decoratedMethodNodeBlockStatement.addStatement(blackBoxEngine.createLoggerDeclaration())
            decoratedMethodNodeBlockStatement.addStatement(blackBoxEngine.decorateMethod(methodNode, blackBoxLevel, annotationNode))
            methodNode.setCode(decoratedMethodNodeBlockStatement)
            blackBoxEngine.result("methodNode.getCode()", methodNode.getDeclaringClass())
        } catch (Throwable throwable) {
            blackBoxEngine.exception(throwable)
            throw throwable
        } finally {
            blackBoxEngine.executionClose()
            blackBoxEngine.executionClose()
        }
    }

    static BlackBoxLevel getBlackBoxLevel(ASTNode iAnnotationNode) {
        AnnotationNode annotationNode = iAnnotationNode as AnnotationNode
        PropertyExpression propertyExpression = annotationNode.getMember("blackBoxLevel") as PropertyExpression
        ConstantExpression constantExpression = propertyExpression.getProperty() as ConstantExpression
        return constantExpression.getValue() as BlackBoxLevel
    }

}
