package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.ClaimTypeCodeModel;

public class ClaimTypeCodeDAO {

    public static ClaimTypeCodeModel getById(String id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM CLAIM_TYPE_CODES WHERE claim_type_cd = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ClaimTypeCodeModel(rs);
                } else {
                    return null;
                }
            }
        }
    }

    public static ArrayList<ClaimTypeCodeModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM CLAIM_TYPE_CODES";
        ArrayList<ClaimTypeCodeModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new ClaimTypeCodeModel(rs));
            }
        }
        return list;
    }

    public static int insert(ClaimTypeCodeModel model, Connection conn) throws SQLException {
        String sql = "INSERT INTO CLAIM_TYPE_CODES (claim_type_cd, claim_type_name, claim_type_description) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, model.getClaim_type_cd());
            pstmt.setString(2, model.getClaim_type_name());
            pstmt.setString(3, model.getClaim_type_description());

            return pstmt.executeUpdate();
        }
    }

    public static int update(ClaimTypeCodeModel model, Connection conn) throws SQLException {
        String sql = "UPDATE CLAIM_TYPE_CODES SET claim_type_name = ?, claim_type_description = ? WHERE claim_type_cd = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, model.getClaim_type_name());
            pstmt.setString(2, model.getClaim_type_description());
            pstmt.setString(3, model.getClaim_type_cd());

            return pstmt.executeUpdate();
        }
    }

    public static int delete(String id, Connection conn) throws SQLException {
        String sql = "DELETE FROM CLAIM_TYPE_CODES WHERE claim_type_cd = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate();
        }
    }
}
