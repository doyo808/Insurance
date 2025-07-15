package customer.contract.gui;

import java.awt.CardLayout;
import java.sql.Connection;

import javax.swing.JPanel;

import common.account.login.SessionOfProduct;
import common.database.dao.ProductDAO;
import common.database.model.ProductModel;
import common.method.InsuranceTeamConnector;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ContractMainPanel extends JPanel {
	public CardLayout c = new CardLayout();
	private CalculatePremiumPanel calPre;
	
	public ContractMainPanel(CustomerMainPanel cmp) {
		setLayout(c);
		
		add(new ContractPanel1(this), "보험선택");
		add(calPre = new CalculatePremiumPanel(this), "보험료계산");
		
		ShowCard("보험선택");
	}
	
	public void ShowCard(String name) {
		if (name.equals("보험료계산")) {
			calPre.updateProduct();
		}
		c.show(this, name);
	}
}
