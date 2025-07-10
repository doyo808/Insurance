package insurance.model;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeneficiaryModel {

    private Integer beneficiary_id;
    private String beneficiary_name;
    private String personal_id;
    private String relationship;
    private String phone_number;
    private String bank_account;
    private String bank_name;

    public BeneficiaryModel(Integer beneficiary_id, String beneficiary_name, String personal_id,
                            String relationship, String phone_number, String bank_account, String bank_name) {
        this.beneficiary_id = beneficiary_id;
        this.beneficiary_name = beneficiary_name;
        this.personal_id = personal_id;
        this.relationship = relationship;
        this.phone_number = phone_number;
        this.bank_account = bank_account;
        this.bank_name = bank_name;
    }

    public BeneficiaryModel(ResultSet rs) throws SQLException {
        this.beneficiary_id = rs.getInt("beneficiary_id");
        this.beneficiary_name = rs.getString("beneficiary_name");
        this.personal_id = rs.getString("personal_id");
        this.relationship = rs.getString("relationship");
        this.phone_number = rs.getString("phone_number");
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

    public String getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(String personal_id) {
        this.personal_id = personal_id;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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
                ", beneficiary_name='" + beneficiary_name + ''' +
                ", personal_id='" + personal_id + ''' +
                ", relationship='" + relationship + ''' +
                ", phone_number='" + phone_number + ''' +
                ", bank_account='" + bank_account + ''' +
                ", bank_name='" + bank_name + ''' +
                '}';
    }
}