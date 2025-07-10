package insurancePanelPractice;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JoinPanel extends JPanel {
	public JoinPanel() {
		addComponents();
	}
	
	void addComponents() {
		JLabel label1 = new JLabel("상품가입 메인 페이지");
		
		add(label1);
	}
}
