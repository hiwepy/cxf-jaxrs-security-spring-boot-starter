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
/** The Cxf Jaxws Saml Dispatcher Property.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */

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
	/** Returns the consume media types.
	 * @return the result
	 */
	public List<String> getConsumeMediaTypes() {
		return consumeMediaTypes;
	}
	/** Sets the consume media types.
	 * @param consumeMediaTypes the consumeMediaTypes
	 */
	public void setConsumeMediaTypes(List<String> consumeMediaTypes) {
		this.consumeMediaTypes = consumeMediaTypes;
	}
	/** Returns the produce media types.
	 * @return the result
	 */
	public List<String> getProduceMediaTypes() {
		return produceMediaTypes;
	}
	/** Sets the produce media types.
	 * @param produceMediaTypes the produceMediaTypes
	 */
	public void setProduceMediaTypes(List<String> produceMediaTypes) {
		this.produceMediaTypes = produceMediaTypes;
	}
	/** Returns whether the enable buffering is enabled.
	 * @return the result
	 */
	public boolean isEnableBuffering() {
		return enableBuffering;
	}
	/** Sets the enable buffering.
	 * @param enableBuffering the enableBuffering
	 */
	public void setEnableBuffering(boolean enableBuffering) {
		this.enableBuffering = enableBuffering;
	}
	/** Returns whether the enable streaming is enabled.
	 * @return the result
	 */
	public boolean isEnableStreaming() {
		return enableStreaming;
	}
	/** Sets the enable streaming.
	 * @param enableStreaming the enableStreaming
	 */
	public void setEnableStreaming(boolean enableStreaming) {
		this.enableStreaming = enableStreaming;
	}
	/** Returns whether the use class names is enabled.
	 * @return the result
	 */
	public boolean isUseClassNames() {
		return useClassNames;
	}
	/** Sets the use class names.
	 * @param useClassNames the useClassNames
	 */
	public void setUseClassNames(boolean useClassNames) {
		this.useClassNames = useClassNames;
	}
	/** Returns whether the strict path check is enabled.
	 * @return the result
	 */
	public boolean isStrictPathCheck() {
		return strictPathCheck;
	}
	/** Sets the strict path check.
	 * @param strictPathCheck the strictPathCheck
	 */
	public void setStrictPathCheck(boolean strictPathCheck) {
		this.strictPathCheck = strictPathCheck;
	}
	/** Returns the bean names.
	 * @return the result
	 */
	public Map<String, String> getBeanNames() {
		return beanNames;
	}
	/** Sets the bean names.
	 * @param beanNames the beanNames
	 */
	public void setBeanNames(Map<String, String> beanNames) {
		this.beanNames = beanNames;
	}
	/** Returns the bean name.
	 * @return the result
	 */
	public String getBeanName() {
		return beanName;
	}
	/** Sets the bean name.
	 * @param beanName the beanName
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	/** Returns the resource path.
	 * @return the result
	 */
	public String getResourcePath() {
		return resourcePath;
	}
	/** Sets the resource path.
	 * @param resourcePath the resourcePath
	 */
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	/** Returns the resource paths.
	 * @return the result
	 */
	public Map<String, String> getResourcePaths() {
		return resourcePaths;
	}
	/** Sets the resource paths.
	 * @param resourcePaths the resourcePaths
	 */
	public void setResourcePaths(Map<String, String> resourcePaths) {
		this.resourcePaths = resourcePaths;
	}
	/** Returns the class resources.
	 * @return the result
	 */
	public Map<String, String> getClassResources() {
		return classResources;
	}
	/** Sets the class resources.
	 * @param classResources the classResources
	 */
	public void setClassResources(Map<String, String> classResources) {
		this.classResources = classResources;
	}
	/** Returns the enum resources.
	 * @return the result
	 */
	public Map<? extends Enum<?>, String> getEnumResources() {
		return enumResources;
	}
	/** Sets the enum resources.
	 * @param enumResources the enumResources
	 */
	public void setEnumResources(Map<? extends Enum<?>, String> enumResources) {
		this.enumResources = enumResources;
	}
	/** Returns the location prefix.
	 * @return the result
	 */
	public String getLocationPrefix() {
		return locationPrefix;
	}
	/** Sets the location prefix.
	 * @param locationPrefix the locationPrefix
	 */
	public void setLocationPrefix(String locationPrefix) {
		this.locationPrefix = locationPrefix;
	}
	/** Returns the resource extension.
	 * @return the result
	 */
	public String getResourceExtension() {
		return resourceExtension;
	}
	/** Sets the resource extension.
	 * @param resourceExtension the resourceExtension
	 */
	public void setResourceExtension(String resourceExtension) {
		this.resourceExtension = resourceExtension;
	}
	/** Returns the error view.
	 * @return the result
	 */
	public String getErrorView() {
		return errorView;
	}
	/** Sets the error view.
	 * @param errorView the errorView
	 */
	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}
	/** Returns whether the log redirects is enabled.
	 * @return the result
	 */
	public boolean isLogRedirects() {
		return logRedirects;
	}
	/** Sets the log redirects.
	 * @param logRedirects the logRedirects
	 */
	public void setLogRedirects(boolean logRedirects) {
		this.logRedirects = logRedirects;
	}
	/** Returns the servlet context path.
	 * @return the result
	 */
	public String getServletContextPath() {
		return servletContextPath;
	}
	/** Sets the servlet context path.
	 * @param servletContextPath the servletContextPath
	 */
	public void setServletContextPath(String servletContextPath) {
		this.servletContextPath = servletContextPath;
	}
	/** Returns the scope.
	 * @return the result
	 */
	public Scope getScope() {
		return scope;
	}
	/** Sets the scope.
	 * @param scope the scope
	 */
	public void setScope(Scope scope) {
		this.scope = scope;
	}
	/** Returns the dispatcher name.
	 * @return the result
	 */
	public String getDispatcherName() {
		return dispatcherName;
	}
	/** Sets the dispatcher name.
	 * @param dispatcherName the dispatcherName
	 */
	public void setDispatcherName(String dispatcherName) {
		this.dispatcherName = dispatcherName;
	}
	/** Returns the servlet path.
	 * @return the result
	 */
	public String getServletPath() {
		return servletPath;
	}
	/** Sets the servlet path.
	 * @param servletPath the servletPath
	 */
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	/** Returns whether the use current servlet is enabled.
	 * @return the result
	 */
	public boolean isUseCurrentServlet() {
		return useCurrentServlet;
	}
	/** Sets the use current servlet.
	 * @param useCurrentServlet the useCurrentServlet
	 */
	public void setUseCurrentServlet(boolean useCurrentServlet) {
		this.useCurrentServlet = useCurrentServlet;
	}
	/** Returns whether the save parameters as attributes is enabled.
	 * @return the result
	 */
	public boolean isSaveParametersAsAttributes() {
		return saveParametersAsAttributes;
	}
	/** Sets the save parameters as attributes.
	 * @param saveParametersAsAttributes the saveParametersAsAttributes
	 */
	public void setSaveParametersAsAttributes(boolean saveParametersAsAttributes) {
		this.saveParametersAsAttributes = saveParametersAsAttributes;
	}
	/** Returns whether the include resource is enabled.
	 * @return the result
	 */
	public boolean isIncludeResource() {
		return includeResource;
	}
	/** Sets the include resource.
	 * @param includeResource the includeResource
	 */
	public void setIncludeResource(boolean includeResource) {
		this.includeResource = includeResource;
	}
    
    
    
}
