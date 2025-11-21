package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups={"regression","master"})
	public void verify_account_registeration()
	{
		try 
		{
		// click "register" from homepage
		log.info("*********** TC001_AccountRegistrationTest STARTED ***********");
		
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		log.info("Clicked my account");
		
		hp.clickRegister();
		log.info("Clicked Register");
		
		
		// enter details in register page
		log.info("Entering customer details....");
		AccountRegistrationPage rp = new AccountRegistrationPage(driver);
		rp.setFirstname(setRandomString().toUpperCase());
		rp.setLastname(setRandomString().toUpperCase());
		rp.setEmail(setRandomString() + "@gmail.com");
		rp.setTelephone(setRandomNum());
		
		// if we call same setRandomNum() method multiple time it will generate diff values each time cuz of .randomNumeric(9) . so use var
		String random_passwrd = setRandomAlphaNum();
		
		rp.setPassword(random_passwrd);
		rp.setConfirmPassword(random_passwrd);
		rp.setPrivacyPolicy();
		rp.clickContinue();
		
		log.info("Validating expected message....");
		
		//Assert.assertEquals(rp.getConfirmationMSG(), "Your Account Has Been Created!");
		
		if(rp.getConfirmationMSG().equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			log.error("Test failed..");
			//log.debug("Debug logs.."); // only can be used when we mention root level=debug in log4j2.xml
			Assert.assertTrue(false);
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		log.info("*********** TC001_AccountRegistrationTest COMPLETED ***********");
		
	}
	
	
	
	
	/*
	 * If we use try catch and use assertions in try it will not execute catch as we are using hard assertions
	 * Hard assertion won't execute the statements after it
	 * so try use it inside if block
	 */
	
	
	
	
	
	
	
	
	
	

}
