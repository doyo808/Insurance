package insurance.payments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class Inquire {
	public static void main(String[] args) {
		
	}
	// 납입내역 조회기능(유저, 보고싶은 시작일, 종료일) 
	public static void inquire(String customer_id, Date start, Date end) {
		try (Connection conn = InsuranceDBConnector.getConnection();) // test DB임
		{
			String sql = "SELECT * FROM payments INNER JOIN contracts USING(customer_id) WHERE customer_id = " + customer_id;
			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
				try (ResultSet rs = pstmt.executeQuery();) {
					System.out.println("구분\t보험상품\t납입월분\t입금일자\t납입횟수\t대상보험료\t실입금액\t입금방법");
					int count = 1;
					while (rs.next()) {
						System.out.printf("%d\t%s\t%s\t%s\t%d\t%d\t%d\t%s", 
								count++,
								
								
								);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("서버를 찾을 수 없습니다");
		}
		
		
		
		
	}
}

// 제 테스트용 로컬 DB입니다
class InsuranceDBConnector {
	static String drivePath = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	static String user = "insurancectr";
	static String password = "insurance@1234";
	
	// static 블록은 이 클래스가 최초로 호출될 때 딱 한 번 실행된다
	static {
		try {
			Class.forName(drivePath);
			System.out.println("드라이버 로드 성공...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);		
	}

}