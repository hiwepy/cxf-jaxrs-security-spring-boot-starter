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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.cxf.spring.boot.CxfJaxrsSamlProperties.Scope;

public class CxfJaxwsSamlDispatcherProperty {

	private List<String> consumeMediaTypes;
    private List<String> produceMediaTypes;
    private boolean enableBuffering;
    private boolean enableStreaming;
    private boolean useClassNames = true;
    private boolean strictPathCheck;
    private Map<String, String> beanNames = Collections.emptyMap();
    private String beanName;
    private String resourcePath;
    private Map<String, String> resourcePaths = Collections.emptyMap();
    private Map<String, String> classResources = Collections.emptyMap();
    private Map<? extends Enum<?>, String> enumResources = Collections.emptyMap();
    private String locationPrefix;
    private String resourceExtension;
    private String errorView = "/error";
    private boolean logRedirects;
    private String servletContextPath;
    private Scope scope = Scope.REQUEST_SCOPE;
    private String dispatcherName = "jsp";
    private String servletPath;
    private boolean useCurrentServlet;
    private boolean saveParametersAsAttributes;
    private boolean includeResource;
	public List<String> getConsumeMediaTypes() {
		return consumeMediaTypes;
	}
	public void setConsumeMediaTypes(List<String> consumeMediaTypes) {
		this.consumeMediaTypes = consumeMediaTypes;
	}
	public List<String> getProduceMediaTypes() {
		return produceMediaTypes;
	}
	public void setProduceMediaTypes(List<String> produceMediaTypes) {
		this.produceMediaTypes = produceMediaTypes;
	}
	public boolean isEnableBuffering() {
		return enableBuffering;
	}
	public void setEnableBuffering(boolean enableBuffering) {
		this.enableBuffering = enableBuffering;
	}
	public boolean isEnableStreaming() {
		return enableStreaming;
	}
	public void setEnableStreaming(boolean enableStreaming) {
		this.enableStreaming = enableStreaming;
	}
	public boolean isUseClassNames() {
		return useClassNames;
	}
	public void setUseClassNames(boolean useClassNames) {
		this.useClassNames = useClassNames;
	}
	public boolean isStrictPathCheck() {
		return strictPathCheck;
	}
	public void setStrictPathCheck(boolean strictPathCheck) {
		this.strictPathCheck = strictPathCheck;
	}
	public Map<String, String> getBeanNames() {
		return beanNames;
	}
	public void setBeanNames(Map<String, String> beanNames) {
		this.beanNames = beanNames;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public Map<String, String> getResourcePaths() {
		return resourcePaths;
	}
	public void setResourcePaths(Map<String, String> resourcePaths) {
		this.resourcePaths = resourcePaths;
	}
	public Map<String, String> getClassResources() {
		return classResources;
	}
	public void setClassResources(Map<String, String> classResources) {
		this.classResources = classResources;
	}
	public Map<? extends Enum<?>, String> getEnumResources() {
		return enumResources;
	}
	public void setEnumResources(Map<? extends Enum<?>, String> enumResources) {
		this.enumResources = enumResources;
	}
	public String getLocationPrefix() {
		return locationPrefix;
	}
	public void setLocationPrefix(String locationPrefix) {
		this.locationPrefix = locationPrefix;
	}
	public String getResourceExtension() {
		return resourceExtension;
	}
	public void setResourceExtension(String resourceExtension) {
		this.resourceExtension = resourceExtension;
	}
	public String getErrorView() {
		return errorView;
	}
	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}
	public boolean isLogRedirects() {
		return logRedirects;
	}
	public void setLogRedirects(boolean logRedirects) {
		this.logRedirects = logRedirects;
	}
	public String getServletContextPath() {
		return servletContextPath;
	}
	public void setServletContextPath(String servletContextPath) {
		this.servletContextPath = servletContextPath;
	}
	public Scope getScope() {
		return scope;
	}
	public void setScope(Scope scope) {
		this.scope = scope;
	}
	public String getDispatcherName() {
		return dispatcherName;
	}
	public void setDispatcherName(String dispatcherName) {
		this.dispatcherName = dispatcherName;
	}
	public String getServletPath() {
		return servletPath;
	}
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	public boolean isUseCurrentServlet() {
		return useCurrentServlet;
	}
	public void setUseCurrentServlet(boolean useCurrentServlet) {
		this.useCurrentServlet = useCurrentServlet;
	}
	public boolean isSaveParametersAsAttributes() {
		return saveParametersAsAttributes;
	}
	public void setSaveParametersAsAttributes(boolean saveParametersAsAttributes) {
		this.saveParametersAsAttributes = saveParametersAsAttributes;
	}
	public boolean isIncludeResource() {
		return includeResource;
	}
	public void setIncludeResource(boolean includeResource) {
		this.includeResource = includeResource;
	}
    
    
    
}
