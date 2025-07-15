package customer.payment.gui;


import java.awt.Color;

import javax.swing.UIManager;

import insuranceMain.customerPanel.CustomerMainPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Rectangle;

public class PaymentMainPanel extends BasicPanel {

	private static final long serialVersionUID = 1L;

	public PaymentMainPanel(CustomerMainPanel cmp) {
		setBounds(new Rectangle(0, 0, 1440, 700));
		setForeground(new Color(255, 255, 255));
		try {
            // Look & Feel을 Metal로 강제 설정 (Swing 기본 스타일)
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[30.00][350px]", "[30][80px][30][80px][30][80px]"));
		
		PaymentMenuPanel menu1 = new PaymentMenuPanel();
		add(menu1, "cell 1 1,grow");
		menu1.setButtonText("조회");
        menu1.setLabelText("보험료 납부 내역 조회");
        
		PaymentMenuPanel menu2 = new PaymentMenuPanel();
		add(menu2, "cell 1 3,grow");
		menu2.setButtonText("납부");
		menu2.setLabelText("보험료 납부");
		
		PaymentMenuPanel menu3 = new PaymentMenuPanel();
		add(menu3, "cell 1 5,grow");
		menu3.setButtonText("등록");
		menu3.setLabelText("자동이체 등록");
		
		
	}
}
