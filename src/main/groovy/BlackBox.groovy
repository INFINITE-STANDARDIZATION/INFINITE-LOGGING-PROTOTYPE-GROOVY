package groovy

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Target([ElementType.METHOD, ElementType.CONSTRUCTOR])
@Retention(RetentionPolicy.RUNTIME)
@GroovyASTTransformationClass("groovy.BlackBoxTransformation")
@interface BlackBox {
    String value() default ""
}