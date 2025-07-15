package common.database.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AutoPaymentModel {

    private Integer account_id;
    private Integer customer_id;
    private String bank_name;
    private String bank_account;
    private String status;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Integer contract_id; // 마지막에 위치

    public AutoPaymentModel(Integer account_id, Integer customer_id, String bank_name,
                             String bank_account, String status,
                             Timestamp created_at, Timestamp updated_at, Integer contract_id) {
        this.account_id = account_id;
        this.customer_id = customer_id;
        this.bank_name = bank_name;
        this.bank_account = bank_account;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.contract_id = contract_id;
    }

    public AutoPaymentModel(ResultSet rs) throws SQLException {
        this.account_id = rs.getInt("account_id");
        this.customer_id = rs.getInt("customer_id");
        this.bank_name = rs.getString("bank_name");
        this.bank_account = rs.getString("bank_account");
        this.status = rs.getString("status");
        this.created_at = rs.getTimestamp("created_at");
        this.updated_at = rs.getTimestamp("updated_at");
        this.contract_id = rs.getInt("contract_id");
    }

    // Getters and setters
    public Integer getAccount_id() { return account_id; }
    public void setAccount_id(Integer account_id) { this.account_id = account_id; }

    public Integer getCustomer_id() { return customer_id; }
    public void setCustomer_id(Integer customer_id) { this.customer_id = customer_id; }

    public String getBank_name() { return bank_name; }
    public void setBank_name(String bank_name) { this.bank_name = bank_name; }

    public String getBank_account() { return bank_account; }
    public void setBank_account(String bank_account) { this.bank_account = bank_account; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreated_at() { return created_at; }
    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }

    public Timestamp getUpdated_at() { return updated_at; }
    public void setUpdated_at(Timestamp updated_at) { this.updated_at = updated_at; }

    public Integer getContract_id() { return contract_id; }
    public void setContract_id(Integer contract_id) { this.contract_id = contract_id; }

    @Override
    public String toString() {
        return "AutoPaymentModel [account_id=" + account_id + ", customer_id=" + customer_id
                + ", bank_name=" + bank_name + ", bank_account=" + bank_account
                + ", status=" + status + ", created_at=" + created_at
                + ", updated_at=" + updated_at + ", contract_id=" + contract_id + "]";
    }
}