//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.17 at 06:10:28 PM GST 
//


package infinite_logging.prototype.groovy;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ASTNode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ASTNode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="restoredScriptCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="astNodeList" type="{https://i-t.io/logging/groovy/2_x_x/Main}ASTNodeList" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="startDateTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="sourceNodeName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lineNumber" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="columnNumber" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="lastLineNumber" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="lastColumnNumber" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ASTNode", propOrder = {
    "restoredScriptCode",
    "astNodeList"
})
@XmlSeeAlso({
    XMLMethodNode.class,
    XMLExpression.class,
    XMLStatement.class
})
public class XMLASTNode {

    @XmlElement(required = true)
    protected String restoredScriptCode;
    protected XMLASTNodeList astNodeList;
    @XmlAttribute(name = "startDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDateTime;
    @XmlAttribute(name = "sourceNodeName")
    protected String sourceNodeName;
    @XmlAttribute(name = "lineNumber")
    protected BigInteger lineNumber;
    @XmlAttribute(name = "columnNumber")
    protected BigInteger columnNumber;
    @XmlAttribute(name = "lastLineNumber")
    protected BigInteger lastLineNumber;
    @XmlAttribute(name = "lastColumnNumber")
    protected BigInteger lastColumnNumber;

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

    /**
     * Gets the value of the astNodeList property.
     * 
     * @return
     *     possible object is
     *     {@link XMLASTNodeList }
     *     
     */
    public XMLASTNodeList getAstNodeList() {
        return astNodeList;
    }

    /**
     * Sets the value of the astNodeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLASTNodeList }
     *     
     */
    public void setAstNodeList(XMLASTNodeList value) {
        this.astNodeList = value;
    }

    /**
     * Gets the value of the startDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the value of the startDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDateTime(XMLGregorianCalendar value) {
        this.startDateTime = value;
    }

    /**
     * Gets the value of the sourceNodeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceNodeName() {
        return sourceNodeName;
    }

    /**
     * Sets the value of the sourceNodeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceNodeName(String value) {
        this.sourceNodeName = value;
    }

    /**
     * Gets the value of the lineNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLineNumber() {
        return lineNumber;
    }

    /**
     * Sets the value of the lineNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLineNumber(BigInteger value) {
        this.lineNumber = value;
    }

    /**
     * Gets the value of the columnNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getColumnNumber() {
        return columnNumber;
    }

    /**
     * Sets the value of the columnNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setColumnNumber(BigInteger value) {
        this.columnNumber = value;
    }

    /**
     * Gets the value of the lastLineNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLastLineNumber() {
        return lastLineNumber;
    }

    /**
     * Sets the value of the lastLineNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLastLineNumber(BigInteger value) {
        this.lastLineNumber = value;
    }

    /**
     * Gets the value of the lastColumnNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLastColumnNumber() {
        return lastColumnNumber;
    }

    /**
     * Sets the value of the lastColumnNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLastColumnNumber(BigInteger value) {
        this.lastColumnNumber = value;
    }

}
