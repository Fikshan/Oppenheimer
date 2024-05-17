package core;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import utils.GenericUtils;

public class BaseClass {
	
	static ExtentReports extent;
	ExtentTest test;
	
	public static String env;
	public static String repoToken;
	public static int createdWorkingClassHeroNatId;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static String DOWNLOAD_FOLDER_PATH="D:\\API Assignment\\api_automation\\OppenheimerProjectAutomation\\test-output\\downloads";
	@BeforeSuite
	public void beforeSuite() {
		env = GenericUtils.getPropertyValue("global", "ENVIRONMENT");
		System.setProperty("env", env);
		
		String baseUrl = GenericUtils.getPropertyValue(env, "BASE_URL");		
		RestAssured.baseURI = baseUrl;
		
		repoToken = "Bearer "+GenericUtils.getPropertyValue(env, "REPO_TOKEN");
		
		extent = new ExtentReports();
		
		ExtentSparkReporter spark = new ExtentSparkReporter("reports/report.html");
		extent.attachReporter(spark);
		
	}

	
	@BeforeMethod
	public void beforeMethod(Method method) {
		test = extent.createTest(method.getName());
		
//		test.assignCategory(method.getDeclaringClass().getSimpleName());
		test.log(Status.INFO, "Test execution started");
		
	}
	
	@BeforeMethod
	public void precondition(ITestContext testContext)
	{
		String name=testContext.getName();
		test.assignCategory(name);
		
		if(name.equals("UITest")) {
			
			Map<String,Object> preferences= new HashMap<>();
	
			preferences.put("profile.default_content_settings.popups", 0);
	
			preferences.put("download.default_directory",DOWNLOAD_FOLDER_PATH);
			
			preferences.put("download.directory_upgrade",true);
	
			ChromeOptions options = new ChromeOptions();
	
			options.setExperimentalOption("prefs",preferences);
	
			driver = new ChromeDriver(options);
			driver.get("http://localhost:9997/login");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		}
	}
	
	@AfterMethod
	public void postCondition()
	{
		if(driver!=null) {
		driver.quit();
		}
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE){
		     test.log(Status.FAIL,"Classname:"+""+result.getTestClass());
		     test.log(Status.FAIL,MarkupHelper.createLabel("FAILED test case name is:"+""+result.getName(),ExtentColor.RED));
		     Reporter.log("Failed Report"+"",true);
		     test.log(Status.FAIL,MarkupHelper.createLabel("Testcase FAILED due to below issues:"+"",ExtentColor.RED));
		     test.fail(result.getThrowable());
		     test.log(Status.FAIL,"ParamsPassed:");
		     for(int i=0;i<result.getParameters().length;i++){
		    	 test.log(Status.FAIL,result.getParameters()[i].toString());
		     }
		  } 
		test.log(Status.INFO, "Test execution ended");
		extent.flush();
		
	}
}
