package customer.contract;

public class ContractInfo {
	// 여기에 담길 정보: 
	// 1. 계약에 선택된 상품
	// 2. 납입기간
	// 3. 보증기간
	// 4. 생성일
	// 5. 보험료
	
	// 6. 피보험자의 정보들
	// 7. 피보험자 이름/ 성별/ 주민등록번호(생일,나이) / 음주여부/ 흡연여부/ 운전여부/ 병력
	// 8. 수익자에 대한 정보들
	// 9. 수익자 이름/ 주민등록번호/ 전화번호/ 은행이름/ 계좌번호
	// 고객아이디는 세션에 있음
	
	private Integer product_payment_period;
	private Integer product_coverage_period;
	
	
	
	public int getProduct_payment_period() {
		return product_payment_period;
	}
	public void setProduct_payment_period(int product_payment_period) {
		this.product_payment_period = product_payment_period;
	}
	public int getProduct_coverage_period() {
		return product_coverage_period;
	}
	public void setProduct_coverage_period(int product_coverage_period) {
		this.product_coverage_period = product_coverage_period;
	}
	
	public void clear() {
		product_payment_period = null;
		product_coverage_period = null;
	}
	
}
