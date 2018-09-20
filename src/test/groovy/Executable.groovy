package groovy

class Executable implements Runnable{


    @Override
    void run() {
        System.out.println(System.getProperty("blackBoxConfFileName"))
        someMethod2()
    }

    @BlackBox(blackBoxLevel = BlackBoxLevel.METHOD)
    void someMethod() {
        System.out.println("zzzzzzzzzz")
    }

}
