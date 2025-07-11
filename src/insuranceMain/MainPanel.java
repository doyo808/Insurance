package insuranceMain;

import java.awt.Dimension;

import javax.swing.JPanel;

import insuranceMain.customerPanel.CustomerMainPanel;

public class MainPanel extends JPanel {
	// 가장 최상위 패널. 여기에 고객패널과 직원패널이 들어간다.
	
	public MainPanel() {
		add(new CustomerMainPanel());
//		add(new EmployeeMainPanel());   : 미구현

	}
}
