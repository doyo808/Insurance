package common.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import common.gui.Unsigned_panel.Unsigned_MainPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 여기가 메인 시작
					MainFrame frame = new MainFrame();
					
					Unsigned_MainPanel ump = new Unsigned_MainPanel();
					
					
					frame.add(ump);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 10, 1440, 1024);
		getContentPane().setLayout(new BorderLayout());
		
		
	}
}
