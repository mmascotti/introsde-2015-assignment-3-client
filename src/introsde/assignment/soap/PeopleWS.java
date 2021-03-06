
package introsde.assignment.soap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PeopleWS", targetNamespace = "http://soap.assignment.introsde/", wsdlLocation = "https://introsde-assignment3-server.herokuapp.com/ws?wsdl")
public class PeopleWS
    extends Service
{

    private final static URL PEOPLEWS_WSDL_LOCATION;
    private final static WebServiceException PEOPLEWS_EXCEPTION;
    private final static QName PEOPLEWS_QNAME = new QName("http://soap.assignment.introsde/", "PeopleWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://introsde-assignment3-server.herokuapp.com/ws?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PEOPLEWS_WSDL_LOCATION = url;
        PEOPLEWS_EXCEPTION = e;
    }

    public PeopleWS() {
        super(__getWsdlLocation(), PEOPLEWS_QNAME);
    }

    public PeopleWS(WebServiceFeature... features) {
        super(__getWsdlLocation(), PEOPLEWS_QNAME, features);
    }

    public PeopleWS(URL wsdlLocation) {
        super(wsdlLocation, PEOPLEWS_QNAME);
    }

    public PeopleWS(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PEOPLEWS_QNAME, features);
    }

    public PeopleWS(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PeopleWS(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns People
     */
    @WebEndpoint(name = "PeopleWSImplementationPort")
    public People getPeopleWSImplementationPort() {
        return super.getPort(new QName("http://soap.assignment.introsde/", "PeopleWSImplementationPort"), People.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns People
     */
    @WebEndpoint(name = "PeopleWSImplementationPort")
    public People getPeopleWSImplementationPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.assignment.introsde/", "PeopleWSImplementationPort"), People.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PEOPLEWS_EXCEPTION!= null) {
            throw PEOPLEWS_EXCEPTION;
        }
        return PEOPLEWS_WSDL_LOCATION;
    }

}
