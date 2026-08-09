package org.apache.cxf.spring.boot.property;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link CxfJaxwsSamlProperty}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
class CxfJaxwsSamlPropertyTest {

    @Test
    void defaultValues_shouldBeCorrect() {
        CxfJaxwsSamlProperty property = new CxfJaxwsSamlProperty();
        assertThat(property).isNotNull();
    }

}
