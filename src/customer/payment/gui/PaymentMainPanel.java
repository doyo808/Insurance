package customer.payment.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JPanel;

import insuranceMain.customerPanel.CustomerMainPanel;

public class PaymentMainPanel extends DefaultPanel implements CardSwitcher {

	private static final long serialVersionUID = 1L;
	private PaymentMenuPanel paymentMenuPanel;
	private AutoPaymentPanel1 autoPaymentPanel1;
	private AutoPaymentPanel2 autoPaymentPanel2;
	private Integer selected_contract_id; 
	
	public PaymentMainPanel() {
		paymentMenuPanel = new PaymentMenuPanel(this);
		autoPaymentPanel1 = new AutoPaymentPanel1(this);
		autoPaymentPanel2 = new AutoPaymentPanel2();				
		
		setBounds(new Rectangle(0, 0, 1440, 700));
		setForeground(new Color(255, 255, 255));
		setLayout(new CardLayout());

		add("PaymentMenu", paymentMenuPanel);
		add("AutoPayment1", autoPaymentPanel1);
		add("AutoPayment2", autoPaymentPanel2);
	}

	@Override
	public void showCard(String cardName) {
	    CardLayout cl = (CardLayout) this.getLayout();
	    cl.show(this, cardName);
	}

	// ✅ AutoPaymentPanel1에서 선택된 계약 정보를 넘겨줄 메서드
	public void showAutoPayment2(String[] contractData) {
	    autoPaymentPanel2.setSelectedData(contractData);
        // 3. 값 들어가는 기능
        if (contractData != null) {
	        autoPaymentPanel2.displayContractInfo(contractData);
	        int contract_id = Integer.valueOf(contractData[2]);
	        autoPaymentPanel2.displayRegisteredAccount(contract_id);
        }
	    showCard("AutoPayment2");
	}
}