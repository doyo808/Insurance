package employee.product.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import employee.product.view.buttons.ShowPanelButtons;
import employee.product.view.center.ShowPanelCenter;

public class ShowPanel extends JPanel {
	
	public ShowPanelCenter center = new ShowPanelCenter();
	public ShowPanelButtons bottom = new ShowPanelButtons();
	
	public ShowPanel() {
		setLayout(new BorderLayout());
		
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
	}
	
}
