package employee.product.view.buttons;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RegistPanelBottom extends JPanel {
	public JButton prev = new JButton("이전으로");
	public JButton regist = new JButton("등록하기");
	
	public RegistPanelBottom() {
		setLayout(new FlowLayout());
		setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
		add(prev);
		add(regist);
	}
}
