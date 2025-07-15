package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.ProductPaymentCycleModel;

public class ProductPaymentCycleDAO {

    // 단일 조회
    public static ProductPaymentCycleModel getById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM PRODUCT_PAYMENT_CYCLES WHERE product_payment_cycle_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ProductPaymentCycleModel(rs);
                } else {
                    return null;
                }
            }
        }
    }

    // 전체 조회
    public static ArrayList<ProductPaymentCycleModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM PRODUCT_PAYMENT_CYCLES";
        ArrayList<ProductPaymentCycleModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new ProductPaymentCycleModel(rs));
            }
        }
        return list;
    }

//    // 삽입
//    public static int insert(ProductPaymentCycleModel model, Connection conn) throws SQLException {
//        String sql = "INSERT INTO PRODUCT_PAYMENT_CYCLES (product_payment_cycle_id, product_id, payment_cycle_month, created_at) VALUES (?, ?, ?, ?)";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, model.getProduct_payment_cycle_id());
//            pstmt.setInt(2, model.getProduct_id());
//            pstmt.setInt(3, model.getPayment_cycle_month());
//            pstmt.setDate(4, new java.sql.Date(model.getCreated_at().getTime()));
//
//            return pstmt.executeUpdate();
//        }
//    }
//
//    // 수정
//    public static int update(ProductPaymentCycleModel model, Connection conn) throws SQLException {
//        String sql = "UPDATE PRODUCT_PAYMENT_CYCLES SET product_id = ?, payment_cycle_month = ?, created_at = ? WHERE product_payment_cycle_id = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, model.getProduct_id());
//            pstmt.setInt(2, model.getPayment_cycle_month());
//            pstmt.setDate(3, new java.sql.Date(model.getCreated_at().getTime()));
//            pstmt.setInt(4, model.getProduct_payment_cycle_id());
//
//            return pstmt.executeUpdate();
//        }
//    }

    // 삭제
    public static int delete(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM PRODUCT_PAYMENT_CYCLES WHERE product_payment_cycle_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}
