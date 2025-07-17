package customer.payment.gui.autopayment;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import common.account.login.Session;
import common.database.dao.ContractDAO;
import common.database.dao.ProductDAO;
import common.database.dao.ProductPaymentCycleDAO;
import common.database.model.ContractModel;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;
import customer.payment.gui.PaymentMainPanel;
import customer.payment.gui.components.CardNavButton;
import customer.payment.gui.components.CardSwitcher;
import customer.payment.gui.components.ContractTablePanel;
import customer.payment.gui.components.DefaultPanel;
import customer.payment.gui.components.PaymentDefaultButton;
import customer.payment.method.AutoPaymentInquire;
import net.miginfocom.swing.MigLayout;

public class AutoPaymentPanel1 extends DefaultPanel {

	private static final long serialVersionUID = 1L;
	
	private ContractTablePanel contractPanel;
	private String[] selected = null;
	private CardSwitcher swicher;
	// 자동이체 등록전 어떤 계약의 자동이체를 지정할지 선택하는 패널
	/**
	 * Create the panel.
	 */
	public AutoPaymentPanel1(CardSwitcher switcher) {
		this.swicher = switcher;
		setBounds(0, 0, 1440, 700);
		setLayout(new MigLayout("", "[200px,grow][1040px,grow][200px]", "[][][318.00,grow][]"));
		
		List<String[]> datas = AutoPaymentInquire.getcontractDataStr();
		
		JLabel lbln = new JLabel("<html><div style='text-align: center;'>자동이체 계좌를 등록할<br>계약을 선택해주세요</div></html>");
		lbln.setHorizontalAlignment(SwingConstants.CENTER);
		lbln.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbln.setBackground(Color.WHITE);
		add(lbln, "cell 1 1,grow");
		
		JPanel contractPanel = new ContractTablePanel(datas);
		contractPanel.setBackground(new Color(255, 255, 255));
		add(contractPanel, "cell 1 2,grow");
		
		
		CardNavButton cnbtn = new CardNavButton("이전", switcher, "PaymentMenu");
		add(cnbtn, "cell 1 3, center");
		
		PaymentDefaultButton chkbtn = new PaymentDefaultButton("확인");
		add(chkbtn, "cell 1 3, center");
		
		chkbtn.addActionListener(e -> {
			selected = ((ContractTablePanel) contractPanel).getSelectedContract();
			
			if (selected != null && switcher instanceof PaymentMainPanel panel) {
				System.out.println("선택된 계약: " + Arrays.toString(selected));
				panel.showCardAndData(selected, "AutoPayment2", this);
            } else {
                System.out.println("계약이 선택되지 않았습니다.");
            }
				
		});
	}
	

}
