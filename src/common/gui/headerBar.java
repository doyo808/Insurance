package common.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class headerBar extends JPanel {
		
	private JPanel parentCardPanel; //카드 전환에 사용될 부모 패널
	
	public headerBar() {
		setPreferredSize(new Dimension(0, 200)); // 높이 고정, 너비는 프레임에 맞춤
		setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
		setBackground(Color.WHITE);
		
//		new CardSwitchButton("보험금청구", 카드패널, "", 150, 60);
		
//		new MainButton("보험상품조회", this ,150, 60);
//		new MainButton("보험상품가입", this ,150, 60);
//		new MainButton("보험금납부", this ,150, 60);
//		new MainButton("보험금청구", this ,150, 60);
//		new MainButton("마이페이지", this ,150, 60);
//		new MainButton("회원가입", this ,150, 60);
		
		setVisible(true);
		
	}
}
	

		// 로그인이 된 상태면 회원가입 버튼 삭제!

	
//
//		// 예시: 아래에 CardLayout을 쓸 메인 패널도 추가
//		JPanel mainPanel = new JPanel(new CardLayout());
//		frame.add(mainPanel, BorderLayout.CENTER);
//
//		frame.setVisible(true);
//		
//		
	

