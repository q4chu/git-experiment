package core.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	public static Properties Property() throws IOException {

		// Create FileInputStream Object
		FileInputStream fileInput = new FileInputStream(new File("properties/property.properties"));
		// Create Properties object
		Properties prop = new Properties();
		// load properties file
		prop.load(fileInput);
		return prop;
	}
	
	public static String getDriverType() throws IOException {
		return PropertiesReader.Property().getProperty("webdriverType");
	}

	public static String getGridUrl() throws IOException {
		return "http://" + PropertiesReader.Property().getProperty("grid_url");
	}

	public static String getGridPort() throws IOException {
		return PropertiesReader.Property().getProperty("grid_port");
	}

	public static String getBrowser() throws IOException {
		return PropertiesReader.Property().getProperty("remote_browser");
	}
	
	/**
	 *
	 * set to true to append to existing report in case of rerunning test
	 * @return
	 */
	public static boolean isAppendReport() {
		boolean isAppend = false;
		try {
		   isAppend = ((PropertiesReader.Property().getProperty("isAppendReport", "false").equals("true")) ? true : false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAppend;
	}
}
