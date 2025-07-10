package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.database.model.ProductKeywordModel;

public class ProductKeywordDAO {

    public static ProductKeywordModel getById(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM PRODUCT_KEYWORD WHERE product_keyword_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new ProductKeywordModel(rs) : null;
            }
        }
    }

    public static List<ProductKeywordModel> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM PRODUCT_KEYWORD";
        List<ProductKeywordModel> keywords = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                keywords.add(new ProductKeywordModel(rs));
            }
        }
        return keywords;
    }

    public static int insert(ProductKeywordModel model, Connection conn) throws SQLException {
        String query = "INSERT INTO PRODUCT_KEYWORD (product_keyword_id, product_id, keyword_name) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, model.getProduct_keyword_id());
            pstmt.setInt(2, model.getProduct_id());
            pstmt.setString(3, model.getKeyword_name());
            return pstmt.executeUpdate();
        }
    }

    public static int update(ProductKeywordModel model, Connection conn) throws SQLException {
        String query = "UPDATE PRODUCT_KEYWORD SET product_id = ?, keyword_name = ? WHERE product_keyword_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, model.getProduct_id());
            pstmt.setString(2, model.getKeyword_name());
            pstmt.setInt(3, model.getProduct_keyword_id());
            return pstmt.executeUpdate();
        }
    }

    public static int delete(int id, Connection conn) throws SQLException {
        String query = "DELETE FROM PRODUCT_KEYWORD WHERE product_keyword_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}
