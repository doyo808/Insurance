package common.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;

public class DAO_Test {
	public static void main(String[] args) {
		
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			
			
//			CustomerModel c = CustomerDAO.getCustomer("hong123", conn);
//			System.out.println(c);
			
			
//			List<CustomerModel> cs = CustomerDAO.getAllCustomers(conn);
//			for (CustomerModel c : cs) {
//				System.out.println(c);
//			}
			
//			List<CustomerModel> cs = CustomerDAO.findCustomerByAgree_yn("Y", conn);
//			for (CustomerModel c : cs) {
//				System.out.println(c);
//			}
			
//			CustomerModel c = new CustomerModel("김테스트", "950101-1234567", "test123", "hash", "salt");
//			System.out.println(CustomerDAO.addCustomer(c, conn));
//			CustomerModel c1 = CustomerDAO.getCustomer("test123", conn);
//			System.out.println(c1);
			
			// 삭제는 오류발생 (외래키 조건 때문인듯)
//			System.out.println("삭제시작");
//			System.out.println(CustomerDAO.deleteCustomer(9, conn));
//			System.out.println("삭제완료");

			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		
	}
}
