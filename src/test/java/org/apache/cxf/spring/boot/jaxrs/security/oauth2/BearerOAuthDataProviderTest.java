package org.apache.cxf.spring.boot.jaxrs.security.oauth2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link BearerOAuthDataProvider}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
class BearerOAuthDataProviderTest {

    @Test
    void constructor_shouldCreateInstance() {
        BearerOAuthDataProvider provider = new BearerOAuthDataProvider();
        assertThat(provider).isNotNull();
    }

}
