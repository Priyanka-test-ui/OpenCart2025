package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	
	public LoginPage(WebDriver driver)
	{
		super(driver); // call constructor from basepage(immediate parent) to initiate driver
	}
	
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_passowrd;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btn_login;
	
	
	public void setEmail(String email)
	{
		txt_email.sendKeys(email);
	}
	
	public void setPasword(String pwd)
	{
		txt_passowrd.sendKeys(pwd);
	}
	
	public void clickLoginbtn()
	{
		btn_login.click();
	}
	
	
	
	
	
	

}
