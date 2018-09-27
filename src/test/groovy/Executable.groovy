package groovy

class Executable implements Runnable{


    @Override
    void run() {
        System.out.println("blackBoxConfFileName: " + System.getProperty("blackBoxConfFileName"))
        someMethod2()
    }

    //@BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void someMethod2() {
        for (i in [0,1]) {
            System.out.println("zzzzzzzzzz " + i)
        }
        //1/0
        //BlackBoxEngine.getInstance().executionClose()
    }

}
