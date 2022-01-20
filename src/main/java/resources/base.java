package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class base {

	public WebDriver driver;
	public Properties prop;
	public WebDriver initializeDriver() throws IOException
	{
		String separator=System.getProperty("file.separator");
		prop=new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ separator+"src"+ separator+"main"+ separator+"java"+ separator+"resources"+ separator+"data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		System.out.println("Execution Done in " + browserName);

		if(browserName.equals("chrome"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			//DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			options.merge(cap);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

		}

		else if(browserName.equals("firefox"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			FirefoxOptions options = new FirefoxOptions();
			cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.merge(cap);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(options);
		}

		else if(browserName.equals("IE"))
		{
			WebDriverManager.iedriver().setup();   
			driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	/**
	 * 
	 * Description: Takes Screenshot of failed Test Cases
	 */
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException
	{
		String separator=System.getProperty("file.separator");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+ separator+"reports"+ separator+testCaseName+ ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}



}
