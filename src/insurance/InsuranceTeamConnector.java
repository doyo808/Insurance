package insurance;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class InsuranceTeamConnector {
	static String driverPath = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@192.168.0.86:1521:XE";
	static String user = "insurancectr";
	static String password = "insurance@1234";
	
	static {
		try {
			Class.forName(driverPath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	 
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	
}
