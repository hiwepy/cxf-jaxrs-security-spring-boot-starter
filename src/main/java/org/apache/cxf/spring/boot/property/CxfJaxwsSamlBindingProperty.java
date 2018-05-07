/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
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

	public long getStateTimeToLive() {
		return stateTimeToLive;
	}

	public void setStateTimeToLive(long stateTimeToLive) {
		this.stateTimeToLive = stateTimeToLive;
	}

	public String getSignaturePropertiesFile() {
		return signaturePropertiesFile;
	}

	public void setSignaturePropertiesFile(String signaturePropertiesFile) {
		this.signaturePropertiesFile = signaturePropertiesFile;
	}

	public String getCallbackHandlerClass() {
		return callbackHandlerClass;
	}

	public void setCallbackHandlerClass(String callbackHandlerClass) {
		this.callbackHandlerClass = callbackHandlerClass;
	}

	public String getSignatureUsername() {
		return signatureUsername;
	}

	public void setSignatureUsername(String signatureUsername) {
		this.signatureUsername = signatureUsername;
	}

	public String getIdpServiceAddress() {
		return idpServiceAddress;
	}

	public void setIdpServiceAddress(String idpServiceAddress) {
		this.idpServiceAddress = idpServiceAddress;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public boolean isSupportUnsolicited() {
		return supportUnsolicited;
	}

	public void setSupportUnsolicited(boolean supportUnsolicited) {
		this.supportUnsolicited = supportUnsolicited;
	}

	public String getAssertionConsumerServiceAddress() {
		return assertionConsumerServiceAddress;
	}

	public void setAssertionConsumerServiceAddress(String assertionConsumerServiceAddress) {
		this.assertionConsumerServiceAddress = assertionConsumerServiceAddress;
	}

	public boolean isSignRequest() {
		return signRequest;
	}

	public void setSignRequest(boolean signRequest) {
		this.signRequest = signRequest;
	}

	public String getWebAppDomain() {
		return webAppDomain;
	}

	public void setWebAppDomain(String webAppDomain) {
		this.webAppDomain = webAppDomain;
	}

	public boolean isAddWebAppContext() {
		return addWebAppContext;
	}

	public void setAddWebAppContext(boolean addWebAppContext) {
		this.addWebAppContext = addWebAppContext;
	}

	public boolean isAddEndpointAddressToContext() {
		return addEndpointAddressToContext;
	}

	public void setAddEndpointAddressToContext(boolean addEndpointAddressToContext) {
		this.addEndpointAddressToContext = addEndpointAddressToContext;
	}

	public boolean isForceAuthn() {
		return forceAuthn;
	}

	public void setForceAuthn(boolean forceAuthn) {
		this.forceAuthn = forceAuthn;
	}

	public boolean isPassive() {
		return isPassive;
	}

	public void setPassive(boolean isPassive) {
		this.isPassive = isPassive;
	}

	public String getProtocolBinding() {
		return protocolBinding;
	}

	public void setProtocolBinding(String protocolBinding) {
		this.protocolBinding = protocolBinding;
	}

	public String getNameIDFormat() {
		return nameIDFormat;
	}

	public void setNameIDFormat(String nameIDFormat) {
		this.nameIDFormat = nameIDFormat;
	}

	
	
}
