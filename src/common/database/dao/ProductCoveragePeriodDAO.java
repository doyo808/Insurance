package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.ProductCoveragePeriodModel;

public class ProductCoveragePeriodDAO {

    // 단일 조회
    public static ProductCoveragePeriodModel getById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM PRODUCT_COVERAGE_PERIODS WHERE product_coverage_period_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ProductCoveragePeriodModel(rs);
                } else {
                    return null;
                }
            }
        }
    }

    // 전체 조회
    public static ArrayList<ProductCoveragePeriodModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM PRODUCT_COVERAGE_PERIODS";
        ArrayList<ProductCoveragePeriodModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new ProductCoveragePeriodModel(rs));
            }
        }
        return list;
    }

    // 삽입
    public static int insert(ProductCoveragePeriodModel model, Connection conn) throws SQLException {
        String sql = "INSERT INTO PRODUCT_COVERAGE_PERIODS (product_coverage_period_id, product_id, coverage_period) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getProduct_coverage_period_id());
            pstmt.setInt(2, model.getProduct_id());
            pstmt.setString(3, String.valueOf(model.getCoverage_period()));

            return pstmt.executeUpdate();
        }
    }

    // 수정
    public static int update(ProductCoveragePeriodModel model, Connection conn) throws SQLException {
        String sql = "UPDATE PRODUCT_COVERAGE_PERIODS SET product_id = ?, coverage_period = ? WHERE product_coverage_period_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getProduct_id());
            pstmt.setString(2, String.valueOf(model.getCoverage_period()));
            pstmt.setInt(3, model.getProduct_coverage_period_id());

            return pstmt.executeUpdate();
        }
    }

    // 삭제
    public static int delete(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM PRODUCT_COVERAGE_PERIODS WHERE product_coverage_period_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}
