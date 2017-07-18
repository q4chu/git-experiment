package core.driver;

import org.openqa.selenium.By;

public class EnhancedBy {

	public By by;
	public String name;

	/**
	 * gets by value for web elements
	 * 
	 * @param by
	 * @param name
	 */
	public EnhancedBy(By by, String name) {
		this.by = by;
		this.name = name;

	}
}
