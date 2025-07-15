package insuranceMain.customerPanel.accounts.signup;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SignupCard1 extends JPanel {
	public SignupCard1(CardLayout c, JPanel jp) {
		JLabel label = new JLabel("여기는 1페이지");
		add(label);
		
		JButton btn1 = new JButton("다음");
		btn1.setBounds(100, 100, 100, 100);
		
		btn1.addActionListener(e -> {
			c.next(jp);
		});
		add(btn1);
	}
	
	
}
