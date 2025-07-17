package customer.payment.gui.pay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import common.account.login.Session;
import common.database.dao.UnpaidDAO;
import common.database.model.CustomerModel;
import common.database.model.UnpaidModel;
import customer.payment.gui.PaymentMainPanel;
import customer.payment.gui.components.CardNavButton;
import customer.payment.gui.components.ContractTablePanel;
import customer.payment.gui.components.PaymentDefaultButton;
import customer.payment.gui.components.UnpaidTablePanel;
import net.miginfocom.swing.MigLayout;

public class PayPanel1 extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<UnpaidModel> unpaids;
	private CustomerModel cm;
	private String substr;
	private JLabel subText;
	private UnpaidTablePanel unpaidTable;
	private String[] selectedData; 
	/**
	 * Create the panel.
	 */
	public PayPanel1(PaymentMainPanel pmp) {
		this.cm = Session.getCustomer();
		if (cm != null) { 
			
			setMaximumSize(new Dimension(1440, 700));
			setSize(new Dimension(1440, 700));
			setBackground(new Color(255, 255, 255));
			setBounds(new Rectangle(0, 0, 1440, 700));
			setLayout(new MigLayout("", "[100px:100][1250.00,grow][100px:100]", "[100][500,grow][100]"));
			
			substr = String.format("<html><div style='text-align: center;'> %s님의 미납건 %d건 입니다.<br>납부하실 계약을 선택해주세요.</div></html>",
					cm.getCustomer_name(), 
					(unpaids = UnpaidDAO.getUnpaidsByCustomer(cm.getCustomer_id())) == null ? 0 : unpaids.size());
			
			subText = new JLabel(substr);
			subText.setHorizontalAlignment(SwingConstants.CENTER);
			subText.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			subText.setBackground(Color.WHITE);
			add(subText, "cell 1 0,alignx center,aligny center");
			
			unpaidTable = new UnpaidTablePanel();
			add(unpaidTable, "cell 1 1,alignx center,aligny center");
		}
		
		CardNavButton cnbtn = new CardNavButton("이전", pmp, "PaymentMenu");
		add(cnbtn, "cell 1 3, center");
		
		PaymentDefaultButton chkbtn = new PaymentDefaultButton("확인");
		add(chkbtn, "cell 1 3, center");
		
		chkbtn.addActionListener(e -> {
			selectedData = ((UnpaidTablePanel) unpaidTable).getSelectedRowData();
			
			if (selectedData != null && pmp instanceof PaymentMainPanel panel) {
				System.out.println("선택된 계약: " + Arrays.toString(selectedData));
				panel.showCardAndData(selectedData, "pay2", this);
            } else {
                System.out.println("계약이 선택되지 않았습니다.");
            }
				
		});
	}
}
