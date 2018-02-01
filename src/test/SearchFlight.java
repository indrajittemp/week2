package test;
import org.testng.annotations.Test;
import static org.testng.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utilities;
import pom.FlightResultsPage;
import pom.OrbitzHomePage;

@Test(groups= {"flights","smoke"}, testName="PuneToKolkataFlight")
public class SearchFlight extends BaseTest
{
	//@Test(groups= {"flights","smoke"}, testName="PuneToKolkataFlight")
	public void testPuneToKolkata()
	{
		OrbitzHomePage ohp=new OrbitzHomePage(driver);
		FlightResultsPage frp=new FlightResultsPage(driver);
		
		//verifying Orbitz homepage is displayed		
		ohp.verifyTitle(driver);
			
		//clicking on flights tab
		ohp.clickOnFlightsTab();
		
		//entering airport code for Pune in origin
		ohp.enterFlightOrigin("pnq");
		
		//selecting suggestion of Pune from origin drop down
		ohp.selectSuggestionPune(driver);
		
		//entering airport code for Kolkata in destination
		ohp.enterFlightDestination("ccu");
		
		//selecting suggestion of Kolkata from destination drop down
		ohp.selectSuggestionKolkata(driver);
		
		//entering tomorrow's date in departure date
		ohp.enterFlightDepartDate(Utilities.getFormattedDate("MM/dd/yyyy",1));
		
		//entering day after tomorrow's date in departure date
		ohp.enterFlightReturnDate(Utilities.getFormattedDate("MM/dd/yyyy",2));
		
		//clicking on search button
		ohp.clickOnSearch();
		
		//verifying Flight Results page is displayed
		frp.verifyTitle(driver);
		
		
		
	}
}
