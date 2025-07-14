package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.database.model.ProductCoverageDetailModel;
import common.database.model.ProductModel;

public class ProductCoverageDetailDAO {

    // 단일 조회
    public static ProductCoverageDetailModel getById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM PRODUCT_COVERAGE_PERIODS WHERE product_coverage_period_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ProductCoverageDetailModel(rs);
                } else {
                    return null;
                }
            }
        }
    }

    // 전체 조회
    public static ArrayList<ProductCoverageDetailModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM PRODUCT_COVERAGE_DETAILS";
        ArrayList<ProductCoverageDetailModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new ProductCoverageDetailModel(rs));
            }
        }
        return list;
    }

    /**
     * 상품번을 기준으로 해당 상품의 모든 보장내용을 가져옵니다.
     *
     * @param productId 상품명 일부
     * @param conn DB 연결 객체
     * @return 검색된 해당 상품의 보장내용 리스트
     * @throws SQLException
     */
    public static List<ProductCoverageDetailModel> getProductDetails(int productId, Connection conn) throws SQLException {
        String query = "SELECT * FROM products WHERE product_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);

            try (ResultSet rs = pstmt.executeQuery()) {
                List<ProductCoverageDetailModel> products = new ArrayList<>();
                while (rs.next()) {
                    products.add(new ProductCoverageDetailModel(rs));
                }
                return products;
            }
        }
    }
    
    // 삽입
    public static int insert(ProductCoverageDetailModel model, int num, Connection conn) throws SQLException {
        String sql = "INSERT INTO PRODUCT_COVERAGE_DETAILS (product_coverage_id, product_id, product_cover_name, product_cover_detail) VALUES (product_coverage_id_seq.NEXTVAL, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, num);
            pstmt.setString(2, model.getProductCoverName());
            pstmt.setString(3, model.getProductCoverDetail());

            return pstmt.executeUpdate();
        }
    }

    // 수정
    public static int update(ProductCoverageDetailModel model, Connection conn) throws SQLException {
        String sql = "UPDATE PRODUCT_COVERAGE_DETAILS SET product_id = ?, product_cover_name = ?, product_cover_detail = ? WHERE product_coverage_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getProductId());
            pstmt.setString(2, model.getProductCoverName());
            pstmt.setString(3, model.getProductCoverDetail());
            pstmt.setInt(4, model.getProductCoverageId());
            return pstmt.executeUpdate();
        }
    }

    // 삭제
    public static int delete(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM PRODUCT_COVERAGE_DETAILS WHERE product_coverage_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}
