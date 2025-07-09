package insurance.mypage.user.vo;

public class ContractVO {
	private int contract_id;
	private int customer_id;
	private int insured_id;
	private int beneficiary_id;
	private int product_id;
	private int signup_date;
	private int effective_date;
	private int created_date;
	private int payment_end_date;
	private int coverage_end_date;
	private int status;
	private int premium;
	private int product_payment_cycle_id;
	
	public ContractVO() {
		
	}
	
	public ContractVO(int contract_id, int customer_id, int insured_id, int beneficiary_id, int product_id,
			int signup_date, int effective_date, int created_date, int payment_end_date, int coverage_end_date,
			int status, int premium, int product_payment_cycle_id) {
		super();
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

	public int getContract_id() {
		return contract_id;
	}

	public void setContract_id(int contract_id) {
		this.contract_id = contract_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getInsured_id() {
		return insured_id;
	}

	public void setInsured_id(int insured_id) {
		this.insured_id = insured_id;
	}

	public int getBeneficiary_id() {
		return beneficiary_id;
	}

	public void setBeneficiary_id(int beneficiary_id) {
		this.beneficiary_id = beneficiary_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getSignup_date() {
		return signup_date;
	}

	public void setSignup_date(int signup_date) {
		this.signup_date = signup_date;
	}

	public int getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(int effective_date) {
		this.effective_date = effective_date;
	}

	public int getCreated_date() {
		return created_date;
	}

	public void setCreated_date(int created_date) {
		this.created_date = created_date;
	}

	public int getPayment_end_date() {
		return payment_end_date;
	}

	public void setPayment_end_date(int payment_end_date) {
		this.payment_end_date = payment_end_date;
	}

	public int getCoverage_end_date() {
		return coverage_end_date;
	}

	public void setCoverage_end_date(int coverage_end_date) {
		this.coverage_end_date = coverage_end_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPremium() {
		return premium;
	}

	public void setPremium(int premium) {
		this.premium = premium;
	}

	public int getProduct_payment_cycle_id() {
		return product_payment_cycle_id;
	}

	public void setProduct_payment_cycle_id(int product_payment_cycle_id) {
		this.product_payment_cycle_id = product_payment_cycle_id;
	}
	
	
	
	

}
