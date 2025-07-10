package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.ContactMethodModel;

public class ContactMethodDAO {

    public static ContactMethodModel getById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM CONTACT_METHODES WHERE contact_method_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ContactMethodModel(rs);
                }
                return null;
            }
        }
    }

    public static ArrayList<ContactMethodModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM CONTACT_METHODES";
        ArrayList<ContactMethodModel> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ContactMethodModel(rs));
            }
        }
        return list;
    }

    public static int insert(ContactMethodModel model, Connection conn) throws SQLException {
        String sql = "INSERT INTO CONTACT_METHODES (contact_method_id, contract_id, contact_method) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getContact_method_id());
            pstmt.setInt(2, model.getContract_id());
            pstmt.setString(3, model.getContact_method());

            return pstmt.executeUpdate();
        }
    }

    public static int update(ContactMethodModel model, Connection conn) throws SQLException {
        String sql = "UPDATE CONTACT_METHODES SET contract_id = ?, contact_method = ? WHERE contact_method_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getContract_id());
            pstmt.setString(2, model.getContact_method());
            pstmt.setInt(3, model.getContact_method_id());

            return pstmt.executeUpdate();
        }
    }

    public static int delete(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM CONTACT_METHODES WHERE contact_method_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}
