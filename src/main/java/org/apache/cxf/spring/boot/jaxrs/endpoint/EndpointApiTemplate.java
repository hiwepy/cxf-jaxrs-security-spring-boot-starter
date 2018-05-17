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
package org.apache.cxf.spring.boot.jaxrs.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.xml.ws.handler.Handler;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.ServerImpl;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.spring.boot.jaxrs.callback.DefaultEndpointCallback;
import org.apache.cxf.validation.BeanValidationFeature;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * TODO
 * @author ： <a href="https://github.com/vindell">vindell</a>
 */
@SuppressWarnings("rawtypes")
public class EndpointApiTemplate {

	private ConcurrentMap<String, ServerImpl> endpointServers = new ConcurrentHashMap<String, ServerImpl>();
	private ConcurrentMap<String, Feature> features = new ConcurrentHashMap<String, Feature>();
	private ConcurrentMap<String, Handler> handlers = new ConcurrentHashMap<String, Handler>();
	private ConcurrentMap<String, Interceptor> interceptors = new ConcurrentHashMap<String, Interceptor>();
	private Bus bus;
	private EndpointCallback callback;
	private LoggingFeature loggingFeature;
	private MetricsFeature metricsFeature;
	private BeanValidationFeature validationFeature;

	public EndpointApiTemplate(Bus bus) {
		this.bus = bus;
		this.callback = new DefaultEndpointCallback(features, handlers, interceptors, loggingFeature, metricsFeature, validationFeature);
	}

	/**
	 * 为指定的addr发布Endpoint
	 * @author ： <a href="https://github.com/vindell">vindell</a>
	 * @param addr
	 * @param api
	 * @return
	 */
	public ServerImpl publish(String addr, Object implementor) {
		return this.publish(addr, implementor, callback);
	}

	/**
	 * 为指定的addr发布Endpoint
	 * @author 		： <a href="https://github.com/vindell">vindell</a>
	 * @param addr
	 * @param implementor
	 * @param callback
	 * @return
	 */
	public ServerImpl publish(String addr, Object implementor, EndpointCallback callback) {
		
		// 1). 服务端工厂类
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
		
		// 2). 设置属性
		factoryBean.setAddress(addr);
		factoryBean.setBindingId(DigestUtils.md5Hex(addr));
		factoryBean.setBus(bus);
		factoryBean.setServiceBean(implementor); 
		
		callback.doCallback(implementor, factoryBean);

		// 3). 添加 Provider，用于支持自动解析各种数据格式、如Json
		List<Object> providerList = new ArrayList<Object>();
		providerList.add(new JacksonJsonProvider());
		factoryBean.setProviders(providerList); 

		// 添加输入&输出日志（可选）
		factoryBean.getInInterceptors().add(new LoggingInInterceptor());
		factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());

		// 4). 创建并发布服务，会发起一个http服务，默认使用Jetty
		ServerImpl server = (ServerImpl) factoryBean.create();
		
		endpointServers.put(addr, server);

		return server;
	}

	/**
	 * 销毁指定路径匹配的Endpoint
	 * @author ： <a href="https://github.com/vindell">vindell</a>
	 * @param addr
	 */
	public void destroy(String addr) {
		ServerImpl myServer = (ServerImpl) endpointServers.get(addr);
		if (myServer != null) {
			myServer.destroy();
		}
	}
	
	public ConcurrentMap<String, Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Map<String, Feature> features) {
		this.features.putAll(features);
	}

	public ConcurrentMap<String, Interceptor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(Map<String, Interceptor> interceptorsOfType) {
		this.interceptors.putAll(interceptorsOfType);
	}

	public ConcurrentMap<String, Handler> getHandlers() {
		return handlers;
	}

	public void setHandlers(Map<String, Handler> handlers) {
		this.handlers.putAll(handlers);
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public void addFeature(String name, Feature feature) {
		this.features.put(name, feature);
	}

	public void addInterceptor(String name, Interceptor in) {
		this.interceptors.put(name, in);
	}
 
	public void addHandler(String name, Handler handler) {
		this.handlers.put(name, handler);
	}
	
	public LoggingFeature getLoggingFeature() {
		return loggingFeature;
	}

	public void setLoggingFeature(LoggingFeature loggingFeature) {
		this.loggingFeature = loggingFeature;
	}

	public MetricsFeature getMetricsFeature() {
		return metricsFeature;
	}

	public void setMetricsFeature(MetricsFeature metricsFeature) {
		this.metricsFeature = metricsFeature;
	}

	public BeanValidationFeature getValidationFeature() {
		return validationFeature;
	}

	public void setValidationFeature(BeanValidationFeature validationFeature) {
		this.validationFeature = validationFeature;
	}
	
}
