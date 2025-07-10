package insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import insurance.model.PaymentModel;



public class PaymentDAO {
	
	
	/**
	 * 특정 고객의 특정 기간 동안의 결제 내역을 조회합니다.
	 *
	 * @param customer_id 조회할 고객 ID
	 * @param conn 데이터베이스 연결 객체
	 * @param start 조회 시작일
	 * @param end 조회 종료일
	 * @return 조회된 결제 내역을 담은 리스트
	 * @throws SQLException 데이터베이스 조회 중 예외 발생 시
	 */
	public static ArrayList<PaymentModel> getPaymentModel(int customer_id, Connection conn, Date start, Date end) throws SQLException {
	    String query = "SELECT * FROM payments WHERE customer_id = ? AND payment_date BETWEEN ? AND ?";

	    ArrayList<PaymentModel> payments = new ArrayList<>();

	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setInt(1, customer_id);
	        pstmt.setDate(2, new java.sql.Date(start.getTime()));
	        pstmt.setDate(3, new java.sql.Date(end.getTime()));

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                payments.add(new PaymentModel(rs));
	            }
	        }
	    }

	    return payments;
	}
	
	
	/**
	 * 특정 상품, 특정 고객, 특정 기간 동안의 결제 내역을 조회합니다. (내역조회)
	 *
	 * @param customer_id 조회할 고객 ID
	 * @param conn 데이터베이스 연결 객체
	 * @param start 조회 시작일
	 * @param end 조회 종료일
	 * @return 조회된 결제 내역을 담은 리스트
	 * @throws SQLException 데이터베이스 조회 중 예외 발생 시
	 */
	public static ArrayList<PaymentModel> getInquireList(Integer customer_id, Integer product_id, Connection conn, Date start, Date end) throws SQLException {
	    String query = "SELECT * FROM payments INNER JOIN contracts USING(contract_id) "
	    		+ "WHERE customer_id = ? AND payment_date BETWEEN ? AND ? AND product_id = ?";

	    ArrayList<PaymentModel> payments = new ArrayList<>();

	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setInt(1, customer_id);
	        pstmt.setDate(2, new java.sql.Date(start.getTime()));
	        pstmt.setDate(3, new java.sql.Date(end.getTime()));
	        pstmt.setInt(4, product_id);

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                payments.add(new PaymentModel(rs));
	            }
	        }
	    }

	    return payments;
	}
}
