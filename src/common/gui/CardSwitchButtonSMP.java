package common.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import insuranceMain.customerPanel.CustomerMainPanel;
import insuranceMain.customerPanel.services.ServicesMainCenterPanel;

public class CardSwitchButtonSMP extends JButton {
	
	private CustomerMainPanel cmp;
	
	public CardSwitchButtonSMP(String buttonName, JPanel parentCardPanel, String cardName, int width, int height, CustomerMainPanel cmp) {
		setText(buttonName);
//		setBackground(Color.BLACK);
//		setFont(new Font("맑은고딕", Font.BOLD, 15));
//		setForeground(Color.WHITE); 
		setPreferredSize(new Dimension(width, height)); // 버튼크기
		setVisible(true);
		this.cmp = cmp;
		CardSwitch(parentCardPanel, cardName, this.cmp);
	}

	private void CardSwitch(JPanel parentCardPanel, String cardName, CustomerMainPanel cmp) {
		addActionListener((e) -> {
			cmp.getSMP().getSmcp().showCard(cardName);
//			CardLayout cl = (CardLayout)(parentCardPanel.getLayout());
//			cl.show(parentCardPanel, cardName);
		});
	}
}



