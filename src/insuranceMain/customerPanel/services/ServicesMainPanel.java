package insuranceMain.customerPanel.services;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import common.gui.FooterImagePanel;
import common.gui.HeaderBar;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ServicesMainPanel extends JPanel {
	private ServicesMainCenterPanel smcp;
	
	public ServicesMainPanel(CustomerMainPanel cmp) {
		setLayout(new BorderLayout());
		
		add(smcp = new ServicesMainCenterPanel(cmp), BorderLayout.CENTER);
		add(new HeaderBar(smcp, cmp), BorderLayout.NORTH);
		add(new FooterImagePanel(), BorderLayout.SOUTH);
	}

	public ServicesMainCenterPanel getSmcp() {
		return smcp;
	}

}
