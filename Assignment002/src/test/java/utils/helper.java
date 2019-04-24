package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class helper {
	
	public static WebDriver localdriver;
	public helper(WebDriver driver) {
		localdriver = driver;
	}
	
	public void doScrolling() {

		JavascriptExecutor js = ((JavascriptExecutor) localdriver);
		long lastPageHeight = (Long) js.executeScript("return document.body.scrollHeight");
		while (true) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			long currentPageHeight = (Long) js.executeScript("return document.body.scrollHeight");
			if (lastPageHeight == currentPageHeight) {
				break;
			}
			lastPageHeight = currentPageHeight;
		}

		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");

	}

}
