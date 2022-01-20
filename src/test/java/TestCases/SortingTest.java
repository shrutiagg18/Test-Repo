package TestCases;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ObjectRepository.CommonMethods;
import ObjectRepository.HomePage;
import ObjectRepository.HousingPage;
import resources.base;


public class SortingTest extends base {
	public WebDriver driver;
	private static final Logger log=LogManager.getLogger(SortingTest.class.getName());
	
	@BeforeMethod
	public void initialize() throws IOException {
		
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

	}

	@Test(description = "Validating Sorting Low to High & High to Low", priority = 0)
	public void validateSorting() throws InterruptedException
	{
		HomePage homepage=new HomePage(driver);
		HousingPage housingpage=new HousingPage(driver);
		log.info("Setting language to English");
		homepage.selectLang("english");
		log.info("Moving to page housing");
		homepage.menu("housing");
		housingpage.selectSort("PriceLowtoHigh");
		log.info("Sorting Low To High");
		housingpage.CaptureAmounts("LowToHigh");
		housingpage.selectSort("PriceHighToLow");
		log.info("Sorting High To Low");
		housingpage.CaptureAmounts("HighToLow");
		housingpage.selectSort("Newest");
	}
	
	@Test(description = "Validating default sorting option are present", priority = 1)
	public void validatedefaultsortoptions() throws InterruptedException
	{
		HomePage homepage=new HomePage(driver);
		HousingPage housingpage=new HousingPage(driver);
		CommonMethods commonmethods = new CommonMethods(driver);
		log.info("Selecting language english");
		homepage.selectLang("english");
		log.info("Moving to page housing");
		homepage.menu("housing");
		log.info("Validating Default Sorting option(Newest,Price Low to High, Price High to Low)");
		housingpage.clickSort();
		commonmethods.AssertIfPresentOnPageByTitle("show newest matches first");
		commonmethods.AssertIfPresentOnPageByTitle("sort by price, lowest to highest");
		commonmethods.AssertIfPresentOnPageByTitle("sort by price, highest to lowest");
	}
	
	@Test(description = "Validating sort option after search", priority = 1)
	public void validatesortoptionsAfterSearch() throws InterruptedException
	{
		HomePage homepage=new HomePage(driver);
		HousingPage housingpage=new HousingPage(driver);
		CommonMethods commonmethods = new CommonMethods(driver);
		log.info("Selecting language english");
		homepage.selectLang("english");
		log.info("Moving to page housing");
		homepage.menu("housing");
		housingpage.Search("M");
		housingpage.clickSort();
		//commonmethods.AssertIfPresentOnPageByTitle("chintu");
		log.info("Validating Sort Options after Search");
		commonmethods.AssertIfPresentOnPageByTitle("show newest matches first");
		commonmethods.AssertIfPresentOnPageByTitle("sort by price, lowest to highest");
		commonmethods.AssertIfPresentOnPageByTitle("sort by price, highest to lowest");
		commonmethods.AssertIfPresentOnPageByTitle("show upcoming open houses");
		commonmethods.AssertIfPresentOnPageByTitle("show most relevant matches first");
	}

	

	@AfterMethod
	public void teardown() throws InterruptedException 
	{
		driver.quit();
	} 
}
