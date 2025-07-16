package customer.payment.gui.components;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;

public class PaymentMenuComponentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel label = new JLabel("내용입력");
	private JButton button = new PaymentMenuButton("내용입력");
	/**
	 * Create the panel.
	 */
	public PaymentMenuComponentPanel() {
		setBackground(new Color(255, 255, 255));
		setSize(350, 80);
		setLayout(null);
		button.setBackground(new Color(0, 0, 0));
		button.setBounds(0, 45, 350, 35);
		add(button);
		
		
		label.setBackground(new Color(255, 255, 255));
		label.setBounds(0, 0, 350, 35);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(label);

	}
	
	public void setButtonText(String txt) {
		button.setText(txt);
	}
	
	public void setLabelText(String txt) {
		label.setText(txt);
	}
	
	public void addButtonListener(ActionListener listener) {
		button.addActionListener(listener);
	}

}
