package insuranceMain.customerPanel.services;

import java.awt.CardLayout;

import javax.swing.JPanel;

import customer.claim.gui.ClaimMainPanel;
import customer.contract.gui.ContractMainPanel;
import customer.mypage.gui.MyPageMainPanel;
import customer.payment.gui.PaymentMainPanel;
import customer.product.gui.ProductMainPanel;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ServicesMainCenterPanel extends JPanel {
	public CardLayout c = new CardLayout();
	private CustomerMainPanel cmp;

	public ServicesMainCenterPanel(CustomerMainPanel cmp) {
		this.cmp = cmp;
		setLayout(c);
		
		add(new ProductMainPanel(cmp), "보험상품조회");
		add(new ContractMainPanel(cmp), "보험상품가입");
//		add(new ClaimMainPanel(cmp), "보험금청구");
		add(new PaymentMainPanel(cmp), "보험료납부");
		add(new MyPageMainPanel(cmp), "마이페이지");
	}
	
	public void showCard(String name) {
		c.show(this, name);
	}
}