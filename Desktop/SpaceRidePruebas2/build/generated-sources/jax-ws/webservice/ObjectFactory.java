
package webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice package. 
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

    private final static QName _EnviaPregResponse_QNAME = new QName("http://WebService/", "EnviaPregResponse");
    private final static QName _ChecarAnswer_QNAME = new QName("http://WebService/", "ChecarAnswer");
    private final static QName _ChecarAnswerResponse_QNAME = new QName("http://WebService/", "ChecarAnswerResponse");
    private final static QName _EnviaPreg_QNAME = new QName("http://WebService/", "EnviaPreg");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnviaPreg }
     * 
     */
    public EnviaPreg createEnviaPreg() {
        return new EnviaPreg();
    }

    /**
     * Create an instance of {@link ChecarAnswerResponse }
     * 
     */
    public ChecarAnswerResponse createChecarAnswerResponse() {
        return new ChecarAnswerResponse();
    }

    /**
     * Create an instance of {@link ChecarAnswer }
     * 
     */
    public ChecarAnswer createChecarAnswer() {
        return new ChecarAnswer();
    }

    /**
     * Create an instance of {@link EnviaPregResponse }
     * 
     */
    public EnviaPregResponse createEnviaPregResponse() {
        return new EnviaPregResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviaPregResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "EnviaPregResponse")
    public JAXBElement<EnviaPregResponse> createEnviaPregResponse(EnviaPregResponse value) {
        return new JAXBElement<EnviaPregResponse>(_EnviaPregResponse_QNAME, EnviaPregResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChecarAnswer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "ChecarAnswer")
    public JAXBElement<ChecarAnswer> createChecarAnswer(ChecarAnswer value) {
        return new JAXBElement<ChecarAnswer>(_ChecarAnswer_QNAME, ChecarAnswer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChecarAnswerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "ChecarAnswerResponse")
    public JAXBElement<ChecarAnswerResponse> createChecarAnswerResponse(ChecarAnswerResponse value) {
        return new JAXBElement<ChecarAnswerResponse>(_ChecarAnswerResponse_QNAME, ChecarAnswerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviaPreg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "EnviaPreg")
    public JAXBElement<EnviaPreg> createEnviaPreg(EnviaPreg value) {
        return new JAXBElement<EnviaPreg>(_EnviaPreg_QNAME, EnviaPreg.class, null, value);
    }

}
