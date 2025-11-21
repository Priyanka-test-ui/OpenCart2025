package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;
// dataprovider class has  special type of annotation so we cannot extends.


public class TC003_LoginDataDrivenTest extends BaseClass{
	
	
	// dataProviderClass=DataProviders.class --> we must mention whenever dataProvider is in another pacakage /diff class. If it's in same class we dont need to mention.
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="datadriven") 
	public void verify_LoginDDT(String email, String pwd, String exp_res)
	{
		
		log.info("************* TC003_LoginDataDrivenTest STARTED ****************");
		
		try
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			hp.clickLogin();
			log.info("clicked to open Login page ..");
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);  // read values from xl file
			lp.setPasword(pwd);
			lp.clickLoginbtn();
			
			MyAccountPage myacc = new MyAccountPage(driver);
			boolean targetpage_status = myacc.isMyaccountPageDisplay();
			
			/*
			  Data is valid -- login success -- Test pass -- logout and proceed nxt data
			  Data is valid -- login fail -- Test fail
			  
			  Data is invalid -- login success -- Test fail -- logout and proceed nxt data
			  Data is invalid -- login fail -- Test pass
			 */
			
			
			if(exp_res.equalsIgnoreCase("valid")) //Data is valid
			{
				if(targetpage_status == true)  //login success if my account page header is displayed
				{
					myacc.clickLogoutbtn();
					Assert.assertTrue(true);
				}
				else  //login fail
				{
					Assert.assertTrue(false);
				}
			}
			
			else if(exp_res.equalsIgnoreCase("Invalid")) //Data is invalid
			{
				if(targetpage_status == true) //login success --> as data is invalid login should fail so --test fail
				{
					myacc.clickLogoutbtn();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true); //login fail - test pass
				}
			}
				
			
			
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		log.info("************* TC003_LoginDataDrivenTest COMPLETED ****************");	
	}

}
