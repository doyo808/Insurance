package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProductPaymentCycleModel {
    private Integer product_payment_cycle_id;
    private Integer product_id;
    private Integer payment_cycle_month;  // 납입주기 (월 단위)
    private Date created_at;

    public ProductPaymentCycleModel(Integer product_payment_cycle_id, Integer product_id, Integer payment_cycle_month, Date created_at) {
        this.product_payment_cycle_id = product_payment_cycle_id;
        this.product_id = product_id;
        this.payment_cycle_month = payment_cycle_month;
        this.created_at = created_at;
    }

    public ProductPaymentCycleModel(ResultSet rs) throws SQLException {
        this.product_payment_cycle_id = rs.getInt("product_payment_cycle_id");
        this.product_id = rs.getInt("product_id");
        this.payment_cycle_month = rs.getInt("payment_cycle_month");
        this.created_at = rs.getDate("created_at");
    }

    public Integer getProduct_payment_cycle_id() {
        return product_payment_cycle_id;
    }

    public void setProduct_payment_cycle_id(Integer product_payment_cycle_id) {
        this.product_payment_cycle_id = product_payment_cycle_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getPayment_cycle_month() {
        return payment_cycle_month;
    }

    public void setPayment_cycle_month(Integer payment_cycle_month) {
        this.payment_cycle_month = payment_cycle_month;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "ProductPaymentCycleModel [product_payment_cycle_id=" + product_payment_cycle_id 
                + ", product_id=" + product_id 
                + ", payment_cycle_month=" + payment_cycle_month 
                + ", created_at=" + created_at + "]";
    }
}
