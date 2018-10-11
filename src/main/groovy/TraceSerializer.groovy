package groovy

import com.thoughtworks.xstream.XStream
import groovy.inspect.swingui.AstNodeToScriptVisitor
import infinite_logging.prototype.groovy.XMLASTTrace
import infinite_logging.prototype.groovy.XMLListTrace
import infinite_logging.prototype.groovy.XMLMapEntry
import infinite_logging.prototype.groovy.XMLMapTrace
import infinite_logging.prototype.groovy.XMLTrace
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode

class TraceSerializer {

    static XMLTrace createXMLTraceTrace(Object iTrace, String iTraceName = null) {
        XMLTrace xMLTrace
        if (iTrace instanceof ClassNode) {
            xMLTrace = new XMLASTTrace()
            StringWriter stringWriter = new StringWriter()
            new AstNodeToScriptVisitor(stringWriter).visitClass(iTrace)
            xMLTrace.setSerializedRepresentation(stringWriter.getBuffer().toString().replace("\$", ""))
            xMLTrace.setColumnNumber(iTrace.getColumnNumber() as BigInteger)
            xMLTrace.setLastColumnNumber(iTrace.getLastColumnNumber() as BigInteger)
            xMLTrace.setLineNumber(iTrace.getLineNumber() as BigInteger)
            xMLTrace.setLastLineNumber(iTrace.getLastLineNumber() as BigInteger)
        } else if (iTrace instanceof ASTNode) {
            xMLTrace = new XMLASTTrace()
            StringWriter stringWriter = new StringWriter()
            iTrace.visit(new AstNodeToScriptVisitor(stringWriter))
            xMLTrace.setSerializedRepresentation(stringWriter.getBuffer().toString().replace("\$", ""))
            xMLTrace.setColumnNumber(iTrace.getColumnNumber() as BigInteger)
            xMLTrace.setLastColumnNumber(iTrace.getLastColumnNumber() as BigInteger)
            xMLTrace.setLineNumber(iTrace.getLineNumber() as BigInteger)
            xMLTrace.setLastLineNumber(iTrace.getLastLineNumber() as BigInteger)
        } else if (iTrace instanceof Map) {
            xMLTrace = new XMLMapTrace()
            xMLTrace.setSize(iTrace.size() as BigInteger)
            for (key in iTrace.keySet()) {
                XMLTrace xMLTraceKey = createXMLTraceTrace(key, "key")
                XMLTrace xMLTraceValue = createXMLTraceTrace(iTrace.get(key), "value")
                XMLMapEntry xmlMapEntry = new XMLMapEntry()
                xmlMapEntry.setKey(xMLTraceKey)
                xmlMapEntry.setValue(xMLTraceValue)
                xMLTrace.getMapEntry().add(xmlMapEntry)
            }
        }  else if (iTrace instanceof List) {
            xMLTrace = new XMLListTrace()
            xMLTrace.setSize(iTrace.size() as BigInteger)
            iTrace.eachWithIndex { Object listEntry, Integer index ->
                XMLTrace xMLTraceValue = createXMLTraceTrace(listEntry, index.toString())
                ((XMLListTrace)xMLTrace).getCollectionElement().add(xMLTraceValue)
            }
        } else {
            xMLTrace = new XMLTrace()
            if (iTrace instanceof XStreamSuitable) {
                xMLTrace.setSerializedRepresentation(new XStream().toXML(iTrace))
            } else {
                xMLTrace.setSerializedRepresentation(iTrace.toString())
            }
        }
        if (iTraceName != null) {
            xMLTrace.setVariableName(iTraceName)
        }
        xMLTrace.setClassName(iTrace.getClass().getCanonicalName())
        return xMLTrace
    }

}
