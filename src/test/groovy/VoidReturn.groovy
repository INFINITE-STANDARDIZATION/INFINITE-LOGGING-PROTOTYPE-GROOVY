import groovy.BlackBox
import groovy.BlackBoxLevel

class VoidReturn implements Runnable {

    @Override
    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void run() {
        return
    }

}