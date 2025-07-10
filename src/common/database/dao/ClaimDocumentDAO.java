package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.ClaimDocumentModel;

public class ClaimDocumentDAO {

    // 단일 조회 by claim_document_id
    public static ClaimDocumentModel getById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM CLAIM_DOCUMENTS WHERE claim_document_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ClaimDocumentModel(rs);
                }
                return null;
            }
        }
    }

    // claim_id로 여러 건 조회
    public static ArrayList<ClaimDocumentModel> getByClaimId(int claimId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM CLAIM_DOCUMENTS WHERE claim_id = ?";
        ArrayList<ClaimDocumentModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, claimId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new ClaimDocumentModel(rs));
                }
            }
        }
        return list;
    }

    // 전체 조회
    public static ArrayList<ClaimDocumentModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM CLAIM_DOCUMENTS";
        ArrayList<ClaimDocumentModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new ClaimDocumentModel(rs));
            }
        }
        return list;
    }

    // 추가
    public static int insert(ClaimDocumentModel model, Connection conn) throws SQLException {
        String sql = "INSERT INTO CLAIM_DOCUMENTS (claim_document_id, claim_id, document_type_cd, file_path, submission_date, document_status, remarks) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getClaim_document_id());
            pstmt.setInt(2, model.getClaim_id());
            pstmt.setString(3, model.getDocument_type_cd());
            pstmt.setString(4, model.getFile_path());
            if (model.getSubmission_date() != null) {
                pstmt.setDate(5, new java.sql.Date(model.getSubmission_date().getTime()));
            } else {
                pstmt.setDate(5, null);
            }
            pstmt.setString(6, model.getDocument_status());
            pstmt.setString(7, model.getRemarks());

            return pstmt.executeUpdate();
        }
    }

    // 수정
    public static int update(ClaimDocumentModel model, Connection conn) throws SQLException {
        String sql = "UPDATE CLAIM_DOCUMENTS SET claim_id = ?, document_type_cd = ?, file_path = ?, submission_date = ?, document_status = ?, remarks = ? "
                   + "WHERE claim_document_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getClaim_id());
            pstmt.setString(2, model.getDocument_type_cd());
            pstmt.setString(3, model.getFile_path());
            if (model.getSubmission_date() != null) {
                pstmt.setDate(4, new java.sql.Date(model.getSubmission_date().getTime()));
            } else {
                pstmt.setDate(4, null);
            }
            pstmt.setString(5, model.getDocument_status());
            pstmt.setString(6, model.getRemarks());
            pstmt.setInt(7, model.getClaim_document_id());

            return pstmt.executeUpdate();
        }
    }

    // 삭제
    public static int delete(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM CLAIM_DOCUMENTS WHERE claim_document_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}
