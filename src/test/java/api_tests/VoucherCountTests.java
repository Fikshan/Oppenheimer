package api_tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.BaseClass;
import io.restassured.response.ValidatableResponse;
import utils.ApiHelper;
import utils.DbUtils;
import utils.ProjectUtils;

//User Story 6
public class VoucherCountTests extends BaseClass{

	String url = "api/v1/voucher/by-person-and-type";
	ProjectUtils projectUtils;
	DbUtils dbUtils;
	
	@BeforeClass
	public void setUp() {
		projectUtils = new ProjectUtils();
		dbUtils = new DbUtils();
	}
	
	@Test
	public void checkVoucherCountByPersonType() {
		
		ValidatableResponse res = ApiHelper.GET(url);
		res.statusCode(200);
		
	}
	
}
