package insurancePanelPractice;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class CenterPanel extends JPanel {
	CardLayout c = new CardLayout();
	
	public CenterPanel() {
		init();
		addComponents();
		showCard("inquiry");
	}

	void init() {
		setLayout(c);
		setBackground(Color.DARK_GRAY);
	}
	void addComponents() {
		add(new InquiryPanel(), "inquiry");
		add(new JoinPanel(), "join");
	}
	
	public void showCard(String name) {
		c.show(this, name);
	}
}

