package insuranceMain.customerPanel.services;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ServicesMainPanel extends JPanel {
	CardLayout c = new CardLayout();

	public ServicesMainPanel() {
		setLayout(c);
		
		add(new JButton("마이페이지"), "마이페이지");
	}
	
	public void showCard(String string) {
		c.show(this, string);
	}
}
