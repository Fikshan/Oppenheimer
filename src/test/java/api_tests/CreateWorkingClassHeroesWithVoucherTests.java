package api_tests;

import java.sql.ResultSet;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.BaseClass;
import io.restassured.response.ValidatableResponse;
import pojo.CreateHeroWithVoucherPojo;
import utils.ApiHelper;
import utils.DbUtils;
import utils.ProjectUtils;

//User Story 4
public class CreateWorkingClassHeroesWithVoucherTests extends BaseClass{

	String url = "api/v1/hero/vouchers";
	ProjectUtils projectUtils;
	DbUtils dbUtils;
	
	@BeforeClass
	public void setUp() {
		projectUtils = new ProjectUtils();
		dbUtils = new DbUtils();
	}
	
	@Test
	public void createWorkingClassHeroWithVouchers() {
		
		CreateHeroWithVoucherPojo obj = projectUtils.getHeroWithVoucherObject(true);
		
		ValidatableResponse res = ApiHelper.POST(url, obj);
		res.statusCode(200);	
		
		ResultSet rs = dbUtils.queryDb("SELECT * FROM testdb.vouchers where working_class_hero_id = (select id from working_class_heroes where natid = '"+obj.getNatid()+"');");
		projectUtils.verifyDbRecordForWorkingClassHeroWithVouchers(rs, obj);	
	}
	
	@Test
	public void createWorkingClassHeroWithoutVouchers() {
		
		CreateHeroWithVoucherPojo obj = projectUtils.getHeroWithVoucherObject(false);
		
		ValidatableResponse res = ApiHelper.POST(url, obj);
		res.statusCode(400);	
		
		res.body("errorMsg", Matchers.equalTo("vouchers cannot be null or empty"));
	}
	
}
