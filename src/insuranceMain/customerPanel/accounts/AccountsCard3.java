package insuranceMain.customerPanel.accounts;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import common.gui.FooterImagePanel;
import insuranceMain.customerPanel.accounts.signup.SignupCard1;
import insuranceMain.customerPanel.accounts.signup.SignupCard2;

public class AccountsCard3 extends JPanel {
	// 이곳은 회원가입카드들의 메인패널
	
	public AccountsCard3(AccountsMainPanel amp) {
		setLayout(new BorderLayout());
		
		add(new PanelNorth(amp, 10, 10), BorderLayout.NORTH);
		add(new FooterImagePanel(), BorderLayout.SOUTH);
		addCenterPanel();
	}
	
	void addCenterPanel() {
		JPanel panel = new JPanel();
		CardLayout c = new CardLayout();
		panel.setLayout(c);
		
		panel.add(new SignupCard1(c, panel), "1");
		panel.add(new SignupCard2(c, panel), "2");
		
		c.show(panel, "1");
		
		add(panel, BorderLayout.CENTER);
	}
}
