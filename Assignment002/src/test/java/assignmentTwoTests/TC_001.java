package assignmentTwoTests;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import assignmentTwoPages.flightResultPage;
import assignmentTwoPages.landingPage;

public class TC_001 {

	static WebDriver driver;
	static LocalDate date;
	
	
	@BeforeTest
	public void setUpTest() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println(date.getDayOfMonth());
	}
	@Test
	public void SearchFlights() throws InterruptedException {
		
		landingPage _landingPage = PageFactory.initElements(driver, landingPage.class);
		_landingPage.setUpTrip();
		_landingPage.setUpDates();
		_landingPage.searchFlights();
	}
	@Test
	public void SearchResults() {
		flightResultPage resultPage = PageFactory.initElements(driver, flightResultPage.class);
		resultPage.flightDetails();
		resultPage.nonStopFlights();
		
	}
	
	
	
	
	@AfterTest
	public void tearDownTest() {
		//driver.close();
		//driver.quit();
		System.out.println("Test Closed.");

	}
	
}
