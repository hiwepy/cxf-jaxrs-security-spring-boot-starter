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
package org.apache.cxf.spring.boot;

import java.util.Map;

public class CxfJaxrsServerProperties {

	private String address;
	private String publishedEndpointUrl;
	private Map<Object, Object> languageMappings;
	private Map<Object, Object> extensionMappings;
	private Map<String, Object> properties;
	 
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPublishedEndpointUrl() {
		return publishedEndpointUrl;
	}

	public void setPublishedEndpointUrl(String publishedEndpointUrl) {
		this.publishedEndpointUrl = publishedEndpointUrl;
	}

	/**
	 * Sets the language mappings, example, 'en' is the key and 'en-gb' is the
	 * value.
	 *
	 * @param lMaps the language mappings
	 */
	public void setLanguageMappings(Map<Object, Object> lMaps) {
		languageMappings = lMaps;
	}

	public Map<Object, Object> getLanguageMappings() {
		return languageMappings;
	}
	
	/**
	 * Sets the extension mappings, example, 'xml' is the key and 'text/xml' is the
	 * value.
	 *
	 * @param extMaps  the extension mappings
	 */
	public void setExtensionMappings(Map<Object, Object> extMaps) {
		extensionMappings = extMaps;
	}

	public Map<Object, Object> getExtensionMappings() {
		return extensionMappings;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
}
