package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	/*
	 * if your homepage (ChildClass) extends the basepage (ParentClass), 
	   the constructor of the child must ensure the parent's constructor is called.

	   If the Parent Class only has parameterized constructors, the Child Class must define a constructor and 
	   explicitly call the Parent's constructor using super(...).
	   
	 */
	
	//constructor
	
	public HomePage(WebDriver driver) {
		super(driver);  //here, super() is used to call the constructor of the immediate parent class.
		
	}
	
	
	//elements using Locators
	
	@FindBy(xpath="//span[normalize-space()='My Account']") 
	WebElement LnkMyaccount;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement LnkRegister;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") 
	WebElement lnklogin;
	
	//Actions
	
	public void clickMyaccount()
	{
		LnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		LnkRegister.click();
	}

	public void clickLogin()
	{
		lnklogin.click();
	}
	
	
	
	
	

}
