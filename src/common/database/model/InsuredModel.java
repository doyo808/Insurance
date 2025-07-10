
package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuredModel {

    private Integer insured_id;
    private String insured_name;
    private String personal_id;
    private String gender;
    private java.sql.Date birth_date;
    private Integer age;
    private String is_smoker;
    private String drinks;
    private Integer driving_status;
    private String medical_history;

    public InsuredModel(Integer insured_id, String insured_name, String personal_id, String gender,
                        java.sql.Date birth_date, Integer age, String is_smoker, String drinks,
                        Integer driving_status, String medical_history) {
        this.insured_id = insured_id;
        this.insured_name = insured_name;
        this.personal_id = personal_id;
        this.gender = gender;
        this.birth_date = birth_date;
        this.age = age;
        this.is_smoker = is_smoker;
        this.drinks = drinks;
        this.driving_status = driving_status;
        this.medical_history = medical_history;
    }

    public InsuredModel(ResultSet rs) throws SQLException {
        this.insured_id = rs.getInt("insured_id");
        this.insured_name = rs.getString("insured_name");
        this.personal_id = rs.getString("personal_id");
        this.gender = rs.getString("gender");
        this.birth_date = rs.getDate("birth_date");
        this.age = rs.getInt("age");
        this.is_smoker = rs.getString("is_smoker");
        this.drinks = rs.getString("drinks");
        this.driving_status = rs.getInt("driving_status");
        this.medical_history = rs.getString("medical_history");
    }

    public Integer getInsured_id() { return insured_id; }
    public String getInsured_name() { return insured_name; }
    public String getPersonal_id() { return personal_id; }
    public String getGender() { return gender; }
    public java.sql.Date getBirth_date() { return birth_date; }
    public Integer getAge() { return age; }
    public String getIs_smoker() { return is_smoker; }
    public String getDrinks() { return drinks; }
    public Integer getDriving_status() { return driving_status; }
    public String getMedical_history() { return medical_history; }

    @Override
    public String toString() {
        return "InsuredModel [insured_id=" + insured_id + ", insured_name=" + insured_name +
                ", personal_id=" + personal_id + ", gender=" + gender + ", birth_date=" + birth_date +
                ", age=" + age + ", is_smoker=" + is_smoker + ", drinks=" + drinks +
                ", driving_status=" + driving_status + ", medical_history=" + medical_history + "]";
    }
}
