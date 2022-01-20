package ObjectRepository;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.google.common.collect.Ordering;
import ObjectRepository.CommonMethods;


public class HousingPage 
{
	public WebDriver driver;
	CommonMethods commonmethods = new CommonMethods(driver);
	private static final Logger log=LogManager.getLogger(HousingPage.class.getName());
	public HousingPage(WebDriver driver) //this is coming from the testcase, so assigning this driver life to our driver
	{
		this.driver=driver; //assigning life
	}



	//region Xpaths
	By regiondropdown1 = By.xpath("//*[@id='areaAbb']"); 
	By sortnewest = By.xpath("//*[@title='show newest matches first']");
	By SearchInput = By.xpath("//*[@id=\"query\"]");
	By SearchButton = By.xpath("//*[@id=\"searchform\"]/div[1]/button");
	By sortlowtohigh = By.xpath("//*[@title='sort by price, lowest to highest']");
	By sorthightolow = By.xpath("//*[@title='sort by price, highest to lowest']");
	By sortmain= By.xpath("((//*[@class='dropdown-item mode sel'])[2])//a");
	By sortdropdownButton = By.xpath("//*[@id='searchform']/div[3]/div[2]/div/ul/li[2]/a/span");
	By TotalCount = By.xpath("//*[@id=\"searchform\"]/div[3]/div[3]/span[2]/span[3]/span[2]");
	//endregion XPaths

	/**
	 * 
	 * Description: Selects Region Dropdown
	 * @throws InterruptedException 
	 */
	public void selectRegion(String RegionOption) throws InterruptedException
	{
		CommonMethods commonmethods = new CommonMethods(driver);
		commonmethods.ClickAndSelectByOptionTag(regiondropdown1,RegionOption);
	} 

	/**
	 * 
	 * Description: Selects Type of sorting
	 */
	public void selectSort(String typeofsort)
	{
		CommonMethods commonmethods = new CommonMethods(driver);
		commonmethods.isElementLoaded(sortmain);
		String titlevalue=driver.findElement(sortmain).getAttribute("title").trim();
		//System.out.println(titlevalue);
		if(titlevalue.equalsIgnoreCase("show newest matches first"))
		{	
			commonmethods.isElementLoaded(sortnewest);
			driver.findElement(sortnewest).click();
			if(typeofsort=="PriceLowtoHigh")
			{
				commonmethods.isElementLoaded(sortlowtohigh);
				driver.findElement(sortlowtohigh).click();
			}
			else if(typeofsort=="PriceHighToLow")
			{
				commonmethods.isElementLoaded(sorthightolow);
				driver.findElement(sorthightolow).click();
			}
		}
		else if(titlevalue.equalsIgnoreCase("sort by price, lowest to highest"))
		{
	
			commonmethods.isElementLoaded(sortmain);
			driver.findElement(sortmain).click();
			if(typeofsort=="Newest")
			{
				commonmethods.isElementLoaded(sortnewest);
				driver.findElement(sortnewest).click();
			}
			else if(typeofsort=="PriceHighToLow")
			{
				commonmethods.isElementLoaded(sorthightolow);
				driver.findElement(sorthightolow).click();
			}
		}

		else if(titlevalue.equalsIgnoreCase("sort by price, highest to lowest"))
		{
			commonmethods.isElementLoaded(sorthightolow);
			driver.findElement(sorthightolow).click();
			if(typeofsort=="PriceLowToHigh")
			{
				commonmethods.isElementLoaded(sortlowtohigh);
				driver.findElement(sortlowtohigh).click();
			}
			else if(typeofsort=="Newest")
			{
				commonmethods.isElementLoaded(sortnewest);
				driver.findElement(sortnewest).click();
			}
		}
	}
	
	/**
	 * 
	 * Description: Compare Values if sorted
	 * @throws InterruptedException 
	 */
	public void CaptureAmounts(String LowToHighORHighToLow) throws InterruptedException
	{
			if(LowToHighORHighToLow == "LowToHigh")
			{
				int Total = Integer.parseInt(driver.findElement(TotalCount).getText().trim());
				List<Integer> list=new ArrayList<Integer>();
				
				for(int i = 1; i<=Total; i++) 
				{
					double Price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"search-results\"]/li["+ i +"]/a/div[1]/div/div[1]/img//parent::div//parent::div//parent::div//parent::a//span")).getText().trim().substring(1));
					int PriceIntValue = (int) Math.round(Price);
					System.out.println("Price at position " + i + " is " + PriceIntValue);
					list.add(PriceIntValue);
				}
			
				Boolean TrueOrFalse = Ordering.natural().isOrdered(list);
				if(TrueOrFalse)
				{
					System.out.println("Low To High is true. Prices are ordered in Low to High order Ascending order");
				}
				else 
				{
					System.out.println("Low To High is false. Prices are not ordered in Ascending/ Low to High order******FAILED******");
				}
			}
			
			
			else
			{
				int Total = Integer.parseInt(driver.findElement(TotalCount).getText().trim());
				List<Integer> list=new ArrayList<Integer>();
				for(int i = 1; i<=Total; i++) 
				{
					double Price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"search-results\"]/li["+ i +"]/a/div[1]/div/div[1]/img//parent::div//parent::div//parent::div//parent::a//span")).getText().trim().substring(1));
					int PriceIntValue = (int) Math.round(Price);
					System.out.println("Price at position " + i + " is " + PriceIntValue);
					list.add(PriceIntValue);
				}
			
				Boolean TrueOrFalse = Ordering.natural().reverse().isOrdered(list);
				
				if(TrueOrFalse)
				{
					System.out.println("High to Low is True. Prices are ordered in Descending/ High to Low order");
				}
				else {
					System.out.println("High to Low is False. Prices are not ordered in Descending/ High to Low order******FAILED****");
				}
			}		
	} 
	
	/*
	 * Description: Click on default sorting option
	 */
	public void clickSort() throws InterruptedException
	{
		CommonMethods commonmethods = new CommonMethods(driver);
		driver.findElement(sortmain).click();
	}
	
	/*
	 * Description: Search from Search Bar
	 */
	public void Search(String SearchTerm) throws InterruptedException
	{
		CommonMethods commonmethods = new CommonMethods(driver);
		log.info("Searching from SearchBar");
		commonmethods.isElementLoaded(SearchInput);
		driver.findElement(SearchInput).sendKeys(SearchTerm);
		log.info("Clicking Search Button");
		driver.findElement(SearchButton).click();
		
	}
	
	
}