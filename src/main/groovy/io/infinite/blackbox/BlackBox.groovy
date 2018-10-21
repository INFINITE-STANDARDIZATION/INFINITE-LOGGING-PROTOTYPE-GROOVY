package groovy.io.infinite.blackbox

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Target([ElementType.METHOD, ElementType.CONSTRUCTOR])
@Retention(RetentionPolicy.RUNTIME)
@GroovyASTTransformationClass("groovy.io.infinite.blackbox.BlackBoxTransformation")
@interface BlackBox {
    BlackBoxLevel blackBoxLevel() default BlackBoxLevel.METHOD
}