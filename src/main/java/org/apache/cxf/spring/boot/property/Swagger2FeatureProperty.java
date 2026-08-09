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

import java.util.Map;

/*
 * http://cxf.apache.org/docs/swagger2feature.html
 */
public class Swagger2FeatureProperty {

	private static final String DEFAULT_LICENSE_VALUE = "Apache 2.0 License";
	private static final String DEFAULT_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.html";

	private static final String DEFAULT_PROPS_LOCATION = "/swagger.properties";
	private static final String RESOURCE_PACKAGE_PROPERTY = "resource.package";
	private static final String TITLE_PROPERTY = "title";
	private static final String SCHEMES_PROPERTY = "schemes";
	private static final String VERSION_PROPERTY = "version";
	private static final String DESCRIPTION_PROPERTY = "description";
	private static final String CONTACT_PROPERTY = "contact";
	private static final String LICENSE_PROPERTY = "license";
	private static final String LICENSE_URL_PROPERTY = "license.url";
	private static final String TERMS_URL_PROPERTY = "terms.url";
	private static final String PRETTY_PRINT_PROPERTY = "pretty.print";
	private static final String FILTER_CLASS_PROPERTY = "filter.class";
	private static final String HOST_PROPERTY = "host";
	private static final String USE_PATH_CFG_PROPERTY = "use.path.based.config";

	private boolean licenseWasSet;
	private boolean runAsFilter;
	private boolean activateOnlyIfJaxrsSupported;
	private String resourcePackage;
	private String version;
	/**
	 * the context root path+
	 */
	private String basePath;
	private String title;
	private String description;
	private String contact;
	private String license;
	private String licenseUrl;
	private String termsOfServiceUrl;
	private String filterClass;

	private boolean scan;
	private boolean scanAllResources;

	private String ignoreRoutes;

	private boolean supportSwaggerUi;

	private String swaggerUiVersion;
	private String swaggerUiMavenGroupAndArtifact;

	private Map<String, String> swaggerUiMediaTypes;

	private boolean dynamicBasePath;

	private String host;
	private String[] schemes;
	private boolean prettyPrint;
	private boolean usePathBasedConfig;

	private String propertiesLocation = DEFAULT_PROPS_LOCATION;

	/** Returns whether the license was set is enabled.
	 * @return the result
	 */
	public boolean isLicenseWasSet() {
		return licenseWasSet;
	}

	/** Returns whether the run as filter is enabled.
	 * @return the result
	 */
	public boolean isRunAsFilter() {
		return runAsFilter;
	}

	/** Returns whether the activate only if jaxrs supported is enabled.
	 * @return the result
	 */
	public boolean isActivateOnlyIfJaxrsSupported() {
		return activateOnlyIfJaxrsSupported;
	}

	/** Returns the resource package.
	 * @return the result
	 */
	public String getResourcePackage() {
		return resourcePackage;
	}

	/** Returns the version.
	 * @return the result
	 */
	public String getVersion() {
		return version;
	}

	/** Returns the base path.
	 * @return the result
	 */
	public String getBasePath() {
		return basePath;
	}

	/** Returns the title.
	 * @return the result
	 */
	public String getTitle() {
		return title;
	}

	/** Returns the description.
	 * @return the result
	 */
	public String getDescription() {
		return description;
	}

	/** Returns the contact.
	 * @return the result
	 */
	public String getContact() {
		return contact;
	}

	/** Returns the license.
	 * @return the result
	 */
	public String getLicense() {
		return license;
	}

	/** Returns the license url.
	 * @return the result
	 */
	public String getLicenseUrl() {
		return licenseUrl;
	}

	/** Returns the terms of service url.
	 * @return the result
	 */
	public String getTermsOfServiceUrl() {
		return termsOfServiceUrl;
	}

	/** Returns the filter class.
	 * @return the result
	 */
	public String getFilterClass() {
		return filterClass;
	}

	/** Returns whether the scan is enabled.
	 * @return the result
	 */
	public boolean isScan() {
		return scan;
	}

	/** Returns whether the scan all resources is enabled.
	 * @return the result
	 */
	public boolean isScanAllResources() {
		return scanAllResources;
	}

	/** Returns the ignore routes.
	 * @return the result
	 */
	public String getIgnoreRoutes() {
		return ignoreRoutes;
	}

	/** Returns whether the support swagger ui is enabled.
	 * @return the result
	 */
	public boolean isSupportSwaggerUi() {
		return supportSwaggerUi;
	}

	/** Returns the swagger ui version.
	 * @return the result
	 */
	public String getSwaggerUiVersion() {
		return swaggerUiVersion;
	}

	/** Returns the swagger ui maven group and artifact.
	 * @return the result
	 */
	public String getSwaggerUiMavenGroupAndArtifact() {
		return swaggerUiMavenGroupAndArtifact;
	}

	/** Returns the swagger ui media types.
	 * @return the result
	 */
	public Map<String, String> getSwaggerUiMediaTypes() {
		return swaggerUiMediaTypes;
	}

	/** Returns whether the dynamic base path is enabled.
	 * @return the result
	 */
	public boolean isDynamicBasePath() {
		return dynamicBasePath;
	}

	/** Returns the host.
	 * @return the result
	 */
	public String getHost() {
		return host;
	}

	/** Returns the schemes.
	 * @return the result
	 */
	public String[] getSchemes() {
		return schemes;
	}

	/** Returns whether the pretty print is enabled.
	 * @return the result
	 */
	public boolean isPrettyPrint() {
		return prettyPrint;
	}

	/** Returns whether the use path based config is enabled.
	 * @return the result
	 */
	public boolean isUsePathBasedConfig() {
		return usePathBasedConfig;
	}

	/** Returns the properties location.
	 * @return the result
	 */
	public String getPropertiesLocation() {
		return propertiesLocation;
	}

}
