package groovy.io.infinite.blackbox

class SandBox {

    static void main(String[] args) {
        //System.setProperty("blackBox.mode", BlackBoxMode.EMERGENCY.value())
        //System.setProperty("blackBox.mode", BlackBoxMode.SEQUENTIAL.value())
        System.setProperty("blackBox.mode", BlackBoxMode.HIERARCHICAL.value())
        new SandBox().visitArgumentlistExpressionExpressionLevel(1,2,3)
        //new SandBox().visitThrowStatementExpressionLevel()
    }


    //@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    String visitArgumentlistExpressionExpressionLevel(def a, def b, def c) {
        tst(a,b,c)
        //1/0
        return "zzzz"
    }

    void tst(def a, def b, def c) {
        System.out.println(a+b+c)
    }

    void z() {

    }

    //@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void visitThrowStatementExpressionLevel() {

    }
}
