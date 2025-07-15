package customer.payment.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

public class PaymentMenuPanel extends DefaultPanel{
	private CardSwitcher switcher;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	PaymentMenuPanel(CardSwitcher swicher) {
		setBounds(new Rectangle(0, 0, 1440, 700));
		setForeground(new Color(255, 255, 255));
		this.switcher = swicher;
		try {
            // Look & Feel을 Metal로 강제 설정 (Swing 기본 스타일)
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
		setBackground(new Color(255, 255, 255));
		setLayout(new MigLayout("", "[30.00][350px]", "[30][80px][30][80px][30][80px]"));
		
		PaymentMenuComponentPanel menu1 = new PaymentMenuComponentPanel();
		add(menu1, "cell 1 1,grow");
		menu1.setButtonText("조회");
        menu1.setLabelText("보험료 납부 내역 조회");
        menu1.addButtonListener(e -> {
        	switcher.showCard("AutoPayment1");
        });
        
        
		PaymentMenuComponentPanel menu2 = new PaymentMenuComponentPanel();
		add(menu2, "cell 1 3,grow");
		menu2.setButtonText("납부");
		menu2.setLabelText("보험료 납부");
		menu2.addButtonListener(e -> {
        	switcher.showCard("AutoPayment1");
        });
		
		PaymentMenuComponentPanel menu3 = new PaymentMenuComponentPanel();
		add(menu3, "cell 1 5,grow");
		menu3.setButtonText("등록");
		menu3.setLabelText("자동이체 등록");
		menu3.addButtonListener(e -> {
        	switcher.showCard("AutoPayment1");
        });
	}
	
}
