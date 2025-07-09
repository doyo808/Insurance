package insurance.payments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import insurance.InsuranceTeamConnector;
import insurance.dao.PaymentDAO;


public class Inquire {
	public static void main(String[] args) {
		
	}
	// 
	// 납입내역 조회기능(유저, 보고싶은 시작일, 종료일) 현재 customer_id로 조회중 나중에 User class가 생기면 변경 가능성 있음
	/**
	 * 납입내역 조회기능입니다.
	 * (결제 내역과는 표시되는 컬럼이 약간 상이)
	 * @param customer_Id
	 * @param start 조회 시작일
	 * @param end 조회 종료일
	 */
	
	public static void inquire(Integer customer_id, Date start, Date end) {
		try (Connection conn = InsuranceTeamConnector.getConnection())
		{
			PaymentDAO.getPaymentModel(customer_id, conn, start, end);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("서버를 찾을 수 없습니다");
		}
		
		
		
		
	}
}

