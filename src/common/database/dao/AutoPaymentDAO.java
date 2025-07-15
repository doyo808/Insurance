package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import common.database.model.AutoPaymentModel;

public class AutoPaymentDAO {

    // 전체 자동이체 정보 조회
    public static List<AutoPaymentModel> getAllAutoPayments(Connection conn) throws SQLException {
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
    public static List<AutoPaymentModel> getAutoPaymentsByCustomerId(int customer_id, Connection conn) throws SQLException {
        String query = "SELECT * FROM AUTO_PAYMENTS WHERE customer_id = ?";
        List<AutoPaymentModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customer_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new AutoPaymentModel(rs));
                }
            }
        }

        return list;
    }
    
    // 특정 계약의 자동이체 정보 조회
    public static AutoPaymentModel getAutoPaymentsByContId(Integer contract_id, Connection conn) throws SQLException {
    	String sql = "SELECT * FROM auto_payments WHERE contract_id = ?";
    	AutoPaymentModel model = null;
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, contract_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                	model = new AutoPaymentModel(rs);
                }
            }
        }
    	return model;
    }

    // 자동이체 계좌 등록
    public static int insertAutoPayment(AutoPaymentModel model, Connection conn) throws SQLException {
        String query = "INSERT INTO AUTO_PAYMENTS (account_id, customer_id, bank_name, bank_account, status, created_at, updated_at, contract_id) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, model.getAccount_id());
            pstmt.setInt(2, model.getCustomer_id());
            pstmt.setString(3, model.getBank_name());
            pstmt.setString(4, model.getBank_account());
            pstmt.setString(5, model.getStatus());
            pstmt.setTimestamp(6, model.getCreated_at());
            pstmt.setTimestamp(7, model.getUpdated_at());
            pstmt.setInt(8, model.getContract_id());
            return pstmt.executeUpdate();
        }
    }

    // 자동이체 계좌 정보 수정
    public static int updateAutoPayment(AutoPaymentModel model, Connection conn) throws SQLException {
        String query = "UPDATE AUTO_PAYMENTS SET customer_id = ?, bank_name = ?, bank_account = ?, status = ?, " +
                       "updated_at = ?, contract_id = ? WHERE account_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, model.getCustomer_id());
            pstmt.setString(2, model.getBank_name());
            pstmt.setString(3, model.getBank_account());
            pstmt.setString(4, model.getStatus());
            pstmt.setTimestamp(5, model.getUpdated_at());
            pstmt.setInt(6, model.getContract_id());
            pstmt.setInt(7, model.getAccount_id());
            return pstmt.executeUpdate();
        }
    }

    // 자동이체 삭제
    public static int deleteAutoPayment(int account_id, Connection conn) throws SQLException {
        String query = "DELETE FROM AUTO_PAYMENTS WHERE account_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, account_id);
            return pstmt.executeUpdate();
        }
    }
}