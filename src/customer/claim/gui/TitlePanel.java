package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitlePanel extends JPanel {
     
	public TitlePanel(String title) {
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 10, 20, 10)); // 상단 간격
		titleLabel.setVisible(true);
		add(titleLabel); 
	}
	
     
}
