package generic;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public abstract class Utilities
{
	public static void getScreenshot(WebDriver driver, ITestResult res)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		File destFile;
		if(res.isSuccess())
			destFile=new File("./Screenshots/passed/"+new SimpleDateFormat("dd-MM-yyyy-H-m-s-").format(new Date()).toString()+res.getTestName()+".png");
		else
			destFile=new File("./Screenshots/failed/"+new SimpleDateFormat("dd-MM-yyyy-H-m-s-").format(new Date()).toString()+res.getTestName()+".png");
		
		try
		{
			FileUtils.copyFile(srcFile, destFile);
		}
		catch(Exception e) {}
	}
	
	public static String getFormattedDate(String format, int plusDays)
	{
		LocalDate ld=LocalDate.now().plusDays(plusDays);
		Date d=Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return new SimpleDateFormat(format).format(d);
	}

	
}
