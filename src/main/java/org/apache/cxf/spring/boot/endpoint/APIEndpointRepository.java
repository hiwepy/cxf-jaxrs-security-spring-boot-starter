package org.apache.cxf.spring.boot.endpoint;

import java.util.List;
/** Repository for A P I Endpoint.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */

public interface APIEndpointRepository {

	List<APIEndpoint> getEndpoints();
	
}
