package utils;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class ApiHelper {

	public static <T> ValidatableResponse POST(String url, T payLoad) {
		return RestAssured
		.given()
			.body(payLoad)
			.contentType(ContentType.JSON)
		.when()
			.log().all()
			.post(url)
		.then()
			.log().all();
	}
	
	public static ValidatableResponse GET(String url, Map<String,Integer> queryParams) {
		return RestAssured
		.given()
			.queryParams(queryParams)
		.when()
			.log().all()
			.get(url)
		.then()
			.log().all();
	}
	
	public static ValidatableResponse GET(String url) {
		return RestAssured
		.given()
		.when()
			.log().all()
			.get(url)
		.then()
			.log().all();
	}
}
