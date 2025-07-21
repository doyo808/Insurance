package common.database.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.database.model.ClaimsModel;
import common.database.model.NewClaimDataModel;

public class ClaimDAO { // 더미데이터로 테스트 해봐야함
		
		// 고객이 청구대상을 '다른사람'으로 선택하면 그 대상이 가입한 보험계약 정보를 가지고 와야함
		
		/*
				<보험금청구내역 표>
				사고발병일자/상품명/증권번호(없으면 pass)/재해자=피보험자/보상구분/재해유형/
				
				<보상담당자 표>
				재해자/(직원)소속(부서)/담당자명/ 전화번호
				
				<처리현황 표>
				접수날짜/서류심사날짜/사고조사날짜/보험금지급심사 날짜/
				
				<결정보험금 표>
		 */
	
		// 고객이 청구대상을 '본인'으로 선택하면 로그인 기록을 가지고와서 그 사람의 정보를 가지고 와야함
		// 고객이 하나 이상의 계약이 있다는 가정
		// 가져올 정보: 고객이름, 고객주민번호, 고객 연락처, 상품명, 계약명
		public static NewClaimDataModel getCustomerInfo(String login_id, Connection conn) throws SQLException {
			NewClaimDataModel customerInfo = new NewClaimDataModel();
			String query = "SELECT cu.customer_name, cu.personal_id, cu.phone_number "
					+ "FROM customers cu "
					+ "WHERE cu.login_id = ? ";
			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
		        pstmt.setString(1, login_id);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            while (rs.next()) {
		            	customerInfo.setCustomer_name(rs.getString("customer_name"));
		            	customerInfo.setPersonal_id(rs.getString("personal_id"));
		            	customerInfo.setPhone_number(rs.getString("phone_number"));
		            }
		        }
		    }
		    return customerInfo;
		}
	
		// 고객이 자동이체 계좌를 선택하면 로그인 기록을 가지고와서 그 사람의 계좌번호를 가지고 와야함
		public static NewClaimDataModel getCustomerBankInfo(String login_id, Connection conn) throws SQLException {
			NewClaimDataModel customerBankInfo = new NewClaimDataModel();
			
			String query = "SELECT bf.bank_name, bf.bank_account, bf.beneficiary_name "
					+ "FROM beneficiaries bf "
					+ "INNER JOIN contracts cn ON bf.beneficiary_id = cn.beneficiary_id "
					+ "INNER JOIN customers cu ON cn.customer_id = cu.customer_id "
					+ "WHERE cu.login_id = ? ";
			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
		        pstmt.setString(1, login_id);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            while (rs.next()) {
		            	customerBankInfo.setBank_name(rs.getString("bank_name"));
		            	customerBankInfo.setBank_account(rs.getString("bank_account"));
		            	customerBankInfo.setBeneficiary_name(rs.getString("beneficiary_name"));
		            }
		        }
		    }
		    return customerBankInfo;
		}
		
