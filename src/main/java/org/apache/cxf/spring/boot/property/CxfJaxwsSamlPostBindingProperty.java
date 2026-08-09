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
/** The Cxf Jaxws Saml Post Binding Property.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */

public class CxfJaxwsSamlPostBindingProperty extends CxfJaxwsSamlBindingProperty {

	private boolean useDeflateEncoding;

	/** Returns whether the use deflate encoding is enabled.
	 * @return the result
	 */
	public boolean isUseDeflateEncoding() {
		return useDeflateEncoding;
	}

	/** Sets the use deflate encoding.
	 * @param useDeflateEncoding the useDeflateEncoding
	 */
	public void setUseDeflateEncoding(boolean useDeflateEncoding) {
		this.useDeflateEncoding = useDeflateEncoding;
	}

}
