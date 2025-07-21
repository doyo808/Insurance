package insuranceMain.customerPanel.accounts;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.gui.HomeButton;
import common.method.ButtonMaker;
import common.method.ButtonPainter;
import insuranceMain.customerPanel.CustomerMainPanel;

public class HeaderBarOfEaryPage extends JPanel {
	private AccountsMainPanel parentPanel;
	
	public HeaderBarOfEaryPage(AccountsMainPanel parentPanel, int x, int y) {
		this.parentPanel = parentPanel;
		setBackground(Color.gray);
		
		add(new HomeButton(parentPanel, x, y));
		ButtonMaker.addButton(1000, 1, this);
		
		JButton btn2 = new JButton("로그인");
		ButtonPainter.back_front(btn2, 20, Color.gray, Color.white);
		
		JButton btn3 = new JButton("회원가입");
		ButtonPainter.back_front(btn3, 20, Color.gray, Color.white);

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