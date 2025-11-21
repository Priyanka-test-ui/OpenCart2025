package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"sanity","master"})
	public void verify_login()
	{
		log.info("************* TC002_LoginTest STARTED ****************");
		
		try
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			hp.clickLogin();
			log.info("clicked to open Login page ..");
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));  // read values from config file
			lp.setPasword(p.getProperty("password"));
			lp.clickLoginbtn();
			
			MyAccountPage myacc = new MyAccountPage(driver);
			boolean targetpage_status = myacc.isMyaccountPageDisplay();
			
			//Assert.assertEquals(targetpage_status, true, "Login Failed"); // "Login Failed" - will be displayed in console if assertion fails
			Assert.assertTrue(targetpage_status);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	
		
		log.info("************* TC002_LoginTest COMPLETED ****************");
	}

}
