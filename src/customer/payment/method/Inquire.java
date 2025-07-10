package customer.payment.method;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import common.database.dao.InquireDAO;
import common.database.dao.ProductDAO;
import common.database.model.InquireModel;
import common.method.InsuranceTeamConnector;


public class Inquire {
	
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		String product_name = "";
		List<InquireModel> inquiredList = new ArrayList<>();
		int count = 1;
		try (Connection conn = InsuranceTeamConnector.getConnection())
		{
			// 조회할 내용 리스트 결제 내역 
			inquiredList = InquireDAO.getInquireList(customer_id, product_id, start, end, conn);
			// 조회할 상품이름
			product_name = ProductDAO.getProductName(product_id, conn);
			// 납부 횟수
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("서버를 찾을 수 없습니다");
		}
		
		// 기본 출력
		System.out.println("구분\t상품이름\t납입월분\t입금일자\t납입횟수\t대상보험료\t실입금액\t입금방법");
		
		for (int i = 0; i < inquiredList.size(); i++) {
			InquireModel im = inquiredList.get(i);
			System.out.printf("%d\t%s\t%s\t%s\t%d\t%d\t%d\t%s", 
					count++,
					im.getProduct_name(),
					im.getUnpaid_date(),
					im.getPayment_date(),
					im.getUnpaid_count(),
					im.getUnpaid_amount(),
					im.getPaid_amount(),
					im.getPaid_method()
					);
		}
	}
	
	public static void main(String[] args) {
		inquire(1, null, null, null);
	}
}

