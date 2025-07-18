package common.gui;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import common.account.login.Session;
import insuranceMain.customerPanel.CustomerMainPanel;
import insuranceMain.customerPanel.accounts.AccountsMainPanel;

public class HomeButton extends JButton {
	private int width = 100;
	private int height = 50;
	
	public HomeButton(CustomerMainPanel cmp, int x, int y) {
		
		ImageIcon icon = new ImageIcon("src/images/logo.png");
		Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(scaled));
		
		setBounds(x, y, 150, 75);
		setBorderPainted(false);     // 테두리 없애기
		setFocusPainted(false);      // 포커스 테두리 없애기
		setContentAreaFilled(false); // 배경 채우기 없애기
		setOpaque(false);            // 투명 처리
		
		addActionListener(e -> {
			if (Session.getCustomer() == null) {
				cmp.showCard("accounts");
				cmp.getAMP().showCard("비회원_메인");
			} else {
				cmp.showCard("accounts");
				cmp.getAMP().showCard("회원_메인");
			}
		});
	}
	
	public HomeButton(AccountsMainPanel amp, int x, int y) {
		
		ImageIcon icon = new ImageIcon("src/images/logo.png");
		Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(scaled));
		
		setBounds(x, y, 150, 75);
		setBorderPainted(false);     // 테두리 없애기
		setFocusPainted(false);      // 포커스 테두리 없애기
		setContentAreaFilled(false); // 배경 채우기 없애기
		setOpaque(false);            // 투명 처리
		
		addActionListener(e -> {
			if (Session.getCustomer() == null) {
				amp.showCard("비회원_메인");
			} else {
				amp.showCard("회원_메인");
			}
		});
	}
	
	
	
}