//		[보험금 청구 내역 조회 페이지]
//		고객이 피보험자/수익자 중 하나를 선택하면 그 선택값이 쿼리문에 들어가야함
//		- 고객이 피보험자인지, 수익자인지에 따라 달라짐 (여기에 피보험자인지 수익자인지 조건 함께 설정하면 좋을 듯)
		// 로그인 된 상태에서 보험청구내역패널 들어가 '조회'누르면 고객이 지금까지 접수했던 모든 청구 내역 리스트 나타남(날짜 기준으로)
		// 로그인ID로 고객의 청구내역을 가져와 테이블에 나열 (최근 청구날짜 순서대로 조회(내림차순))
		public static List<ClaimsModel> getClaims(String login_id, Date startDate, Date endDateStr, Connection conn) throws SQLException {
		    List<ClaimsModel> claimList = new ArrayList<>();
		    String query = "SELECT c.claim_id, c.claim_date, d.diagnosis_name, i.insured_name, p.product_name, e.employee_name, c.claim_status "
		            + "FROM claims c "
		            + "INNER JOIN diagnosis_codes d ON c.diagnosis_cd = d.diagnosis_cd "
		            + "INNER JOIN contracts ct ON c.contract_id = ct.contract_id "
		            + "INNER JOIN insureds i ON ct.insured_id = i.insured_id "
		            + "INNER JOIN products p ON ct.product_id = p.product_id "
		            + "INNER JOIN employees e ON c.employee_id = e.employee_id "
		            + "INNER JOIN customers cu ON ct.customer_id = cu.customer_id "
		            + "WHERE cu.login_id = ? "
		            + "AND c.claim_date BETWEEN ? AND ? "
		            + "ORDER BY c.claim_date DESC ";

		    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
		        pstmt.setString(1, login_id);
		        pstmt.setDate(2, new java.sql.Date(startDate.getTime()));
		        pstmt.setDate(3, new java.sql.Date(endDateStr.getTime()));

		        try (ResultSet rs = pstmt.executeQuery()) {
		            while (rs.next()) {
		                ClaimsModel claim = new ClaimsModel(
		                    rs.getInt("claim_id"),
		                    rs.getDate("claim_date"),
		                    rs.getString("diagnosis_name"),
		                    rs.getString("insured_name"),
		                    rs.getString("product_name"),
		                    rs.getString("employee_name"),
		                    rs.getString("claim_status")
		                );
		                claimList.add(claim);
		            }
		        }
		    }
		    return claimList;
		}

		// 보험금 상세내역 조회 패널에 값 불러오기 (한 페이지당 하나의 청구 기록)
//		// 보험금 청구내역(1)
//		// 접수번호(청구번호)/사고발병일자/상품명/재해자=피보험자 or 재물/청구유형
		public static ClaimsModel getClaimDetail(String login_id, int claim_id, Connection conn) throws SQLException {
			ClaimsModel claimDetail = null;
		    String query = "SELECT \r\n"
		    		+ "cl.claim_id, cl.accident_date, pr.product_name, i.insured_name, cl.claim_category, \r\n"
		    		+ "d.department_name, e.employee_name, e.phone_number,\r\n"
		    		+ "cl.claim_date,\r\n"
		    		+ "MAX(CASE WHEN cpl.claim_process_log_name = '서류심사' THEN cpl.process_date END) AS document_review_date,\r\n"
		    		+ "MAX(CASE WHEN cpl.claim_process_log_name = '사고조사' THEN cpl.process_date END) AS accident_investigation_date,\r\n"
		    		+ "MAX(CASE WHEN cpl.claim_process_log_name = '보험금 지급심사' THEN cpl.process_date END) AS payment_review_date,\r\n"
		    		+ "cl.completion_date, cl.total_paid_amount\r\n"
		    		+ "FROM claims cl \r\n"
		    		+ "INNER JOIN contracts ct ON cl.contract_id = ct.contract_id \r\n"
		    		+ "INNER JOIN customers cu ON ct.customer_id = cu.customer_id\r\n"
		    		+ "INNER JOIN products pr ON pr.product_id = ct.product_id \r\n"
		    		+ "INNER JOIN insureds i ON ct.insured_id = i.insured_id \r\n"
		    		+ "INNER JOIN employees e ON cl.employee_id = e.employee_id\r\n"
		    		+ "INNER JOIN departments d ON e.department_id = d.department_id\r\n"
		    		+ "LEFT JOIN claim_process_logs cpl ON cl.claim_id = cpl.claim_id\r\n"
		    		+ "WHERE cu.login_id = ? AND cl.claim_id = ? "
		    		+ "GROUP BY \r\n"
		    		+ "cl.claim_id,\r\n"
		    		+ "cl.accident_date,\r\n"
		    		+ "pr.product_name,\r\n"
		    		+ "i.insured_name,\r\n"
		    		+ "cl.claim_category,\r\n"
		    		+ "d.department_name,\r\n"
		    		+ "e.employee_name,\r\n"
		    		+ "e.phone_number,\r\n"
		    		+ "cl.claim_date,\r\n"
		    		+ "cl.completion_date,\r\n"
		    		+ "cl.total_paid_amount";
		    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
		        pstmt.setString(1, login_id);
		        pstmt.setInt(2, claim_id);
		        
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                ClaimsModel claim = new ClaimsModel(rs);
		                claim.setClaim_id(rs.getInt("claim_id"));
		                claim.setAccident_date(rs.getDate("accident_date"));
		                claim.setProduct_name(rs.getString("product_name"));
		                claim.setInsured_name(rs.getString("insured_name"));
		                claim.setClaim_category(rs.getString("claim_category"));
		                claim.setDepartment_name(rs.getString("department_name"));
		                claim.setEmployee_name(rs.getString("employee_name"));
		                claim.setEmployee_phone_number(rs.getString("phone_number"));
		                claim.setClaim_date(rs.getDate("claim_date"));
		                claim.setDocument_review_date(rs.getDate("document_review_date"));
		                claim.setAccident_investigation_date(rs.getDate("accident_investigation_date"));
		                claim.setPayment_review_date(rs.getDate("payment_review_date"));
		                claim.setCompletion_date(rs.getDate("completion_date"));
		                claim.setTotal_paid_amount(rs.getInt("total_paid_amount"));
		                claimDetail = claim;
		            }
		        }
		    }
		    
			return claimDetail;
		}
		
		
		
		// 처리현황
		// 접수날짜/서류심사날짜/사고조사날짜/보험금지급심사 날짜/결정보험금/총지급금액
