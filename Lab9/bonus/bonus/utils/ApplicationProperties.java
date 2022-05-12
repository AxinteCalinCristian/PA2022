package bonus.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import lombok.extern.java.Log;

@Log
public class ApplicationProperties {
	private final Properties configProp = new Properties();
	
	private static ApplicationProperties instance = null;
	
	public static ApplicationProperties getInstance() {
        if (instance == null)
        	instance = new ApplicationProperties();
 
        return instance;
    } 
	
	private ApplicationProperties() {
		setLoggingLevel(Level.DEBUG);
		String app_properties = this.getClass().getPackageName().substring(0, this.getClass().getPackageName().indexOf('.'));
		app_properties = "./" + app_properties + "/resources/application.properties";
		
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(app_properties);
		try {
		      configProp.load(in);
		} catch (IOException e) {
			  log.warning("Could not import properties file.");
		      e.printStackTrace();
		}
	}
	
	public Properties getProperties() {
		return this.configProp;
	}
	
	public String getProperty(String key) {
		if(configProp.containsKey(key)) {
			return configProp.getProperty(key);
		}
		return null;
	}
	
	public boolean containsProperty(String key) {
		return configProp.containsKey(key);
	}
	
	/**
	 * Sets the logging level for the application.
	 * @param loggingLevel
	 */
	public void setLoggingLevel(Level loggingLevel) {
		Configurator.setLevel("org.hibernate.SQL", loggingLevel);
		Configurator.setLevel("org.hibernate.stat", loggingLevel);
	}
}
