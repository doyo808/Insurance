package customer.payment.method;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.account.login.Session;
import common.database.dao.AutoPaymentDAO;
import common.database.dao.ContractDAO;
import common.database.dao.CustomerDAO;
import common.database.dao.ProductDAO;
import common.database.dao.ProductPaymentCycleDAO;
import common.database.model.ContractModel;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;

public class AutoPaymentInquire {
	
	public static List<String[]> getcontractDataStr() {
		List<String[]> datas = new ArrayList<String[]>();
		CustomerModel cm = Session.getCustomer();
		List<ContractModel> models = new ArrayList<ContractModel>();
		
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			cm = CustomerDAO.getCustomerByLoginId("hong123", conn);
			models = ContractDAO.getAllById(cm.getCustomer_id(), conn);
			int count = 1;
			for (ContractModel model : models) {
				String[] data = new String[7];
				data[0] = String.valueOf(count);
				data[1] = ProductDAO.getProductName(model.getProduct_id(), conn);
				data[2] = String.valueOf(model.getContract_id());
				data[3] = String.valueOf(model.getSignup_date());
				data[4] = String.valueOf(model.getCoverage_end_date());
				data[5] = String.valueOf(
						AutoPaymentDAO.getAutoPaymentsByContId(model.getContract_id(), conn) == null ? "수동이체" : "자동이체");
				data[6] = String.valueOf(model.getPremium());
				datas.add(data);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return datas;
	}
}
