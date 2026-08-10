package org.apache.cxf.rs.security.oauth2.grants.code;

/** The E H Cache Code Data Provider.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */

public class EHCacheCodeDataProvider extends DefaultEncryptingCodeDataProvider {

	public EHCacheCodeDataProvider() {
		super("ehcache.xml", 300);
	}

	public EHCacheCodeDataProvider(String configFileURL, int tokenLifetime) {
		super(configFileURL, tokenLifetime);
	}

}
