package utils;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.Assert;

import net.datafaker.Faker;
import pojo.CreateHeroPojo;
import pojo.CreateHeroWithVoucherPojo;
import pojo.VoucherPojo;

public class ProjectUtils {

	HashMap<String, String> nameGender;
	HashMap<Integer, HashMap<String, String>> nameGenderMap;
	
	public ProjectUtils() {
		dataMapGenerator();
	}
	
	public void dataMapGenerator() {
		nameGender = new HashMap<String, String>();
		nameGenderMap = new HashMap<Integer, HashMap<String, String>>();
		
		nameGender.put("Tom", "MALE");
		nameGenderMap.put(0, nameGender);		
		
		nameGender.put("Michael", "MALE");
		nameGenderMap.put(1, nameGender);		
		
		nameGender.put("Lindsay", "FEMALE");
		nameGenderMap.put(2, nameGender);
		
		nameGender.put("Byron", "MALE");
		nameGenderMap.put(3, nameGender);
		
		nameGender.put("Rachel", "FEMALE");
		nameGenderMap.put(4, nameGender);
		
		nameGender.put("Janet", "FEMALE");
		nameGenderMap.put(5, nameGender);
		
		nameGender.put("Emma", "FEMALE");
		nameGenderMap.put(6, nameGender);
		
		nameGender.put("Charles", "MALE");
		nameGenderMap.put(7, nameGender);
		
		nameGender.put("EVE", "FEMALE");
		nameGenderMap.put(8, nameGender);
		
		nameGender.put("George", "MALE");
		nameGenderMap.put(9, nameGender);		
	}
	
	public CreateHeroPojo getHeroObject() {
		Faker faker = new Faker();
		String natId = "natid-"+faker.number().numberBetween(0, 9999999);		
		String bDay = faker.date().birthday(1, 50, "YYYY-MM-dd'T'hh:mm:ss");
		float salary = (float) faker.number().randomDouble(2, 10000, 15000);
		float taxPaid = (float) (salary*.25);
		int browniePoints = faker.number().numberBetween(0, 10);
		
		int nameRandomNumber = faker.number().numberBetween(0, 10);
		Object[] keyArr = nameGenderMap.get(nameRandomNumber).keySet().toArray();
		
		String name = keyArr[nameRandomNumber].toString();
		String gender = nameGenderMap.get(nameRandomNumber).get(name);
		
		CreateHeroPojo obj = new CreateHeroPojo();
		obj.setNatid(natId);
		obj.setName(name);
		obj.setGender(gender);
		obj.setBirthDate(bDay);
		obj.setSalary(salary);
		obj.setTaxPaid(taxPaid);
		obj.setBrowniePoints(browniePoints);
		
		return obj;
	}
	
	public CreateHeroWithVoucherPojo getHeroWithVoucherObject(boolean withVoucher) {
		Faker faker = new Faker();
		String natId = "natid-"+faker.number().numberBetween(0, 9999999);		
		String bDay = faker.date().birthday(1, 50, "YYYY-MM-dd'T'hh:mm:ss");
		float salary = (float) faker.number().randomDouble(2, 10000, 15000);
		float taxPaid = (float) (salary*.25);
		int browniePoints = faker.number().numberBetween(0, 10);
		
		int nameRandomNumber = faker.number().numberBetween(0, 10);
		Object[] keyArr = nameGenderMap.get(nameRandomNumber).keySet().toArray();
		
		String name = keyArr[nameRandomNumber].toString();
		String gender = nameGenderMap.get(nameRandomNumber).get(name);		
		
		ArrayList<VoucherPojo> vouchers = new ArrayList<VoucherPojo>();
		if(withVoucher) {
			VoucherPojo voucherObj = new VoucherPojo();
			voucherObj.setVoucherName("VOUCHER "+faker.number().numberBetween(0, 99));
			voucherObj.setVoucherType("TRAVEL");
						
			vouchers.add(voucherObj);
		}
		
		CreateHeroWithVoucherPojo obj = new CreateHeroWithVoucherPojo();
		obj.setNatid(natId);
		obj.setName(name);
		obj.setGender(gender);
		obj.setBirthDate(bDay);
		obj.setSalary(salary);
		obj.setTaxPaid(taxPaid);
		obj.setBrowniePoints(browniePoints);
		obj.setVouchers(vouchers);		
		
		return obj;
	}
	
	public void verifyDbRecordForWorkingClassHeroCreation(ResultSet rs, CreateHeroPojo obj) {
		try {
			rs.next();
			String dbNatIdVal = rs.getString("natid");
			String dbNameVal = rs.getString("name");
			Assert.assertEquals(dbNatIdVal, obj.getNatid());
			Assert.assertEquals(dbNameVal, obj.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verifyDbRecordForWorkingClassHeroWithVouchers(ResultSet rs, CreateHeroWithVoucherPojo obj) {
		try {
			rs.next();
			String dbVoucherName = rs.getString("name");
			String dbVoucherType = rs.getString("voucher_type");
			Assert.assertEquals(dbVoucherName, obj.getVouchers().get(0).getVoucherName());
			Assert.assertEquals(dbVoucherType, obj.getVouchers().get(0).getVoucherType());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteFilesInFolder(String folderPath) {
        File folder = new File(folderPath);        
        File[] files = folder.listFiles();
            for (File file : files) 
            {
                file.delete();
            }
        
    }
}
