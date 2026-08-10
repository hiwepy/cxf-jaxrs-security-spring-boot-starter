package org.apache.cxf.spring.boot.endpoint;
/** The A P I Endpoint Result.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */

public class APIEndpointResult {

	// javax.jws.WebResult

	/**
	 * WSDL 。
	 * RPC Web Service， <wsdl:part> 。 Web Service， XML 。
	 * result。
	 */
	private String name;

	private String partName;
	
	/**
	 * XML 。used for Web Service， XML 。
	 * Web Service targetNamespace。
	 */
	private String targetNamespace;

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
