package insurance.contracts;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ContractMainFrame extends JFrame {
	public ContractMainFrame() {
		init();
		
		
	}
	
	private void init() {
		setVisible(true);
		setLayout(new BorderLayout());
		setBounds(100, 10, 1440, 1024);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
