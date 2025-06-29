package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger;     //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
public static WebDriver driver;
public Logger logger; //Log4j2
public Properties p;

	@BeforeClass(groups= {"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException
	{
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		switch(browser.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox" : driver= new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name.."); return;
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); //reading url from properties file
		driver.manage().window().maximize();
	}

	@AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
	public void tearDown()
	{
	   driver.quit();	
	}

	public String randomString() {
	    RandomStringGenerator generator = new RandomStringGenerator.Builder()
	        .withinRange('a', 'z')
	        .build();	
	    return generator.generate(5);
	}


	public String randomNumber()
	{
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
			.withinRange('0','9')
			.build();
		return generator.generate(10);	
	}

	public String randomAlphanumeric()
	{
		RandomStringGenerator alphaGenerator = new RandomStringGenerator.Builder()
		        .withinRange('a', 'z')
		        .build();	
		RandomStringGenerator numericGenerator = new RandomStringGenerator.Builder()
				.withinRange('0','9')
				.build();
		return alphaGenerator.generate(3)+"_"+numericGenerator.generate(3);	    
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
	}
}
