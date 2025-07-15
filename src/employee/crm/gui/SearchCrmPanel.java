package employee.crm.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class SearchCrmPanel extends JPanel {

	
	public SearchCrmPanel() {
		setPreferredSize(new Dimension(1440, 700));
		setBounds(0, 162, 1440, 700);
		
		setLayout(null);
		
		JSeparator seperator = new JSeparator(SwingConstants.HORIZONTAL);
		seperator.setBounds(0, 0, 1440, 700);
		seperator.setForeground(Color.RED);
		add(seperator);
		
	}
}
