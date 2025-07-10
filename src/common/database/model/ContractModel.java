package common.database.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractModel {
    private Integer contract_id;
    private Integer customer_id;
    private Integer insured_id;
    private Integer beneficiary_id;
    private Integer product_id;
    private Date signup_date;
    private Date effective_date;
    private Date created_date;
    private Date payment_end_date;
    private Date coverage_end_date;
    private String status;
    private Double premium;
    private Integer product_payment_cycle_id;

    public ContractModel(Integer contract_id, Integer customer_id, Integer insured_id, Integer beneficiary_id,
                         Integer product_id, Date signup_date, Date effective_date, Date created_date,
                         Date payment_end_date, Date coverage_end_date, String status, Double premium,
                         Integer product_payment_cycle_id) {
        this.contract_id = contract_id;
        this.customer_id = customer_id;
        this.insured_id = insured_id;
        this.beneficiary_id = beneficiary_id;
        this.product_id = product_id;
        this.signup_date = signup_date;
        this.effective_date = effective_date;
        this.created_date = created_date;
        this.payment_end_date = payment_end_date;
        this.coverage_end_date = coverage_end_date;
        this.status = status;
        this.premium = premium;
        this.product_payment_cycle_id = product_payment_cycle_id;
    }

    public ContractModel(ResultSet rs) throws SQLException {
        this.contract_id = rs.getInt("contract_id");
        this.customer_id = rs.getInt("customer_id");
        this.insured_id = rs.getInt("insured_id");
        this.beneficiary_id = rs.getInt("beneficiary_id");
        this.product_id = rs.getInt("product_id");
        this.signup_date = rs.getDate("signup_date");
        this.effective_date = rs.getDate("effective_date");
        this.created_date = rs.getDate("created_date");
        this.payment_end_date = rs.getDate("payment_end_date");
        this.coverage_end_date = rs.getDate("coverage_end_date");
        this.status = rs.getString("status");
        this.premium = rs.getDouble("premium");
        this.product_payment_cycle_id = rs.getInt("product_payment_cycle_id");
    }

    public Integer getContract_id() {
        return contract_id;
    }

    public void setContract_id(Integer contract_id) {
        this.contract_id = contract_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getInsured_id() {
        return insured_id;
    }

    public void setInsured_id(Integer insured_id) {
        this.insured_id = insured_id;
    }

    public Integer getBeneficiary_id() {
        return beneficiary_id;
    }

    public void setBeneficiary_id(Integer beneficiary_id) {
        this.beneficiary_id = beneficiary_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Date getSignup_date() {
        return signup_date;
    }

    public void setSignup_date(Date signup_date) {
        this.signup_date = signup_date;
    }

    public Date getEffective_date() {
        return effective_date;
    }

    public void setEffective_date(Date effective_date) {
        this.effective_date = effective_date;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getPayment_end_date() {
        return payment_end_date;
    }

    public void setPayment_end_date(Date payment_end_date) {
        this.payment_end_date = payment_end_date;
    }

    public Date getCoverage_end_date() {
        return coverage_end_date;
    }

    public void setCoverage_end_date(Date coverage_end_date) {
        this.coverage_end_date = coverage_end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public Integer getProduct_payment_cycle_id() {
        return product_payment_cycle_id;
    }

    public void setProduct_payment_cycle_id(Integer product_payment_cycle_id) {
        this.product_payment_cycle_id = product_payment_cycle_id;
    }

    @Override
    public String toString() {
        return "ContractModel [contract_id=" + contract_id + ", customer_id=" + customer_id + ", insured_id=" + insured_id
                + ", beneficiary_id=" + beneficiary_id + ", product_id=" + product_id + ", signup_date=" + signup_date
                + ", effective_date=" + effective_date + ", created_date=" + created_date + ", payment_end_date=" + payment_end_date
                + ", coverage_end_date=" + coverage_end_date + ", status=" + status + ", premium=" + premium
                + ", product_payment_cycle_id=" + product_payment_cycle_id + "]";
    }
}
