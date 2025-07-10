package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.database.model.ClaimProcessLogModel;

public class ClaimProcessLogDAO {

    // 단일 조회
    public static ClaimProcessLogModel getById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM CLAIM_PROCESS_LOGS WHERE claim_process_log_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ClaimProcessLogModel(rs);
                } else {
                    return null;
                }
            }
        }
    }

    // 특정 claim_id에 대한 처리 로그 리스트 조회
    public static ArrayList<ClaimProcessLogModel> getByClaimId(int claimId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM CLAIM_PROCESS_LOGS WHERE claim_id = ?";
        ArrayList<ClaimProcessLogModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, claimId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new ClaimProcessLogModel(rs));
                }
            }
        }
        return list;
    }

    // 전체 조회
    public static ArrayList<ClaimProcessLogModel> getAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM CLAIM_PROCESS_LOGS";
        ArrayList<ClaimProcessLogModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                list.add(new ClaimProcessLogModel(rs));
            }
        }
        return list;
    }

    // 삽입
    public static int insert(ClaimProcessLogModel model, Connection conn) throws SQLException {
        String sql = "INSERT INTO CLAIM_PROCESS_LOGS (claim_process_log_id, claim_id, employee_id, process_date, previous_status_cd, new_status_cd, action_description) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getClaim_process_log_id());
            pstmt.setInt(2, model.getClaim_id());
            pstmt.setInt(3, model.getEmployee_id());
            pstmt.setDate(4, new java.sql.Date(model.getProcess_date().getTime()));
            pstmt.setString(5, model.getPrevious_status_cd());
            pstmt.setString(6, model.getNew_status_cd());
            pstmt.setString(7, model.getAction_description());

            return pstmt.executeUpdate();
        }
    }

    // 수정
    public static int update(ClaimProcessLogModel model, Connection conn) throws SQLException {
        String sql = "UPDATE CLAIM_PROCESS_LOGS SET claim_id = ?, employee_id = ?, process_date = ?, previous_status_cd = ?, new_status_cd = ?, action_description = ? "
                   + "WHERE claim_process_log_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, model.getClaim_id());
            pstmt.setInt(2, model.getEmployee_id());
            pstmt.setDate(3, new java.sql.Date(model.getProcess_date().getTime()));
            pstmt.setString(4, model.getPrevious_status_cd());
            pstmt.setString(5, model.getNew_status_cd());
            pstmt.setString(6, model.getAction_description());
            pstmt.setInt(7, model.getClaim_process_log_id());

            return pstmt.executeUpdate();
        }
    }

    // 삭제
    public static int delete(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM CLAIM_PROCESS_LOGS WHERE claim_process_log_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}
