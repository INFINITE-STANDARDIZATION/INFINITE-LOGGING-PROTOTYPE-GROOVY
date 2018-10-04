//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.04 at 02:15:08 PM GST 
//


package infinite_logging.prototype.groovy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for LogTrailer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LogTrailer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="dateTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="logStatus" use="required" type="{https://i-t.io/logging/groovy/2_x_x/Main}LogStatus" />
 *       &lt;attribute name="currentLogName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nextLogName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LogTrailer")
public class XMLLogTrailer {

    @XmlAttribute(name = "dateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateTime;
    @XmlAttribute(name = "logStatus", required = true)
    protected XMLLogStatus logStatus;
    @XmlAttribute(name = "currentLogName", required = true)
    protected String currentLogName;
    @XmlAttribute(name = "nextLogName", required = true)
    protected String nextLogName;

    /**
     * Gets the value of the dateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateTime() {
        return dateTime;
    }

    /**
     * Sets the value of the dateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateTime(XMLGregorianCalendar value) {
        this.dateTime = value;
    }

    /**
     * Gets the value of the logStatus property.
     * 
     * @return
     *     possible object is
     *     {@link XMLLogStatus }
     *     
     */
    public XMLLogStatus getLogStatus() {
        return logStatus;
    }

    /**
     * Sets the value of the logStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLLogStatus }
     *     
     */
    public void setLogStatus(XMLLogStatus value) {
        this.logStatus = value;
    }

    /**
     * Gets the value of the currentLogName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentLogName() {
        return currentLogName;
    }

    /**
     * Sets the value of the currentLogName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentLogName(String value) {
        this.currentLogName = value;
    }

    /**
     * Gets the value of the nextLogName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextLogName() {
        return nextLogName;
    }

    /**
     * Sets the value of the nextLogName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextLogName(String value) {
        this.nextLogName = value;
    }

}
