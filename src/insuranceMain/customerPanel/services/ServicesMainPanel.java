package insuranceMain.customerPanel.services;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import common.gui.FooterImagePanel;
import common.gui.HeaderBar;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ServicesMainPanel extends JPanel {
	private ServicesMainCenterPanel smcp;
	private CustomerMainPanel cmp;
	
	public ServicesMainPanel(CustomerMainPanel cmp) {
		this.cmp = cmp;
		setLayout(new BorderLayout());
		
		add(smcp = new ServicesMainCenterPanel(cmp), BorderLayout.CENTER);
		add(new HeaderBar(smcp, cmp), BorderLayout.NORTH);
		add(new FooterImagePanel(), BorderLayout.SOUTH);
	}

	public ServicesMainCenterPanel getSmcp() {
		return smcp;
	}
	
	public void refreshPanels() {
        // 기존 패널 제거 후 새로 생성
        remove(smcp);
        smcp = new ServicesMainCenterPanel(cmp);
        add(smcp, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
