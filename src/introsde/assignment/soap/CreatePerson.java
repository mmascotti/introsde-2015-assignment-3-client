
package introsde.assignment.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r createPerson complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="createPerson">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="p" type="{http://soap.assignment.introsde/}person" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPerson", propOrder = {
    "p"
})
public class CreatePerson {

    protected Person p;

    /**
     * Ruft den Wert der p-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getP() {
        return p;
    }

    /**
     * Legt den Wert der p-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setP(Person value) {
        this.p = value;
    }

}
