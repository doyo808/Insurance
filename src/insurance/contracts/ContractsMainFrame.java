package insurance.contracts;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ContractsMainFrame extends JFrame {
	public ContractsMainFrame() {
		init();
		
		
	}
	
	private void init() {
		setVisible(true);
		setLayout(new BorderLayout());
		setBounds(100, 10, 1440, 1024);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
