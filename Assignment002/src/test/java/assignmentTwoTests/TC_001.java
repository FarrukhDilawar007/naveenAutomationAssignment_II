package assignmentTwoTests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import assignmentTwoPages.flightResultPage;
import assignmentTwoPages.landingPage;
import utils.dataProvider;

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
		//driver.get("https://www.makemytrip.com/flight/search?itinerary=DEL-BLR-24/04/2019_BLR-DEL-30/04/2019&tripType=R&paxType=A-1_C-0_I-0&intl=false&=&cabinClass=E");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
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
	
	
	//@Test(dataProvider="getData", dataProviderClass=dataProvider.class)
	@Test
	public void verifyFlightDetails() {
				
		flightResultPage resultPage = PageFactory.initElements(driver, flightResultPage.class);
		resultPage.planTrip(1, 3);
		
	}
	
	@AfterTest
	public void tearDownTest() {
		//driver.close();
		//driver.quit();
		System.out.println("Test Closed.");

	}
	
}
