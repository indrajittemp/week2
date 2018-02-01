package extra;
import java.io.File;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserDriver implements WebDriver
{
	private WebDriver driver;
	private final String browserName;
	private final int timeout=10;
	private final String chromeDriverPath="./drivers/chromedriver.exe";
	private final String firefoxDriverPath="./drivers/geckodriver.exe";
	
	public BrowserDriver(String browserName)
	{
		this.browserName=browserName;
		this.driver=createDriver(browserName);
	}
	
	private WebDriver createDriver(String browserName)
	{
		if(browserName.equalsIgnoreCase("FIREFOX"))
			return new FirefoxDriver();
		
		if(browserName.equalsIgnoreCase("CHROME"))
			return chromeDriver();
		
		throw new RuntimeException ("invalid browser name");
	}
	
	private WebDriver chromeDriver()
	{
		if(!new File(chromeDriverPath).exists())
			throw new RuntimeException(chromeDriverPath+" doesn't exist");
		
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		
		return new ChromeDriver();
	}
	
	private WebDriver firefoxeDriver()
	{
		if(!new File(firefoxDriverPath).exists())
			throw new RuntimeException(chromeDriverPath+" doesn't exist");
		
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		
		return new ChromeDriver();
	}

	public WebDriver driver()
	{
		return this.driver;
	}
	
	@Override
	public void close()
	{
		driver.close();
	}
	
	@Override
	public WebElement findElement(By arg0)
	{
		return driver.findElement(arg0);
	}
	
	@Override
	public List<WebElement> findElements(By arg0)
	{
		return driver.findElements(arg0);
	}
	
	@Override
	public void get(String arg0)
	{
		driver.get(arg0);
	}
	
	@Override
	public String getCurrentUrl()
	{
		return driver.getCurrentUrl();
	}
	
	@Override
	public String getPageSource()
	{
		return driver.getPageSource();
	}
	
	@Override
	public String getTitle()
	{		
		return driver.getTitle();
	}
	
	@Override
	public String getWindowHandle()
	{
		return driver.getWindowHandle();
	}
	
	@Override
	public Set<String> getWindowHandles()
	{		
		return driver.getWindowHandles();
	}
	
	@Override
	public Options manage()
	{
		return driver.manage();
	}
	
	@Override
	public Navigation navigate()
	{
		return driver.navigate();
	}
	
	@Override
	public void quit()
	{		
		driver.quit();
	}
	
	@Override
	public TargetLocator switchTo()
	{
		return driver.switchTo();
	}
	
	public WebElement findVisibleElement(By locator)
	{
		return new WebDriverWait(driver(), timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