//		public static ClaimsModel getProcessingStatus(String login_id, Connection conn) throws SQLException {
//		    String query = "";
//
//		    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//		        pstmt.setString(1, login_id);
//
//		        try (ResultSet rs = pstmt.executeQuery()) {
//		            while (rs.next()) {
//		                ClaimsModel claimDetail = new ClaimsModel(
//		                    rs.get(""),
//		                    rs.get(""),
//		                    rs.get("")
//		                );
//		                return claimDetail;
//		            }
//		        }
//		    }
//		}
		
		// 고객이 입력한 내용을 토대로 새로운 청구 내역을 추가하는 메서드 
		
//		// 새 고객을 추가하는 메서드 (CREATE) * 주민등록번호, 로그인 아이디 유니크 주의해주세요
//		public static int addCustomer(CustomerModel c, Connection conn) {
//
//			String query = "INSERT INTO CUSTOMERS(customer_id, customer_name"
//					+ ", personal_id, login_id, password_hash, password_salt) "
//					+ "VALUES(SEQ_CUSTOMER_ID.nextval, ?, ?, ?, ?, ?)";
//			
//			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//				pstmt.setString(1, c.getCustomer_name());
//				pstmt.setString(2, c.getPersonal_id());
//				pstmt.setString(3, c.getLogin_id());
//				pstmt.setString(4, c.getPassword_hash());
//				pstmt.setString(5, c.getPassword_salt());
//				
//				return pstmt.executeUpdate();
//				
//			} catch (SQLException e) {
//				e.getErrorCode();
//				e.getMessage();
//				e.printStackTrace();
//				return -1;
//			}
//		}
//		// 해당 고객을 삭제하고 삭제 여부를 반환하는 메서드 (DELETE) - 현재는 오류 발생 DB수정해야함
//		public static boolean deleteCustomer(int customer_id, Connection conn) throws SQLException {
//			String query = "DELETE FROM CUSTOMERS WHERE customer_id = ?";
//			
//			try (PreparedStatement pstmt = conn.prepareStatement(query)){
//				pstmt.setInt(1, customer_id);
//				return pstmt.executeUpdate() == 1;
//			}
//		}
//	}

}
