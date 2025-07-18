package customer.payment.gui.pay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import common.account.login.Session;
import common.database.dao.UnpaidDAO;
import common.database.model.CustomerModel;
import common.database.model.UnpaidModel;
import customer.payment.gui.PaymentMainPanel;
import customer.payment.gui.components.CardNavButton;
import customer.payment.gui.components.PaymentDefaultButton;
import customer.payment.gui.components.UnpaidTablePanel;
import customer.payment.method.payment;
import net.miginfocom.swing.MigLayout;

public class PayPanel1 extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<UnpaidModel> unpaids;
	private List<String[]> datas;
	
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
			if ((unpaids = UnpaidDAO.getUnpaidsListByCustomer(cm.getCustomer_id())) != null) {
				substr = String.format("<html><div style='text-align: center;'> %s님의 미납건이 없습니다.</div></html>",
						cm.getCustomer_name());
			} else {
				substr = String.format("<html><div style='text-align: center;'> %s님의 미납건 %d건 입니다.<br>납부하실 계약을 선택해주세요.</div></html>",
						cm.getCustomer_name(), unpaids.size());
			}
		
			subText = new JLabel(substr);
			subText.setHorizontalAlignment(SwingConstants.CENTER);
			subText.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			subText.setBackground(Color.WHITE);
			add(subText, "cell 1 0,alignx center,aligny center");
			
			datas = payment.ModelsToStringData(unpaids);
			unpaidTable = new UnpaidTablePanel(datas);
			add(unpaidTable, "cell 1 1,alignx center,aligny center");
		}
		
		CardNavButton cnbtn = new CardNavButton("이전", pmp, "PaymentMenu");
		add(cnbtn, "cell 1 3, center");
		
		PaymentDefaultButton chkbtn = new PaymentDefaultButton("확인");
		add(chkbtn, "cell 1 3, center");
		
		chkbtn.addActionListener(e -> {
		    if (unpaids == null || unpaids.isEmpty()) {
		        JOptionPane.showMessageDialog(
		            null,
		            "미납내역이 없습니다. 메인페이지로 이동합니다.",
		            "알림",
		            JOptionPane.INFORMATION_MESSAGE
		        );
		        pmp.getCMP().showCard("accounts");
		        pmp.getCMP().getAMP().showCard("회원_메인");

		    } else {
		        selectedData = unpaidTable.getSelectedRowData();
		        if (selectedData != null && pmp instanceof PaymentMainPanel panel) {
		            System.out.println("선택된 계약: " + Arrays.toString(selectedData));
		            panel.showCardAndData(selectedData, "pay2", this);
		        } else {
		            JOptionPane.showMessageDialog(
		                PayPanel1.this,
		                "계약을 선택해주세요.",
		                "경고",
		                JOptionPane.WARNING_MESSAGE
		            );
		        }
		    }
		});
	}
}
