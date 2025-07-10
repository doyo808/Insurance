package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiagnosisCodeModel {
    private String diagnosis_cd;
    private String diagnosis_name;

    public DiagnosisCodeModel(String diagnosis_cd, String diagnosis_name) {
        this.diagnosis_cd = diagnosis_cd;
        this.diagnosis_name = diagnosis_name;
    }

    public DiagnosisCodeModel(ResultSet rs) throws SQLException {
        this.diagnosis_cd = rs.getString("diagnosis_cd");
        this.diagnosis_name = rs.getString("diagnosis_name");
    }

    public String getDiagnosis_cd() {
        return diagnosis_cd;
    }

    public void setDiagnosis_cd(String diagnosis_cd) {
        this.diagnosis_cd = diagnosis_cd;
    }

    public String getDiagnosis_name() {
        return diagnosis_name;
    }

    public void setDiagnosis_name(String diagnosis_name) {
        this.diagnosis_name = diagnosis_name;
    }

    @Override
    public String toString() {
        return "DiagnosisCodeModel [diagnosis_cd=" + diagnosis_cd + ", diagnosis_name=" + diagnosis_name + "]";
    }
}
