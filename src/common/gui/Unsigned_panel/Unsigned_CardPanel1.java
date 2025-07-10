package common.gui.Unsigned_panel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Unsigned_CardPanel1 extends JPanel {
	
	public Unsigned_CardPanel1(int x, int y, int width, int height) {
		setLayout(null);
		setPreferredSize(new Dimension(width, height));
		setBounds(x, y, width, height);
		setBackground(Color.gray);
		
		add(new ButtonSignup());
        add(new ButtonLogin());
        add(new ButtonMypage());
		
	}
}
