package customer.payment.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PaymentMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new PaymentMainFrame();
	}

	/**
	 * Create the frame.
	 */
	public PaymentMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1440, 1024);
		
		add(new PaymentMainPanel());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		

	}
}
