package insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import insurance.model.AutoPaymentModel;

public class AutoPaymentDAO {

    // 전체 자동이체 정보 조회
    public List<AutoPaymentModel> getAllAutoPayments(Connection conn) throws SQLException {
        String query = "SELECT * FROM AUTO_PAYMENTS";
        List<AutoPaymentModel> list = new ArrayList<>();

        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                list.add(new AutoPaymentModel(rs));
            }
        }

        return list;
    }

    // 특정 고객의 자동이체 정보 조회
    public List<AutoPaymentModel> getAutoPaymentsByCustomerId(int customerId, Connection conn) throws SQLException {
        String query = "SELECT * FROM AUTO_PAYMENTS WHERE customer_id = ?";
        List<AutoPaymentModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new AutoPaymentModel(rs));
                }
            }
        }

        return list;
    }

    // 자동이체 계좌 등록
    public int insertAutoPayment(AutoPaymentModel model, Connection conn) throws SQLException {
        String query = "INSERT INTO AUTO_PAYMENTS (account_id, customer_id, bank_name, bank_account, status, created_at, updated_at) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, model.getAccountId());
            pstmt.setInt(2, model.getCustomerId());
            pstmt.setString(3, model.getBankName());
            pstmt.setString(4, model.getBankAccount());
            pstmt.setString(5, model.getStatus());
            pstmt.setDate(6, new java.sql.Date(model.getCreatedAt().getTime()));
            pstmt.setDate(7, new java.sql.Date(model.getUpdatedAt().getTime()));
            return pstmt.executeUpdate();
        }
    }
    
    // 수정
    public int updateAutoPayment(AutoPaymentModel model, Connection conn) throws SQLException {
        String query = "UPDATE AUTO_PAYMENTS SET customer_id = ?, bank_name = ?, bank_account = ?, status = ?, " +
                       "created_at = ?, updated_at = ? WHERE account_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, model.getCustomerId());
            pstmt.setString(2, model.getBankName());
            pstmt.setString(3, model.getBankAccount());
            pstmt.setString(4, model.getStatus());
            pstmt.setDate(5, new java.sql.Date(model.getCreatedAt().getTime()));
            pstmt.setDate(6, new java.sql.Date(model.getUpdatedAt().getTime()));
            pstmt.setInt(7, model.getAccountId());
            return pstmt.executeUpdate();
        }
    }

    // 삭제
    public int deleteAutoPayment(int accountId, Connection conn) throws SQLException {
        String query = "DELETE FROM AUTO_PAYMENTS WHERE account_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, accountId);
            return pstmt.executeUpdate();
        }
    }
}