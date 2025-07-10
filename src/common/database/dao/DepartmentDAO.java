package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.database.model.DepartmentModel;

public class DepartmentDAO {

    public static DepartmentModel getById(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM departments WHERE department_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? new DepartmentModel(rs) : null;
            }
        }
    }

    public static List<DepartmentModel> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM departments";
        List<DepartmentModel> departments = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                departments.add(new DepartmentModel(rs));
            }
        }
        return departments;
    }

    public static int insert(DepartmentModel dept, Connection conn) throws SQLException {
        String query = "INSERT INTO departments (department_id, department_name) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, dept.getDepartment_id());
            pstmt.setString(2, dept.getDepartment_name());
            return pstmt.executeUpdate();
        }
    }

    public static int update(DepartmentModel dept, Connection conn) throws SQLException {
        String query = "UPDATE departments SET department_name = ? WHERE department_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, dept.getDepartment_name());
            pstmt.setInt(2, dept.getDepartment_id());
            return pstmt.executeUpdate();
        }
    }

    public static int delete(int id, Connection conn) throws SQLException {
        String query = "DELETE FROM departments WHERE department_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
}
