package org.apache.cxf.rs.security.oauth2.grants.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link EHCacheCodeDataProvider}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
class EHCacheCodeDataProviderTest {

    @Test
    void provider_shouldBeDefaultEncryptingCodeDataProvider() {
        // Verify the class hierarchy
        assertThat(EHCacheCodeDataProvider.class.getSuperclass())
                .isEqualTo(DefaultEncryptingCodeDataProvider.class);
    }

}
