
package insurance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import insurance.model.InsuredModel;

public class InsuredDAO {

    public List<InsuredModel> getAllInsureds(Connection conn) throws SQLException {
        String query = "SELECT * FROM INSUREDS";
        List<InsuredModel> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new InsuredModel(rs));
            }
        }
        return list;
    }

    public int addInsured(InsuredModel insured, Connection conn) throws SQLException {
        String query = "INSERT INTO INSUREDS (insured_id, insured_name, personal_id, gender, birth_date, age, is_smoker, drinks, driving_status, medical_history) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, insured.getInsured_id());
            pstmt.setString(2, insured.getInsured_name());
            pstmt.setString(3, insured.getPersonal_id());
            pstmt.setString(4, insured.getGender());
            pstmt.setDate(5, insured.getBirth_date());
            pstmt.setInt(6, insured.getAge());
            pstmt.setString(7, insured.getIs_smoker());
            pstmt.setString(8, insured.getDrinks());
            pstmt.setInt(9, insured.getDriving_status());
            pstmt.setString(10, insured.getMedical_history());
            return pstmt.executeUpdate();
        }
    }

    public int updateInsured(InsuredModel insured, Connection conn) throws SQLException {
        String query = "UPDATE INSUREDS SET insured_name = ?, personal_id = ?, gender = ?, birth_date = ?, age = ?, "
                     + "is_smoker = ?, drinks = ?, driving_status = ?, medical_history = ? WHERE insured_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, insured.getInsured_name());
            pstmt.setString(2, insured.getPersonal_id());
            pstmt.setString(3, insured.getGender());
            pstmt.setDate(4, insured.getBirth_date());
            pstmt.setInt(5, insured.getAge());
            pstmt.setString(6, insured.getIs_smoker());
            pstmt.setString(7, insured.getDrinks());
            pstmt.setInt(8, insured.getDriving_status());
            pstmt.setString(9, insured.getMedical_history());
            pstmt.setInt(10, insured.getInsured_id());
            return pstmt.executeUpdate();
        }
    }

    public int deleteInsured(int insuredId, Connection conn) throws SQLException {
        String query = "DELETE FROM INSUREDS WHERE insured_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, insuredId);
            return pstmt.executeUpdate();
        }
    }
}
