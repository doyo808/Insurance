package common.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import common.database.model.InquireModel;
import common.database.model.PaymentModel;

public class InquireDAO {
	
	//구분	상품이름	납입월분	입금일자	납입횟수	대상보험료		실입금액	입금방법
	public static List<InquireModel> getInquireList(Integer customer_id, Integer contract_id,
			Date startDate, Date endDate, Connection conn) throws SQLException {
		
		int count = 1; //구분
		ArrayList<InquireModel> inquires = new ArrayList<>();
		String sql = "SELECT * FROM payments INNER JOIN unpaids USING(unpaid_id) INNER JOIN contract USING(contract_id)"
				+ "WHERE customer_id = ? AND contract_id = ? AND unpaid_date between ? AND ?";
		
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customer_id);
            pstmt.setInt(2, contract_id);
            pstmt.setDate(3, startDate);
            pstmt.setDate(4, endDate);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                	inquires.add(new InquireModel(rs));
                }
            }
        }
		
		return inquires;
	}
}
