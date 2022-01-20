package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ObjectRepository.CommonMethods;



public class HomePage 
{

	WebDriver driver;
	CommonMethods commonmethods = new CommonMethods(driver);
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}

	/**
	 * 
	 * Description: Selects Language
	 */
	public void selectLang(String langoption)
	{	
		CommonMethods commonmethods = new CommonMethods(driver);
		commonmethods.isElementLoaded(By.xpath("//a[contains(text(),'"+ langoption +"')]"));
		driver.findElement(By.xpath("//a[contains(text(),'"+ langoption +"')]")).click();
	} 

	/**
	 * 
	 * Description: Selects Options from Homepage
	 * @throws InterruptedException 
	 */
	public void menu(String option) throws InterruptedException
	{
		CommonMethods commonmethods = new CommonMethods(driver);
		if(option=="housing")
		{	
			Thread.sleep(500);
			commonmethods.isElementLoaded(By.xpath("(//span[contains(text(),'"+ option +"')])[3]"));
			driver.findElement(By.xpath("(//span[contains(text(),'"+ option +"')])[3]")).click();
		}
		else if(option=="anyoption")
		{

		}
	}
}
