package insuranceMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import common.account.login.Session;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// 이곳에서 TEST모드 변경(true이면 홍길동 로그인된 상태, false는 로그아웃)
	public static boolean TEST = true;
	public static String TEST_LOGIN_ID = "hong123";
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 여기가 메인 시작
					MainFrame frame = new MainFrame();
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 흐름도: 메인프레임 > 메인패널 > (고객메인패널, 직원메인패널)
	 * 고객메인패널(카드) > 회원가입/로그인 > 조회, 가입, 청구 등 각 기능 패널
	 */
	public MainFrame() {
		init();
		Session.clear();
		add(new MainPanel());
	}
	
	void init() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);	
	}
}
