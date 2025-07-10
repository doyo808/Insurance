package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UnpaidModel {
    private Integer unpaid_id;
    private Integer customer_id;
    private Integer contract_id;
    private Double unpaid_amount;
    private Date unpaid_date;
    private Integer unpaid_count;
    private Date payment_deadline;

    public UnpaidModel(Integer unpaid_id, Integer customer_id, Integer contract_id,
                       Double unpaid_amount, Date unpaid_date,
                       Integer unpaid_count, Date payment_deadline) {
        this.unpaid_id = unpaid_id;
        this.customer_id = customer_id;
        this.contract_id = contract_id;
        this.unpaid_amount = unpaid_amount;
        this.unpaid_date = unpaid_date;
        this.unpaid_count = unpaid_count;
        this.payment_deadline = payment_deadline;
    }

    public UnpaidModel(ResultSet rs) throws SQLException {
        this.unpaid_id = rs.getInt("unpaid_id");
        this.customer_id = rs.getInt("customer_id");
        this.contract_id = rs.getInt("contract_id");
        this.unpaid_amount = rs.getDouble("unpaid_amount");
        this.unpaid_date = rs.getDate("unpaid_date");
        this.unpaid_count = rs.getInt("unpaid_count");
        this.payment_deadline = rs.getDate("payment_deadline");
    }

    // Getter/Setter
    public Integer getUnpaidId() {
        return unpaid_id;
    }

    public void setUnpaidId(Integer unpaid_id) {
        this.unpaid_id = unpaid_id;
    }

    public Integer getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getContractId() {
        return contract_id;
    }

    public void setContractId(Integer contract_id) {
        this.contract_id = contract_id;
    }

    public Double getUnpaidAmount() {
        return unpaid_amount;
    }

    public void setUnpaidAmount(Double unpaid_amount) {
        this.unpaid_amount = unpaid_amount;
    }

    public Date getUnpaidDate() {
        return unpaid_date;
    }

    public void setUnpaidDate(Date unpaid_date) {
        this.unpaid_date = unpaid_date;
    }

    public Integer getUnpaidCount() {
        return unpaid_count;
    }

    public void setUnpaidCount(Integer unpaid_count) {
        this.unpaid_count = unpaid_count;
    }

    public Date getPaymentDeadline() {
        return payment_deadline;
    }

    public void setPaymentDeadline(Date payment_deadline) {
        this.payment_deadline = payment_deadline;
    }

    @Override
    public String toString() {
        return "UnpaidModel [unpaid_id=" + unpaid_id +
               ", customer_id=" + customer_id +
               ", contract_id=" + contract_id +
               ", unpaid_amount=" + unpaid_amount +
               ", unpaid_date=" + unpaid_date +
               ", unpaid_count=" + unpaid_count +
               ", payment_deadline=" + payment_deadline + "]";
    }
}