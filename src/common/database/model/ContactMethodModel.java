package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMethodModel {
    private Integer contact_method_id;
    private Integer contract_id;
    private String contact_method;

    public ContactMethodModel(Integer contact_method_id, Integer contract_id, String contact_method) {
        this.contact_method_id = contact_method_id;
        this.contract_id = contract_id;
        this.contact_method = contact_method;
    }

    public ContactMethodModel(ResultSet rs) throws SQLException {
        this.contact_method_id = rs.getInt("contact_method_id");
        this.contract_id = rs.getInt("contract_id");
        this.contact_method = rs.getString("contact_method");
    }

    public Integer getContact_method_id() {
        return contact_method_id;
    }

    public void setContact_method_id(Integer contact_method_id) {
        this.contact_method_id = contact_method_id;
    }

    public Integer getContract_id() {
        return contract_id;
    }

    public void setContract_id(Integer contract_id) {
        this.contract_id = contract_id;
    }

    public String getContact_method() {
        return contact_method;
    }

    public void setContact_method(String contact_method) {
        this.contact_method = contact_method;
    }

    @Override
    public String toString() {
        return "ContactMethodModel [contact_method_id=" + contact_method_id + ", contract_id=" + contract_id
                + ", contact_method=" + contact_method + "]";
    }
}
