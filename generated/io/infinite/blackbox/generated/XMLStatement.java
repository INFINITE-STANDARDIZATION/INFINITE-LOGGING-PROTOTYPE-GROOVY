//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.21 at 03:00:26 PM GST 
//


package io.infinite.blackbox.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Statement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Statement">
 *   &lt;complexContent>
 *     &lt;extension base="{https://i-t.io/blackbox/groovy/2_x_x/Main}ASTNode">
 *       &lt;attribute name="statementClassName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Statement")
public class XMLStatement
    extends XMLASTNode
{

    @XmlAttribute(name = "statementClassName", required = true)
    protected String statementClassName;

    /**
     * Gets the value of the statementClassName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatementClassName() {
        return statementClassName;
    }

    /**
     * Sets the value of the statementClassName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatementClassName(String value) {
        this.statementClassName = value;
    }

}
