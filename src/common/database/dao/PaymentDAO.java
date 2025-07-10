package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.PaymentModel;

public class PaymentDAO {

    public PaymentModel getById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM PAYMENTS WHERE payment_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new PaymentModel(rs) : null;
            }
        }
    }

    public ArrayList<PaymentModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM PAYMENTS";
        ArrayList<PaymentModel> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new PaymentModel(rs));
            }
        }
        return list;
    }

    public int insert(PaymentModel model, Connection conn) throws SQLException {
        String sql = "INSERT INTO PAYMENTS (payment_id, customer_id, contract_id, unpaid_id, paid_amount, payment_date, account_id, pay_status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getPayment_id());
            pstmt.setInt(2, model.getCustomer_id());
            pstmt.setInt(3, model.getContract_id());
            pstmt.setInt(4, model.getUnpaid_id());
            pstmt.setDouble(5, model.getPaid_amount());
            pstmt.setDate(6, model.getPayment_date());
            pstmt.setObject(7, model.getAccount_id()); // null 허용
            pstmt.setString(8, model.getPay_status());
            return pstmt.executeUpdate();
        }
    }

    public int update(PaymentModel model, Connection conn) throws SQLException {
        String sql = "UPDATE PAYMENTS SET customer_id = ?, contract_id = ?, unpaid_id = ?, paid_amount = ?, payment_date = ?, account_id = ?, pay_status = ? WHERE payment_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getCustomer_id());
            pstmt.setInt(2, model.getContract_id());
            pstmt.setInt(3, model.getUnpaid_id());
            pstmt.setDouble(4, model.getPaid_amount());
            pstmt.setDate(5, model.getPayment_date());
            pstmt.setObject(6, model.getAccount_id());
            pstmt.setString(7, model.getPay_status());
            pstmt.setInt(8, model.getPayment_id());
            return pstmt.executeUpdate();
        }
    }

    public int delete(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM PAYMENTS WHERE payment_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}