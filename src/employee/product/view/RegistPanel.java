package employee.product.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import employee.product.view.buttons.RegistPanelBottom;
import employee.product.view.center.RegistPanelCenter;

public class RegistPanel extends JPanel{
	
	public RegistPanelCenter center = new RegistPanelCenter();
	public RegistPanelBottom bottom = new RegistPanelBottom();
	
	public RegistPanel() {
		setLayout(new BorderLayout());
		
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
	}
}
