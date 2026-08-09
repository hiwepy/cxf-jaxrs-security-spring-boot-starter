/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.cxf.spring.boot.property;

import org.apache.cxf.rs.security.saml.sso.SSOConstants;
/** The Cxf Jaxws Saml Binding Property.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */

public class CxfJaxwsSamlBindingProperty {

	private boolean addEndpointAddressToContext;
	private boolean addWebAppContext = true;
	/**
	 * RACS address - it can be absolute or relative if RACS is collocated (shares
	 * the same web application context) with the application endpoint. required
	 */
	private String assertionConsumerServiceAddress;
	
	private long stateTimeToLive = SSOConstants.DEFAULT_STATE_TIME;
	private String signaturePropertiesFile;
	private String callbackHandlerClass;
	private String signatureUsername;
	/**
	 * IDP service address. required
	 */
	private String idpServiceAddress = "https://localhost:9443/idp";
	/**
	 * it defaults to the base URI of the application endpoint protected by this
	 * filter, for example, "http://localhost:8080/services/app1".
	 */
	private String issuerId;
	private boolean supportUnsolicited;


	private boolean signRequest;
	

	private String webAppDomain;
	
	/**
	 * DefaultAuthnRequestBuilder
	 */
	private boolean forceAuthn;
	private boolean isPassive;
	private String protocolBinding = "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST";
	private String nameIDFormat = "urn:oasis:names:tc:SAML:2.0:nameid-format:persistent";

	/** Returns the state time to live.
	 * @return the result
	 */
	public long getStateTimeToLive() {
		return stateTimeToLive;
	}

	/** Sets the state time to live.
	 * @param stateTimeToLive the stateTimeToLive
	 */
	public void setStateTimeToLive(long stateTimeToLive) {
		this.stateTimeToLive = stateTimeToLive;
	}

	/** Returns the signature properties file.
	 * @return the result
	 */
	public String getSignaturePropertiesFile() {
		return signaturePropertiesFile;
	}

	/** Sets the signature properties file.
	 * @param signaturePropertiesFile the signaturePropertiesFile
	 */
	public void setSignaturePropertiesFile(String signaturePropertiesFile) {
		this.signaturePropertiesFile = signaturePropertiesFile;
	}

	/** Returns the callback handler class.
	 * @return the result
	 */
	public String getCallbackHandlerClass() {
		return callbackHandlerClass;
	}

	/** Sets the callback handler class.
	 * @param callbackHandlerClass the callbackHandlerClass
	 */
	public void setCallbackHandlerClass(String callbackHandlerClass) {
		this.callbackHandlerClass = callbackHandlerClass;
	}

	/** Returns the signature username.
	 * @return the result
	 */
	public String getSignatureUsername() {
		return signatureUsername;
	}

	/** Sets the signature username.
	 * @param signatureUsername the signatureUsername
	 */
	public void setSignatureUsername(String signatureUsername) {
		this.signatureUsername = signatureUsername;
	}

	/** Returns the idp service address.
	 * @return the result
	 */
	public String getIdpServiceAddress() {
		return idpServiceAddress;
	}

	/** Sets the idp service address.
	 * @param idpServiceAddress the idpServiceAddress
	 */
	public void setIdpServiceAddress(String idpServiceAddress) {
		this.idpServiceAddress = idpServiceAddress;
	}

	/** Returns the issuer id.
	 * @return the result
	 */
	public String getIssuerId() {
		return issuerId;
	}

	/** Sets the issuer id.
	 * @param issuerId the issuerId
	 */
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	/** Returns whether the support unsolicited is enabled.
	 * @return the result
	 */
	public boolean isSupportUnsolicited() {
		return supportUnsolicited;
	}

	/** Sets the support unsolicited.
	 * @param supportUnsolicited the supportUnsolicited
	 */
	public void setSupportUnsolicited(boolean supportUnsolicited) {
		this.supportUnsolicited = supportUnsolicited;
	}

	/** Returns the assertion consumer service address.
	 * @return the result
	 */
	public String getAssertionConsumerServiceAddress() {
		return assertionConsumerServiceAddress;
	}

	/** Sets the assertion consumer service address.
	 * @param assertionConsumerServiceAddress the assertionConsumerServiceAddress
	 */
	public void setAssertionConsumerServiceAddress(String assertionConsumerServiceAddress) {
		this.assertionConsumerServiceAddress = assertionConsumerServiceAddress;
	}

	/** Returns whether the sign request is enabled.
	 * @return the result
	 */
	public boolean isSignRequest() {
		return signRequest;
	}

	/** Sets the sign request.
	 * @param signRequest the signRequest
	 */
	public void setSignRequest(boolean signRequest) {
		this.signRequest = signRequest;
	}

	/** Returns the web app domain.
	 * @return the result
	 */
	public String getWebAppDomain() {
		return webAppDomain;
	}

	/** Sets the web app domain.
	 * @param webAppDomain the webAppDomain
	 */
	public void setWebAppDomain(String webAppDomain) {
		this.webAppDomain = webAppDomain;
	}

	/** Returns whether the add web app context is enabled.
	 * @return the result
	 */
	public boolean isAddWebAppContext() {
		return addWebAppContext;
	}

	/** Sets the add web app context.
	 * @param addWebAppContext the addWebAppContext
	 */
	public void setAddWebAppContext(boolean addWebAppContext) {
		this.addWebAppContext = addWebAppContext;
	}

	/** Returns whether the add endpoint address to context is enabled.
	 * @return the result
	 */
	public boolean isAddEndpointAddressToContext() {
		return addEndpointAddressToContext;
	}

	/** Sets the add endpoint address to context.
	 * @param addEndpointAddressToContext the addEndpointAddressToContext
	 */
	public void setAddEndpointAddressToContext(boolean addEndpointAddressToContext) {
		this.addEndpointAddressToContext = addEndpointAddressToContext;
	}

	/** Returns whether the force authn is enabled.
	 * @return the result
	 */
	public boolean isForceAuthn() {
		return forceAuthn;
	}

	/** Sets the force authn.
	 * @param forceAuthn the forceAuthn
	 */
	public void setForceAuthn(boolean forceAuthn) {
		this.forceAuthn = forceAuthn;
	}

	/** Returns whether the passive is enabled.
	 * @return the result
	 */
	public boolean isPassive() {
		return isPassive;
	}

	/** Sets the passive.
	 * @param isPassive the isPassive
	 */
	public void setPassive(boolean isPassive) {
		this.isPassive = isPassive;
	}

	/** Returns the protocol binding.
	 * @return the result
	 */
	public String getProtocolBinding() {
		return protocolBinding;
	}

	/** Sets the protocol binding.
	 * @param protocolBinding the protocolBinding
	 */
	public void setProtocolBinding(String protocolBinding) {
		this.protocolBinding = protocolBinding;
	}

	/** Returns the name i d format.
	 * @return the result
	 */
	public String getNameIDFormat() {
		return nameIDFormat;
	}

	/** Sets the name i d format.
	 * @param nameIDFormat the nameIDFormat
	 */
	public void setNameIDFormat(String nameIDFormat) {
		this.nameIDFormat = nameIDFormat;
	}

	
	
}
