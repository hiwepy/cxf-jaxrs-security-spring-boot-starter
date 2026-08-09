package org.apache.cxf.spring.boot.endpoint;
/** The A P I Endpoint Method.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */

public class APIEndpointMethod {

	// javax.jws.WebMethod
	
	/**
	 * 。 WSDL <wsdl:operation> 。 。
	 */
	private String operationName;
	/**
	 * 。 SOAP binding， SOAP SOAPAction 。
	 */
	private String action;
	private boolean exclude;

	// javax.jws.WebParam
	private APIEndpointParam[] params;
	
	// javax.jws.WebResult
	
	private APIEndpointResult result;

	/** Returns the operation name.
	 * @return the result
	 */
	public String getOperationName() {
		return operationName;
	}

	/** Sets the operation name.
	 * @param operationName the operationName
	 */
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	/** Returns the action.
	 * @return the result
	 */
	public String getAction() {
		return action;
	}

	/** Sets the action.
	 * @param action the action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/** Returns whether the exclude is enabled.
	 * @return the result
	 */
	public boolean isExclude() {
		return exclude;
	}

	/** Sets the exclude.
	 * @param exclude the exclude
	 */
	public void setExclude(boolean exclude) {
		this.exclude = exclude;
	}

	/** Returns the params.
	 * @return the result
	 */
	public APIEndpointParam[] getParams() {
		return params;
	}

	/** Sets the params.
	 * @param params the params
	 */
	public void setParams(APIEndpointParam... params) {
		this.params = params;
	}

	/** Returns the result.
	 * @return the result
	 */
	public APIEndpointResult getResult() {
		return result;
	}

	/** Sets the result.
	 * @param result the result
	 */
	public void setResult(APIEndpointResult result) {
		this.result = result;
	}
	
}
