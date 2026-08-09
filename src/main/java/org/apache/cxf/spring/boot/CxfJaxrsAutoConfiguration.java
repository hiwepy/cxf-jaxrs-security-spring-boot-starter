package org.apache.cxf.spring.boot;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxrs.validation.ValidationExceptionMapper;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.apache.cxf.metrics.codahale.CodahaleMetricsProvider;
import org.apache.cxf.rs.security.oauth2.grants.code.EHCacheCodeDataProvider;
import org.apache.cxf.rs.security.oauth2.provider.DefaultEHCacheOAuthDataProvider;
import org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider;
import org.apache.cxf.rs.security.oauth2.services.AccessTokenService;
import org.apache.cxf.spring.boot.endpoint.APIEndpointRepository;
import org.apache.cxf.spring.boot.jaxrs.endpoint.EndpointApiTemplate;
import org.apache.cxf.spring.boot.property.LoggingFeatureProperty;
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

//http://cxf.apache.org/docs/springboot.html
/** Auto-configuration for Cxf Jaxrs.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
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
	/** Creates a validation provider bean.
	 * @return the result
	 */
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
	public LoggingFeature loggingFeature(CxfJaxrsProperties properties) {
		
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
	/** Creates a metrics provider bean.
	 * @param bus the bus
	 * @return the result
	 */
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
			BeanValidationFeature validationFeature,
			CxfJaxrsProperties properties) {
		
		EndpointApiTemplate template = new EndpointApiTemplate(bus, properties.getServer());

		template.setLoggingFeature(loggingFeature);
		template.setMetricsFeature(metricsFeature);
		template.setValidationFeature(validationFeature);
		
		return template;
	}
	
	

	/**
	 * EventDisruptor 。 RingBuffer processing。
	 * （：，，retrieve ，processing，）
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
	/** Sets the application context.
	 * @param applicationContext the applicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/** Returns the application context.
	 * @return the result
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/** Returns the endpoint repository.
	 * @return the result
	 */
	public APIEndpointRepository getEndpointRepository() {
		return endpointRepository;
	}

	/** Sets the endpoint repository.
	 * @param endpointRepository the endpointRepository
	 */
	public void setEndpointRepository(APIEndpointRepository endpointRepository) {
		this.endpointRepository = endpointRepository;
	}

}
