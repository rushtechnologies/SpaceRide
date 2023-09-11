
package com.rushtechnologies.spaceride.webservice;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Login", targetNamespace = "http://WebService.spaceride.rushtechnologies.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Login {


    /**
     * 
     * @param pass
     * @param user
     * @return
     *     returns java.util.List<java.lang.Integer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://WebService.spaceride.rushtechnologies.com/", className = "com.rushtechnologies.spaceride.webservice.Login_Type")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://WebService.spaceride.rushtechnologies.com/", className = "com.rushtechnologies.spaceride.webservice.LoginResponse")
    @Action(input = "http://WebService.spaceride.rushtechnologies.com/Login/loginRequest", output = "http://WebService.spaceride.rushtechnologies.com/Login/loginResponse")
    public List<Integer> login(
        @WebParam(name = "user", targetNamespace = "")
        String user,
        @WebParam(name = "pass", targetNamespace = "")
        String pass);

}
