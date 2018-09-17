package groovy


import infinite_logging.prototype.groovy.XMLExecution

class Execution {

    XMLExecution xmlExecution
    Execution parentExecution
    Date startDate = new Date()
    Date endDate
    Throwable throwable

    Execution(XMLExecution xmlExecution, Execution parentExecution) {
        this.xmlExecution = xmlExecution
        this.parentExecution = parentExecution
    }
}
