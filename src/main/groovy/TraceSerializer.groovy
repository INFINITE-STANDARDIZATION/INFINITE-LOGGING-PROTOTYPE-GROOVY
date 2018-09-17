package groovy

import com.thoughtworks.xstream.XStream
import groovy.inspect.swingui.AstNodeToScriptVisitor
import infinite_logging.prototype.groovy.XMLASTObjectTrace
import infinite_logging.prototype.groovy.XMLObject
import org.codehaus.groovy.ast.ASTNode

class TraceSerializer {

    static XMLObject createXMLObjectTrace(String iTraceName, Object iTrace) {
        XMLObject xmlObject
        if (iTrace instanceof ASTNode) {
            xmlObject = new XMLASTObjectTrace()
            StringWriter stringWriter = new StringWriter()
            iTrace.visit(new AstNodeToScriptVisitor(stringWriter))
            xmlObject.setSerializedRepresentation(stringWriter.getBuffer().toString().replace("\$", ""))
            xmlObject.setColumnNumber(iTrace.getColumnNumber() as BigInteger)
            xmlObject.setLastColumnNumber(iTrace.getLastColumnNumber() as BigInteger)
            xmlObject.setLineNumber(iTrace.getLineNumber() as BigInteger)
            xmlObject.setLastLineNumber(iTrace.getLastLineNumber() as BigInteger)
        } else {
            xmlObject = new XMLObject()
            if (iTrace instanceof XStreamSuitable) {
                xmlObject.setSerializedRepresentation(new XStream().toXML(iTrace))
            } else {
                xmlObject.setSerializedRepresentation(iTrace.toString())
            }
        }
        xmlObject.setVariableName(iTraceName)
        xmlObject.setClassName(iTrace.getClass().getCanonicalName())
        return xmlObject
    }

}
