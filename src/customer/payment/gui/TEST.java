package customer.payment.gui;

import javax.swing.JFrame;

public class TEST {
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame();
		mainFrame.setBounds(0, 0, 1440, 1024);
		
		mainFrame.add(new AutoPaymentPanel1());
		
		
		

		
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
