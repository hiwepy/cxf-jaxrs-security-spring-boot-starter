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

	public boolean isLicenseWasSet() {
		return licenseWasSet;
	}

	public boolean isRunAsFilter() {
		return runAsFilter;
	}

	public boolean isActivateOnlyIfJaxrsSupported() {
		return activateOnlyIfJaxrsSupported;
	}

	public String getResourcePackage() {
		return resourcePackage;
	}

	public String getVersion() {
		return version;
	}

	public String getBasePath() {
		return basePath;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getContact() {
		return contact;
	}

	public String getLicense() {
		return license;
	}

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public String getTermsOfServiceUrl() {
		return termsOfServiceUrl;
	}

	public String getFilterClass() {
		return filterClass;
	}

	public boolean isScan() {
		return scan;
	}

	public boolean isScanAllResources() {
		return scanAllResources;
	}

	public String getIgnoreRoutes() {
		return ignoreRoutes;
	}

	public boolean isSupportSwaggerUi() {
		return supportSwaggerUi;
	}

	public String getSwaggerUiVersion() {
		return swaggerUiVersion;
	}

	public String getSwaggerUiMavenGroupAndArtifact() {
		return swaggerUiMavenGroupAndArtifact;
	}

	public Map<String, String> getSwaggerUiMediaTypes() {
		return swaggerUiMediaTypes;
	}

	public boolean isDynamicBasePath() {
		return dynamicBasePath;
	}

	public String getHost() {
		return host;
	}

	public String[] getSchemes() {
		return schemes;
	}

	public boolean isPrettyPrint() {
		return prettyPrint;
	}

	public boolean isUsePathBasedConfig() {
		return usePathBasedConfig;
	}

	public String getPropertiesLocation() {
		return propertiesLocation;
	}

}
