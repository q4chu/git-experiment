package core.helpers;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebDriver;

import core.appManager.AppPage;
import core.driver.EnhancedBy;
import core.driver.EnhancedWebElement;
import core.driver.TestLog;

public class FileUploadHelper extends AppPage {

	public FileUploadHelper(WebDriver driver) {
		super(driver);
	}

	/**
	 * uploads file by specifying file location relative to main path
	 * 
	 * NOTICE: The image you upload should be under your automation main folder. If not, delete the file.getAbsolutePath() in the second line
	 * @param location
	 * @param fileButton
	 */
	public static void uploadFile(String location, EnhancedBy fileButton) {
		File file = new File("");
		String path = file.getAbsolutePath() + location;
		FormHelper.setField(path, fileButton);
		EnhancedWebElement fieldElement = findElement(fileButton);
		fieldElement.clear();
		TestLog.logPass("I upload file at location '" + location + "'");
	}

	public void uploadImages(List<String> locations, EnhancedBy imageButton, EnhancedBy images) {
		for (String location : locations) {
			uploadImage(location, imageButton, images);
		}
	}

	/**
	 * sets the image based on location
	 * 
	 * @param location
	 * @param imageButton
	 * @param images
	 *            : uploaded image
	 */
	public void uploadImage(String location, EnhancedBy imageButton, EnhancedBy images) {
		int imageCount = ListHelper.getListCount(images);
		File file = new File("");
		String path = file.getAbsolutePath() + location;
		FormHelper.setField(path, imageButton);
		WaitHelper.waitForAdditionalElementsToLoad(images, imageCount);
		TestLog.logPass("I upload image at location '" + location + "'");
	}
}