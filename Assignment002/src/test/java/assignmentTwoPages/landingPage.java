package assignmentTwoPages;

import java.time.LocalDate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class landingPage {
	
	
	public static LocalDate date;
	public static WebDriver localdriver;
	
	public landingPage(WebDriver driver) {
		localdriver = driver;
	}
	
	@FindBy(how = How.XPATH, using = "//li[contains(text(),'Round Trip')]")
	WebElement roundTrip;
	
	@FindBy(how = How.ID, using = "fromCity")
	WebElement fromCity;
	
	@FindBy(how = How.ID, using = "toCity")
	WebElement toCity;
	
	
	//Return Date Element
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'RETURN')]")
	WebElement returnDate;
	
	//Return Date Value > 07 Days after Departure Date
	@FindBy(how = How.XPATH, using = "(//div[@class = 'DayPicker-Day']//div[@class = 'dateInnerCell'])[6]")
	WebElement returnFlightDate;
	
	
	//Search For Flights
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Search')]")
	WebElement searchNow;
	
	
	
	
	public void setUpTrip() throws InterruptedException {
		
		roundTrip.click();
		
		System.out.println("Departure City: "+fromCity.getAttribute("value").toString());
		System.out.println("Destination City: "+toCity.getAttribute("value").toString());
		returnDate.click();
		returnFlightDate.click();
		
	}
	
	public void searchFlights() {
		searchNow.click();
	}
	

}
