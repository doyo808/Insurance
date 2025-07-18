package customer.contract.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import common.database.dao.ProductDAO;
import customer.contract.ContractInfo;

public class Insert {
	
	public static int insertInsured(ContractInfo ci, Connection conn) {
		String query = "INSERT INTO insureds (insured_id, insured_name, personal_id, gender, birth_date"
				+ ", age, is_smoker, drinks, driving_status, medical_history) "
				+ "VALUES (seq_insureds_id.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(query, new String[] { "insured_id" })) {
			pstmt.setString(1, ci.getInsuredName());
			pstmt.setString(2, ci.getInsuredJumin());
			pstmt.setString(3, ci.getInsuredGender());
			pstmt.setDate(4, java.sql.Date.valueOf(ci.getInsuredBirth()));
			pstmt.setInt(5, ci.getInsuredAge());
			pstmt.setString(6, ci.getInsuredSmoke());
			pstmt.setString(7, ci.getInsuredDrink());
			pstmt.setInt(8, ci.getInsuredDrive());
			pstmt.setString(9, ci.getInsuredDisease());
			
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0) { throw new SQLException("피보험자 입력 실패!!"); }
			
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) { return rs.getInt(1); } 
				else { throw new SQLException("피보험자 ID를 가져오지 못했음"); }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int insertBeneficiary(ContractInfo ci, Connection conn) {
		String query = "INSERT INTO beneficiaries (beneficiary_id, beneficiary_name, relationship"
				+ ", bank_account, bank_name) VALUES (seq_beneficiaries_id.NEXTVAL, ?, ?, ?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(query, new String[] { "beneficiary_id" })) {
			pstmt.setString(1, ci.getBeneficiaryName());
			pstmt.setString(2, ci.getRelationship());
			pstmt.setString(3, ci.getBeneficiaryAccount());
			pstmt.setString(4, ci.getBeneficiaryBank());
			
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0) { throw new SQLException("수익자 추가 실패!!"); }
			
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) { return rs.getInt(1); }
				else { throw new SQLException("생성된 수익자 ID를 가져오지 못했음"); }
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static final int CONTRACT_STATUS_PENDING = 0;
	public static int insertContract
			(ContractInfo ci, Connection conn, int cust_id, int insu_id, int bene_id) {
		
		String query = "INSERT INTO contracts (contract_id, customer_id, insured_id, beneficiary_id, product_id"
				+ ", created_date, payment_end_date, coverage_end_date, status, premium) "
				+ "VALUES (seq_contracts_id.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setInt(1, cust_id);
			pstmt.setInt(2, insu_id);
			pstmt.setInt(3, bene_id);
			pstmt.setInt(4, ProductDAO.getProductByName(SelectedProductName.getProduct_name(), conn).getProductId());
			pstmt.setDate(5, java.sql.Date.valueOf(ci.getCreatedDate()));
			
			LocalDate payment_end_date = ci.getCreatedDate().plusYears(ci.getProduct_payment_period()).plusMonths(1);
			pstmt.setDate(6, java.sql.Date.valueOf(payment_end_date));
			
			LocalDate coverage_end_date = ci.getInsuredBirth().plusYears(ci.getProduct_coverage_period());
			pstmt.setDate(7, java.sql.Date.valueOf(coverage_end_date));
			
			pstmt.setInt(8, CONTRACT_STATUS_PENDING);
			pstmt.setDouble(9, ci.getPremium_final());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
