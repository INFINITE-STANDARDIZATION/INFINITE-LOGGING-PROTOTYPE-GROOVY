//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.03 at 03:00:06 PM GST 
//


package infinite_logging.prototype.groovy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MapEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MapEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{https://i-t.io/logging/groovy/2_x_x/Main}Trace" minOccurs="0"/>
 *         &lt;element name="value" type="{https://i-t.io/logging/groovy/2_x_x/Main}Trace" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MapEntry", propOrder = {
    "key",
    "value"
})
public class XMLMapEntry {

    protected XMLTrace key;
    protected XMLTrace value;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link XMLTrace }
     *     
     */
    public XMLTrace getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLTrace }
     *     
     */
    public void setKey(XMLTrace value) {
        this.key = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link XMLTrace }
     *     
     */
    public XMLTrace getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLTrace }
     *     
     */
    public void setValue(XMLTrace value) {
        this.value = value;
    }

}
