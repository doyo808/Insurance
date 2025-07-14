package insuranceMain.customerPanel.services;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.gui.HomeButton;
import insuranceMain.customerPanel.CustomerMainPanel;

public class ProductMainPanel extends JPanel {
	public ProductMainPanel(CustomerMainPanel cmp) {
		add(new HomeButton(cmp, 700, 10));
		add(new JButton("상품 조회페이지 입니다."));
	}
}
