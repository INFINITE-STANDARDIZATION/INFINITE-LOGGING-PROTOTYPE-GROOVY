//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.11 at 12:16:46 PM GST 
//


package infinite_logging.prototype.groovy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Expression complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Expression">
 *   &lt;complexContent>
 *     &lt;extension base="{https://i-t.io/logging/groovy/2_x_x/Main}ASTNode">
 *       &lt;sequence>
 *         &lt;element name="expressionResult" type="{https://i-t.io/logging/groovy/2_x_x/Main}Trace" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="expressionTypeClassName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="expressionName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Expression", propOrder = {
    "expressionResult"
})
public class XMLExpression
    extends XMLASTNode
{

    protected XMLTrace expressionResult;
    @XmlAttribute(name = "expressionTypeClassName", required = true)
    protected String expressionTypeClassName;
    @XmlAttribute(name = "expressionName", required = true)
    protected String expressionName;

    /**
     * Gets the value of the expressionResult property.
     * 
     * @return
     *     possible object is
     *     {@link XMLTrace }
     *     
     */
    public XMLTrace getExpressionResult() {
        return expressionResult;
    }

    /**
     * Sets the value of the expressionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLTrace }
     *     
     */
    public void setExpressionResult(XMLTrace value) {
        this.expressionResult = value;
    }

    /**
     * Gets the value of the expressionTypeClassName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpressionTypeClassName() {
        return expressionTypeClassName;
    }

    /**
     * Sets the value of the expressionTypeClassName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpressionTypeClassName(String value) {
        this.expressionTypeClassName = value;
    }

    /**
     * Gets the value of the expressionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpressionName() {
        return expressionName;
    }

    /**
     * Sets the value of the expressionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpressionName(String value) {
        this.expressionName = value;
    }

}
