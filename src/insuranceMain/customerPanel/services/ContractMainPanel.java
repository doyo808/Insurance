package insuranceMain.customerPanel.services;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.gui.HomeButton;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ContractMainPanel extends JPanel {
	public ContractMainPanel(CustomerMainPanel cmp) {
		add(new HomeButton(cmp, 700, 10));
		add(new JButton("계약페이지 입니다."));
	}
}
