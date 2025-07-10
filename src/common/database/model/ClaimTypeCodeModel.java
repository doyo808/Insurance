package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClaimTypeCodeModel {
    private String claim_type_cd;
    private String claim_type_name;
    private String claim_type_description;

    public ClaimTypeCodeModel(String claim_type_cd, String claim_type_name, String claim_type_description) {
        this.claim_type_cd = claim_type_cd;
        this.claim_type_name = claim_type_name;
        this.claim_type_description = claim_type_description;
    }

    public ClaimTypeCodeModel(ResultSet rs) throws SQLException {
        this.claim_type_cd = rs.getString("claim_type_cd");
        this.claim_type_name = rs.getString("claim_type_name");
        this.claim_type_description = rs.getString("claim_type_description");
    }

    public String getClaim_type_cd() {
        return claim_type_cd;
    }

    public void setClaim_type_cd(String claim_type_cd) {
        this.claim_type_cd = claim_type_cd;
    }

    public String getClaim_type_name() {
        return claim_type_name;
    }

    public void setClaim_type_name(String claim_type_name) {
        this.claim_type_name = claim_type_name;
    }

    public String getClaim_type_description() {
        return claim_type_description;
    }

    public void setClaim_type_description(String claim_type_description) {
        this.claim_type_description = claim_type_description;
    }

    @Override
    public String toString() {
        return "ClaimTypeCodeModel [claim_type_cd=" + claim_type_cd + ", claim_type_name=" + claim_type_name
                + ", claim_type_description=" + claim_type_description + "]";
    }
}
