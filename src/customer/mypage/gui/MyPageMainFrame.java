package customer.mypage.gui;

import java.sql.SQLException;

import javax.swing.JFrame;

public class MyPageMainFrame extends JFrame {
	
	public MyPageMainFrame() {
		
		setTitle("OO다이렉트 보험 마이페이지");
		setBounds(100, 10, 1440, 1024);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(null);
		
		MyPageMainPanel panelMyPage = new MyPageMainPanel();		
		add(panelMyPage);
		
		setVisible(true);
		
	}	
	
	public static void main(String[] args) throws SQLException {
		new MyPageMainFrame();
	}

}
