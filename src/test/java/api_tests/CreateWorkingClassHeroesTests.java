package api_tests;

import java.sql.ResultSet;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.BaseClass;
import io.restassured.response.ValidatableResponse;
import pojo.CreateHeroPojo;
import utils.ApiHelper;
import utils.DbUtils;
import utils.ProjectUtils;

//User Story 1
public class CreateWorkingClassHeroesTests extends BaseClass{

	String url = "api/v1/hero";
	ProjectUtils projectUtils;
	DbUtils dbUtils;
	
	@BeforeClass
	public void setUp() {
		projectUtils = new ProjectUtils();
		dbUtils = new DbUtils();
	}
	
	@Test
	public void createWorkingClassHeroWithValidInput() {
		
		CreateHeroPojo obj = projectUtils.getHeroObject();
		
		ValidatableResponse res = ApiHelper.POST(url, obj);
		res.statusCode(200);	
		
		ResultSet rs = dbUtils.queryDb("SELECT * FROM testdb.working_class_heroes where natid='"+obj.getNatid()+"'");
		projectUtils.verifyDbRecordForWorkingClassHeroCreation(rs, obj);	
		
		createdWorkingClassHeroNatId = Integer.parseInt(obj.getNatid().split("natid-")[1]);
	}
	
	@Test
	public void createWorkingClassHeroWithDuplicateNatId() {
		
		CreateHeroPojo obj = projectUtils.getHeroObject();
		String natId = obj.getNatid();
		
		ValidatableResponse res = ApiHelper.POST(url, obj);
		res.statusCode(200);	
				
		ValidatableResponse duplicateRes = ApiHelper.POST(url, obj);
		duplicateRes.statusCode(400);	
		
		duplicateRes.body("errorMsg", Matchers.equalTo("Working Class Hero of natid: "+natId+" already exists!"));
	}
	
}
