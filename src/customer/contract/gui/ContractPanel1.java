package customer.contract.gui;

import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.account.login.SessionOfProduct;
import common.database.dao.ProductDAO;
import common.database.model.ProductModel;
import common.method.InsuranceTeamConnector;

public class ContractPanel1 extends JPanel {
	public ContractPanel1(ContractMainPanel contractMP) {
		JButton btn = new JButton("확인");
		btn.setBounds(100, 100, 100, 100);
		
		btn.addActionListener(e -> {
			
			try (Connection conn = InsuranceTeamConnector.getConnection()) {
				ProductModel pm = ProductDAO.getProduct(1, conn);
				SessionOfProduct.setProduct(pm);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			
			contractMP.ShowCard("보험료계산");
		});
		
		
		add(btn);
	}
}
