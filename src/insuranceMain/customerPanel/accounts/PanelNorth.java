package insuranceMain.customerPanel.accounts;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.gui.HomeButton;
import insuranceMain.customerPanel.CustomerMainPanel;

public class PanelNorth extends JPanel {
	private AccountsMainPanel parentPanel;
	
	public PanelNorth(AccountsMainPanel parentPanel, int x, int y) {
		this.parentPanel = parentPanel;
		setBackground(Color.gray);
		
		add(new HomeButton(parentPanel, x, y));
		
		JButton btn2 = new JButton("로그인");
		JButton btn3 = new JButton("회원가입");

		btn2.addActionListener(e -> {
			parentPanel.showCard("로그인");
		});
		btn3.addActionListener(e -> {
			parentPanel.showCard("회원가입_메인");
		});
		
		add(btn2);
		add(btn3);
	}
}


// setPreferredSize(new java.awt.Dimension(width, height));