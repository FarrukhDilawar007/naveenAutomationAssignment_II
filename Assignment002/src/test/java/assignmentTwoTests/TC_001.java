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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
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
		//driver.get("https://www.makemytrip.com/");
		driver.get("https://www.makemytrip.com/flight/search?tripType=R&itinerary=DEL-BLR-26/04/2019_BLR-DEL-01/05/2019&paxType=A-1_C-0_I-0&cabinClass=E&sTime=1556110356664&forwardFlowRequired=true&pot=undefined&cmp=undefined&intl=undefined");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//https://stackoverflow.com/questions/11908249/debugging-element-is-not-clickable-at-point-error
		
	}
	//@Test
	public void SearchFlights() throws InterruptedException {
		
		landingPage _landingPage = PageFactory.initElements(driver, landingPage.class);
		_landingPage.setUpTrip();
		_landingPage.setUpDates();
		_landingPage.searchFlights();
	}
	//@Test
	public void SearchResults() {
		
		flightResultPage resultPage = PageFactory.initElements(driver, flightResultPage.class);
		resultPage.flightDetails();
		resultPage.nonStopFlights();
		
	}
	
	
	@Test(dataProvider="getData")
	public void verifyFlightDetails(int p1, int p2) throws InterruptedException {
		
		flightResultPage resultPage = PageFactory.initElements(driver, flightResultPage.class);
		resultPage.planTrip(p1, p2);
		
		
	}
	
	@AfterTest
	public void tearDownTest() {
		//driver.close();
		//driver.quit();
		System.out.println("Test Closed.");

	}
	
	@DataProvider
    public Object[][] getData() {
        
		
		Object[][] param = new Object[4][2];
		
		param[0][0] = 4;
		param[0][1] = 7;
		
		param[1][0] = 3;
		param[1][1] = 8;
		
		param[2][0] = 5;
		param[2][1] = 8;
		
		param[3][0] = 3;
		param[3][1] = 9;
		
		return param;
		
				
    }
	
}
