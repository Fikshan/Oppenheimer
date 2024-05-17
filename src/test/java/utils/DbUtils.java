package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {

	private String DB_URL;
	private String USER;
	private String PASS;

	public ResultSet queryDb(String QUERY) {
		DB_URL = GenericUtils.getPropertyValue(System.getProperty("env"), "DB_URL");
		USER = GenericUtils.getPropertyValue(System.getProperty("env"), "USER");
		PASS = GenericUtils.getPropertyValue(System.getProperty("env"), "PASS");
		ResultSet rs = null;

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(QUERY);
			//System.err.println(QUERY);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
}
