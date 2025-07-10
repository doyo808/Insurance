package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.ClaimDiagnosisModel;

public class ClaimDiagnosisDAO {

    // 단일 조회
    public static ClaimDiagnosisModel getById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM CLAIM_DIAGNOSIS WHERE claim_diagnosis_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ClaimDiagnosisModel(rs);
                } else {
                    return null;
                }
            }
        }
    }

    // 특정 claim_id에 대한 진단 코드 리스트 조회
    public static ArrayList<ClaimDiagnosisModel> getByClaimId(int claimId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM CLAIM_DIAGNOSIS WHERE claim_id = ?";
        ArrayList<ClaimDiagnosisModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, claimId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new ClaimDiagnosisModel(rs));
                }
            }
        }
        return list;
    }

    // 전체 조회
    public static ArrayList<ClaimDiagnosisModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM CLAIM_DIAGNOSIS";
        ArrayList<ClaimDiagnosisModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new ClaimDiagnosisModel(rs));
            }
        }
        return list;
    }

    // 삽입
    public static int insert(ClaimDiagnosisModel model, Connection conn) throws SQLException {
        String sql = "INSERT INTO CLAIM_DIAGNOSIS (claim_diagnosis_id, claim_id, diagnosis_cd) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getClaim_diagnosis_id());
            pstmt.setInt(2, model.getClaim_id());
            pstmt.setString(3, model.getDiagnosis_cd());

            return pstmt.executeUpdate();
        }
    }

    // 수정
    public static int update(ClaimDiagnosisModel model, Connection conn) throws SQLException {
        String sql = "UPDATE CLAIM_DIAGNOSIS SET claim_id = ?, diagnosis_cd = ? WHERE claim_diagnosis_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getClaim_id());
            pstmt.setString(2, model.getDiagnosis_cd());
            pstmt.setInt(3, model.getClaim_diagnosis_id());

            return pstmt.executeUpdate();
        }
    }

    // 삭제
    public static int delete(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM CLAIM_DIAGNOSIS WHERE claim_diagnosis_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}
