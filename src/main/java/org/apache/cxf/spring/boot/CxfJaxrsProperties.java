package org.apache.cxf.spring.boot;

import org.apache.cxf.spring.boot.property.LoggingFeatureProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
/** Configuration properties for Cxf Jaxrs.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */

@ConfigurationProperties(CxfJaxrsProperties.PREFIX)
public class CxfJaxrsProperties {

	public static final String PREFIX = "cxf.jaxrs";

	/**
	 * Enable Component Scan.
	 */
	private boolean componentScan = false;
	private String componentScanPackages;
	private String componentScanBeans;

	/**
	 * Enable Classes Scan.
	 */
	private boolean classesScan = false;
	private String classesScanPackages;

	@NestedConfigurationProperty
	private CxfJaxrsServerProperties server = new CxfJaxrsServerProperties();

	@NestedConfigurationProperty
	private LoggingFeatureProperty loggingFeature = new LoggingFeatureProperty();


	/** Returns the logging feature.
	 * @return the result
	 */
	public LoggingFeatureProperty getLoggingFeature() {
		return loggingFeature;
	}

	/** Sets the logging feature.
	 * @param loggingFeature the loggingFeature
	 */
	public void setLoggingFeature(LoggingFeatureProperty loggingFeature) {
		this.loggingFeature = loggingFeature;
	}
	
	/** Returns whether the component scan is enabled.
	 * @return the result
	 */
	public boolean isComponentScan() {
		return componentScan;
	}

	/** Sets the component scan.
	 * @param componentScan the componentScan
	 */
	public void setComponentScan(boolean componentScan) {
		this.componentScan = componentScan;
	}

	/** Returns the component scan packages.
	 * @return the result
	 */
	public String getComponentScanPackages() {
		return componentScanPackages;
	}

	/** Sets the component scan packages.
	 * @param componentScanPackages the componentScanPackages
	 */
	public void setComponentScanPackages(String componentScanPackages) {
		this.componentScanPackages = componentScanPackages;
	}

	/** Returns the component scan beans.
	 * @return the result
	 */
	public String getComponentScanBeans() {
		return componentScanBeans;
	}

	/** Sets the component scan beans.
	 * @param componentScanBeans the componentScanBeans
	 */
	public void setComponentScanBeans(String componentScanBeans) {
		this.componentScanBeans = componentScanBeans;
	}

	/** Returns whether the classes scan is enabled.
	 * @return the result
	 */
	public boolean isClassesScan() {
		return classesScan;
	}

	/** Sets the classes scan.
	 * @param classesScan the classesScan
	 */
	public void setClassesScan(boolean classesScan) {
		this.classesScan = classesScan;
	}

	/** Returns the classes scan packages.
	 * @return the result
	 */
	public String getClassesScanPackages() {
		return classesScanPackages;
	}

	/** Sets the classes scan packages.
	 * @param classesScanPackages the classesScanPackages
	 */
	public void setClassesScanPackages(String classesScanPackages) {
		this.classesScanPackages = classesScanPackages;
	}

	/** Returns the server.
	 * @return the result
	 */
	public CxfJaxrsServerProperties getServer() {
		return server;
	}

	/** Sets the server.
	 * @param server the server
	 */
	public void setServer(CxfJaxrsServerProperties server) {
		this.server = server;
	}

}