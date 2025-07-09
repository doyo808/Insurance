package insurance.claims.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Claim_DAO {
		
//		// 이어서 만들예정
//		// 고객이 입력한 정보를 토대로 다른 테이블과 연결해 claims 테이블에 값이 들어가야함 (이클립스->DB)	

		// 고객이 청구대상을 '다른사람'으로 선택하면 그 대상이 가입한 보험계약 정보를 가지고 와야함
		// 청구테이블(계약ID) JOIN 계약테이블(고객ID) JOIN 고객테이블(이름, 주민번호, 전화번호)
		// 피보험자테이블로 다시 수정하기 
		
		
	
		// 고객이 청구대상을 '본인'으로 선택하면 로그인 기록을 가지고와서 그 사람의 보험계약 정보를 가지고 와야함
		// 고객이 하나 이상의 계약이 꼭 있다는 가정
		// 가져올 정보: 고객이름, 고객주민번호, 고객 연락처, 상품명, 계약명
		// LOG_TABLE(고객ID) JOIN 고객테이블(고객ID) JOIN 계약(고객ID/상품ID) JOIN 상품(상품명)
		public static ClaimModel getCustomerInfo(String login_id, Connection conn) throws SQLException {
			String query = "SELECT cs.customer_name, cs.personal_id, cs.phone_number, pr.product_name "
					+ "FROM normal_logs "
					+ "INNER JOIN customers cs USING(customer_id)"
					+ "INNER JOIN contracts ct USING(customer_id) "
					+ "INNER JOIN products pr USING(product_id) "
					+ "WHERE login_id = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, login_id);
				
				try (ResultSet rs = pstmt.executeQuery()) { 
					return rs.next() ? new ClaimModel(rs) : null;
				}
			}
		}
	
	
		// 고객의 로그인ID를 입력하고 '조회'누르면 고객이 지금까지 접수했던 모든 청구 내역 표시 (DB -> 이클립스)
		// 로그인ID로 고객의 청구내역을 청구날짜 내림차순으로 조회
		public static ClaimModel getClaims(String login_id, Connection conn) throws SQLException {
			String query = "SELECT * FROM CLAIMS "
					+ "INNER JOIN CONTRACTS USING(contract_id) "
					+ "INNER JOIN CUSTOMERS USING(customer_id) "
					+ "WHERE login_id = ? ORDER BY claim_date DESC";
			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, login_id);
				
				try (ResultSet rs = pstmt.executeQuery()) {
					return rs.next() ? new ClaimModel(rs) : null;
				}
			} 
		}
		
//		// 모든 고객 조회
//		public static List<CustomerModel> getAllCustomers(Connection conn) throws SQLException {
//			String query = "SELECT * FROM CUSTOMERS";
//			
//			try (Statement stmt = conn.prepareStatement(query);
//				 ResultSet rs = stmt.executeQuery(query)){
//
//				List<CustomerModel> CustomerModels = new ArrayList<>();
//				
//				while (rs.next()) {
//					CustomerModels.add(new CustomerModel(rs));
//				}
//				return CustomerModels;
//			}		
//		}
//		
//		// 수신동의여부로 검색하기
//		public static List<CustomerModel> findCustomerByAgree_yn(String yn, Connection conn) throws SQLException {
//			String query = "SELECT * FROM CUSTOMERS WHERE agree_yn = ?";
//			
//			try (PreparedStatement pstmt = conn.prepareStatement(query)){
//				
//				pstmt.setString(1, yn);
//				
//				try (ResultSet rs = pstmt.executeQuery()) {
//					List<CustomerModel> CustomerModels = new ArrayList<>();
//					
//					while (rs.next()) {
//						CustomerModels.add(new CustomerModel(rs));
//					}
//					return CustomerModels;	
//				}			
//			}		
//		}
//		
//		// 새 고객을 추가하는 메서드 (CREATE) * 주민등록번호, 로그인 아이디 유니크 주의해주세요
//		public static int addCustomer(CustomerModel c, Connection conn) {
//
//			String query = "INSERT INTO CUSTOMERS(customer_id, customer_name"
//					+ ", personal_id, login_id, password_hash, password_salt) "
//					+ "VALUES(SEQ_CUSTOMER_ID.nextval, ?, ?, ?, ?, ?)";
//			
//			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//				pstmt.setString(1, c.getCustomer_name());
//				pstmt.setString(2, c.getPersonal_id());
//				pstmt.setString(3, c.getLogin_id());
//				pstmt.setString(4, c.getPassword_hash());
//				pstmt.setString(5, c.getPassword_salt());
//				
//				return pstmt.executeUpdate();
//				
//			} catch (SQLException e) {
//				e.getErrorCode();
//				e.getMessage();
//				e.printStackTrace();
//				return -1;
//			}
//		}
//		
//		// 해당 고객을 삭제하고 삭제 여부를 반환하는 메서드 (DELETE) - 현재는 오류 발생 DB수정해야함
//		public static boolean deleteCustomer(int customer_id, Connection conn) throws SQLException {
//			String query = "DELETE FROM CUSTOMERS WHERE customer_id = ?";
//			
//			try (PreparedStatement pstmt = conn.prepareStatement(query)){
//				pstmt.setInt(1, customer_id);
//				return pstmt.executeUpdate() == 1;
//			}
//		}
//		
//		
//		
//	}

}
