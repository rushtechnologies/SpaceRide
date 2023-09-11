
package com.rushtechnologies.spaceride.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para read complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="read">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="u_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "read", propOrder = {
    "uId"
})
public class Read_Type {

    @XmlElement(name = "u_id")
    protected int uId;

    /**
     * Obtiene el valor de la propiedad uId.
     * 
     */
    public int getUId() {
        return uId;
    }

    /**
     * Define el valor de la propiedad uId.
     * 
     */
    public void setUId(int value) {
        this.uId = value;
    }

}
