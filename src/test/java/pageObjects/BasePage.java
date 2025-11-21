package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	/*
	 *  For every page object classes we need to initiate driver in constructor.
	 *  To avoid this repetition we use one basepage where we keep a constructor to initiate driver
	 *  we can extends this basepage class to whichever pageclass we need 
	 */
	
	protected WebDriver driver;
	
	//constructor
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
