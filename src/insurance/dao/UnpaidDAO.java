package insurance.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import insurance.model.UnpaidModel;

public class UnpaidDAO {

    // 특정 고객의 미납 내역 조회
    public static List<UnpaidModel> getUnpaidsByCustomer(int customerId, Connection conn) throws SQLException {
        String query = "SELECT * FROM unpaids WHERE customer_id = ?";
        List<UnpaidModel> result = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customerId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new UnpaidModel(rs));
                }
            }
        }

        return result;
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
                "unpaid_date, unpaid_count, payment_deadline) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, u.getUnpaidId());
            pstmt.setInt(2, u.getCustomerId());
            pstmt.setInt(3, u.getContractId());
            pstmt.setDouble(4, u.getUnpaidAmount());
            pstmt.setDate(5, new java.sql.Date(u.getUnpaidDate().getTime()));
            pstmt.setInt(6, u.getUnpaidCount());
            pstmt.setDate(7, new java.sql.Date(u.getPaymentDeadline().getTime()));

            return pstmt.executeUpdate();
        }
    }

    // 삭제
    public static int deleteUnpaid(int unpaidId, Connection conn) throws SQLException {
        String query = "DELETE FROM unpaids WHERE unpaid_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, unpaidId);
            return pstmt.executeUpdate();
        }
    }
}
