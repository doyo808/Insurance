package common.database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class InquireModel {
    // "구분\t상품이름\t납입월분\t입금일자\t납입횟수\t대상보험료\t실입금액\t입금방법"
    private Integer count;
    private String product_name;
    private Date unpaid_date;
    private Date payment_date;
    private Integer unpaid_count;
    private Integer unpaid_amount;
    private Double paid_amount;
    private String paid_method;

    // 생성자
    public InquireModel(Integer count, String product_name, Date unpaid_date, Date payment_date,
                        Integer unpaid_count, Integer unpaid_amount, Double paid_amount, String paid_method) {
        this.count = count;
        this.product_name = product_name;
        this.unpaid_date = unpaid_date;
        this.payment_date = payment_date;
        this.unpaid_count = unpaid_count;
        this.unpaid_amount = unpaid_amount;
        this.paid_amount = paid_amount;
        this.paid_method = paid_method;
    }
    
    public InquireModel(ResultSet rs) throws SQLException {
        this.count = rs.getInt("count");
        this.product_name = rs.getString("product_name");
        this.unpaid_date = rs.getDate("unpaid_date");
        this.payment_date = rs.getDate("payment_date");
        this.unpaid_count = rs.getInt("unpaid_count");
        this.unpaid_amount = rs.getInt("unpaid_amount");
        this.paid_amount = rs.getDouble("paid_amount");
        if (rs.getInt("account_id") == -1) {
        	this.paid_method = "단건이체";
        } else {
        	this.paid_method = "계좌이체";
        }
        
    }


    // Getter/Setter
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Date getUnpaid_date() {
        return unpaid_date;
    }

    public void setUnpaid_date(Date unpaid_date) {
        this.unpaid_date = unpaid_date;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public Integer getUnpaid_count() {
        return unpaid_count;
    }

    public void setUnpaid_count(Integer unpaid_count) {
        this.unpaid_count = unpaid_count;
    }

    public Integer getUnpaid_amount() {
        return unpaid_amount;
    }

    public void setUnpaid_amount(Integer unpaid_amount) {
        this.unpaid_amount = unpaid_amount;
    }

    public Double getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(Double paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getPaid_method() {
        return paid_method;
    }

    public void setPaid_method(String paid_method) {
        this.paid_method = paid_method;
    }

    // toString
    @Override
    public String toString() {
        return "InquireModel [count=" + count +
                ", product_name=" + product_name +
                ", unpaid_date=" + unpaid_date +
                ", payment_date=" + payment_date +
                ", unpaid_count=" + unpaid_count +
                ", unpaid_amount=" + unpaid_amount +
                ", paid_amount=" + paid_amount +
                ", paid_method=" + paid_method + "]";
    }
}

