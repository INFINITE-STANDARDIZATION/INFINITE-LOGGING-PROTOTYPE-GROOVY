package groovy

import org.codehaus.groovy.ast.expr.*

class SandBox {

    static void main(String[] args) {
        new SandBox().someMethod()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void someMethod() {
        int z = 1
    }

}
