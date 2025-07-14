package insuranceMain.customerPanel.accounts.signup;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SignupCard2 extends JPanel {
	public SignupCard2(CardLayout c, JPanel jp) {
		JLabel label = new JLabel("여기는 2페이지");
		add(label);
		
		JButton btn1 = new JButton("다음");
		btn1.setBounds(100, 100, 100, 100);
		
		btn1.addActionListener(e -> {
			c.next(jp);
		});
		add(btn1);
		
		JButton btn2 = new JButton("이전");
		btn2.setBounds(200, 100, 100, 100);
		
		btn2.addActionListener(e -> {
			c.previous(jp);
		});
		add(btn2);
	}
}
