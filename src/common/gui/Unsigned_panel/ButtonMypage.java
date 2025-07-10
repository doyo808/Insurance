package common.gui.Unsigned_panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonMypage extends JButton {
	public ButtonMypage() {
		init();
	}
	private void init() {
		setText("마이페이지");
		setFont(new Font("굴림", Font.BOLD, 20));
		setBackground(Color.black);
		setForeground(Color.white);
		setBounds(300, 100, 150, 150);
		
		
		addActionListener(e -> {
			System.out.println("마이페이지 시작!");
			

		});
	}
}

