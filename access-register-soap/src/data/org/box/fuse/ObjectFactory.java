
package org.box.fuse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.box.fuse package. 
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

    private final static QName _Addbook_QNAME = new QName("http://fuse.box.org/", "addbook");
    private final static QName _AddbookResponse_QNAME = new QName("http://fuse.box.org/", "addbookResponse");
    private final static QName _Unregister_QNAME = new QName("http://fuse.box.org/", "unregister");
    private final static QName _UnregisterResponse_QNAME = new QName("http://fuse.box.org/", "unregisterResponse");
    private final static QName _Register_QNAME = new QName("http://fuse.box.org/", "register");
    private final static QName _RegisterResponse_QNAME = new QName("http://fuse.box.org/", "registerResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.box.fuse
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Addbook }
     * 
     */
    public Addbook createAddbook() {
        return new Addbook();
    }

    /**
     * Create an instance of {@link AddbookResponse }
     * 
     */
    public AddbookResponse createAddbookResponse() {
        return new AddbookResponse();
    }

    /**
     * Create an instance of {@link Unregister }
     * 
     */
    public Unregister createUnregister() {
        return new Unregister();
    }

    /**
     * Create an instance of {@link UnregisterResponse }
     * 
     */
    public UnregisterResponse createUnregisterResponse() {
        return new UnregisterResponse();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link Member }
     * 
     */
    public Member createMember() {
        return new Member();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Addbook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fuse.box.org/", name = "addbook")
    public JAXBElement<Addbook> createAddbook(Addbook value) {
        return new JAXBElement<Addbook>(_Addbook_QNAME, Addbook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddbookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fuse.box.org/", name = "addbookResponse")
    public JAXBElement<AddbookResponse> createAddbookResponse(AddbookResponse value) {
        return new JAXBElement<AddbookResponse>(_AddbookResponse_QNAME, AddbookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Unregister }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fuse.box.org/", name = "unregister")
    public JAXBElement<Unregister> createUnregister(Unregister value) {
        return new JAXBElement<Unregister>(_Unregister_QNAME, Unregister.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnregisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fuse.box.org/", name = "unregisterResponse")
    public JAXBElement<UnregisterResponse> createUnregisterResponse(UnregisterResponse value) {
        return new JAXBElement<UnregisterResponse>(_UnregisterResponse_QNAME, UnregisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fuse.box.org/", name = "register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://fuse.box.org/", name = "registerResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

}
