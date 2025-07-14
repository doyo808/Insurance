package insuranceMain.customerPanel.services;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.gui.HomeButton;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ClaimMainPanelTemp extends JPanel {
	public ClaimMainPanelTemp(CustomerMainPanel cmp) {
		add(new HomeButton(cmp, 700, 10));
		add(new JButton("청구페이지 입니다."));
	}
}
