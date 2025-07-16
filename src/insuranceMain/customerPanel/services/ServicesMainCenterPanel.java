package insuranceMain.customerPanel.services;

import java.awt.CardLayout;

import javax.swing.JPanel;

import customer.claim.gui.ClaimMainPanel;
import customer.contract.ContractMainPanel;
import customer.mypage.gui.MyPageMainPanel;
import customer.payment.gui.PaymentMainPanel;
import customer.product.gui.ProductMainPanel;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ServicesMainCenterPanel extends JPanel {
	public CardLayout c = new CardLayout();
	private CustomerMainPanel cmp;
	private PaymentMainPanel pmp; 
	
	public ServicesMainCenterPanel(CustomerMainPanel cmp) {
		this.cmp = cmp;
		setLayout(c);
		
		add(new ProductMainPanel(cmp), "보험상품조회");
		add(new ContractMainPanel(cmp), "보험상품가입");
		add(new ClaimMainPanel(cmp), "보험금청구");
		add(pmp = new PaymentMainPanel(cmp), "보험료납부");
		add(new MyPageMainPanel(cmp), "마이페이지");
	}
	
	public void showCard(String name) {
        if (name.equals("보험료납부")) {
            remove(pmp); // 기존 패널 제거
            pmp = new PaymentMainPanel(cmp); // 새로 생성
            add(pmp, "보험료납부"); // 새로 등록
        }
		c.show(this, name);
	}
}