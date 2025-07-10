package common.database.dao;

import java.sql.Connection;
import java.sql.SQLException;

import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;

public class ClaimDAOMain {
	public static void main(String[] args) {
		
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			
			
		
			// 고객 로그인ID 입력하면 고개깅 청구한 청구내역 List로 나타남(지금은 청구내역 데이터 없어서 안뜸)
//			System.out.println("고객의 로그인ID를 입력하고 '조회'누르면 고객이 지금까지 접수했던 모든 청구 내역 표시"); 
//			List<ClaimModel> claimList = ClaimDAO.getClaims("sldlkfjs", conn);
//			for (ClaimModel claimModel: claimList) {
//				System.out.println(claimModel);
//			}
		
		
		//
			System.out.println("고객의 로그인ID 입력하면 그 고객의 정보 나타남"); 
			
//			CustomerModel c = CustomerDAO.getCustomer("hong123", conn);
//			System.out.println(c);
			
//			ClaimModel customerInfo = ClaimDAO.getCustomerInfo("hong123", conn);
//				System.out.println(customerInfo);
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
//		(1) DB에서 모든 과일 목록을 꺼내 리스트 형태로 만들어 반환하는 메서드
//		System.out.println("모든 과일");
//		List<Fruit> allFruits = FruitDao.getAllFruits(conn);
//		for (Fruit fruit : allFruits) {
//			System.out.println(fruit);
//		}
	}
}

