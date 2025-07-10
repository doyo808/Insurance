package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClaimDiagnosisModel {
    private Integer claim_diagnosis_id;
    private Integer claim_id;
    private String diagnosis_cd;

    public ClaimDiagnosisModel(Integer claim_diagnosis_id, Integer claim_id, String diagnosis_cd) {
        this.claim_diagnosis_id = claim_diagnosis_id;
        this.claim_id = claim_id;
        this.diagnosis_cd = diagnosis_cd;
    }

    public ClaimDiagnosisModel(ResultSet rs) throws SQLException {
        this.claim_diagnosis_id = rs.getInt("claim_diagnosis_id");
        this.claim_id = rs.getInt("claim_id");
        this.diagnosis_cd = rs.getString("diagnosis_cd");
    }

    public Integer getClaim_diagnosis_id() {
        return claim_diagnosis_id;
    }

    public void setClaim_diagnosis_id(Integer claim_diagnosis_id) {
        this.claim_diagnosis_id = claim_diagnosis_id;
    }

    public Integer getClaim_id() {
        return claim_id;
    }

    public void setClaim_id(Integer claim_id) {
        this.claim_id = claim_id;
    }

    public String getDiagnosis_cd() {
        return diagnosis_cd;
    }

    public void setDiagnosis_cd(String diagnosis_cd) {
        this.diagnosis_cd = diagnosis_cd;
    }

    @Override
    public String toString() {
        return "ClaimDiagnosisModel [claim_diagnosis_id=" + claim_diagnosis_id 
                + ", claim_id=" + claim_id + ", diagnosis_cd=" + diagnosis_cd + "]";
    }
}
