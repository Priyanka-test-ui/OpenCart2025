package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	//constructor
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
		
	}
	
	
	//elements

	@FindBy(xpath="//input[@id='input-firstname']") WebElement txt_firstname;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txt_lastname;
	@FindBy(xpath="//input[@id='input-email']") WebElement txt_email;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txt_tel;
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_password;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txt_confirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement chkbx_privacyPolicy;
	@FindBy(xpath="//input[@value='Continue']") WebElement btn_continue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement confirmationMsg;
	
	// Actions
	
	public void setFirstname(String f_name)
	{
		txt_firstname.sendKeys(f_name);
	}
	
	public void setLastname(String l_name)
	{
		txt_lastname.sendKeys(l_name);
	}
	
	
	public void setEmail(String email)
	{
		txt_email.sendKeys(email);
	}
	
	public void setTelephone(String teleNo)
	{
		txt_tel.sendKeys(teleNo);
	}
	
	
	public void setPassword(String pwd)
	{
		txt_password.sendKeys(pwd);
	}
	
	
	public void setConfirmPassword(String pwd)
	{
		txt_confirmPassword.sendKeys(pwd);
	}
	
	
	public void setPrivacyPolicy()
	{
		chkbx_privacyPolicy.click();
	}
	
	public void clickContinue()
	{
		btn_continue.click();
	}
	
	
	public String getConfirmationMSG()
	{
		try 
		{
			return(confirmationMsg.getText());
		}
		catch(Exception e)
		{
			return e.getMessage();  // returns the exception message if the registation has any issues
		}
	}
	
	

}
