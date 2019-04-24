package assignmentTwoTests;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import assignmentTwoPages.flightResultPage;
import assignmentTwoPages.landingPage;
import utils.dataProvider;

public class TC_001 {

	static WebDriver driver;
	
	
	@BeforeTest
	public void setUpTest() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}
	@Test
	public void SearchFlights() throws InterruptedException {
		
		landingPage _landingPage = PageFactory.initElements(driver, landingPage.class);
		_landingPage.setUpTrip();
		_landingPage.searchFlights();
	}
	
	@Test
	public void SearchResults() {
		
		flightResultPage resultPage = PageFactory.initElements(driver, flightResultPage.class);
		resultPage.flightDetails();
		resultPage.flightDetailswithFilters();
		
	}
	
	
	@Test(dataProvider="getData", dataProviderClass = dataProvider.class)
	public void verifyFlightDetails(int p1, int p2) {
		
		flightResultPage resultPage = PageFactory.initElements(driver, flightResultPage.class);
		resultPage.compareFlightFares(p1, p2);
	}
	
	@AfterTest
	public void tearDownTest() {
		driver.close();
		driver.quit();
		System.out.println("Test Closed.");

	}
	
}
