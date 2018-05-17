package org.apache.cxf.spring.boot;

import org.apache.cxf.spring.boot.property.LoggingFeatureProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

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


	public LoggingFeatureProperty getLoggingFeature() {
		return loggingFeature;
	}

	public void setLoggingFeature(LoggingFeatureProperty loggingFeature) {
		this.loggingFeature = loggingFeature;
	}
	
	public boolean isComponentScan() {
		return componentScan;
	}

	public void setComponentScan(boolean componentScan) {
		this.componentScan = componentScan;
	}

	public String getComponentScanPackages() {
		return componentScanPackages;
	}

	public void setComponentScanPackages(String componentScanPackages) {
		this.componentScanPackages = componentScanPackages;
	}

	public String getComponentScanBeans() {
		return componentScanBeans;
	}

	public void setComponentScanBeans(String componentScanBeans) {
		this.componentScanBeans = componentScanBeans;
	}

	public boolean isClassesScan() {
		return classesScan;
	}

	public void setClassesScan(boolean classesScan) {
		this.classesScan = classesScan;
	}

	public String getClassesScanPackages() {
		return classesScanPackages;
	}

	public void setClassesScanPackages(String classesScanPackages) {
		this.classesScanPackages = classesScanPackages;
	}

	public CxfJaxrsServerProperties getServer() {
		return server;
	}

	public void setServer(CxfJaxrsServerProperties server) {
		this.server = server;
	}

}