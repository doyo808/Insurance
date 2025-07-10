package common.gui.Unsigned_panel;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Unsigned_MainPanel extends JPanel {
	
	CardLayout c = new CardLayout();
	
	public Unsigned_MainPanel() {
		init();
		
		JPanel card1 = new Unsigned_CardPanel1(0, 0, 1440, 1024);
		JPanel card2 = new Unsigned_CardPanel2();
		
		
        
        add(card1, "firstCard");
        add(card2, "secondCard");
        c.show(this, "secondCard");            
	}
	
	void init() {
		setLayout(c);
	}
}
