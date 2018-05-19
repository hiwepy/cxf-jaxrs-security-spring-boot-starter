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
package org.apache.cxf.spring.boot.jaxrs;

import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

public class ServerPublish {

	public static void main(String[] args) throws Exception {
		// jax-rs服务工厂
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
		factoryBean.getInInterceptors().add(new LoggingInInterceptor());
		factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());

		factoryBean.setResourceClasses(CustomerServiceImpl.class, CustomerServiceImpl2.class, CustomerServiceImpl3.class);
		//factoryBean.setPublishedEndpointUrl("http://localhost:9000");
		factoryBean.setAddress("http://localhost:9000");
		factoryBean.create();
		
		// jax-rs服务工厂
		JAXRSServerFactoryBean factoryBean2 = new JAXRSServerFactoryBean();
		factoryBean2.getInInterceptors().add(new LoggingInInterceptor());
		factoryBean2.getOutInterceptors().add(new LoggingOutInterceptor());
	
		factoryBean2.setResourceClasses(CustomerServiceImpl4.class);
		//factoryBean2.setPublishedEndpointUrl("http://localhost:8000");
		
		factoryBean2.setAddress("http://localhost:8000");
		factoryBean2.create();

		//
	}
}