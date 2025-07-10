package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.database.model.BeneficiaryModel;

public class BeneficiaryDAO {

    public BeneficiaryModel getById(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM BENEFICIARIES WHERE beneficiary_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new BeneficiaryModel(rs) : null;
            }
        }
    }

    public List<BeneficiaryModel> getAll(Connection conn) throws SQLException {
        List<BeneficiaryModel> list = new ArrayList<>();
        String query = "SELECT * FROM BENEFICIARIES";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new BeneficiaryModel(rs));
            }
        }
        return list;
    }

    public int insert(BeneficiaryModel model, Connection conn) throws SQLException {
        String query = "INSERT INTO BENEFICIARIES (beneficiary_id, beneficiary_name, personal_id, relationship, phone_number, bank_account, bank_name) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, model.getBeneficiary_id());
            pstmt.setString(2, model.getBeneficiary_name());
            pstmt.setString(3, model.getPersonal_id());
            pstmt.setString(4, model.getRelationship());
            pstmt.setString(5, model.getPhone_number());
            pstmt.setString(6, model.getBank_account());
            pstmt.setString(7, model.getBank_name());
            return pstmt.executeUpdate();
        }
    }

    public int update(BeneficiaryModel model, Connection conn) throws SQLException {
        String query = "UPDATE BENEFICIARIES SET beneficiary_name = ?, personal_id = ?, relationship = ?, phone_number = ?, bank_account = ?, bank_name = ? " +
                       "WHERE beneficiary_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, model.getBeneficiary_name());
            pstmt.setString(2, model.getPersonal_id());
            pstmt.setString(3, model.getRelationship());
            pstmt.setString(4, model.getPhone_number());
            pstmt.setString(5, model.getBank_account());
            pstmt.setString(6, model.getBank_name());
            pstmt.setInt(7, model.getBeneficiary_id());
            return pstmt.executeUpdate();
        }
    }

    public int delete(int id, Connection conn) throws SQLException {
        String query = "DELETE FROM BENEFICIARIES WHERE beneficiary_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}