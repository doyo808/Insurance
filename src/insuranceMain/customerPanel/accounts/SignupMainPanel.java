package insuranceMain.customerPanel.accounts;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import common.database.model.CustomerModel;
import common.gui.FooterImagePanel;
import insuranceMain.customerPanel.accounts.signup.SignupCard1;
import insuranceMain.customerPanel.accounts.signup.SignupCard2;
import insuranceMain.customerPanel.accounts.signup.SignupCard3;
import insuranceMain.customerPanel.accounts.signup.SignupCard4;

public class SignupMainPanel extends JPanel {
	// 이곳은 회원가입카드들의 메인패널
	private CustomerModel cm;
	private AccountsMainPanel amp;
	public SignupMainPanel(AccountsMainPanel amp) {
		this.amp = amp;
		setLayout(new BorderLayout());
		
		add(new HeaderBarOfEaryPage(amp, 10, 10), BorderLayout.NORTH);
		add(new FooterImagePanel(), BorderLayout.SOUTH);
		addCenterPanel();
	}
	
	void addCenterPanel() {
		this.cm = new CustomerModel();
		JPanel panel = new JPanel();
		CardLayout c = new CardLayout();
		panel.setLayout(c);
		
		panel.add(new SignupCard1(c, panel, cm), "Signup1");
		panel.add(new SignupCard2(c, panel, cm), "Signup2");
		panel.add(new SignupCard3(c, panel, cm), "Signup3");
		panel.add(new SignupCard4(amp));
		
		
		c.show(panel, "Signup1");
		
		add(panel, BorderLayout.CENTER);
	}
	
	
}
