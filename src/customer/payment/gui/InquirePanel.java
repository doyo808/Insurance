package customer.payment.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class InquirePanel extends BasicPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public InquirePanel() {
		setBounds(0, 0, 1440, 700);
		setLayout(new MigLayout("", "[200px,grow][1040px,grow][200px]", "[150px,grow][][318.00,grow]"));
		List<String[]> data = List.of(
	            new String[]{"1", "실손보험", "C12345", "2023-01-01", "2033-01-01", "월납", "30,000", "홍길동"},
	            new String[]{"2", "암보험", "C67890", "2022-05-01", "2032-05-01", "연납", "100,000", "김영희"}
	        );
		JLabel lbln = new JLabel("<html><div style='text-align: center;'>상세내역을 확일할<br>계약을 선택해주세요</div></html>");
		lbln.setHorizontalAlignment(SwingConstants.CENTER);
		lbln.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbln.setBackground(Color.WHITE);
		add(lbln, "cell 1 1,grow");
		
		
		
		JPanel ContractPanel = new ContractTablePanel(data);
		ContractPanel.setBackground(new Color(255, 255, 255));
		add(ContractPanel, "cell 1 2,alignx center,aligny center");
		ContractPanel.setLayout(new MigLayout("", "[]", "[]"));
	}
	
	
}
