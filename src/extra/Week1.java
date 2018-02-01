package extra;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Week1 {

	public static void main(String[] args) throws InterruptedException 
	{
		//Thread.sleep() has been used only for demonstration purpose, not required for execution
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				
		WebDriver driver=new ChromeDriver();		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver, 10);
		Actions act=new Actions(driver);
		
		driver.get("https://www.orbitz.com");
		
		
		//clicking on flights tab
		WebElement flights=driver.findElement(By.id("tab-flight-tab"));			
		wait.until(ExpectedConditions.visibilityOf(flights));
		flights.click();
		Thread.sleep(1000);
		
		//entering origin
		WebElement origin=driver.findElement(By.id("flight-origin"));			
		origin.click();
		Thread.sleep(1000);
		origin.sendKeys("pnq");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(.,'Pune, India')]"))));
		act.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		
		//entering destination
		WebElement dest=driver.findElement(By.id("flight-destination"));		
		dest.click();
		Thread.sleep(1000);
		dest.sendKeys("ccu");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(.,'Kolkata, India')]"))));
		act.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		
		//entering departure date
		WebElement departDate=driver.findElement(By.id("flight-departing"));
		LocalDate tomorrow=LocalDate.now().plusDays(1);
		Date td=Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String tomoDate=new SimpleDateFormat("MM/dd/yyyy").format(td);
		departDate.sendKeys(tomoDate);
		Thread.sleep(1000);
		
		//entering return date
		WebElement returnDate=driver.findElement(By.id("flight-returning"));
		LocalDate dayAfterTomorrow=LocalDate.now().plusDays(2);
		Date datd=Date.from(dayAfterTomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String dayAfterTomorrowDate=new SimpleDateFormat("MM/dd/yyyy").format(datd);
		returnDate.clear();
		Thread.sleep(1000);
		returnDate.sendKeys(dayAfterTomorrowDate);
		Thread.sleep(1000);
		
		getScreenshot(driver, "HomePage");		
		
		driver.findElement(By.id("search-button")).click();
		
		wait.until(ExpectedConditions.titleContains("Flights"));
		
		getScreenshot(driver, "ResultPage");
		
		Thread.sleep(5000);
		driver.quit();
	}
	
	
	static void getScreenshot(WebDriver driver, String pageName)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		File destFile=new File("./Screenshots/"+new SimpleDateFormat("dd-MM-yyyy-H-m-s").format(new Date()).toString()+pageName+".png");
		try
		{
			FileUtils.copyFile(srcFile, destFile);
		}
		catch(Exception e) {}
		
	}

}
