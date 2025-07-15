package common.database.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewClaimDataModel {
	
	  // 청구 대상 관련
    private boolean isSelf;
    private String name;
    private String personalId;
    private String phoneNumber;

    // 사고 정보
    private Date accidentDate;

    // 청구 상황
    private String claimSituation;
    private String claimType;

    // 수령 계좌
    private String bankAccount;

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

	public Date getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}

	public String getClaimSituation() {
		return claimSituation;
	}

	public void setClaimSituation(String claimSituation) {
		this.claimSituation = claimSituation;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
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
				+ ", phoneNumber=" + phoneNumber + ", accidentDate=" + accidentDate + ", claimSituation="
				+ claimSituation + ", claimType=" + claimType + ", bankAccount=" + bankAccount + ", documents="
				+ documents + "]";
	}

	
    
}
