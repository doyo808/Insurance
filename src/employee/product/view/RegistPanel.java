package employee.product.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import employee.product.view.buttons.RegistPanelBottom;
import employee.product.view.center.RegistPanelCenter;

public class RegistPanel extends JPanel{
	
	public RegistPanelCenter center;
	public RegistPanelBottom bottom;
	
	public RegistPanel() {
		setLayout(new BorderLayout());
	}
}
