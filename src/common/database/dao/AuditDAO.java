package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.AuditModel;

public class AuditDAO {

    public static AuditModel getById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM AUDITS WHERE audit_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new AuditModel(rs);
                }
                return null;
            }
        }
    }

    public static ArrayList<AuditModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM AUDITS";
        ArrayList<AuditModel> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new AuditModel(rs));
            }
        }
        return list;
    }

    public static int insert(AuditModel model, Connection conn) throws SQLException {
        String sql = "INSERT INTO AUDITS (audit_id, contract_id, employee_id, audit_date, audit_status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getAudit_id());
            pstmt.setInt(2, model.getContract_id());
            pstmt.setInt(3, model.getEmployee_id());
            pstmt.setDate(4, model.getAudit_date());
            pstmt.setString(5, model.getAudit_status());

            return pstmt.executeUpdate();
        }
    }

    public static int update(AuditModel model, Connection conn) throws SQLException {
        String sql = "UPDATE AUDITS SET contract_id = ?, employee_id = ?, audit_date = ?, audit_status = ? WHERE audit_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getContract_id());
            pstmt.setInt(2, model.getEmployee_id());
            pstmt.setDate(3, model.getAudit_date());
            pstmt.setString(4, model.getAudit_status());
            pstmt.setInt(5, model.getAudit_id());

            return pstmt.executeUpdate();
        }
    }

    public static int delete(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM AUDITS WHERE audit_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}
