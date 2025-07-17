package employee.product.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import employee.product.view.buttons.EditPanelBottom;
import employee.product.view.center.EditPanelCenter;

public class EditPanel extends JPanel{
	
	public EditPanelCenter center = new EditPanelCenter();
	public EditPanelBottom bottom = new EditPanelBottom();
	
	public EditPanel() {
		setLayout(new BorderLayout());
		
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
	}
}
