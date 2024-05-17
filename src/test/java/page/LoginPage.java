package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(id="username-in")
	private WebElement userName;
	
	@FindBy(id="password-in")
	private WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement submit;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public void setUserName(String un)
	{
		userName.sendKeys(un);
	}
	public void setPassword(String pw)
	{
		password.sendKeys(pw);
	}
	
	public void clickSubmit() {
		submit.click();
	}
}
