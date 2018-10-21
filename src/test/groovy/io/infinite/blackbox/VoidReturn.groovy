package groovy.io.infinite.blackbox

import groovy.io.infinite.blackbox.BlackBox
import groovy.io.infinite.blackbox.BlackBoxLevel

class VoidReturn implements Runnable {

    @Override
    @BlackBox(blackBoxLevel = BlackBoxLevel.EXPRESSION)
    void run() {
        return
    }

}