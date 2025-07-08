package insurance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryTest {
	public static void main(String[] args) {
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			
			String sql = "INSERT INTO DEPARTMENTS"
					+ "(department_id, department_name) "
					+ "VALUES(?, ?)";
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setInt(1, 1);
				pstmt.setString(2, "IT");
				
				int row = pstmt.executeUpdate();
				System.out.printf("%d행이 업데이트되었습니다.\n", row);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
