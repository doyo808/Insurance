package common.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import common.database.model.InquireModel;


public class InquireDAO {
	
	//구분	상품이름	납입월분	입금일자	납입횟수	대상보험료		실입금액	입금방법
	public static List<InquireModel> getInquireList(Integer customer_id, Integer contract_id,
			Date startDate, Date endDate, Connection conn) throws SQLException {
		
		
		ArrayList<InquireModel> inquires = new ArrayList<>();
		String sql = "SELECT " +
			    "pd.product_name, " +
			    "u.unpaid_date, " +
			    "pay.payment_date, " +
			    "u.unpaid_count, " +
			    "u.unpaid_amount, " +
			    "pay.paid_amount, " +
			    "pay.account_id " +
			    "FROM payments pay " +
			    "INNER JOIN unpaids u ON pay.unpaid_id = u.unpaid_id " +
			    "INNER JOIN contracts c ON u.contract_id = c.contract_id " +
			    "INNER JOIN products pd ON c.product_id = pd.product_id " +
			    "WHERE c.customer_id = ? " +
			    "AND u.contract_id = ? " +
			    "AND u.unpaid_date BETWEEN ? AND ?";
		
		
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
