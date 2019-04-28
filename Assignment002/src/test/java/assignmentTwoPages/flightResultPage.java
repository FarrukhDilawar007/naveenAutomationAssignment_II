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

import utils.helper;

public class flightResultPage {

	// Departure Flights
	@FindAll({ @FindBy(how = How.CSS, using = " #ow_domrt-jrny span.splitVw-outer.append_right9") })
	List<WebElement> departureFlights;

	// Return Flights
	@FindAll({ @FindBy(how = How.CSS, using = "#rt-domrt-jrny span.splitVw-outer.append_right9") })
	List<WebElement> returnFlights;

	// Departure Flight Price at Footer
	@FindBy(how = How.XPATH, using = "//div[@class = 'splitVw-footer-left ']//p[@class = 'actual-price']")
	WebElement FooterDeptPrice;

	// Return Flight Price at Footer
	@FindBy(how = How.XPATH, using = "//div[@class = 'splitVw-footer-right ']//p[@class = 'actual-price']")
	WebElement FooterRetPrice;

	// Total Price of the Flights
	@FindBy(how = How.XPATH, using = "//div//span[@class = 'splitVw-total-fare']")
	WebElement flightTotalPrice;

	// Discounted Price of the Flights
	@FindBy(how = How.XPATH, using = "//div[@class = 'splitVw-footer-total make_relative make_flex alC']//*[contains(text(),'Return trip discount')]//following-sibling::span")
	WebElement flightDiscountedPrice;

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
	public static helper _helper;
	public static WebDriverWait wait;
	public flightResultPage(WebDriver driver) {
		localdriver = driver;
	}

	public void flightDetails() {
		
		_helper = new helper(localdriver);
		
		//waiting for the loading of Flight results
		wait = new WebDriverWait(localdriver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ow_domrt-jrny")));

		//Scrolling to the bottom of the page to fetch all the flights
		_helper.doScrolling();

		System.out.println("------ Flight Details -------");
		System.out.println();
		System.out.println("Number of Departure Flights:" + departureFlights.size());
		System.out.println("Number of Return Flights:" + returnFlights.size());
		System.out.println();
	}

	public void flightDetailswithFilters() {
		
		//Clicking on Filters
		filterNonStopFlights.click();
		filterOneStopFlights.click();
		
		//Scrolling to the bottom of the page to fetch all the flights
		_helper.doScrolling();
		
		System.out.println("------ Flight Details ( with Stop Filters ) -------");
		System.out.println();
		System.out.println("Number of Departure Flights:" + departureFlights.size());
		System.out.println("Number of Return Flights:" + returnFlights.size());
		
		//resetting the filter options
		resetFilters.click();
	}

	public void compareFlightFares(int depatureFlightIndex, int returnFlightIndex) {

		JavascriptExecutor js = (JavascriptExecutor) localdriver;

		WebElement DepatureFlight = localdriver
				.findElement(By.xpath("(//div[@id = 'ow_domrt-jrny']//span[@class = 'splitVw-outer append_right9'])["
						+ depatureFlightIndex + "]"));
		js.executeScript("arguments[0].click()", DepatureFlight);

		WebElement ReturnFlight = localdriver
				.findElement(By.xpath("(//div[@id = 'rt-domrt-jrny']//span[@class = 'splitVw-outer append_right9'])["
						+ returnFlightIndex + "]"));
		js.executeScript("arguments[0].click()", ReturnFlight);

		WebElement DepatureFlightFare = localdriver.findElement(
				By.xpath("(//div[@id = 'ow_domrt-jrny']//p[@class = 'actual-price'])[" + depatureFlightIndex + "]"));

		WebElement ReturnFlightFare = localdriver.findElement(
				By.xpath("(//div[@id = 'rt-domrt-jrny']//p[@class = 'actual-price'])[" + returnFlightIndex + "]"));

		int depatureFlightFare = Integer.parseInt(DepatureFlightFare.getText().substring(3).replace(",", ""));
		int returnFlightFare = Integer.parseInt(ReturnFlightFare.getText().substring(3).replace(",", ""));

		
		//Collecting the discounted Price if displayed with any combination of flights
		boolean discount = false;
		int discountedFare = 0;
		try {
			discount = flightDiscountedPrice.isDisplayed();
			if ( discount == true ) {
				discountedFare = Integer.parseInt(flightDiscountedPrice.getText().substring(3));
			}
			
		} catch (Exception e) {
			
			e.toString();
		}
		
		int footer_departFare = Integer.parseInt(FooterDeptPrice.getText().substring(3).replace(",", ""));
		int footer_returnFare = Integer.parseInt(FooterRetPrice.getText().substring(3).replace(",", ""));
		int footer_totalFare = Integer.parseInt(flightTotalPrice.getText().substring(3).replace(",", ""));
		
		
		//Assertion of Flights Fare and Total Fare
		Assert.assertEquals(footer_departFare, depatureFlightFare);
		Assert.assertEquals(footer_returnFare, returnFlightFare);
		Assert.assertEquals(footer_totalFare + discountedFare, returnFlightFare + depatureFlightFare);
		
		
		System.out.println();
		System.out.println("Flight Fares >> ");
		System.out.println("Departure Flight: " + DepatureFlightFare.getText());
		System.out.println("Return Flight: " + ReturnFlightFare.getText());
		System.out.println();
		
		System.out.println("Flights Fares at Footer >> ");
		System.out.println("Footer Depature Flight Fare: " + FooterDeptPrice.getText());
		System.out.println("Footer Return Flight Fare: " + FooterRetPrice.getText());
		System.out.println("Footer Total Fare: " + flightTotalPrice.getText());
		System.out.println("Footer Discounted Fare: " + discountedFare);
		System.out.println();
	}

}
