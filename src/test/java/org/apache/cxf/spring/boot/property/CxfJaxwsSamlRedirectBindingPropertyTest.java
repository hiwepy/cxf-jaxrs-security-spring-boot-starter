package org.apache.cxf.spring.boot.property;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link CxfJaxwsSamlRedirectBindingProperty}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
class CxfJaxwsSamlRedirectBindingPropertyTest {

    @Test
    void defaultValues_shouldBeCorrect() {
        CxfJaxwsSamlRedirectBindingProperty property = new CxfJaxwsSamlRedirectBindingProperty();

        assertThat(property.getIssuerId()).isNull();
    }

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        CxfJaxwsSamlRedirectBindingProperty property = new CxfJaxwsSamlRedirectBindingProperty();

        property.setIssuerId("issuer1");
        assertThat(property.getIssuerId()).isEqualTo("issuer1");
    }

}
