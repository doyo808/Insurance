package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentModel {
    private Integer department_id;
    private String department_name;

    public DepartmentModel(Integer department_id, String department_name) {
        this.department_id = department_id;
        this.department_name = department_name;
    }

    public DepartmentModel(ResultSet rs) throws SQLException {
        this.department_id = rs.getInt("department_id");
        this.department_name = rs.getString("department_name");
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    @Override
    public String toString() {
        return "DepartmentModel [department_id=" + department_id + ", department_name=" + department_name + "]";
    }
}
