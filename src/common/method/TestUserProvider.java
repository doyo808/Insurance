package common.method;

import java.sql.Connection;
import java.sql.SQLException;

import common.account.login.Session;
import common.database.dao.CustomerDAO;
import common.database.model.CustomerModel;

public class TestUserProvider {
	public static CustomerModel getTestUser(String loginId) {
		try {
			Connection conn = new InsuranceTeamConnector().getConnection();
			CustomerModel c = CustomerDAO.getCustomerByLoginId(loginId, conn);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
