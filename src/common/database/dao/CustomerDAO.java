package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.database.model.CustomerModel;



public class CustomerDAO {
	
	// 로그인 아이디로 고객 조회
	public static CustomerModel getCustomerByLoginId(String login_id, Connection conn) throws SQLException {
		String query = "SELECT * FROM CUSTOMERS WHERE login_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, login_id);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next() ? new CustomerModel(rs) : null;
			}
		} 
	}
	
	// 주민등록번호로 고객 조회
		public static CustomerModel getCustomerByPersonalId(String personal_id, Connection conn) throws SQLException {
			String query = "SELECT * FROM CUSTOMERS WHERE personal_id = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, personal_id);
				
				try (ResultSet rs = pstmt.executeQuery()) {
					return rs.next() ? new CustomerModel(rs) : null;
				}
			} 
		}
	
	// 모든 고객 조회
	public static List<CustomerModel> getAllCustomers(Connection conn) throws SQLException {
		String query = "SELECT * FROM CUSTOMERS";
		
		try (Statement stmt = conn.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery(query)){

			List<CustomerModel> CustomerModels = new ArrayList<>();
			
			while (rs.next()) {
				CustomerModels.add(new CustomerModel(rs));
			}
			return CustomerModels;
		}		
	}
	
	// 수신동의여부로 검색하기
	public static List<CustomerModel> findCustomerByAgree_yn(String yn, Connection conn) throws SQLException {
		String query = "SELECT * FROM CUSTOMERS WHERE agree_yn = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(query)){
			
			pstmt.setString(1, yn);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				List<CustomerModel> CustomerModels = new ArrayList<>();
				
				while (rs.next()) {
					CustomerModels.add(new CustomerModel(rs));
				}
				return CustomerModels;	
			}			
		}		
	}
	
	// 새 고객을 추가하는 메서드 (CREATE) * 주민등록번호, 로그인 아이디 유니크 주의해주세요
	public static int addCustomer(CustomerModel c, Connection conn) {

		String query = "INSERT INTO CUSTOMERS(customer_id, customer_name"
				+ ", personal_id, phone_number, login_id, password_hash, password_salt) "
				+ "VALUES(SEQ_CUSTOMER_ID.nextval, ?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, c.getCustomer_name());
			pstmt.setString(2, c.getPersonal_id());
			pstmt.setString(3, c.getPhone_number());
			pstmt.setString(4, c.getLogin_id());
			pstmt.setString(5, c.getPassword_hash());
			pstmt.setString(6, c.getPassword_salt());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.getErrorCode();
			e.getMessage();
			e.printStackTrace();
			return -1;
		}
	}
	
	// 해당 고객을 삭제하고 삭제 여부를 반환하는 메서드 (DELETE) - 현재는 오류 발생 DB수정해야함
	public static boolean deleteCustomer(int customer_id, Connection conn) throws SQLException {
		String query = "DELETE FROM CUSTOMERS WHERE customer_id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(query)){
			pstmt.setInt(1, customer_id);
			return pstmt.executeUpdate() == 1;
		}
	}
	
	
	
}
