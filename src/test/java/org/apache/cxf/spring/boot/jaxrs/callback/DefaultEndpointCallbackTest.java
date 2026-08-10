package org.apache.cxf.spring.boot.jaxrs.callback;

import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.spring.boot.jaxrs.endpoint.EndpointCallback;
import org.apache.cxf.validation.BeanValidationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link DefaultEndpointCallback}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
class DefaultEndpointCallbackTest {

    private LoggingFeature loggingFeature;
    private BeanValidationFeature validationFeature;
    private DefaultEndpointCallback callback;

    @BeforeEach
    void setUp() {
        loggingFeature = new LoggingFeature();
        validationFeature = new BeanValidationFeature();
        callback = new DefaultEndpointCallback(loggingFeature, null, validationFeature);
    }

    @Test
    void constructor_shouldCreateInstance() {
        assertThat(callback).isNotNull();
    }

    @Test
    void callback_shouldImplementEndpointCallback() {
        assertThat(callback).isInstanceOf(EndpointCallback.class);
    }

    @Test
    void doCallback_shouldExist() throws Exception {
        assertThat(DefaultEndpointCallback.class.getMethod("doCallback",
                org.apache.cxf.jaxrs.JAXRSServerFactoryBean.class, Object[].class)).isNotNull();
    }

}
