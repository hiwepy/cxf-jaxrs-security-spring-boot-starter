package org.apache.cxf.spring.boot;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jws.WebService;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import javax.xml.ws.handler.Handler;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.common.util.ClasspathScanner;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.spring.JAXRSServerFactoryBeanDefinitionParser;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.jaxrs.validation.JAXRSBeanValidationInInterceptor;
import org.apache.cxf.jaxrs.validation.JAXRSBeanValidationOutInterceptor;
import org.apache.cxf.jaxrs.validation.ValidationExceptionMapper;
import org.apache.cxf.message.Message;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.apache.cxf.metrics.codahale.CodahaleMetricsProvider;
import org.apache.cxf.rs.security.oauth2.grants.code.EHCacheCodeDataProvider;
import org.apache.cxf.rs.security.oauth2.provider.DefaultEHCacheOAuthDataProvider;
import org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider;
import org.apache.cxf.rs.security.oauth2.services.AccessTokenService;
import org.apache.cxf.service.factory.ServiceConstructionException;
import org.apache.cxf.spring.boot.endpoint.APIEndpoint;
import org.apache.cxf.spring.boot.endpoint.APIEndpointRepository;
import org.apache.cxf.spring.boot.jaxws.annotation.JaxwsEndpoint;
import org.apache.cxf.spring.boot.jaxws.endpoint.EndpointApiTemplate;
import org.apache.cxf.spring.boot.jaxws.property.LoggingFeatureProperty;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.cxf.validation.BeanValidationFeature;
import org.apache.cxf.validation.BeanValidationProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

