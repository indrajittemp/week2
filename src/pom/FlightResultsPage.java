package pom;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import generic.IConstants;

public class FlightResultsPage implements IConstants
{
	public FlightResultsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void verifyTitle(WebDriver driver)
	{
		try{
			new WebDriverWait(driver, EXPLICIT_TIMEOUT).until(ExpectedConditions.titleContains("Flights | Orbitz"));
			Reporter.log("Flight Results page is displayed",true);
		}
		catch(TimeoutException e)
		{
			Reporter.log("Flight Results page is not displayed",true);
			Assert.fail();
		}
	}
}
