package common.gui.Unsigned_panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonSignup extends JButton {
	public ButtonSignup() {
		init();
	}
	private void init() {
		setText("회원가입");
		setFont(new Font("굴림", Font.BOLD, 20));
		setBackground(Color.black);
		setForeground(Color.white);
		setBounds(700, 100, 150, 150);
		
		
		addActionListener(e -> {
			System.out.println("회원가입 시작!");
			

		});
	}
}
