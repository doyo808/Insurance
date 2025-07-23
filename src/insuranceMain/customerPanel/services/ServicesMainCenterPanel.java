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
	private ProductMainPanel productMP;
	private ContractMainPanel contractMP;
	private ClaimMainPanel claimMP;
	private PaymentMainPanel paymentMP;
	private MyPageMainPanel mypageMP;
	
	
	public ServicesMainCenterPanel(CustomerMainPanel cmp) {
		this.cmp = cmp;
		setLayout(c);
		
		add(productMP = new ProductMainPanel(cmp), "보험상품조회");
		add(contractMP = new ContractMainPanel(cmp), "보험상품가입");
		add(claimMP = new ClaimMainPanel(cmp), "보험금청구");
		add(paymentMP = new PaymentMainPanel(cmp), "보험료납부");
		add(mypageMP =  new MyPageMainPanel(cmp), "마이페이지");
	}
	
	public void showCard(String name) {
        if (name.equals("보험상품조회")) {
        	
            remove(productMP); // 기존 패널 제거
            productMP = new ProductMainPanel(cmp); // 새로 생성
            add(productMP, name); // 새로 등록
        } else if (name.equals("보험상품가입")) {
        	
            remove(contractMP); 
            contractMP = new ContractMainPanel(cmp); 
            add(contractMP, name); 
        } else if (name.equals("보험금청구")) {
        	
            remove(claimMP); 
            claimMP = new ClaimMainPanel(cmp);
            add(claimMP, name);
        } else if (name.equals("보험료납부")) {
        	
            remove(paymentMP);
            paymentMP = new PaymentMainPanel(cmp);
            add(paymentMP, name);
        } else if (name.equals("마이페이지")) {
        	
        	remove(mypageMP);
        	mypageMP = new MyPageMainPanel(cmp);
        	add(mypageMP, name);
        }
        
		c.show(this, name);
	}
}