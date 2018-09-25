package groovy

class Executable implements Runnable{


    @Override
    void run() {
        System.out.println("blackBoxConfFileName: " + System.getProperty("blackBoxConfFileName"))
        someMethod2()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void someMethod2() {
        System.out.println("zzzzzzzzzz")
        //BlackBoxEngine.getInstance().executionClose()
    }

}
