package customer.payment.method;

import java.sql.Connection;
import java.sql.SQLException;

import common.database.model.ContractModel;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;

public class AccountRegistrator {
	public static void register(CustomerModel cm, Integer contract_id) {
		try (Connection conn = InsuranceTeamConnector.getConnection();) 
		{
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
