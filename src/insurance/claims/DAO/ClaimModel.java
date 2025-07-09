package insurance.claims.DAO;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Date;

public class ClaimModel {
			

			private Integer claim_id;
			private Integer contract_id;
			private Date claim_date;
			private Date incident_date;
			private String compensation_type; // rs.getCharacter이 없어서 여기서 String으로 
			private String claim_type_cd;
			private String claim_category;
			private String claim_description;
			private Integer employee_id;
			private String claim_status;
			private Date completion_date;
			private Integer total_paid_amount;
			
			// 생성자
			public ClaimModel(ResultSet rs) throws SQLException {
				this.claim_id = rs.getInt("claim_id");
				this.contract_id = rs.getInt("contract_id");
				this.claim_date = rs.getDate("claim_date");
				this.incident_date = rs.getDate("incident_date");
				this.compensation_type = rs.getString("compensation_type"); // rs.getCharacter이 없어서 위에서 String으로 
				this.claim_type_cd = rs.getString("claim_type_cd");
				this.claim_category = rs.getString("claim_category");
				this.claim_description = rs.getString("claim_description");
				this.employee_id = rs.getInt("employee_id");
				this.claim_status = rs.getString("claim_status");
				this.completion_date = rs.getDate("completion_date");
				this.total_paid_amount = rs.getInt("total_paid_amount");
			}
		
			
			// 출력
			@Override
			public String toString() {
				return "ClaimModel [claim_id=" + claim_id + ", contract_id=" + contract_id + ", claim_date="
						+ claim_date + ", incident_date=" + incident_date + ", compensation_type=" + compensation_type
						+ ", claim_type_cd=" + claim_type_cd + ", claim_category=" + claim_category
						+ ", claim_description=" + claim_description + ", employee_id=" + employee_id
						+ ", claim_status=" + claim_status + ", completion_date=" + completion_date
						+ ", total_paid_amount=" + total_paid_amount + "]";
			}

			// 게터&세터
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

			public Date getIncident_date() {
				return incident_date;
			}

			public void setIncident_date(Date incident_date) {
				this.incident_date = incident_date;
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
}
