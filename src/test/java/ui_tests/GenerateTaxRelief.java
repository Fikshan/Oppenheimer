package ui_tests;

import java.io.File;
import java.sql.ResultSet;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.BaseClass;
import page.BKDashboardPage;
import page.ClerkDashboardPage;
import page.LoginPage;
import utils.DbUtils;
import utils.ProjectUtils;

//User Story 3
public class GenerateTaxRelief extends BaseClass{

	ProjectUtils projectUtils;
	DbUtils dbUtils;
	
	@BeforeClass
	public void setUp() {
		projectUtils = new ProjectUtils();
		dbUtils = new DbUtils();
		ProjectUtils.deleteFilesInFolder(DOWNLOAD_FOLDER_PATH);
	}
	
	@Test
	public void testGenerateTaxRelief() throws Exception
	{

		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName("bk");
		loginPage.setPassword("bk");
		loginPage.clickSubmit();
		BKDashboardPage dashboardPage=new BKDashboardPage(driver);
		dashboardPage.clickGenerateTax();
		dashboardPage.clickLogout();
		boolean result = dashboardPage.verifyTaxFile();
		Assert.assertTrue(result,"Number of records are not matching with footer value");
		ResultSet rs = dbUtils.queryDb("SELECT count(*) as count FROM testdb.file");
		rs.next();
		int recordGenerationCount = rs.getInt("count");
		Assert.assertEquals(recordGenerationCount, 1,"DB Mismatch in file generation count");
		
	}
}
