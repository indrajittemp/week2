package pom;

import java.awt.RenderingHints.Key;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.internal.annotations.ITest;

import generic.IConstants;
import generic.Utilities;

public class OrbitzHomePage implements IConstants
{
	private Actions action;	
	
	@FindBy(id="primary-header-flight")
	private WebElement flightsTab;
	
	@FindBy(id="flight-origin")
	private WebElement flightOriginTB;
	
	@FindBy(xpath="//div[contains(.,'Pune, India')]")
	private WebElement airportSuggestionPune;
	
	@FindBy(id="flight-destination")
	private WebElement flightDestinationTB;
	
	@FindBy(xpath="//div[contains(.,'Kolkata, India')]")
	private WebElement airportSuggestionKolkata;
	
	@FindBy(id="flight-departing")
	private WebElement flightDepartDateTB;
	
	@FindBy(id="flight-returning")
	private WebElement flightReturnDateTB;
	
	@FindBy(id="search-button")
	private WebElement searchButton;
		
	public OrbitzHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		action=new Actions(driver);
	}
	
	public void clickOnFlightsTab()
	{
		flightsTab.click();
	}
	
	public void enterFlightOrigin(String originCode)
	{
		flightOriginTB.click();
		flightOriginTB.sendKeys(originCode);
	}
	
	public void selectSuggestionPune(WebDriver driver)
	{
		new WebDriverWait(driver, EXPLICIT_TIMEOUT).until
			(ExpectedConditions.visibilityOf(airportSuggestionPune));
		action.sendKeys(Keys.ENTER).perform();
	}
	
	public void enterFlightDestination(String destinationCode)
	{
		flightDestinationTB.click();
		flightDestinationTB.sendKeys(destinationCode);
	}
	
	public void selectSuggestionKolkata(WebDriver driver)
	{
		new WebDriverWait(driver, EXPLICIT_TIMEOUT).until
			(ExpectedConditions.visibilityOf(airportSuggestionKolkata));
		action.sendKeys(Keys.ENTER).perform();
	}
	
	public void enterFlightDepartDate(String departDate)
	{
		flightDepartDateTB.sendKeys(departDate);
	}
	
	public void enterFlightReturnDate(String returnDate)
	{
		flightReturnDateTB.clear();
		flightReturnDateTB.sendKeys(returnDate);
	}
	
	public void clickOnSearch()
	{
		searchButton.click();
	}
	
	public void verifyTitle(WebDriver driver)
	{
		try{
			new WebDriverWait(driver, EXPLICIT_TIMEOUT).until
				(ExpectedConditions.titleIs("ORBITZ.com – Best Travel Deals"));
			Reporter.log("Homepage is displayed",true);			
		}
		catch(TimeoutException e)
		{
			Reporter.log("Homepage is not displayed",true);
			Assert.fail();
		}
	}
}
