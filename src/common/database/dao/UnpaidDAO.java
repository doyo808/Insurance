package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.database.model.UnpaidModel;

public class UnpaidDAO {

    // 특정 고객의 미납 내역 조회
    public static List<UnpaidModel> getUnpaidsByCustomer(int customer_id, Connection conn) throws SQLException {
        String query = "SELECT * FROM unpaids WHERE customer_id = ?";
        List<UnpaidModel> result = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customer_id);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new UnpaidModel(rs));
                }
            }
        }

        return result;
    }
    // 고객의 미납내역 한개를 조회
    public static UnpaidModel getUnpaidByCustomer(int customer_id, int unpaid_id, Connection conn) throws SQLException {
        String query = "SELECT * FROM unpaids WHERE customer_id = ? AND unpaid_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customer_id);
            pstmt.setInt(2, unpaid_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new UnpaidModel(rs) : null; 
            }
        }
    }
    
    // 미납id로 조회
    public static UnpaidModel getUnpaidByUnpaidId(int unpaid_id, Connection conn) throws SQLException {
        String query = "SELECT * FROM unpaids WHERE npaid_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, unpaid_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new UnpaidModel(rs) : null; 
            }
        }
    }
    
    
    // 모든 미납 내역 조회
    public static List<UnpaidModel> getAllUnpaids(Connection conn) throws SQLException {
        String query = "SELECT * FROM unpaids";
        List<UnpaidModel> result = new ArrayList<>();

        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                result.add(new UnpaidModel(rs));
            }
        }

        return result;
    }

    // 미납 추가
    public static int addUnpaid(UnpaidModel u, Connection conn) throws SQLException {
        String query = "INSERT INTO unpaids (unpaid_id, customer_id, contract_id, unpaid_amount, " +
                "unpaid_date, unpaid_count, payment_deadline, ispaid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, u.getUnpaidId());
            pstmt.setInt(2, u.getCustomerId());
            pstmt.setInt(3, u.getContractId());
            pstmt.setDouble(4, u.getUnpaidAmount());
            pstmt.setDate(5, new java.sql.Date(u.getUnpaidDate().getTime()));
            pstmt.setInt(6, u.getUnpaidCount());
            pstmt.setDate(7, new java.sql.Date(u.getPaymentDeadline().getTime()));
            pstmt.setString(8, u.getIsPaid());
            return pstmt.executeUpdate();
        }
    }
    
    /**
     *  결제가 완료되면 미납내역을 완료됐다고 수정
     * @param unpaid_id
     * @param conn
     * @return
     * @throws SQLException
     */
    public static int updateUnpaidY(int unpaid_id, Connection conn) throws SQLException {
    	String query = "UPDATE unpaids SET ispaid = 'Y' WHERE unpaid_id = ?";
    	
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    		pstmt.setInt(1, unpaid_id);
    		return pstmt.executeUpdate();
    	}
    }
    
    
    // 삭제
    public static int deleteUnpaid(int unpaid_id, Connection conn) throws SQLException {
        String query = "DELETE FROM unpaids WHERE unpaid_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, unpaid_id);
            return pstmt.executeUpdate();
        }
    }
}
