package common.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import insuranceMain.customerPanel.CustomerMainPanel;

public class HeaderBar extends JPanel {
		
	private JPanel parentCardPanel; //카드 전환에 사용될 부모 패널
	
	public HeaderBar(JPanel parentCardPanel, CustomerMainPanel cmp) {
		this.parentCardPanel = parentCardPanel;
		
//		setPreferredSize(new Dimension(0, 200)); // 높이 고정, 너비는 프레임에 맞춤
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
		setBackground(Color.WHITE);
		
		add(new HomeButton(cmp, 0, 0));
		
		// 여기서 parentCardPanel은 상품조회,상품가입,청구,납부,마이페이지.. 등의 패널이 다 들어간 카드레이아웃 패널입니다.
		CardSwitchButton 보험상품조회버튼 = new CardSwitchButton("보험상품조회", parentCardPanel, "", 150, 60); // 보험상품조회메인패널 이름 필요
		CardSwitchButton 보험상품가입버튼 = new CardSwitchButton("보험상품가입", parentCardPanel, "", 150, 60); // 보험상품가입메인패널 이름 필요
		CardSwitchButton 보험금청구버튼 = new CardSwitchButton("보험금청구", parentCardPanel, "ClaimMainPanel", 150, 60);
		CardSwitchButton 보험금납부버튼 = new CardSwitchButton("보험금납부", parentCardPanel, "", 150, 60); // 보험금납부메인패널 이름 필요
		CardSwitchButton 마이페이지버튼 = new CardSwitchButton("마이페이지", parentCardPanel, "", 150, 60); // 마이페이지메인패널 이름 필요
		CardSwitchButton 로그아웃버튼 = new CardSwitchButton("로그아웃", parentCardPanel, "", 150, 60); // 회원가입메인패널이름 필요
		
		add(보험상품조회버튼);
		add(보험상품가입버튼);
		add(보험금청구버튼);
		add(보험금납부버튼);
		add(마이페이지버튼);
		add(로그아웃버튼);
		
		// 로그인이 된 상태면 회원가입 버튼 삭제!
		
		
		setVisible(true);
		
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
	

