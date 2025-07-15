package insuranceMain.customerPanel.accounts;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import common.account.login.Session;
import common.database.model.CustomerModel;
import common.method.TestUserProvider;
import insuranceMain.MainFrame;
import insuranceMain.customerPanel.CustomerMainPanel;
import insuranceMain.customerPanel.services.ServicesMainPanel;

public class AccountsMainPanel extends JPanel {
	
	private ServicesMainPanel smp;
	private CustomerMainPanel cmp;
	private LoggedInMainCard ac2;
	CardLayout c = new CardLayout();
	
	public AccountsMainPanel(ServicesMainPanel smp, CustomerMainPanel cmp) {
		setLayout(c);
		setPreferredSize(new Dimension(1440, 1024));
		this.smp = smp;
		this.cmp = cmp;
		
		add(new UnLoggedInMainCard(this), "비회원_메인");
		add(ac2 = new LoggedInMainCard(this, smp, cmp), "회원_메인");
		add(new SignupMainPanel(this), "회원가입_메인");
		add(new LogInCard(this, smp, cmp), "로그인");
		
		showCard("비회원_메인");
		
		testing(smp);
	}
	
	void testing(ServicesMainPanel smp) {
		if (MainFrame.TEST) {
			CustomerModel c = TestUserProvider.getTestUser(MainFrame.LOGIN_ID);
			if (c != null) {
				Session.setCustomer(c);
				smp.refreshPanels();
				showCard("회원_메인");
			} else {
				System.out.println("회원아이디를 확인하세요!");
			}
		}
	}
	
	public void showCard(String str) {
		
		if (str.equals("회원_메인")) {
			ac2.getPN().updateUserName();
		}
		c.show(this, str);
	}


}
