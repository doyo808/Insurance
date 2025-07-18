package common.database.model;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeneficiaryModel {

    private Integer beneficiary_id;
    private String beneficiary_name;
    private String relationship;
    private String bank_account;
    private String bank_name;

    public BeneficiaryModel(Integer beneficiary_id, String beneficiary_name,
                            String relationship, String bank_account, String bank_name) {
        this.beneficiary_id = beneficiary_id;
        this.beneficiary_name = beneficiary_name;
        this.relationship = relationship;
        this.bank_account = bank_account;
        this.bank_name = bank_name;
    }

    public BeneficiaryModel(ResultSet rs) throws SQLException {
        this.beneficiary_id = rs.getInt("beneficiary_id");
        this.beneficiary_name = rs.getString("beneficiary_name");
        this.relationship = rs.getString("relationship");
        this.bank_account = rs.getString("bank_account");
        this.bank_name = rs.getString("bank_name");
    }

    // Getters and Setters

    public Integer getBeneficiary_id() {
        return beneficiary_id;
    }

    public void setBeneficiary_id(Integer beneficiary_id) {
        this.beneficiary_id = beneficiary_id;
    }

    public String getBeneficiary_name() {
        return beneficiary_name;
    }

    public void setBeneficiary_name(String beneficiary_name) {
        this.beneficiary_name = beneficiary_name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    @Override
    public String toString() {
        return "BeneficiaryModel{" +
                "beneficiary_id=" + beneficiary_id +
                ", beneficiary_name='" + beneficiary_name + "'" +
                ", relationship='" + relationship + "'" +
                ", bank_account='" + bank_account + "'" +
                ", bank_name='" + bank_name + "'" +
                '}';
    }
}