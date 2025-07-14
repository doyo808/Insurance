package common.database.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.database.model.ProductModel;

public class ProductDAO {

    /**
     * 특정 상품 ID로 상품 정보를 조회합니다.
     *
     * @param productId 조회할 상품 ID
     * @param conn DB 연결 객체
     * @return Product 객체 (없으면 null)
     * @throws SQLException
     */
    public static ProductModel getProduct(int productId, Connection conn) throws SQLException {
        String query = "SELECT * FROM products WHERE product_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new ProductModel(rs) : null;
            }
        }
    }

    /**
     * 모든 상품 목록을 조회합니다.
     *
     * @param conn DB 연결 객체
     * @return 상품 리스트
     * @throws SQLException
     */
    public static List<ProductModel> getAllProducts(Connection conn) throws SQLException {
        String query = "SELECT * FROM products";

        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            List<ProductModel> products = new ArrayList<>();
            while (rs.next()) {
                products.add(new ProductModel(rs));
            }
            return products;
        }
    }
    
    /**
     * 상품의 이름만 반환하는 메서드
     * @param productId
     * @param conn
     * @return
     * @throws SQLException
     */
    
    public static String getProductName(int productId, Connection conn) throws SQLException {
        String query = "SELECT product_name FROM products WHERE product_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new ProductModel(rs).getProductName() : null;
            }
        }
    }

    /**
     * 상품명을 기준으로 LIKE 검색을 수행합니다.
     *
     * @param nameFrag 상품명 일부
     * @param conn DB 연결 객체
     * @return 검색된 상품 리스트
     * @throws SQLException
     */
    public static List<ProductModel> findProductByName(String nameFrag, Connection conn) throws SQLException {
        String query = "SELECT * FROM products WHERE LOWER(product_name) LIKE ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + nameFrag.toLowerCase() + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                List<ProductModel> products = new ArrayList<>();
                while (rs.next()) {
                    products.add(new ProductModel(rs));
                }
                return products;
            }
        }
    }

    /**
     * 새로운 상품을 등록합니다.
     *
     * @param p 등록할 상품 객체
     * @param conn DB 연결 객체
     * @return 삽입 성공 행 수 (보통 1)
     * @throws SQLException
     * @throws FileNotFoundException 
     */
    public static int addProduct(ProductModel p, String path, Connection conn) throws SQLException, FileNotFoundException {
        String query = "INSERT INTO products ("
                + "product_id, division, product_name, join_age_low, join_age_high, "
                + "join_limit_low, join_limit_high, term_and_conditions_path, "
                + "product_manual_path, base_premium, premium_constant, product_introduce"
                + ") VALUES (product_id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, p.getDivision());
            pstmt.setString(2, p.getProductName());
            pstmt.setInt(3, p.getJoinAgeLow());
            pstmt.setInt(4, p.getJoinAgeHigh());
            pstmt.setInt(5, p.getJoinLimitLow());
            pstmt.setInt(6, p.getJoinLimitHigh());
            pstmt.setString(7, p.getTermAndConditionsPath());
            pstmt.setString(8, p.getProductManualPath());
            pstmt.setDouble(9, p.getBasePremium());
            pstmt.setDouble(10, p.getPremiumConstant());
            pstmt.setBlob(11, new FileInputStream(path));

            return pstmt.executeUpdate();
        }
    }
}
