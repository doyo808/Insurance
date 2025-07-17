package employee.product.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import employee.product.view.buttons.ShowPanelButtons;
import employee.product.view.center.ShowPanelCenter;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class ShowPanel extends JPanel {
	
	public ShowPanelCenter center = new ShowPanelCenter();
	public ShowPanelButtons bottom = new ShowPanelButtons();
	
	public ShowPanel() {
		setLayout(new BorderLayout());
		
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		
		
	}
	
}
