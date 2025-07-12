package customer.mypage.gui;

import javax.swing.JFrame;

public class MyPageMainFrame extends JFrame {
	
	public MyPageMainFrame() {
		
		setTitle("윤한식");
		setBounds(100, 10, 1440, 1024);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(null);
		
		MyPageMainPanel panelMyPage = new MyPageMainPanel();
				
		
		add(panelMyPage);	
		
		setVisible(true);
		
	}	
	
	public static void main(String[] args) {
		new MyPageMainFrame();
	}

}
