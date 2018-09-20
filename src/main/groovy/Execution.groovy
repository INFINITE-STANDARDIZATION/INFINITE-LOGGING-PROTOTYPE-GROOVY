package groovy


import infinite_logging.prototype.groovy.XMLExecution
import infinite_logging.prototype.groovy.XMLMethodExecution

class Execution {

    XMLExecution xmlExecution
    XMLMethodExecution xmlMethodExecution
    Execution parentExecution
    Date startDate = new Date()
    Date endDate
    Throwable throwable

    Execution(XMLExecution xmlExecution, Execution parentExecution) {
        this.xmlExecution = xmlExecution
        this.parentExecution = parentExecution
    }

}