//http://cxf.apache.org/docs/springboot.html
@AutoConfigureAfter(name = { "org.apache.cxf.spring.boot.autoconfigure.CxfAutoConfiguration" })
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ SpringBus.class, CXFServlet.class })
@ConditionalOnProperty(prefix = CxfJaxrsProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ CxfJaxrsProperties.class })
public class CxfJaxrsAutoConfiguration implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Autowired
	private Bus bus;
	@Autowired
	private APIEndpointRepository endpointRepository;

	@Bean
	@ConditionalOnMissingBean(Bus.class)
	public Bus bus() {
		SpringBus bus = new SpringBus();
		BusFactory.setDefaultBus(bus);
		return bus;
	}

	@Bean
	@ConditionalOnMissingBean(BeanValidationProvider.class)
	public BeanValidationProvider validationProvider() {
		return new BeanValidationProvider();
	}

	@Bean
	public BeanValidationFeature validationFeature(BeanValidationProvider validationProvider) {
		BeanValidationFeature feature = new BeanValidationFeature();
		feature.setProvider(validationProvider);
		return feature;
	}

	@Bean
	public ValidationExceptionMapper exceptionMapper() {
		return new ValidationExceptionMapper();
	}

	
	@Bean
	public LoggingFeature loggingFeature(CxfJaxwsProperties properties) {
		
		LoggingFeatureProperty property = properties.getLoggingFeature();

		LoggingFeature feature = new LoggingFeature();
		feature.setInMemThreshold(property.getThreshold());
		feature.setLimit(property.getLimit());
		feature.setLogBinary(property.isLogBinary());
		feature.setLogMultipart(property.isLogMultipart());
		feature.setPrettyLogging(property.isPrettyLogging());
		feature.setVerbose(property.isVerbose());
		
		return feature;
	}
	
	@Bean
	@ConditionalOnMissingBean(MetricsProvider.class)
	public MetricsProvider metricsProvider(Bus bus) {
		return new CodahaleMetricsProvider(bus);
	}

	@Bean
	public MetricsFeature metricsFeature(MetricsProvider metricsProvider) {
		return new MetricsFeature(metricsProvider);
	}
	
	@Bean
	public EndpointApiTemplate endpointTemplate(Bus bus,
			LoggingFeature loggingFeature,
			MetricsFeature metricsFeature,
			BeanValidationFeature validationFeature) {

		Map<String, Feature> featuresOfType = getApplicationContext().getBeansOfType(Feature.class);
		Map<String, Handler> handlersOfType = getApplicationContext().getBeansOfType(Handler.class);
		Map<String, Interceptor> interceptorsOfType = getApplicationContext().getBeansOfType(Interceptor.class);

		EndpointApiTemplate template = new EndpointApiTemplate(bus);

		template.setFeatures(featuresOfType);
		template.setHandlers(handlersOfType);
		template.setInterceptors(interceptorsOfType);
		template.setLoggingFeature(loggingFeature);
		template.setMetricsFeature(metricsFeature);
		template.setValidationFeature(validationFeature);
		
		// 动态创建、发布 Ws
		Map<String, Object> beansOfType = getApplicationContext().getBeansWithAnnotation(WebService.class);
		if (!ObjectUtils.isEmpty(beansOfType)) {

			Iterator<Entry<String, Object>> ite = beansOfType.entrySet().iterator();
			while (ite.hasNext()) {
				Entry<String, Object> entry = ite.next();
				// 查找该实现上的自定义注解
				JaxwsEndpoint annotationType = getApplicationContext().findAnnotationOnBean(entry.getKey(),
						JaxwsEndpoint.class);
				if (annotationType == null) {
					// 注解为空，则跳过该实现，并打印错误信息
					LOG.error("Not Found AnnotationType {0} on Bean {1} Whith Name {2}", JaxwsEndpoint.class,
							entry.getValue().getClass(), entry.getKey());
					continue;
				}
				template.publish(annotationType.addr(), entry.getValue());
			}
		}

		return template;
	}
	
	
	protected Server createJaxRsServer() {

        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setAddress(getAddress());
        factory.setTransportId(getTransportId());
        factory.setBus(getBus());

        setJaxrsResources(factory);

        factory.setInInterceptors(getInInterceptors());
        factory.setOutInterceptors(getOutInterceptors());
        factory.setOutFaultInterceptors(getOutFaultInterceptors());
        factory.setFeatures(getFeatures());
        finalizeFactorySetup(factory);
        return factory.create();
    }
	
	 protected void setJaxrsResources(JAXRSServerFactoryBean factory) {
	        try {
	            final Map< Class< ? extends Annotation >, Collection< Class< ? > > > classes =
	                ClasspathScanner.findClasses(basePackages, Provider.class, Path.class);

	            List<Object> jaxrsServices = JAXRSServerFactoryBeanDefinitionParser
	                .createBeansFromDiscoveredClasses(super.applicationContext, classes.get(Path.class), null);
	            List<Object> jaxrsProviders = JAXRSServerFactoryBeanDefinitionParser
	                .createBeansFromDiscoveredClasses(super.applicationContext, classes.get(Provider.class), null);

	            factory.setServiceBeans(jaxrsServices);
	            factory.setProviders(jaxrsProviders);
	        } catch (Exception ex) {
	            throw new ServiceConstructionException(ex);
	        }

	    }

	 

	@Bean
	public Server rsServer(BeanValidationProvider validationProvider) {
		JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
		endpoint.setBus(bus);
		endpoint.setAddress("/");
		// Register 2 JAX-RS root resources supporting "/sayHello/{id}" and
		// "/sayHello2/{id}" relative paths
		// endpoint.setServiceBeans(Arrays.<Object>asList(new HelloServiceImpl1(), new
		// HelloServiceImpl2()));
		endpoint.setFeatures(Arrays.asList(new Swagger2Feature()));

		JAXRSBeanValidationInInterceptor validationInInterceptor = new JAXRSBeanValidationInInterceptor();
		validationInInterceptor.setProvider(validationProvider);

		JAXRSBeanValidationOutInterceptor validationOutInterceptor = new JAXRSBeanValidationOutInterceptor();
		validationOutInterceptor.setProvider(validationProvider);

		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		// sf.setResourceClasses( ... );
		// sf.setResourceProvider( ... );
		sf.setProvider(new ValidationExceptionMapper());
		sf.setInInterceptors(Arrays.<Interceptor<? extends Message>>asList(validationInInterceptor));
		sf.setOutInterceptors(Arrays.<Interceptor<? extends Message>>asList(validationOutInterceptor));
		sf.create();
		
		return endpoint.create();
	}

	/**
	 * 决定一个消费者将如何等待生产者将Event置入Disruptor的策略。用来权衡当生产者无法将新的事件放进RingBuffer时的处理策略。
	 * （例如：当生产者太快，消费者太慢，会导致生成者获取不到新的事件槽来插入新事件，则会根据该策略进行处理，默认会堵塞）
	 */
	@Bean
	@ConditionalOnMissingBean
	public DefaultEHCacheOAuthDataProvider oauthProvider() {

		/*
		 * <bean id="oauthProvider" class=
		 * "org.apache.cxf.systest.jaxrs.security.oauth2.common.OAuthDataProviderImpl">
		 *        <property name="useJwtFormatForAccessTokens" value="true"/>
		 *        <property name="storeJwtTokenKeyOnly" value="true"/> </bean>
		 */
		/*
		 * <bean id="oauthProvider" class=
		 * "org.apache.cxf.rs.security.oauth2.grants.code.EHCacheCodeDataProvider">
		 *        <property name="useJwtFormatForAccessTokens" value="true"/> </bean>
		 */

		DefaultEHCacheOAuthDataProvider dataProvider = new EHCacheCodeDataProvider();

		dataProvider.setUseJwtFormatForAccessTokens(true);

		return dataProvider;
	}

	@Bean
	@ConditionalOnMissingBean
	public AccessTokenService accessTokenService(OAuthDataProvider dataProvider) {
		/*
		 * <bean id="oauthProvider" class="oauth2.manager.OAuthManager"/>
		 * 
		 * <bean id="accessTokenService"
		 * class="org.apache.cxf.rs.security.oauth2.services.AccessTokenService">
		 * <property name="dataProvider" ref="oauthProvider"/> <property
		 * name="writeCustomErrors" value="true"/> </bean>
		 */

		AccessTokenService accessTokenService = new AccessTokenService();

		accessTokenService.setDataProvider(dataProvider);
		accessTokenService.setWriteCustomErrors(true);

		return accessTokenService;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public APIEndpointRepository getEndpointRepository() {
		return endpointRepository;
	}

	public void setEndpointRepository(APIEndpointRepository endpointRepository) {
		this.endpointRepository = endpointRepository;
	}

}
