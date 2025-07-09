package insurance.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PaymentModel {
	
	// 결제 내역을 가져오기 위한 Model 클래스임

	private Integer payment_id;
	private Integer customer_id;
	private Integer contract_id;	
	private Integer account_id;
	
	private Integer unpaid_id;
	private Date payment_date;
	private String pay_method;
	private String pay_status;
	
	public PaymentModel(Integer payment_id, Integer customer_id, Integer contract_id, Integer account_id,
			Integer unpaid_id, Date payment_date, String pay_method, String pay_status) {
		super();
		this.payment_id = payment_id;
		this.customer_id = customer_id;
		this.contract_id = contract_id;
		this.account_id = account_id;
		this.unpaid_id = unpaid_id;
		this.payment_date = payment_date;
		this.pay_method = pay_method;
		this.pay_status = pay_status;
	}
	
	public PaymentModel(ResultSet rs) throws SQLException {
		this.payment_id = rs.getInt("payment_id");
		this.customer_id = rs.getInt("customer_id");
		this.contract_id = rs.getInt("contract_id");
		this.account_id = rs.getInt("account_id");
		this.unpaid_id = rs.getInt("unpaid_id");
		this.payment_date = rs.getDate("payment_date");
		this.pay_method = rs.getString("pay_method");
		this.pay_status = rs.getString("pay_status");
	}
	
	public Integer getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getContract_id() {
		return contract_id;
	}

	public void setContract_id(Integer contract_id) {
		this.contract_id = contract_id;
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public Integer getUnpaid_id() {
		return unpaid_id;
	}

	public void setUnpaid_id(Integer unpaid_id) {
		this.unpaid_id = unpaid_id;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

}

