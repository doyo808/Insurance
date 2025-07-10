package common.gui.Unsigned_panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonLogin extends JButton {
	public ButtonLogin() {
		init();
	}
	private void init() {
		setText("로그인");
		setFont(new Font("굴림", Font.BOLD, 20));
		setBackground(Color.black);
		setForeground(Color.white);
		setBounds(500, 100, 150, 150);
		
		
		addActionListener(e -> {
			System.out.println("로그인 시작!");
			

		});
	}
}
