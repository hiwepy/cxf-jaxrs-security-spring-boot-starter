package org.apache.cxf.spring.boot.jaxrs.endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.spring.boot.CxfJaxrsServerProperties;
import org.apache.cxf.validation.BeanValidationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link EndpointApiTemplate}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
class EndpointApiTemplateTest {

    private SpringBus bus;
    private CxfJaxrsServerProperties serverProperties;
    private EndpointApiTemplate template;

    @BeforeEach
    void setUp() {
        bus = new SpringBus();
        serverProperties = new CxfJaxrsServerProperties();
        template = new EndpointApiTemplate(bus, serverProperties);
    }

    @Test
    void constructor_shouldCreateInstance() {
        assertThat(template).isNotNull();
        assertThat(template.getBus()).isSameAs(bus);
    }

    @Test
    void loggingFeature_shouldBeSettable() {
        LoggingFeature loggingFeature = new LoggingFeature();
        template.setLoggingFeature(loggingFeature);
        assertThat(template.getLoggingFeature()).isSameAs(loggingFeature);
    }

    @Test
    void metricsFeature_shouldBeSettable() {
        template.setMetricsFeature(null);
        assertThat(template.getMetricsFeature()).isNull();
    }

    @Test
    void validationFeature_shouldBeSettable() {
        BeanValidationFeature validationFeature = new BeanValidationFeature();
        template.setValidationFeature(validationFeature);
        assertThat(template.getValidationFeature()).isSameAs(validationFeature);
    }

    @Test
    void bus_shouldBeSettable() {
        SpringBus newBus = new SpringBus();
        template.setBus(newBus);
        assertThat(template.getBus()).isSameAs(newBus);
    }

    @Test
    void publish_shouldExist() throws Exception {
        // Verify the publish method exists
        assertThat(EndpointApiTemplate.class.getMethod("publish", String.class, Object[].class)).isNotNull();
    }

    @Test
    void destroy_shouldExist() throws Exception {
        // Verify the destroy method exists
        assertThat(EndpointApiTemplate.class.getMethod("destroy", String.class)).isNotNull();
    }

}
