package insuranceMain.customerPanel.services;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import common.gui.FooterImagePanel;
import common.gui.HeaderBar;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ServicesMainPanel extends JPanel {
	private ServicesMainCenterPanel smcp;
	private CustomerMainPanel cmp;
	private HeaderBar hb;
	
	public ServicesMainPanel(CustomerMainPanel cmp) {
		this.cmp = cmp;
		setLayout(new BorderLayout());
		
		add(smcp = new ServicesMainCenterPanel(cmp), BorderLayout.CENTER);
		add(hb = new HeaderBar(smcp, cmp), BorderLayout.NORTH);
		add(new FooterImagePanel(), BorderLayout.SOUTH);
	}

	public ServicesMainCenterPanel getSmcp() {
		return smcp;
	}
	
	public void refreshPanels() {
        // 기존 패널 제거 후 새로 생성
        remove(smcp);
        remove(hb);
        
        add(smcp = new ServicesMainCenterPanel(cmp), BorderLayout.CENTER);
        add(hb = new HeaderBar(smcp, cmp), BorderLayout.NORTH);
        
        revalidate();
        repaint();
    }
}
