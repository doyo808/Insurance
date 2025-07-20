package common.database.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 고객이 보험금 신규청구를 할 때 입력한 정보를 담는 Model
public class NewClaimDataModel {
	
	// 청구 대상 관련
    private boolean isSelf;
    private String customer_name;
    private String personal_id;
    private String phone_number;
    
    // 청구 날짜 - 오늘 날짜
	private Date claim_date;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	{
		try {
			claim_date = sdf.parse(sdf.format(new Date()));
			} catch(ParseException e) {
				e.printStackTrace();
				claim_date = new Date(); // 예외 발생 시 현재 날짜로 처리
			}
	}
    
    // 사고 날짜
    private Date accident_date;
    
    // 보상 구분 (인물인지:H / 재산인지:P)
    private Character compensation_type;
    
    // 청구 항목
    private List<String> claim_type_name;

    // 청구 유형 (질병/일반상해/교통사고)
    private String claim_category;
    
    // 진단명
    private String diagnosis_name;
    
    // 사고 내용
    private String accident_description;
    
    // 직원누구하지
    private String employee_name = "이철수";
    
    // 청구상태 - 처음 접수할때는 다 RECEIVED
    private String claim_status = "RECEIVED"; 
    // 마지막 확인패널에서 한글로 나타나게 바꿀 수 있어야 할 것 같다..
    // 조회 때에도 영어가 아닌 한글로 나타날 수 있도록
    
    // 수령 계좌
    private String bank_account; // 계좌번호
    private String bank_name; // 은행명
    private String beneficiary_name; // 예금주
    
    // 제출 서류 목록
    private List<String> document_type_name = new ArrayList<>();
	
    public NewClaimDataModel() {
    	
    }
    
    public void resetAll() {
        isSelf = false;
        customer_name = null;
        personal_id = null;
        phone_number = null;
        accident_date = null;
        compensation_type = null;
        claim_type_name = new ArrayList<String>();
        claim_category = null;
        diagnosis_name = null;
        accident_description = null;
        claim_type_name = new ArrayList<>();
        bank_account = null;
        bank_name = null;
        beneficiary_name = null;
        document_type_name = new ArrayList<String>();
    }

	public boolean isSelf() {
		return isSelf;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getPersonal_id() {
		return personal_id;
	}

	public void setPersonal_id(String personal_id) {
		this.personal_id = personal_id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Date getClaim_date() {
		return claim_date;
	}

	public void setClaim_date(Date claim_date) {
		this.claim_date = claim_date;
	}

	public Date getAccident_date() {
		return accident_date;
	}

	public void setAccident_date(Date accident_date) {
		this.accident_date = accident_date;
	}

	public Character getCompensation_type() {
		return compensation_type;
	}

	public void setCompensation_type(Character compensation_type) {
		this.compensation_type = compensation_type;
	}

	public List<String> getClaim_type_name() {
		return claim_type_name;
	}

	public void setClaim_type_name(List<String> claim_type_name) {
		this.claim_type_name = claim_type_name;
	}

	public String getClaim_category() {
		return claim_category;
	}

	public void setClaim_category(String claim_category) {
		this.claim_category = claim_category;
	}

	public String getDiagnosis_name() {
		return diagnosis_name;
	}

	public void setDiagnosis_name(String diagnosis_name) {
		this.diagnosis_name = diagnosis_name;
	}

	public String getAccident_description() {
		return accident_description;
	}

	public void setAccident_description(String accident_description) {
		this.accident_description = accident_description;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getClaim_status() {
		return claim_status;
	}

	public void setClaim_status(String claim_status) {
		this.claim_status = claim_status;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBeneficiary_name() {
		return beneficiary_name;
	}

	public void setBeneficiary_name(String beneficiary_name) {
		this.beneficiary_name = beneficiary_name;
	}

	public List<String> getDocument_type_name() {
		return document_type_name;
	}

	public void setDocument_type_name(List<String> document_type_name) {
		this.document_type_name = document_type_name;
	}

	@Override
	public String toString() {
		return "NewClaimDataModel [isSelf=" + isSelf + ", customer_name=" + customer_name + ", personal_id="
				+ personal_id + ", phone_number=" + phone_number + ", claim_date=" + claim_date + ","
				+ " accident_date=" + accident_date + ", compensation_type=" + compensation_type + ", claim_type_name="
				+ claim_type_name + ", claim_category=" + claim_category + ", diagnosis_name=" + diagnosis_name
				+ ", accident_description=" + accident_description + ", employee_name=" + employee_name
				+ ", claim_status=" + claim_status + ", bank_account=" + bank_account + ", bank_name=" + bank_name
				+ ", beneficiary_name=" + beneficiary_name + ", document_type_name=" + document_type_name + "]";
	}
	
}
