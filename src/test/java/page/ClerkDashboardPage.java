package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClerkDashboardPage
{
	@FindBy(xpath="//button[text()='Add a hero']")
	private WebElement addAhero;
	
	@FindBy(xpath="//a[text()='Add']")
	private WebElement addOption;
	
	@FindBy(xpath="//a[text()='Upload a csv file']")
	private WebElement uploadCSVOption;
	
	@FindBy(id="upload-csv-file")
	private WebElement uploadCSVButton;
	
	@FindBy(xpath="//button[text()='Create']")
	private WebElement createButton;
	
	@FindBy(css="#notification-block>div>h3")
	private WebElement notification;
	
	@FindBy(xpath="//button[text()='Log out']")
	private WebElement logoutButton;
	
	public ClerkDashboardPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public void clickAddHero()
	{
		addAhero.click();
	}
	
	public void selectAdd()
	{
		addOption.click();
	}
	
	public void selectUploadCSV()
	{
		uploadCSVOption.click();
	}
	
	public void selectCSVFile(String path)
	{
		uploadCSVButton.sendKeys(path);
	}
	
	public void clickCreate() {
		createButton.click();
	}
	
	public boolean verifyNotification(String expectedMsg)
	{
		String actualMsg = notification.getText();
		System.out.println(actualMsg);	
		return 	actualMsg.equals(expectedMsg);
	}
	
	public void clickLogout()
	{
		logoutButton.click();
	}
}
