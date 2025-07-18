package customer.mypage.gui;

import java.sql.SQLException;

import javax.swing.JFrame;


//개별화면 개발 테스트용 메인플레임
// 2025.07.15 프로젝트 메인프레임 통합 후 필요없어짐 -- 패널 추가 부분 주석처리되어 있음
public class MyPageMainFrame extends JFrame {	
	
	public MyPageMainFrame() {
		
		setTitle("KD손해보험 다이렉트센터 마이페이지");
		setBounds(100, 10, 1440, 1024);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(null);
		
//		MyPageMainPanel panelMyPage = new MyPageMainPanel();		
//		add(panelMyPage);
		
		setVisible(true);
		
	}	
	
	public static void main(String[] args) throws SQLException {
		new MyPageMainFrame();
	}

}
