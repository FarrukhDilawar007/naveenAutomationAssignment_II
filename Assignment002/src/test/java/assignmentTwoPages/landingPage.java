package assignmentTwoPages;

import java.time.LocalDate;


import org.openqa.selenium.By;
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
	
	@FindBy(how = How.ID, using = "departure")
	WebElement departureDate;
	
	//@FindBy(how = How.ID, using = "return")
	
	@FindBy(how = How.XPATH, using = "//div[@class = 'fsw_inputBox dates reDates inactiveWidget  ']//span[@class = 'lbl_input latoBold appendBottom10']")
	WebElement returnDate;
	
	//Search For Flights
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Search')]")
	WebElement searchNow;
	
	
	
	
	public void setUpTrip() throws InterruptedException {
		
		roundTrip.click();
		
		System.out.println("Departure City: "+fromCity.getAttribute("value").toString());
		System.out.println("Destination City: "+toCity.getAttribute("value").toString());
		
		
	}
	
	public void setUpDates() {
		
		returnDate.click();
		System.out.println(date.getDayOfMonth());
		int returnDate = date.getDayOfMonth() + 7;
		//System.out.println("Return Date: " + returnDate +"/" + date.getMonthValue() +"/" + date.getYear()+"");		
		By selectReturnDate = By.xpath("//div[@class = 'dateInnerCell']//p[contains(text(),'"+ returnDate +"')]");
		localdriver.findElement(selectReturnDate).click();;
		
	}
	
	public void searchFlights() {
		searchNow.click();
	}
	

}
