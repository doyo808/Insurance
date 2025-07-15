package insuranceMain.customerPanel;

import java.awt.CardLayout;

import javax.swing.JPanel;

import common.account.login.LoginService;
import common.database.model.CustomerModel;
import insuranceMain.customerPanel.accounts.AccountsMainPanel;
import insuranceMain.customerPanel.services.ServicesMainPanel;

public class CustomerMainPanel extends JPanel {
	// 고객서비스의 최상단 패널(이곳에 각 기능패널묶음과 로그인/회원가입묶음이 카드로 들어간다)
	
	CardLayout c = new CardLayout();
	private ServicesMainPanel servicesPanel;
	private AccountsMainPanel accountsPanel;
	
	
	public CustomerMainPanel() {
		setLayout(c);
		
		servicesPanel = new ServicesMainPanel(this);
		accountsPanel = new AccountsMainPanel(servicesPanel, this);
		
		add(servicesPanel, "services");
		add(accountsPanel, "accounts");
		c.show(this, "accounts");
	}


	public void showCard(String string) {
		c.show(this, string);
	}

	public AccountsMainPanel getAMP() {
		return accountsPanel;
	}
}
