package api_tests;

import java.util.HashMap;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.BaseClass;
import io.restassured.response.ValidatableResponse;
import pojo.HeroOweMoneyResponseRootPojo;
import utils.ApiHelper;
import utils.DbUtils;
import utils.ProjectUtils;

//User Story 5
public class WorkingClassHeroOwesMoneyTests extends BaseClass{

	String url = "api/v1/hero/owe-money";
	ProjectUtils projectUtils;
	DbUtils dbUtils;
	
	@BeforeClass
	public void setUp() {
		projectUtils = new ProjectUtils();
		dbUtils = new DbUtils();
	}
	
	@Test
	public void checkWorkingClassHeroOwesMoney() {
		
		if(createdWorkingClassHeroNatId == 0) {
			createdWorkingClassHeroNatId = 1;
		}
		
		HashMap<String,Integer> queryParamsMap = new HashMap<String,Integer>();
		queryParamsMap.put("natid", createdWorkingClassHeroNatId);	
		
		ValidatableResponse res = ApiHelper.GET(url, queryParamsMap);
		res.statusCode(200);
		
		HeroOweMoneyResponseRootPojo respObj = res.extract().as(HeroOweMoneyResponseRootPojo.class);
		MatcherAssert.assertThat(respObj.getMessage().getData(), Matchers.equalTo("natid-"+createdWorkingClassHeroNatId));
		MatcherAssert.assertThat(respObj.getMessage().getStatus(), Matchers.equalTo("OWE"));
		
	}
	
}
