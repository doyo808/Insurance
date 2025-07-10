package common.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CardSwitchButton extends JButton {
	
	
	public CardSwitchButton(String buttonName, JPanel parentCardPanel, String cardName, int with, int height) {
		setText(buttonName);
		setBackground(Color.BLACK);
		setFont(new Font("맑은고딕", Font.BOLD, 15));
		setForeground(Color.WHITE); 
		setPreferredSize(new Dimension(with, height)); // 버튼크기
		setVisible(true);
		
		CardSwitch(parentCardPanel, cardName);
	}

	private void CardSwitch(JPanel parentCardPanel, String cardName) {
		addActionListener((e) -> {
			CardLayout cl = (CardLayout)(parentCardPanel.getLayout());
			cl.show(parentCardPanel, cardName);
		});
	}
}
