package org.apache.cxf.spring.boot.endpoint;

import java.io.Serializable;

/**
 * http://www.cnblogs.com/wanggd/archive/2013/04/19/3030480.html
 */
@SuppressWarnings("serial")
/**
 * <p>Auto-configuration for APIEndpoint.</p>
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class APIEndpoint implements Serializable {

	// javax.jws.WebService

	/**
	 * Web Service 。 WSDL <wsdl:portType> 。 JWS Java 。
	 */
	private String name;

	/**
	 * used for Web Service WSDL and XML XML 。 JAX-RPC specification 。
	 */
	private String targetNamespace;

	/**
	 * Web Service service。 WSDL <wsdl:service> 。 JWS Java ， Service。
	 */
	private String serviceName;

	private String portName;

	/**
	 * WSDL or URL。， JWS WSDL andbinding，jwsc Ant WSDL ，。
	 * ：wsdlc Ant WSDL JWS 。，userin JWS 。
	 */
	private String wsdlLocation;
	
	/**
	 * service 。，， CLASSPATH 。
	 */
	private String endpointInterface;

	/**
	 * Web Service service
	 */
	private String addr;

	// javax.jws.WebMethod

	private APIEndpointMethod[] methods;
	
	/** Returns the name.
	 * @return the result
	 */
	public String getName() {
		return name;
	}

	/** Sets the name.
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** Returns the target namespace.
	 * @return the result
	 */
	public String getTargetNamespace() {
		return targetNamespace;
	}

	/** Sets the target namespace.
	 * @param targetNamespace the targetNamespace
	 */
	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}

	/** Returns the service name.
	 * @return the result
	 */
	public String getServiceName() {
		return serviceName;
	}

	/** Sets the service name.
	 * @param serviceName the serviceName
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/** Returns the port name.
	 * @return the result
	 */
	public String getPortName() {
		return portName;
	}

	/** Sets the port name.
	 * @param portName the portName
	 */
	public void setPortName(String portName) {
		this.portName = portName;
	}

	/** Returns the wsdl location.
	 * @return the result
	 */
	public String getWsdlLocation() {
		return wsdlLocation;
	}

	/** Sets the wsdl location.
	 * @param wsdlLocation the wsdlLocation
	 */
	public void setWsdlLocation(String wsdlLocation) {
		this.wsdlLocation = wsdlLocation;
	}

	/** Returns the endpoint interface.
	 * @return the result
	 */
	public String getEndpointInterface() {
		return endpointInterface;
	}

	/** Sets the endpoint interface.
	 * @param endpointInterface the endpointInterface
	 */
	public void setEndpointInterface(String endpointInterface) {
		this.endpointInterface = endpointInterface;
	}

	/** Returns the addr.
	 * @return the result
	 */
	public String getAddr() {
		return addr;
	}

	/** Sets the addr.
	 * @param addr the addr
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/** Returns the methods.
	 * @return the result
	 */
	public APIEndpointMethod[] getMethods() {
		return methods;
	}

	/** Sets the methods.
	 * @param methods the methods
	 */
	public void setMethods(APIEndpointMethod... methods) {
		this.methods = methods;
	}
	
}
