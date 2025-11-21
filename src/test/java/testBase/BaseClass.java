package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger;  //log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/*
 * This baseclass contains all re-usable/common methods from all Testcases 
 * To achieve re-usability, avoid duplications
 * Logging - record all the events in the form of text.
 * Appenders - where to generate logs (Console/File)
   Loggers  - what type of logs generate (All < Trace < Debug < Info < Warn < Error < Fatal < Off)
   This baseclass is getting paramters from xml file and its common class for all TC's . so here-on we should only run TCs using xml.
 */


public class BaseClass 
{
	
	public static WebDriver driver;
	public Logger log;
	public Properties p;
	
	@BeforeClass(groups= {"sanity", "regression","master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		
		//Loading config.properties file
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		// FileReader file = new FileReader("./src//test//resources//config.properties"); //--> ./ will fecth proj loc
		p= new Properties();
		p.load(file);
		
		
		log =LogManager.getLogger(this.getClass()); // this will dynamically get class name of current class and get logger for that particular class and store it in log var.
		
		// remote env --> selenium grid(standalone) usage
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching OS found");
				return; // break execution
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome" :cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : cap.setBrowserName("firefox"); break;
			default : System.out.println("Invalid browser name .."); return;
			}
			
			//launch driver
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			
			/*
			log.info("Attempting to connect to Grid Hub at http://localhost:4444/wd/hub with capabilities: " + cap.toString());

		    try {
		        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		        log.info("Successfully connected to RemoteWebDriver.");
		    } catch (Exception e) {
		        log.error("Failed to connect to RemoteWebDriver. Error: " + e.getMessage());
		        throw e; // Re-throw to ensure TestNG fails with the error.
		    } */
			
			
		}
		
		
		// local env - normal local system
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome"   : driver = new ChromeDriver(); break;
			case "edge"     : driver = new EdgeDriver(); break;
			case "firefox"   : driver = new FirefoxDriver(); break;
			default         : System.out.println("Invalid browser name"); return;
			}
		}
		
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); // reading url from properties file
		driver.manage().window().maximize();
		
	}
	
	
	@AfterClass(groups= {"sanity", "regression","master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public String setRandomString()
	{
		//RandomStringUtils - predefined class from commons-lang3 library 
		String generated_randomString = RandomStringUtils.randomAlphabetic(5); 
		return generated_randomString;
	}
	
	 
	
	public String setRandomNum()
	{
		
		String generated_randomNumber = RandomStringUtils.randomNumeric(9); 
		return generated_randomNumber;
	}
	
	public String setRandomAlphaNum()
	{
		
		String generated_String = RandomStringUtils.randomAlphabetic(5);
		String generated_Number = RandomStringUtils.randomNumeric(9); 
		return (generated_String + "#" + generated_Number) ;
	}
	
	public String captureScreen(String tname) throws IOException
	{
		TakesScreenshot ss = (TakesScreenshot) driver;
		File sourcefile = ss.getScreenshotAs(OutputType.FILE);
		
		/*
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyyMMddhhmmss");
		Date dt = new Date();
		String timestamp = timeformat.format(dt);
		*/
		
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String targetfile_path = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";
		
		File targetfile = new File(targetfile_path);
		
		sourcefile.renameTo(targetfile);
		return targetfile_path; // ss path in proj
	}
	

}
