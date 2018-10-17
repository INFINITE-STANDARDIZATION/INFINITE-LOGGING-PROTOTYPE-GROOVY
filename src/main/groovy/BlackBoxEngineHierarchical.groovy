package groovy

import groovy.util.logging.Slf4j
import infinite_logging.prototype.groovy.ObjectFactory

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

@Slf4j
class BlackBoxEngineHierarchical extends BlackBoxEngine{

    @Override
    void executionClose() {
        if (astNode.parentAstNode == null) {
            JAXBContext lJAXBContext = JAXBContext.newInstance(astNode.getClass())
            Marshaller marshaller = lJAXBContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE)
            StringWriter stringWriter = new StringWriter()
            marshaller.marshal(new ObjectFactory().createRootAstNode(astNode), stringWriter)
            String xmlString = stringWriter.toString()
            log.debug(xmlString)
        }
        super.executionClose()
    }
}
