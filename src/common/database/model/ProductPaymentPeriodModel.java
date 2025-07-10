package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductPaymentPeriodModel {
    private Integer product_payment_period_id;
    private Integer product_id;
    private Integer payment_period;

    public ProductPaymentPeriodModel(Integer product_payment_period_id, Integer product_id, Integer payment_period) {
        this.product_payment_period_id = product_payment_period_id;
        this.product_id = product_id;
        this.payment_period = payment_period;
    }

    public ProductPaymentPeriodModel(ResultSet rs) throws SQLException {
        this.product_payment_period_id = rs.getInt("product_payment_period_id");
        this.product_id = rs.getInt("product_id");
        this.payment_period = rs.getInt("payment_period");
    }

    public Integer getProduct_payment_period_id() {
        return product_payment_period_id;
    }

    public void setProduct_payment_period_id(Integer product_payment_period_id) {
        this.product_payment_period_id = product_payment_period_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getPayment_period() {
        return payment_period;
    }

    public void setPayment_period(Integer payment_period) {
        this.payment_period = payment_period;
    }

    @Override
    public String toString() {
        return "ProductPaymentPeriodModel [product_payment_period_id=" + product_payment_period_id
                + ", product_id=" + product_id + ", payment_period=" + payment_period + "]";
    }
}
