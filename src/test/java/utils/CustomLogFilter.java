package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class CustomLogFilter implements Filter{

	ExtentTest test;
	public CustomLogFilter(ExtentTest test) {
		this.test = test;
	}
	
	Logger log = LoggerFactory.getLogger(CustomLogFilter.class);
	
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		// TODO Auto-generated method stub
		
		Response response = ctx.next(requestSpec, responseSpec);
		
		
		return null;
	}

	
}
