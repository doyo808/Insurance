package insuranceMain.customerPanel.services;

import java.awt.CardLayout;

import javax.swing.JPanel;

import customer.claim.gui.ClaimMainPanel;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ServicesMainCenterPanel extends JPanel {
	CardLayout c = new CardLayout();

	public ServicesMainCenterPanel(CustomerMainPanel cmp) {
		setLayout(c);
		
		add(new ProductMainPanel(cmp), "보험상품조회");
		add(new ContractMainPanel(cmp), "보험상품가입");
		add(new ClaimMainPanelTemp(cmp), "보험금청구");
		add(new PaymentMainPanel(cmp), "보험료납부");
		add(new MypageMainPanel(cmp), "마이페이지");
	}
	
	public void showCard(String string) {
		c.show(this, string);
	}
}