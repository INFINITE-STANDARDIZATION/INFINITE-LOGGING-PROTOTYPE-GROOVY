//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.27 at 01:40:43 PM GST 
//


package infinite_logging.prototype.groovy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExecutionWithCode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExecutionWithCode">
 *   &lt;complexContent>
 *     &lt;extension base="{https://i-t.io/logging/groovy/2_x_x/Main}Execution">
 *       &lt;sequence>
 *         &lt;element name="restoredScriptCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExecutionWithCode", propOrder = {
    "restoredScriptCode"
})
@XmlSeeAlso({
    XMLStatementExecution.class,
    XMLExpressionEvaluation.class
})
public class XMLExecutionWithCode
    extends XMLExecution
{

    @XmlElement(required = true)
    protected String restoredScriptCode;

    /**
     * Gets the value of the restoredScriptCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestoredScriptCode() {
        return restoredScriptCode;
    }

    /**
     * Sets the value of the restoredScriptCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestoredScriptCode(String value) {
        this.restoredScriptCode = value;
    }

}
