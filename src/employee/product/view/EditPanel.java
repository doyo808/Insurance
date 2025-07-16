package employee.product.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import employee.product.view.buttons.EditPanelBottom;
import employee.product.view.center.EditPanelCenter;

public class EditPanel extends JPanel{
	
	public EditPanelCenter center;
	public EditPanelBottom bottom;
	
	public EditPanel() {
		setLayout(new BorderLayout());
	}
}
