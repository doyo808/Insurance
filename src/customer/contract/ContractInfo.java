package customer.contract;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ContractInfo {
	// 계약정보
    private String  selectedProductName;     	// 선택된 상품 이름
    private Integer product_payment_period; 	// 납입기간
	private Integer product_coverage_period;	// 보증기간
    private LocalDate  createdDate;         	// 생성일 (yyyy-MM-dd 등)
    private Double  premium;               	 	// 보험료
    private Double  premium_final;
    
    // 피보험자 정보
    private String insuredName;                 // 이름
    private String insuredGender;               // 성별 (M/F)
    private String insuredJumin;		        // 주민등록번호 (901231-1234567)
    private LocalDate insuredBirth;           	// 생년월일 (yyyy-MM-dd)
    private Integer insuredAge;                 // 나이
    private String insuredSmoke;                // 흡연 여부 ("Y"/"N")
    private String insuredDrink;                // 음주 여부 ("Y"/"N")
    private Integer insuredDrive;               // 운전 여부 ("0", "1", "2", "3")
    private String insuredDisease;    	        // 병력 첨부파일 경로

    // 수익자 정보
    private String beneficiaryName;             // 수익자 이름
    private String relationship;				// 계약자와의 관계("본인", "배우자", "자녀", "부모님")
    private String beneficiaryBank;             // 수익자 은행명
    private String beneficiaryAccount;          // 수익자 계좌번호

    // TODO: 고객 ID는 세션에서 별도 처리 (ex: String customerId)
    
	public void clear() {
		selectedProductName = null;
	    product_payment_period = null;
	    product_coverage_period = null;
	    createdDate = null;
	    premium = null;

	    insuredName = null;
	    insuredGender = null;
	    insuredJumin = null;
	    insuredBirth = null;
	    insuredAge = null;
	    insuredSmoke = null;
	    insuredDrink = null;
	    insuredDrive = null;
	    insuredDisease = null;

	    beneficiaryName = null;
	    relationship = null;
	    beneficiaryBank = null;
	    beneficiaryAccount = null;
	    selectedCoverageNames.clear();
	}

	// 커버리지 관련
	private List<String> selectedCoverageNames;

	public void setSelectedCoverageNames(List<String> coverages) {
	    this.selectedCoverageNames = coverages;
	}

	public List<String> getSelectedCoverageNames() {
	    return selectedCoverageNames;
	}
	
	/// FIXME: 게터, 세터
	public String getSelectedProductName() {
		return selectedProductName;
	}

	public void setSelectedProductName(String selectedProductName) {
		this.selectedProductName = selectedProductName;
	}

	public Integer getProduct_payment_period() {
		return product_payment_period;
	}

	public void setProduct_payment_period(Integer product_payment_period) {
		this.product_payment_period = product_payment_period;
	}

	public Integer getProduct_coverage_period() {
		return product_coverage_period;
	}

	public void setProduct_coverage_period(Integer product_coverage_period) {
		this.product_coverage_period = product_coverage_period;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = premium;
	}

	public Double getPremium_final() {
		return premium_final;
	}

	public void setPremium_final(Double premium_final) {
		this.premium_final = premium_final;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getInsuredGender() {
		return insuredGender;
	}

	public void setInsuredGender(String insuredGender) {
		this.insuredGender = insuredGender;
	}

	public String getInsuredJumin() {
		return insuredJumin;
	}

	public void setInsuredJumin(String insuredJumin) {
		this.insuredJumin = insuredJumin;
	}

	public LocalDate getInsuredBirth() {
		return insuredBirth;
	}

	public void setInsuredBirth(LocalDate insuredBirth) {
		this.insuredBirth = insuredBirth;
	}

	public Integer getInsuredAge() {
		return insuredAge;
	}

	public void setInsuredAge(Integer insuredAge) {
		this.insuredAge = insuredAge;
	}

	public String getInsuredSmoke() {
		return insuredSmoke;
	}

	public void setInsuredSmoke(String insuredSmoke) {
		this.insuredSmoke = insuredSmoke;
	}

	public String getInsuredDrink() {
		return insuredDrink;
	}

	public void setInsuredDrink(String insuredDrink) {
		this.insuredDrink = insuredDrink;
	}

	public Integer getInsuredDrive() {
		return insuredDrive;
	}

	public void setInsuredDrive(Integer insuredDrive) {
		this.insuredDrive = insuredDrive;
	}

	public String getInsuredDisease() {
		return insuredDisease;
	}

	public void setInsuredDisease(String insuredDisease) {
		this.insuredDisease = insuredDisease;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public String getBeneficiaryBank() {
		return beneficiaryBank;
	}

	public void setBeneficiaryBank(String beneficiaryBank) {
		this.beneficiaryBank = beneficiaryBank;
	}

	public String getBeneficiaryAccount() {
		return beneficiaryAccount;
	}

	public void setBeneficiaryAccount(String beneficiaryAccount) {
		this.beneficiaryAccount = beneficiaryAccount;
	}

	@Override
	public String toString() {
		return String.format(
				"ContractInfo [selectedProductName=%s, product_payment_period=%s, product_coverage_period=%s"
				+ ", createdDate=%s\n, premium=%s, premium_final=%s, insuredName=%s, insuredGender=%s, insuredJumin=%s"
				+ ", insuredBirth=%s, insuredAge=%s, insuredSmoke=%s, insuredDrink=%s, insuredDrive=%s"
				+ ", insuredDisease=%s\n, beneficiaryName=%s, relationship=%s, beneficiaryBank=%s, beneficiaryAccount=%s"
				+ ", selectedCoverageNames=%s]",
				selectedProductName, product_payment_period, product_coverage_period, createdDate, premium,
				premium_final, insuredName, insuredGender, insuredJumin, insuredBirth, insuredAge, insuredSmoke,
				insuredDrink, insuredDrive, insuredDisease, beneficiaryName, relationship, beneficiaryBank,
				beneficiaryAccount, selectedCoverageNames);
	}



	
	
}
