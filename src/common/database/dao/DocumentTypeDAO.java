package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.DocumentTypeModel;

public class DocumentTypeDAO {

    public static DocumentTypeModel getById(String id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM DOCUMENT_TYPES WHERE document_type_cd = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new DocumentTypeModel(rs);
                } else {
                    return null;
                }
            }
        }
    }

    public static ArrayList<DocumentTypeModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM DOCUMENT_TYPES";
        ArrayList<DocumentTypeModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new DocumentTypeModel(rs));
            }
        }
        return list;
    }

    public static int insert(DocumentTypeModel model, Connection conn) throws SQLException {
        String sql = "INSERT INTO DOCUMENT_TYPES (document_type_cd, document_type_name, document_description) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, model.getDocument_type_cd());
            pstmt.setString(2, model.getDocument_type_name());
            pstmt.setString(3, model.getDocument_description());
            return pstmt.executeUpdate();
        }
    }

    public static int update(DocumentTypeModel model, Connection conn) throws SQLException {
        String sql = "UPDATE DOCUMENT_TYPES SET document_type_name = ?, document_description = ? WHERE document_type_cd = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, model.getDocument_type_name());
            pstmt.setString(2, model.getDocument_description());
            pstmt.setString(3, model.getDocument_type_cd());
            return pstmt.executeUpdate();
        }
    }

    public static int delete(String id, Connection conn) throws SQLException {
        String sql = "DELETE FROM DOCUMENT_TYPES WHERE document_type_cd = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate();
        }
    }
}
