package org.apache.cxf.spring.boot.endpoint;

import java.util.List;
/** Repository for A P I Endpoint.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */

public interface APIEndpointRepository {

	List<APIEndpoint> getEndpoints();
	
}
