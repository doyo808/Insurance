package common.database.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AutoPaymentModel {

    private Integer accountId;
    private Integer customerId;
    private String bankName;
    private String bankAccount;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public AutoPaymentModel(Integer accountId, Integer customerId, String bankName, String bankAccount,
                             String status, Date createdAt, Date updatedAt) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AutoPaymentModel(ResultSet rs) throws SQLException {
        this.accountId = rs.getInt("account_id");
        this.customerId = rs.getInt("customer_id");
        this.bankName = rs.getString("bank_name");
        this.bankAccount = rs.getString("bank_account");
        this.status = rs.getString("status");
        this.createdAt = rs.getDate("created_at");
        this.updatedAt = rs.getDate("updated_at");
    }

    // Getters and setters
    public Integer getAccountId() { return accountId; }
    public void setAccountId(Integer accountId) { this.accountId = accountId; }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getBankAccount() { return bankAccount; }
    public void setBankAccount(String bankAccount) { this.bankAccount = bankAccount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "AutoPaymentModel [accountId=" + accountId + ", customerId=" + customerId + ", bankName=" + bankName
                + ", bankAccount=" + bankAccount + ", status=" + status + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + "]";
    }
}