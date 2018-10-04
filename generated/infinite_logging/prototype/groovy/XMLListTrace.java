//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.03 at 03:00:06 PM GST 
//


package infinite_logging.prototype.groovy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListTrace complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListTrace">
 *   &lt;complexContent>
 *     &lt;extension base="{https://i-t.io/logging/groovy/2_x_x/Main}Trace">
 *       &lt;sequence>
 *         &lt;element name="collectionElement" type="{https://i-t.io/logging/groovy/2_x_x/Main}Trace" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="size" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListTrace", propOrder = {
    "collectionElement"
})
public class XMLListTrace
    extends XMLTrace
{

    protected List<XMLTrace> collectionElement;
    @XmlAttribute(name = "size", required = true)
    protected BigInteger size;

    /**
     * Gets the value of the collectionElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the collectionElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCollectionElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLTrace }
     * 
     * 
     */
    public List<XMLTrace> getCollectionElement() {
        if (collectionElement == null) {
            collectionElement = new ArrayList<XMLTrace>();
        }
        return this.collectionElement;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSize(BigInteger value) {
        this.size = value;
    }

}
