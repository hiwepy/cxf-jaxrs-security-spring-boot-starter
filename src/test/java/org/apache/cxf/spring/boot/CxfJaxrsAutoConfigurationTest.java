package org.apache.cxf.spring.boot;

import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.spring.boot.jaxrs.endpoint.EndpointApiTemplate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link CxfJaxrsAutoConfiguration}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
class CxfJaxrsAutoConfigurationTest {

    @Test
    void constructor_shouldCreateInstance() {
        CxfJaxrsAutoConfiguration config = new CxfJaxrsAutoConfiguration();
        assertThat(config).isNotNull();
    }

    @Test
    void applicationContext_shouldBeSettable() {
        CxfJaxrsAutoConfiguration config = new CxfJaxrsAutoConfiguration();
        assertThat(config.getApplicationContext()).isNull();
    }

    @Test
    void endpointRepository_shouldBeNullByDefault() {
        CxfJaxrsAutoConfiguration config = new CxfJaxrsAutoConfiguration();
        assertThat(config.getEndpointRepository()).isNull();
    }

    @Test
    void configuration_shouldImplementApplicationContextAware() {
        CxfJaxrsAutoConfiguration config = new CxfJaxrsAutoConfiguration();
        assertThat(config).isInstanceOf(org.springframework.context.ApplicationContextAware.class);
    }

}
