package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import common.account.login.Session;
import common.database.dao.ClaimDAO;
import common.database.model.ClaimsModel;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;
import customer.claim.gui.component.BottomButtonPanel;
import customer.claim.gui.component.TitlePanel;

public class ClaimDetailPanel extends JPanel {

	private JPanel parentCardPanel;

	// 테이블 참조용 필드 선언 → DAO로 데이터 넣기 쉽게
	private JTable claimInfoTable;
	private JTable employeeInfoTable;
	private JTable processStatusTable;
	
	private CustomerModel cm;

	public ClaimDetailPanel(JPanel parentCardPanel) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) parentCardPanel.getLayout();
		setLayout(new BorderLayout());
		
		cm = Session.getCustomer();
		if (cm == null) {
			JOptionPane.showMessageDialog(this, "로그인 정보가 없습니다.");
			return;
		}

		TitlePanel title = new TitlePanel("보험금 청구 상세내역");
		add(title, BorderLayout.NORTH);

		BottomButtonPanel bottomBP = new BottomButtonPanel(this);
		bottomBP.getNextButton().setVisible(false);
		bottomBP.getPreviousButton().addActionListener((e) -> {
			cl.show(parentCardPanel, "ClaimHistoryPanel");
		});

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setPreferredSize(new Dimension(1200, 700));

		centerPanel.add(Box.createVerticalStrut(50));
		claimInfoTable = createTableSection(centerPanel, "보험금 청구내역",
				new String[] { "접수번호", "사고발병일자", "보험상품명", "재해자", "청구유형" });
		claimInfoTable.setRowHeight(30);

		centerPanel.add(Box.createVerticalStrut(50));
		employeeInfoTable = createTableSection(centerPanel, "보상 담당자",
				new String[] { "재해자", "소속부서", "담당자명", "담당자 전화번호" });
		employeeInfoTable.setRowHeight(30);
		
		centerPanel.add(Box.createVerticalStrut(50));

		processStatusTable = createTableSection(centerPanel, "처리 현황",
				new String[] { "접수날짜", "서류심사날짜", "사고조사날짜", "보험금지급심사날짜", "결정보험금", "총 지급금액" });
		processStatusTable.setRowHeight(30);
		
		add(centerPanel, BorderLayout.CENTER);
		add(bottomBP, BorderLayout.SOUTH);
		
		getEmployeeInfoTable();
		getEmployeeInfoTable();
		getProcessStatusTable();
	}

	// 수정 불가능한 테이블 생성 메서드
	private JTable createTableSection(JPanel parent, String title, String[] columnNames) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(title));

		DefaultTableModel model = new DefaultTableModel(new Object[1][columnNames.length], columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(1100, 35));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table); // 컬럼명 보이게 하려면 반드시 JScrollPane 사용
		panel.add(scrollPane, BorderLayout.CENTER);

		panel.setMaximumSize(new Dimension(1200, 80));
		parent.add(panel);

		return table;
	}

	// DAO를 통해 외부에서 접근할 수 있도록 getter 제공
	private void getClaimDetailTable() {
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			
			ClaimsModel claimDetail = ClaimDAO.getClaimDetail(cm.getLogin_id(), conn);
			if (claimDetail != null) {
				DefaultTableModel model = (DefaultTableModel) claimInfoTable.getModel();
				model.setValueAt(claimDetail.getClaim_id(), 0, 0);
				model.setValueAt(claimDetail.getAccident_date(), 0, 1);
				model.setValueAt(claimDetail.getProduct_name(), 0, 2);
				model.setValueAt(claimDetail.getInsured_name(), 0, 3);
				model.setValueAt(claimDetail.getClaim_category(), 0, 4);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "DB 오류: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private void getEmployeeInfoTable() {
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			ClaimsModel employeeInfo = ClaimDAO.getEmployeeInfo(cm.getLogin_id(), conn);
			if (employeeInfo != null) {
				DefaultTableModel model = (DefaultTableModel) employeeInfoTable.getModel();
				model.setValueAt(employeeInfo.getInsured_name(), 0, 0);
				model.setValueAt(employeeInfo.getDepartment_name(), 0, 1);
				model.setValueAt(employeeInfo.getEmployee_name(), 0, 2);
				model.setValueAt(employeeInfo.getEmployee_phone_number(), 0, 3);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "DB 오류: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void getProcessStatusTable() {
	}
}