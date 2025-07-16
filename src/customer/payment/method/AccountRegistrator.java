package customer.payment.method;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import common.account.login.Session;
import common.database.dao.AutoPaymentDAO;
import common.database.dao.ContractDAO;
import common.database.dao.CustomerDAO;
import common.database.model.AutoPaymentModel;
import common.database.model.ContractModel;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;


// 하나의 계약당 하나의 자동결제 계좌를 입력
public class AccountRegistrator {
	
	/**
	 * 자동이체 계좌정보를 등록하거나 수정하는 기능
	 * @param cm
	 * @param contract_id
	 * @param bank_account
	 * @param bank_name
	 */
	public static boolean register(CustomerModel cm, Integer contract_id, String bank_account, String bank_name) {
		try (Connection conn = InsuranceTeamConnector.getConnection();) 
		{
			// 입력된 은행이름과 계좌번호를 통해 받은 은행이름이 동일한지 확인
			if (!bank_name.equals(AccountVerifier.detectBank(bank_account))) {
				System.out.println("유효하지 않은 계좌번호입니다");
				return false;
			}
			AutoPaymentModel apm = null;
		if (AutoPaymentDAO.getAutoPaymentsByContId(contract_id, conn) == null) {
			// 자동이체 계좌가 없는 경우 (새로 만들어짐)
			apm = new AutoPaymentModel(
				contract_id, cm.getCustomer_id(),
				bank_name, bank_account, "Y",
				Timestamp.valueOf(LocalDateTime.now()),
				Timestamp.valueOf(LocalDateTime.now()),
				contract_id);
			AutoPaymentDAO.insertAutoPayment(apm, conn);
			return true;
		} else {
			// 자동이체 계좌가 있는 경우 (수정되는거임)
			apm = new AutoPaymentModel(
					contract_id, cm.getCustomer_id(),
					bank_name, bank_account, "Y",
					null,
					Timestamp.valueOf(LocalDateTime.now()),
					contract_id);
			AutoPaymentDAO.updateAutoPayment(apm, conn);
			return true;
		}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) throws SQLException {
		try (Connection conn = InsuranceTeamConnector.getConnection();) 
		{
			System.out.println(register(CustomerDAO.getCustomerByLoginId("hong123", conn), 1, "1002145651178", "우리은행"));
		}
		
	}
}

