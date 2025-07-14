package customer.mypage.gui;

import javax.swing.JFrame;

public class MyPageMainFrame extends JFrame {
	
	public MyPageMainFrame() {
		
		setTitle("윤한식_MyPage");
		setBounds(100, 10, 1440, 1024);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(null);
		
		MyPageMainWindow panelMyPage = new MyPageMainWindow();
				
		
		add(panelMyPage);	
		
		setVisible(true);
		
	}	
	
	public static void main(String[] args) {
		new MyPageMainFrame();
	}

}
