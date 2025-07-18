package customer.payment.method;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import common.database.dao.ContractDAO;
import common.database.dao.PaymentDAO;
import common.database.dao.ProductDAO;
import common.database.dao.UnpaidDAO;
import common.database.model.CustomerModel;
import common.database.model.PaymentModel;
import common.database.model.UnpaidModel;
import common.method.InsuranceTeamConnector;

public class payment {
	
	// 커스터머 id와 납입 id를 받아 결제를한다
	// 미납내역을 가져온다 > 미납 내역의 결제여부가 N인지 확인 > 결제를한다 > 미납내역 테이블을 Y로 바꾸고 결제 내역을 추가한다
	
	public static boolean pay(CustomerModel cm, Integer unpaid_id, Integer account_id) {
		// 결제금액이 일치할 때 버튼이 활성화되고 pay가 가능함
		
		try (Connection conn = InsuranceTeamConnector.getConnection();) 
		{	
			// unpaid_id를 통해서 미납내역 한개 가져오기
			UnpaidModel unpaid = UnpaidDAO.getUnpaidByCustomer(cm.getCustomer_id(), unpaid_id, conn);
			if (unpaid.getIsPaidBool()) {
				return false; 
			}
			
			// 새로운 결제내역
			PaymentModel n_payment = new PaymentModel(cm.getCustomer_id(), unpaid_id);
			n_payment.setAccount_id(account_id);
			n_payment.setContract_id(unpaid.getContractId());
			n_payment.setPaid_amount(unpaid.getUnpaidAmount());
			n_payment.setPay_statusYes();
			n_payment.setPayment_date(LocalDateTime.now());
			
			// 업데이트
			UnpaidDAO.updateUnpaidY(unpaid_id, conn);
			PaymentDAO.insert(n_payment, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static List<String[]> ModelsToStringData(List<UnpaidModel> unpaids) {
		
		if (unpaids == null) return null;
		SimpleDateFormat formatterUnpaid = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat formatterDeadline = new SimpleDateFormat("yyyy-MM-dd");
		List<String[]> datas = new ArrayList<String[]>();
		
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			for (UnpaidModel model : unpaids) {
				int count = 1;
				String[] data = new String[6];
				data[0] = String.valueOf(count++);
				data[1] = ProductDAO.getProductName((ContractDAO.getByContId(model.getContractId(), conn)).getProduct_id(), conn);
				data[2] = String.valueOf(model.getContractId());
				data[3] = String.valueOf(model.getUnpaidAmount());
				data[4] = formatterUnpaid.format(model.getUnpaidDate());
				data[5] = formatterDeadline.format(model.getPaymentDeadline());
				data[6] = String.valueOf(model.getUnpaidId());
				datas.add(data);
			}
			return datas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}
	
	
	

}
