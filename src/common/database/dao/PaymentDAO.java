package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

import common.database.model.PaymentModel;

public class PaymentDAO {

    public static PaymentModel getById(int payment_id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM PAYMENTS WHERE payment_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, payment_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new PaymentModel(rs) : null;
            }
        }
    }

    public static ArrayList<PaymentModel> getAll(Connection conn) throws SQLException {
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
    
    public static ArrayList<PaymentModel> getAllByContId(Integer contract_id, Connection conn) throws SQLException {
		String sql = "SELECT * FROM payments WHERE contract_id = ?";
		ArrayList<PaymentModel> list = new ArrayList<PaymentModel>();
		
        try (PreparedStatement pstmt = conn.prepareStatement(sql);){
        	pstmt.setInt(1, contract_id);
        	try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new PaymentModel(rs));
                }
        	}   
       }
       return list;
    	
    }

    public static int insert(PaymentModel payment, Connection conn) throws SQLException {
        String sql = "INSERT INTO PAYMENTS (payment_id, customer_id, contract_id, unpaid_id, paid_amount, payment_date, account_id, pay_status) " +
                     "VALUES (SEQ_PAYMENT_ID.nextval, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, payment.getCustomer_id());
            pstmt.setInt(2, payment.getContract_id());
            pstmt.setInt(3, payment.getUnpaid_id());
            pstmt.setDouble(4, payment.getPaid_amount());
            pstmt.setTimestamp(5, payment.getPayment_date());
            pstmt.setObject(6, payment.getAccount_id()); // null 허용
            pstmt.setString(7, payment.getPay_status());
            return pstmt.executeUpdate();
        }
    }

    public static int update(PaymentModel payment, Connection conn) throws SQLException {
        String sql = "UPDATE PAYMENTS SET customer_id = ?, contract_id = ?, unpaid_id = ?, paid_amount = ?, payment_date = ?, account_id = ?, pay_status = ? WHERE payment_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, payment.getCustomer_id());
            pstmt.setInt(2, payment.getContract_id());
            pstmt.setInt(3, payment.getUnpaid_id());
            pstmt.setDouble(4, payment.getPaid_amount());
            pstmt.setTimestamp(5, payment.getPayment_date());
            pstmt.setObject(6, payment.getAccount_id());
            pstmt.setString(7, payment.getPay_status());
            pstmt.setInt(8, payment.getPayment_id());
            return pstmt.executeUpdate();
        }
    }

    public static int delete(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM PAYMENTS WHERE payment_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}