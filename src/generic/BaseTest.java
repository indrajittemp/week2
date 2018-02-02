package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest implements IConstants
{
	static 
	{
		System.setProperty(CHROME_KEY,CHROME_DRIVER_PATH);
		System.setProperty(FIREFOX_KEY,FIREFOX_DRIVER_PATH);
	}
	
	public WebDriver driver;
		
	@Parameters("browser")
	@BeforeMethod(alwaysRun=true)
	public void openApplication(@Optional("chrom") String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver();
		else
			driver=new FirefoxDriver();
		
		
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);		
		try
		{
			driver.get("https://www.orbitz.com");
		}
		catch(TimeoutException e) {}
			
		driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeApplication(ITestResult res)
	{
		Utilities.getScreenshot(driver, res);

		driver.quit();
	}
	
}
