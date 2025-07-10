package insurancePanelPractice;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TopMenuPanel extends JPanel {
	CenterPanel cp = new CenterPanel();
	
	public TopMenuPanel(CenterPanel cp) {
		this.cp = cp;
		addComponents();
		setBackground(Color.gray);
	}
	
	void addComponents() {
		JButton btn1 = new JButton("조회");
		JButton btn2 = new JButton("상품가입");
		
		btn1.addActionListener(e -> {
			cp.showCard("inquiry");
		});
		btn2.addActionListener(e -> {
			cp.showCard("join");
		});
		
		
		add(btn1);
		add(btn2);
	}
}
