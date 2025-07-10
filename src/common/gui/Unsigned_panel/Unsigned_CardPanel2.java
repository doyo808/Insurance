package common.gui.Unsigned_panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Unsigned_CardPanel2 extends JPanel {
	
	public Unsigned_CardPanel2() {
		setLayout(new BorderLayout());
		
		add(new Unsigned_CardPanel1(0, 0, 500, 300), BorderLayout.NORTH);
		add(new JButton("안녕"), BorderLayout.CENTER);
		
		
	}
}
