package ui_tests;

import java.io.File;
import java.sql.ResultSet;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.BaseClass;
import page.ClerkDashboardPage;
import page.LoginPage;
import utils.DbUtils;
import utils.ProjectUtils;

//User Story 2
public class UploadCSVCreateHero extends BaseClass{

	ProjectUtils projectUtils;
	DbUtils dbUtils;
	
	@BeforeClass
	public void setUp() {
		projectUtils = new ProjectUtils();
		dbUtils = new DbUtils();
	}
	
	@Test
	public void testUploadCSVCreateHero() throws Exception
	{
		
		ResultSet rs = dbUtils.queryDb("SELECT count(*) as count FROM testdb.working_class_heroes");
		rs.next();
		int bc = rs.getInt("count");
		System.out.println("Before count:"+bc);
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName("clerk");
		loginPage.setPassword("clerk");
		loginPage.clickSubmit();
		ClerkDashboardPage dashBoard=new ClerkDashboardPage(driver);
		dashBoard.clickAddHero();
		dashBoard.selectUploadCSV();
		String path = new File("src/test/resources/testdata/hero1.csv").getAbsolutePath();
		dashBoard.selectCSVFile(path);
		dashBoard.clickCreate();
		Thread.sleep(1000);
		boolean UI_status = dashBoard.verifyNotification("Created Successfully!");
		dashBoard.clickLogout();
		Thread.sleep(1000);
		rs = dbUtils.queryDb("SELECT count(*) as count FROM testdb.working_class_heroes");
		rs.next();
		int ac =rs.getInt("count");
		System.out.println("After count:"+ac);
		boolean DB_Status=bc<ac;
		
		Assert.assertTrue(UI_status && DB_Status);
		
	}
	
	@Test
	public void testUploadErrorCSVCreateHero() throws Exception
	{
		
		ResultSet rs = dbUtils.queryDb("SELECT count(*) as count FROM testdb.working_class_heroes");
		rs.next();
		int bc = rs.getInt("count");
		System.out.println("Before count:"+bc);
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName("clerk");
		loginPage.setPassword("clerk");
		loginPage.clickSubmit();
		ClerkDashboardPage dashBoard=new ClerkDashboardPage(driver);
		dashBoard.clickAddHero();
		dashBoard.selectUploadCSV();
		String path = new File("src/test/resources/testdata/hero2.csv").getAbsolutePath();
		dashBoard.selectCSVFile(path);
		dashBoard.clickCreate();
		Thread.sleep(1000);
		boolean UI_status = dashBoard.verifyNotification("Unable to create hero!");
		dashBoard.clickLogout();
		Thread.sleep(1000);
		rs = dbUtils.queryDb("SELECT count(*) as count FROM testdb.working_class_heroes");
		rs.next();
		int ac =rs.getInt("count");
		System.out.println("After count:"+ac);
		boolean DB_Status=bc==ac;
		
		Assert.assertTrue(UI_status && DB_Status);
		
	}
}
