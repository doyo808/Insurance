package common.database.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewClaimDataModel {
	
	// 청구 대상 관련
    private boolean isSelf;
    private String name;
    private String personalId;
    private String phoneNumber;
    
    // 청구 날짜 - 오늘 날짜
	private Date claimDate;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	{
		try {
			claimDate = sdf.parse(sdf.format(new Date()));
			} catch(ParseException e) {
				e.printStackTrace();
				claimDate = new Date(); // 예외 발생 시 현재 날짜로 처리
			}
	}
    
    // 사고 날짜
    private Date accidentDate;
    
    // 보상 구분 (인물인지:H / 재산인지:P)
    private Character compensationType;
    
    // 청구 항목
    private List<String> claimType;

    // 청구 유형 (질병/일반상해/교통사고)
    private String claimCategory;
    
    // 진단코드 
    private String diagnosis;
    
    // 사고 내용
    private String accidentDescription;
    
    // 직원누구하지
    
    // 청구상태 - 처음 접수할때는 다 RECEIVED
    private String claimStatus = "RECEIVED"; // 접수
    
    // 수령 계좌
    private String bankAccount; // 계좌번호
    private String bankName; // 은행명
    private String beneficiaryName; // 예금주
    
    // 제출 서류 목록
    private List<String> documents = new ArrayList<>();
	
    public NewClaimDataModel() {
    	
    }

	public boolean isSelf() {
		return isSelf;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}

	public Date getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}

	public Character getCompensationType() {
		return compensationType;
	}

	public void setCompensationType(Character compensationType) {
		this.compensationType = compensationType;
	}

	public List<String> getClaimType() {
		return claimType;
	}

	public void setClaimType(List<String> claimType) {
		this.claimType = claimType;
	}

	public String getClaimCategory() {
		return claimCategory;
	}

	public void setClaimCategory(String claimCategory) {
		this.claimCategory = claimCategory;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getAccidentDescription() {
		return accidentDescription;
	}

	public void setAccidentDescription(String accidentDescription) {
		this.accidentDescription = accidentDescription;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String bankAccountName) {
		this.beneficiaryName = bankAccountName;
	}

	public List<String> getDocuments() {
		return documents;
	}

	public void setDocuments(List<String> documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return "NewClaimDataModel [isSelf=" + isSelf + ", name=" + name + ", personalId=" + personalId
				+ ", phoneNumber=" + phoneNumber + ", claimDate=" + claimDate + ", accidentDate=" + accidentDate
				+ ", compensationType=" + compensationType + ", claimType=" + claimType + ", claimCategory="
				+ claimCategory + ", diagnosis=" + diagnosis + ", accidentDescription=" + accidentDescription
				+ ", claimStatus=" + claimStatus + ", bankAccount=" + bankAccount + ", bankName=" + bankName
				+ ", bankAccountName=" + beneficiaryName + ", documents=" + documents + "]";
	}

    

}
