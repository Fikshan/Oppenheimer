package page;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.BaseClass;
import utils.GenericUtils;

public class BKDashboardPage
{
	@FindBy(id="tax_relief_btn")
	private WebElement  generateTaxReliefButton;
	
	@FindBy(xpath="//button[text()='Log out']")
	private WebElement logoutButton;
	
	public BKDashboardPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	
	public void clickLogout()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logoutButton.click();
	}
	
	public void clickGenerateTax()
	{
		generateTaxReliefButton.click();
	}
	
	public boolean verifyTaxFile()
	{
		
		ArrayList<String> data = GenericUtils.readCSV(BaseClass.DOWNLOAD_FOLDER_PATH+"\\tax_relief_file.txt");
		int numberOfRecords=data.size()-1;
		int footer=Integer.parseInt(data.get(numberOfRecords));
		return footer==numberOfRecords;
	}
	
	
}
