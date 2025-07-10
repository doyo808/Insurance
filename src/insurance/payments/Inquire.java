package insurance.payments;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import insurance.InsuranceTeamConnector;
import insurance.dao.PaymentDAO;
import insurance.dao.ProductDAO;
import insurance.model.PaymentModel;


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
	
	public static void inquire(Integer customer_id, Integer product_id, Date start, Date end) {
		String product_name = "";
		List<PaymentModel> inquiredList = new ArrayList<PaymentModel>();
		int count = 0;
		try (Connection conn = InsuranceTeamConnector.getConnection())
		{
			// 조회할 내용 리스트 결제 내역 
			inquiredList = PaymentDAO.getInquireList(customer_id, product_id, conn, start, end);
			// 조회할 상품이름
			product_name = ProductDAO.getProductName(product_id, conn);
			// 납부 횟수
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("서버를 찾을 수 없습니다");
		}
		
		// 기본 출력
		System.out.println("구분\t상품이름\t납입월분\t입금일자\t납입횟수\t대상보험료\t실입금액\t입금방법");
		
		System.out.printf("%d\t%s\t");
		
		
		
	}
}

