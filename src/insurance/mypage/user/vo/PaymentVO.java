package insurance.mypage.user.vo;

public class PaymentVO {
	private int payment_id;
	private int customer_id;
	private int contract_id;
	private int account_id;
	private int unpaid_id;
	private int paid_amount;
	private int payment_date;
	private int pay_method;
	private int pay_status;
	
	public PaymentVO() {
		
	}
	
	public PaymentVO(int payment_id, int customer_id, int contract_id, int account_id, int unpaid_id, int paid_amount,
			int payment_date, int pay_method, int pay_status) {
		super();
		this.payment_id = payment_id;
		this.customer_id = customer_id;
		this.contract_id = contract_id;
		this.account_id = account_id;
		this.unpaid_id = unpaid_id;
		this.paid_amount = paid_amount;
		this.payment_date = payment_date;
		this.pay_method = pay_method;
		this.pay_status = pay_status;
	}
	
	

}
