package org.apache.cxf.spring.boot.jaxrs.security.oauth2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link BearerOAuthDataProvider}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
class BearerOAuthDataProviderTest {

    @Test
    void constructor_shouldCreateInstance() {
        BearerOAuthDataProvider provider = new BearerOAuthDataProvider();
        assertThat(provider).isNotNull();
    }

}
