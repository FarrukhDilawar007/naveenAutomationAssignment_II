package assignmentTwoPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class flightResultPage {

	// Departure Flights
	@FindAll({@FindBy(how = How.CSS, using = " #ow_domrt-jrny span.splitVw-outer.append_right9")})
	List<WebElement> departureFlights;

	// Return Flights
	@FindAll({@FindBy(how = How.CSS, using = "#rt-domrt-jrny span.splitVw-outer.append_right9")})
	List<WebElement> returnFlights;

	// Departure Flight Price at Footer
	@FindBy(how = How.XPATH, using = "//div[@class = 'splitVw-footer-left ']//p[@class = 'actual-price']")
	WebElement FooterDeptPrice;

	// Return Flight Price at Footer
	@FindBy(how = How.XPATH, using = "//div[@class = 'splitVw-footer-right ']//p[@class = 'actual-price']")
	WebElement FooterRetPrice;

	// Total Price of the Flight
	@FindBy(how = How.XPATH, using = "//div//span[@class = 'splitVw-total-fare']")
	WebElement flightTotalPrice;

	// Filter - Non Stop
	@FindBy(how = How.XPATH, using = "(//span[@class = 'check'])[1]")
	WebElement filterNonStopFlights;

	// Filter - Non Stop
	@FindBy(how = How.XPATH, using = "(//span[@class = 'check'])[2]")
	WebElement filterOneStopFlights;
	
	// Reset Stop Filters
	@FindBy(how = How.XPATH, using = "//a[@class= 'pull-right reset-filter']")
	WebElement resetFilters;

	public static WebDriver localdriver;
	
	public flightResultPage(WebDriver driver) {
		localdriver = driver;
	}

	public void flightDetails() {

		WebDriverWait wait = new WebDriverWait(localdriver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ow_domrt-jrny")));

		System.out.println(localdriver.getTitle());

		doScrolling();

		System.out.println("------ Flight Details -------");

		System.out.println("Number of Departure Flights:" + departureFlights.size());
		System.out.println("Number of Return Flights:" + returnFlights.size());
	}

	public void nonStopFlights() {

		filterNonStopFlights.click();
		filterOneStopFlights.click();
		doScrolling();
		System.out.println("");
		System.out.println("------ Flight Details ( with Stop Filters ) -------");
		System.out.println("");
		System.out.println("Number of Departure Flights:" + departureFlights.size());
		System.out.println("Number of Return Flights:" + returnFlights.size());
		
		resetFilters.click();
	}
	
	
	public void planTrip(int depatureFlightIndex, int returnFlightIndex) {
		
//		WebElement DepatureFlight = localdriver.findElement(By.xpath("(//div[@id = 'ow_domrt-jrny']//span[@class = 'splitVw-outer append_right9'])["
//				+depatureFlightIndex+"]"));
//				
//		WebElement ReturnFlight = localdriver.findElement(By.xpath("(//div[@id = 'rt-domrt-jrny']//span[@class = 'splitVw-outer append_right9'])["
//				+returnFlightIndex+"]"));
//		
		System.out.println(depatureFlightIndex);
		System.out.println(returnFlightIndex);
		
		departureFlights.get(depatureFlightIndex).click();

		returnFlights.get(returnFlightIndex).click();
		
		WebElement DepatureFlightFare = localdriver.findElement(By.xpath("(//div[@id = 'ow_domrt-jrny']//p[@class = 'actual-price'])["
		+depatureFlightIndex+"]"));
		
		WebElement ReturnFlightFare = localdriver.findElement(By.xpath("(//div[@id = 'rt-domrt-jrny']//p[@class = 'actual-price'])["
				+returnFlightIndex+"]"));
		
		//DepatureFlight.click();
		
		int depatureFlightFare = Integer.parseInt(DepatureFlightFare.getText().substring(3).replace(",", ""));
		
		//ReturnFlight.click();
		
		int returnFlightFare = Integer.parseInt(ReturnFlightFare.getText().substring(3).replace(",", ""));
		
		System.out.println("Departure Flight: " + depatureFlightFare);
		System.out.println("Return Flight: " + returnFlightFare);
		
		System.out.println("Footer Prices >> ");
		
		System.out.println("Footer Depature Flight Fare: " +FooterDeptPrice.getText());
		System.out.println("Footer Return Flight Fare: " + FooterRetPrice.getText());
		System.out.println("Footer Total Fare: " + flightTotalPrice.getText());
		
		int departFare = Integer.parseInt(FooterDeptPrice.getText().substring(3).replace(",", ""));
		int returnFare = Integer.parseInt(FooterRetPrice.getText().substring(3).replace(",", ""));
		int totalFare = Integer.parseInt(flightTotalPrice.getText().substring(3).replace(",", ""));
//		Assert.assertEquals(departFare, depatureFlightFare);
//		Assert.assertEquals(returnFare, returnFlightFare);
//		Assert.assertEquals(totalFare, returnFlightFare + depatureFlightFare);
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
