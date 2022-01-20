package ObjectRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;




public class CommonMethods {

	WebDriver driver;
	private static final Logger log=LogManager.getLogger(CommonMethods.class.getName());
	public CommonMethods(WebDriver driver)
	{
		this.driver=driver;
	}

	//region Common Methods
	/**
	 * Description: Is Element Loaded
	 */
	public void isElementLoaded(By elementToBeLoaded) 
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementToBeLoaded));
	}

	/**
	 * *Description: Click and Select Contains Text by Li, With Fixed turein box path

	 */
	public void ClickAndSelectByOptionTag(By Path, String Option) throws InterruptedException
	{
		Thread.sleep(500);
		isElementLoaded(Path);
		driver.findElement(Path).click();
		Thread.sleep(1000);
		isElementLoaded(By.xpath("//option[contains(text(),'"+ Option +"')]"));
		JavaScriptClick(By.xpath("//option[contains(text(),'"+ Option +"')]"));
		driver.findElement(By.xpath("//option[contains(text(),'"+ Option +"')]")).click();
		//driver.findElement(By.xpath("//option[contains(text(),'"+ Option +"')]")).click();

	}
	
	/**
	 * Description: to click Element
	 */
	public void JavaScriptClick(By Path)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement ele = driver.findElement(Path);
		jse.executeScript("arguments[0].click()", ele);
	}
	
	/**
	 * Description: Assert if element is present on page by title
	 */
	public void AssertIfPresentOnPageByTitle(String TextToCheck)
	{
		
		try
		{
			String textActual=driver.findElement(By.xpath("//*[@title='"+ TextToCheck +"']")).getAttribute("Title").trim();
			System.out.println(textActual);
			if (textActual.equalsIgnoreCase(TextToCheck))
			{
				log.info(TextToCheck + " is present on the screen ****PASSED****");
			}	
			
			Assert.assertEquals(textActual,TextToCheck);
			
		}
		catch(NoSuchElementException | AssertionError e)
		{
			log.error(TextToCheck + " is not present *******FAILED*******");
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
		}
		
	}
	 
}
	//endregion

