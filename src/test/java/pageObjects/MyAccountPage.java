package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) 
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement myacc_heading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	public boolean isMyaccountPageDisplay()
	{
		try 
		{
			return myacc_heading.isDisplayed();  // will return true/false
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	public void clickLogoutbtn()
	{
		lnkLogout.click();
	}
	

}
