
package com.rushtechnologies.spaceride.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.rushtechnologies.spaceride.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Read_QNAME = new QName("http://WebService.spaceride.rushtechnologies.com/", "read");
    private final static QName _ReadResponse_QNAME = new QName("http://WebService.spaceride.rushtechnologies.com/", "readResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.rushtechnologies.spaceride.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReadResponse }
     * 
     */
    public ReadResponse createReadResponse() {
        return new ReadResponse();
    }

    /**
     * Create an instance of {@link Read_Type }
     * 
     */
    public Read_Type createRead_Type() {
        return new Read_Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Read_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService.spaceride.rushtechnologies.com/", name = "read")
    public JAXBElement<Read_Type> createRead(Read_Type value) {
        return new JAXBElement<Read_Type>(_Read_QNAME, Read_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService.spaceride.rushtechnologies.com/", name = "readResponse")
    public JAXBElement<ReadResponse> createReadResponse(ReadResponse value) {
        return new JAXBElement<ReadResponse>(_ReadResponse_QNAME, ReadResponse.class, null, value);
    }

}
