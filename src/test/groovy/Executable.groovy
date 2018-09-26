package groovy

class Executable implements Runnable{


    @Override
    void run() {
        System.out.println("blackBoxConfFileName: " + System.getProperty("blackBoxConfFileName"))
        someMethod2()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.STATEMENT)
    void someMethod2() {
        System.out.println("zzzzzzzzzz")
        //1/0
        //BlackBoxEngine.getInstance().executionClose()
    }
    //todo: statement code, line numbers

}
