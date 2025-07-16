package common.gui;

import java.awt.Dimension;
import java.awt.Font;

import common.account.login.Session;
import customer.payment.gui.components.PaymentDefaultButton;
import insuranceMain.customerPanel.CustomerMainPanel;

public class MainPageButton extends PaymentDefaultButton{
	public MainPageButton(CustomerMainPanel cmp) {
		super("메인페이지");
		
		setPreferredSize(new Dimension(150, 30));
		setFont(new Font("맑은 고딕", Font.BOLD, 16));
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
}
