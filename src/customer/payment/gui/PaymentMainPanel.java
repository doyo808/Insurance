package customer.payment.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;

import customer.payment.gui.autopayment.AutoPaymentPanel1;
import customer.payment.gui.autopayment.AutoPaymentPanel2;
import customer.payment.gui.autopayment.AutoPaymentPanel3;
import customer.payment.gui.components.CardSwitcher;
import customer.payment.gui.components.DefaultPanel;
import customer.payment.gui.inquire.InquirePanel1;
import customer.payment.gui.inquire.InquirePanel2;
import customer.payment.gui.pay.PayPanel1;
import customer.payment.gui.pay.PayPanel2;
import insuranceMain.customerPanel.CustomerMainPanel;

public class PaymentMainPanel extends DefaultPanel implements CardSwitcher {

	private static final long serialVersionUID = 1L;
	private PaymentMenuPanel paymentMenuPanel;
	private AutoPaymentPanel1 autoPaymentPanel1;
	private AutoPaymentPanel2 autoPaymentPanel2;
	private AutoPaymentPanel3 autoPaymentPanel3;
	private InquirePanel1 inquirePanel1;
	private InquirePanel2 inquirePanel2;
	private PayPanel1 payPanel1;
	private PayPanel2 payPanel2;

	private CustomerMainPanel cmp;

	public PaymentMainPanel(CustomerMainPanel cmp) {
		this.cmp = cmp;

		paymentMenuPanel = new PaymentMenuPanel(this);
		autoPaymentPanel1 = new AutoPaymentPanel1(this);
		autoPaymentPanel2 = new AutoPaymentPanel2(this);
		autoPaymentPanel3 = new AutoPaymentPanel3(this, cmp);

		inquirePanel1 = new InquirePanel1(this);
		inquirePanel2 = new InquirePanel2(this, cmp); // 초기 하나만 등록
		
		payPanel1 = new PayPanel1(this);
		payPanel2 = new PayPanel2(this);
		
		

		setBounds(new Rectangle(0, 0, 1440, 700));
		setForeground(new Color(255, 255, 255));
		setLayout(new CardLayout());

		add("PaymentMenu", paymentMenuPanel);
		add("AutoPayment1", autoPaymentPanel1);
		add("AutoPayment2", autoPaymentPanel2);
		add("AutoPayment3", autoPaymentPanel3);
		add("Inquire1", inquirePanel1);
		add("Inquire2", inquirePanel2);
		add("pay1", payPanel1);
		add("apy2", payPanel2);
	}

	@Override
	public void showCard(String cardName) {
		CardLayout cl = (CardLayout) this.getLayout();
		cl.show(this, cardName);
	}

	// Panel1에서 선택된 계약 정보를 넘겨줄 메서드
	public void showCardAndData(String[] contractData, String name, JPanel p) {

		if (contractData != null) {
	
			if (p.equals(autoPaymentPanel1)) {
				int contract_id = Integer.valueOf(contractData[2]);
				autoPaymentPanel2.setSelectedData(contractData);
				autoPaymentPanel2.displayContractInfo(contractData);
				autoPaymentPanel2.displayRegisteredAccount(contract_id);
			} else if (p.equals(inquirePanel1)) {
				// 기존 InquirePanel2 제거
				this.remove(inquirePanel2);

				// 새로 생성 후 추가
				inquirePanel2 = new InquirePanel2(this, cmp);
				inquirePanel2.setSelectedData(contractData);
				this.add("Inquire2", inquirePanel2);
			} else if (p.equals(payPanel1)) {
				// 기존 InquirePanel2 제거
				this.remove(payPanel2);
				
				// 새로 생성 후 추가
				payPanel2 = new PayPanel2(this);
				payPanel2.setSelectedData(contractData);
				payPanel2.displayContractInfo(contractData);
				this.add("pay2", payPanel2);
			}

			
			
			
			
		}

		showCard(name);
	}
	
	public CustomerMainPanel getCMP() {
		return cmp;
	}
}