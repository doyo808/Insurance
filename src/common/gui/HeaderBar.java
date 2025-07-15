package common.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import common.account.login.Session;
import common.method.ButtonMaker;
import insuranceMain.MainFrame;
import insuranceMain.customerPanel.CustomerMainPanel;

public class HeaderBar extends JPanel {
		
	private JPanel parentCardPanel; //카드 전환에 사용될 부모 패널
	
	public HeaderBar(JPanel parentCardPanel, CustomerMainPanel cmp) {
		this.parentCardPanel = parentCardPanel;
		
//		setPreferredSize(new Dimension(0, 200)); // 높이 고정, 너비는 프레임에 맞춤
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
		setBackground(Color.gray);
		
		add(new HomeButton(cmp, 0, 0));
		ButtonMaker.addButton(240, 1, this);
		
		// 여기서 parentCardPanel은 상품조회,상품가입,청구,납부,마이페이지.. 등의 패널이 다 들어간 카드레이아웃 패널입니다.
		CardSwitchButton 보험상품조회버튼 = new CardSwitchButton("보험상품조회", parentCardPanel, "보험상품조회", 150, 60); 
		CardSwitchButton 보험상품가입버튼 = new CardSwitchButton("보험상품가입", parentCardPanel, "보험상품가입", 150, 60); 
		CardSwitchButton 보험금청구버튼 = new CardSwitchButton("보험금청구", parentCardPanel, "보험금청구", 150, 60);
		CardSwitchButton 보험금납부버튼 = new CardSwitchButton("보험료납부", parentCardPanel, "보험료납부", 150, 60); 
		CardSwitchButton 마이페이지버튼 = new CardSwitchButton("마이페이지", parentCardPanel, "마이페이지", 150, 60);
		
		JButton btn = new JButton("로그아웃");
		btn.setSize(new Dimension(150, 60));
		AddLogoutAction(btn, cmp);
		
		add(보험상품조회버튼);
		add(보험상품가입버튼);
		add(보험금청구버튼);
		add(보험금납부버튼);
		add(마이페이지버튼);
		add(btn);
		
		setVisible(true);
	}
	
	private void AddLogoutAction(JButton btn, CustomerMainPanel cmp) {
		btn.addActionListener(e -> {
			Session.clear();
			cmp.showCard("accounts");
			cmp.getAMP().showCard("비회원_메인");
		});
	}
}


//
//		// 예시: 아래에 CardLayout을 쓸 메인 패널도 추가
//		JPanel mainPanel = new JPanel(new CardLayout());
//		frame.add(mainPanel, BorderLayout.CENTER);
//
//		frame.setVisible(true);
//		
//		
	

