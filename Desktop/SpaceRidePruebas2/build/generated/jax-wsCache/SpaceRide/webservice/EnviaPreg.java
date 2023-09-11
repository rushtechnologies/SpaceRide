
package webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EnviaPreg complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EnviaPreg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="diff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnviaPreg", propOrder = {
    "diff",
    "area"
})
public class EnviaPreg {

    protected String diff;
    protected String area;

    /**
     * Obtiene el valor de la propiedad diff.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiff() {
        return diff;
    }

    /**
     * Define el valor de la propiedad diff.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiff(String value) {
        this.diff = value;
    }

    /**
     * Obtiene el valor de la propiedad area.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Define el valor de la propiedad area.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

}
