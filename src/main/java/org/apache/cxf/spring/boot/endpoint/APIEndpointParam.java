package org.apache.cxf.spring.boot.endpoint;

import jakarta.jws.WebParam.Mode;
/** The A P I Endpoint Param.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */

public class APIEndpointParam {

	// javax.jws.WebParam

	/**
	 * WSDL 。
	 * RPC Web Service， <wsdl:part> 。
	 * Web Service， XML 。
	 * 。
	 */
	private String name;

	private String partName;

	/**
	 * XML 。used for Web Service， XML 。 Web Service targetNamespace。
	 */
	private String targetNamespace;

	/**
	 * 。
     *
	 * ：
	 *	
	 *	    § WebParam.Mode.IN
	 *	    WebParam.Mode.OUT
	 *	    WebParam.Mode.INOUT
	 *	
	 *	WebParam.Mode.IN。
	 *	
	 *	WebParam.Mode.OUT or WebParam.Mode.INOUT， Holder or Holder。information， JAX-RPC specification。
	 *	
	 *	WebParam.Mode.OUT and WebParam.Mode.INOUT RPC Web Service or 。
	 * 
	 */
	private Mode mode = jakarta.jws.WebParam.Mode.IN;
	
	/**
	 * in SOAP 。， SOAP 。 true and false。 false。
	 */
	private boolean header;

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

	/** Returns the part name.
	 * @return the result
	 */
	public String getPartName() {
		return partName;
	}

	/** Sets the part name.
	 * @param partName the partName
	 */
	public void setPartName(String partName) {
		this.partName = partName;
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

	/** Returns the mode.
	 * @return the result
	 */
	public Mode getMode() {
		return mode;
	}

	/** Sets the mode.
	 * @param mode the mode
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/** Returns whether the header is enabled.
	 * @return the result
	 */
	public boolean isHeader() {
		return header;
	}

	/** Sets the header.
	 * @param header the header
	 */
	public void setHeader(boolean header) {
		this.header = header;
	}

}
