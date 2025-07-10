package common.database.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {
    private Integer payment_id;
    private Integer customer_id;
    private Integer contract_id;
    private Integer unpaid_id;
    private Double paid_amount;
    private Date payment_date;
    private Integer account_id;
    private String pay_status;

    public PaymentModel(Integer payment_id, Integer customer_id, Integer contract_id,
                        Integer unpaid_id, Double paid_amount, Date payment_date,
                        Integer account_id, String pay_status) {
        this.payment_id = payment_id;
        this.customer_id = customer_id;
        this.contract_id = contract_id;
        this.unpaid_id = unpaid_id;
        this.paid_amount = paid_amount;
        this.payment_date = payment_date;
        this.account_id = account_id;
        this.pay_status = pay_status;
    }

    public PaymentModel(ResultSet rs) throws SQLException {
        this.payment_id = rs.getInt("payment_id");
        this.customer_id = rs.getInt("customer_id");
        this.contract_id = rs.getInt("contract_id");
        this.unpaid_id = rs.getInt("unpaid_id");
        this.paid_amount = rs.getDouble("paid_amount");
        this.payment_date = rs.getDate("payment_date");
        this.account_id = rs.getInt("account_id");
        this.pay_status = rs.getString("pay_status");
    }

    // Getter & Setter 생략 가능
    public Integer getPayment_id() { return payment_id; }
    public void setPayment_id(Integer payment_id) { this.payment_id = payment_id; }
    public Integer getCustomer_id() { return customer_id; }
    public void setCustomer_id(Integer customer_id) { this.customer_id = customer_id; }
    public Integer getContract_id() { return contract_id; }
    public void setContract_id(Integer contract_id) { this.contract_id = contract_id; }
    public Integer getUnpaid_id() { return unpaid_id; }
    public void setUnpaid_id(Integer unpaid_id) { this.unpaid_id = unpaid_id; }
    public Double getPaid_amount() { return paid_amount; }
    public void setPaid_amount(Double paid_amount) { this.paid_amount = paid_amount; }
    public Date getPayment_date() { return payment_date; }
    public void setPayment_date(Date payment_date) { this.payment_date = payment_date; }
    public Integer getAccount_id() { return account_id; }
    public void setAccount_id(Integer account_id) { this.account_id = account_id; }
    public String getPay_status() { return pay_status; }
    public void setPay_status(String pay_status) { this.pay_status = pay_status; }

    @Override
    public String toString() {
        return "PaymentModel [payment_id=" + payment_id + ", customer_id=" + customer_id +
                ", contract_id=" + contract_id + ", unpaid_id=" + unpaid_id +
                ", paid_amount=" + paid_amount + ", payment_date=" + payment_date +
                ", account_id=" + account_id + ", pay_status=" + pay_status + "]";
    }
}
