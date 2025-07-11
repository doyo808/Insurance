package insuranceMain.customerPanel.accounts;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import common.database.model.CustomerModel;
import insuranceMain.customerPanel.CustomerMainPanel;
import insuranceMain.customerPanel.services.ServicesMainPanel;

public class AccountsMainPanel extends JPanel {
	
	private ServicesMainPanel smp;
	private CustomerMainPanel cmp;
	CardLayout c = new CardLayout();
	
	public AccountsMainPanel(ServicesMainPanel smp, CustomerMainPanel cmp) {
		setLayout(c);
		setPreferredSize(new Dimension(1440, 1024));
		this.smp = smp;
		this.cmp = cmp;
		
		add(new AccountsCard1(this), "비회원_메인");
		add(new AccountsCard2(this, smp, cmp), "회원_메인");
		add(new AccountsCard3(), "회원가입_메인");
		add(new AccountsCard4(this), "로그인");
		
		c.show(this, "비회원_메인");
	}
	
	void showCard(String str) {
		c.show(this, str);
	}


}
