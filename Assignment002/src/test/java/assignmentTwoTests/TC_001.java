package assignmentTwoTests;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import assignmentTwoPages.flightResultPage;
import assignmentTwoPages.landingPage;
import utils.dataProvider;

public class TC_001 {

	static WebDriver driver;
	static flightResultPage resultPage;
	static landingPage _landingPage;
	
	@SuppressWarnings("deprecation")
	@BeforeTest
	public void setUpTest() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Initialzing PageFactory WebElements and methods of class landingPage
		_landingPage = PageFactory.initElements(driver, landingPage.class);
		
		//Initialzing PageFactory WebElements and methods of class FlightResultPage
		resultPage = PageFactory.initElements(driver, flightResultPage.class);
		
		
		
	}
	@Test
	public void SearchFlights() throws InterruptedException {
		
		//Setting up only the returnDate 07 days after the today's date
		//Departure City, Destination City and Today's date are by default correctly selected.
		_landingPage.setUpTrip();
		
		driver.manage().deleteAllCookies();
		
		//searching for the flights
		_landingPage.searchFlights();
		
		driver.manage().deleteAllCookies();
	}
	
	@Test
	public void SearchResults() {
		
		//Collecting the No. of Departure Flights and Return Flights
		resultPage.flightDetails();
		
		//Collecting the No. of Departure Flights and Return Flights with Stops filters
		resultPage.flightDetailswithFilters();
		
	}
	 
	//Applying 10 different options using dataProvider Class
	@Test(dataProvider="getData", dataProviderClass = dataProvider.class)
	public void comparingFlightDetailswithFooter(int p1, int p2) {
		//Comparing Price Listed in Body with Price Listed in Footer
		resultPage.compareFlightFares(p1, p2);
	}
	
	@AfterTest
	public void tearDownTest() {
		driver.close();
		driver.quit();
		System.out.println("Test Closed.");

	}
	
}
