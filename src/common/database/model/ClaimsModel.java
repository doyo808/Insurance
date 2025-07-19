package common.database.model;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Date;

public class ClaimsModel {
	// --------------------- ClaimsModel에 더 추가해야겠는데 ----------------------------------
			private Integer claim_id;
			private Integer contract_id;
			private Date claim_date;
			private Date accident_date;
			private String compensation_type; // rs.getCharacter이 없어서 여기서 String으로 
			private String claim_type_cd;
			private Integer employee_id;
			private String claim_status;
			private Date completion_date;
			private Integer total_paid_amount;
			private String claim_category;
			private String claim_description;
			private String diagnosis_cd;
			private String bank_name;
			private String bank_account;
			private String beneficiary_name;
			
			private String diagnosis_name;
			private String insured_name;
			private String product_name;
			private String employee_name;
			
			// 생성자
			public ClaimsModel(ResultSet rs) throws SQLException {
				this.claim_id = rs.getInt("claim_id");
				this.contract_id = rs.getInt("contract_id");
				this.claim_date = rs.getDate("claim_date");
				this.accident_date = rs.getDate("accident_date");
				this.compensation_type = rs.getString("compensation_type"); // rs.getCharacter이 없어서 위에서 String으로 
				this.claim_type_cd = rs.getString("claim_type_cd");
				this.employee_id = rs.getInt("employee_id");
				this.claim_status = rs.getString("claim_status");
				this.completion_date = rs.getDate("completion_date");
				this.total_paid_amount = rs.getInt("total_paid_amount");
				this.claim_category = rs.getString("claim_category");
				this.claim_description = rs.getString("claim_description");
				this.diagnosis_cd = rs.getString("diagnosis_cd");
				this.bank_name = rs.getString("bank_name");
				this.bank_account = rs.getString("bank_account");
				this.beneficiary_name = rs.getString("beneficiary_name");
				
				this.diagnosis_name = rs.getString("diagnosis_name");
			    this.insured_name = rs.getString("insured_name");
			    this.product_name = rs.getString("product_name");
			    this.employee_name = rs.getString("employee_name");
			}
			
			// 필요한 필드만 사용하는 생성자 추가
			public ClaimsModel(int claim_id, Date claim_date, String diagnosis_name, String insured_name,
			                   String product_name, String employee_name, String claim_status) {
			    this.claim_id = claim_id;
			    this.claim_date = claim_date;
			    this.diagnosis_name = diagnosis_name;
			    this.insured_name = insured_name;
			    this.product_name = product_name;
			    this.employee_name = employee_name;
			    this.claim_status = claim_status;
			}

			public Integer getClaim_id() {
				return claim_id;
			}

			public void setClaim_id(Integer claim_id) {
				this.claim_id = claim_id;
			}

			public Integer getContract_id() {
				return contract_id;
			}

			public void setContract_id(Integer contract_id) {
				this.contract_id = contract_id;
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

			public String getCompensation_type() {
				return compensation_type;
			}

			public void setCompensation_type(String compensation_type) {
				this.compensation_type = compensation_type;
			}

			public String getClaim_type_cd() {
				return claim_type_cd;
			}

			public void setClaim_type_cd(String claim_type_cd) {
				this.claim_type_cd = claim_type_cd;
			}

			public Integer getEmployee_id() {
				return employee_id;
			}

			public void setEmployee_id(Integer employee_id) {
				this.employee_id = employee_id;
			}

			public String getClaim_status() {
				return claim_status;
			}

			public void setClaim_status(String claim_status) {
				this.claim_status = claim_status;
			}

			public Date getCompletion_date() {
				return completion_date;
			}

			public void setCompletion_date(Date completion_date) {
				this.completion_date = completion_date;
			}

			public Integer getTotal_paid_amount() {
				return total_paid_amount;
			}

			public void setTotal_paid_amount(Integer total_paid_amount) {
				this.total_paid_amount = total_paid_amount;
			}

			public String getClaim_category() {
				return claim_category;
			}

			public void setClaim_category(String claim_category) {
				this.claim_category = claim_category;
			}

			public String getClaim_description() {
				return claim_description;
			}

			public void setClaim_description(String claim_description) {
				this.claim_description = claim_description;
			}

			public String getDiagnosis_cd() {
				return diagnosis_cd;
			}

			public void setDiagnosis_cd(String diagnosis_cd) {
				this.diagnosis_cd = diagnosis_cd;
			}

			public String getBank_name() {
				return bank_name;
			}

			public void setBank_name(String bank_name) {
				this.bank_name = bank_name;
			}

			public String getBank_account() {
				return bank_account;
			}

			public void setBank_account(String bank_account) {
				this.bank_account = bank_account;
			}

			public String getBeneficiary_name() {
				return beneficiary_name;
			}

			public void setBeneficiary_name(String beneficiary_name) {
				this.beneficiary_name = beneficiary_name;
			}

			public String getDiagnosis_name() {
				return diagnosis_name;
			}

			public void setDiagnosis_name(String diagnosis_name) {
				this.diagnosis_name = diagnosis_name;
			}

			public String getInsured_name() {
				return insured_name;
			}

			public void setInsured_name(String insured_name) {
				this.insured_name = insured_name;
			}

			public String getProduct_name() {
				return product_name;
			}

			public void setProduct_name(String product_name) {
				this.product_name = product_name;
			}

			public String getEmployee_name() {
				return employee_name;
			}

			public void setEmployee_name(String employee_name) {
				this.employee_name = employee_name;
			}

			
			@Override
			public String toString() {
				return "ClaimsModel [claim_id=" + claim_id + ", contract_id=" + contract_id + ", claim_date="
						+ claim_date + ", accident_date=" + accident_date + ", compensation_type=" + compensation_type
						+ ", claim_type_cd=" + claim_type_cd + ", employee_id=" + employee_id + ", claim_status="
						+ claim_status + ", completion_date=" + completion_date + ", total_paid_amount="
						+ total_paid_amount + ", claim_category=" + claim_category + ", claim_description="
						+ claim_description + ", diagnosis_cd=" + diagnosis_cd + ", bank_name=" + bank_name
						+ ", bank_account=" + bank_account + ", beneficiary_name=" + beneficiary_name
						+ ", diagnosis_name=" + diagnosis_name + ", insured_name=" + insured_name + ", product_name="
						+ product_name + ", employee_name=" + employee_name + "]";
			}

			
			
			
		
			
			
}
