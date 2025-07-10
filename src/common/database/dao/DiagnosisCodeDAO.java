package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.DiagnosisCodeModel;

public class DiagnosisCodeDAO {

    // 단일 조회
    public static DiagnosisCodeModel getById(String id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM DIAGNOSIS_CODES WHERE diagnosis_cd = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new DiagnosisCodeModel(rs);
                } else {
                    return null;
                }
            }
        }
    }

    // 전체 조회
    public static ArrayList<DiagnosisCodeModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM DIAGNOSIS_CODES";
        ArrayList<DiagnosisCodeModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new DiagnosisCodeModel(rs));
            }
        }
        return list;
    }

    // 삽입
    public static int insert(DiagnosisCodeModel model, Connection conn) throws SQLException {
        String sql = "INSERT INTO DIAGNOSIS_CODES (diagnosis_cd, diagnosis_name) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, model.getDiagnosis_cd());
            pstmt.setString(2, model.getDiagnosis_name());

            return pstmt.executeUpdate();
        }
    }

    // 수정
    public static int update(DiagnosisCodeModel model, Connection conn) throws SQLException {
        String sql = "UPDATE DIAGNOSIS_CODES SET diagnosis_name = ? WHERE diagnosis_cd = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, model.getDiagnosis_name());
            pstmt.setString(2, model.getDiagnosis_cd());

            return pstmt.executeUpdate();
        }
    }

    // 삭제
    public static int delete(String id, Connection conn) throws SQLException {
        String sql = "DELETE FROM DIAGNOSIS_CODES WHERE diagnosis_cd = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate();
        }
    }
}
