package customer.contract.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.method.InsuranceTeamConnector;

public class GetProductNamesFromKeyword {
	public static List<String> list = new ArrayList<>();
	
	private static final String query = "SELECT p.product_name FROM products p "
            + "JOIN product_keyword_mapping pkm ON p.product_id = pkm.product_id "
            + "JOIN product_keywords pk ON pkm.product_keyword_id = pk.product_keyword_id "
            + "WHERE pk.keyword_name = ?";
	
	public static Set<String> getNames() {
		Set<String> product_names = new HashSet<>();
		
		if (list.size() == 0) {
			return product_names;
		}
		
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
		    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
		        
		    	for (String keyword : list) {
		            pstmt.setString(1, keyword);
		            try (ResultSet rs = pstmt.executeQuery()) {
		                while (rs.next()) {
		                    product_names.add(rs.getString("product_name"));
		                }
		            }
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return product_names;
	}
}
