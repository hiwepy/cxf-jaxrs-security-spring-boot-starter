package org.apache.cxf.spring.boot;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.validation.ValidationExceptionMapper;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.apache.cxf.metrics.codahale.CodahaleMetricsProvider;
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
	private APIEndpointRepository endpointRepository;

	@Bean
	@ConditionalOnMissingBean
	public ValidationExceptionMapper validationExceptionMapper() {
		return new ValidationExceptionMapper();
	}

	/**
	 * Creates the CXF bus.
	 * @return the Spring bus
	 */
	@Bean(name = Bus.DEFAULT_BUS_ID)
	@ConditionalOnMissingBean(Bus.class)
	public SpringBus springBus() {
		SpringBus bus = new SpringBus();
		BusFactory.setDefaultBus(bus);
		return bus;
	}

	/**
	 * Creates a logging feature from the bound properties.
	 * @param properties the CXF properties
	 * @return the logging feature
	 */
	@Bean
	@ConditionalOnMissingBean
	public LoggingFeature loggingFeature(CxfJaxrsProperties properties) {
		LoggingFeature feature = new LoggingFeature();
		LoggingFeatureProperty logging = properties.getLoggingFeature();
		if (logging != null) {
			feature.setLimit(logging.getLimit());
			feature.setPrettyLogging(logging.isPrettyLogging());
		}
		return feature;
	}

	/**
	 * Creates a metrics feature using Codahale/Dropwizard metrics.
	 * @param bus the CXF bus
	 * @return the metrics feature
	 */
	@Bean
	@ConditionalOnMissingBean
	public MetricsFeature metricsFeature(Bus bus) {
		return new MetricsFeature(MetricsProvider.class.cast(new CodahaleMetricsProvider(bus)));
	}

	/**
	 * Creates a bean validation feature.
	 * @return the bean validation feature
	 */
	@Bean
	@ConditionalOnMissingBean
	public BeanValidationFeature validationFeature() {
		return new BeanValidationFeature();
	}

	/**
	 * Creates a bean validation provider.
	 * @return the bean validation provider
	 */
	@Bean
	@ConditionalOnMissingBean
	public BeanValidationProvider validationProvider() {
		return new BeanValidationProvider();
	}

	/**
	 * Creates an endpoint API template for publishing JAX-RS endpoints.
	 * @param bus the CXF bus
	 * @param loggingFeature the logging feature
	 * @param metricsFeature the metrics feature
	 * @param validationFeature the validation feature
	 * @param properties the CXF properties
	 * @return the endpoint API template
	 */
	@Bean
	@ConditionalOnMissingBean
	public EndpointApiTemplate endpointApiTemplate(Bus bus,
			LoggingFeature loggingFeature, MetricsFeature metricsFeature,
			BeanValidationFeature validationFeature,
			CxfJaxrsProperties properties) {
		
		EndpointApiTemplate template = new EndpointApiTemplate(bus, properties.getServer());

		template.setLoggingFeature(loggingFeature);
		template.setMetricsFeature(metricsFeature);
		template.setValidationFeature(validationFeature);
		
		return template;
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

}
