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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
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

	DefaultTableModel claimModel;
	DefaultTableModel empModel;
	DefaultTableModel processModel;

	ClaimsModel claimDetail;

	public ClaimDetailPanel(JPanel parentCardPanel, int claim_id) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) parentCardPanel.getLayout();
		setLayout(new BorderLayout());

		TitlePanel title = new TitlePanel("보험금 청구 상세내역");
		add(title, BorderLayout.NORTH);

		BottomButtonPanel bottomBP = new BottomButtonPanel(this);
		bottomBP.getNextButton().setVisible(false);
		bottomBP.getPreviousButton().addActionListener((e) -> {
			cl.show(parentCardPanel, "ClaimHistoryPanel");
		});

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		claimInfoTable = createTableSection(centerPanel, "보험금 청구내역",
				new String[] { "접수번호", "사고발병일자", "보험상품명", "재해자", "청구유형" });
		claimInfoTable.setRowHeight(30);

		centerPanel.add(Box.createVerticalStrut(50));
		employeeInfoTable = createTableSection(centerPanel, "보상 담당자",
				new String[] { "재해자", "소속부서", "담당자명", "담당자 전화번호" });
		employeeInfoTable.setRowHeight(30);

		centerPanel.add(Box.createVerticalStrut(50));

		processStatusTable = createTableSection(centerPanel, "처리 현황",
				new String[] { "접수날짜", "서류심사날짜", "사고조사날짜", "보험금 지급심사날짜", "보험금 지급날짜", "총 지급금액" });
		processStatusTable.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);

//		add(centerPanel, BorderLayout.CENTER);
		add(bottomBP, BorderLayout.SOUTH);

		getClaimDetailTable(claim_id);

		// 가운데 정렬 렌더러
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		// 모든 열에 가운데 정렬 적용
		for (int i = 0; i < claimInfoTable.getColumnCount(); i++) {
			claimInfoTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			claimInfoTable.getColumnModel().getColumn(i).setResizable(false);
		}

		for (int i = 0; i < employeeInfoTable.getColumnCount(); i++) {
			employeeInfoTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			employeeInfoTable.getColumnModel().getColumn(i).setResizable(false);
		}

		for (int i = 0; i < processStatusTable.getColumnCount(); i++) {
			processStatusTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			processStatusTable.getColumnModel().getColumn(i).setResizable(false);
		}

	}

	// 수정 불가능한 테이블 생성 메서드
	private JTable createTableSection(JPanel parent, String title, String[] columnNames) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(title), 
				BorderFactory.createEmptyBorder(10, 10, 10, 10)
		));

		DefaultTableModel model = new DefaultTableModel(new Object[1][columnNames.length], columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(model);
//		table.setFillsViewportHeight(true);
		table.setRowHeight(30);

		JScrollPane tableScroll = new JScrollPane(table);
		 tableScroll.setPreferredSize(new Dimension(1200, 120)); 
		panel.add(tableScroll, BorderLayout.CENTER);
		
	    
	    panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
	    panel.setAlignmentX(CENTER_ALIGNMENT); // 박스 레이아웃 정렬
	    
//		panel.add(table);
		parent.add(panel);

		return table;
	}

	// DAO를 통해 외부에서 접근할 수 있도록 getter 제공
	public void getClaimDetailTable(int claim_id) {
		try (Connection conn = InsuranceTeamConnector.getConnection()) {
			CustomerModel cm = Session.getCustomer();
			if (cm == null) {
				JOptionPane.showMessageDialog(this, "로그인 정보가 없습니다.");
				return;
			}
			claimDetail = ClaimDAO.getClaimDetail(cm.getLogin_id(), claim_id, conn);
			
			if (claimDetail == null) {
				JOptionPane.showMessageDialog(this, "해당 청구 내역이 존재하지 않습니다.");
			 	return;
			}
				claimModel = (DefaultTableModel) claimInfoTable.getModel();
				claimModel.setValueAt(claimDetail.getClaim_id(), 0, 0);
				claimModel.setValueAt(claimDetail.getAccident_date(), 0, 1);
				claimModel.setValueAt(claimDetail.getProduct_name(), 0, 2);
				claimModel.setValueAt(claimDetail.getInsured_name(), 0, 3);
				claimModel.setValueAt(claimDetail.getClaim_category(), 0, 4);
				
				empModel = (DefaultTableModel) employeeInfoTable.getModel();
				empModel.setValueAt(claimDetail.getInsured_name(), 0, 0); 
		        empModel.setValueAt(claimDetail.getDepartment_name(), 0, 1);
		        empModel.setValueAt(claimDetail.getEmployee_name(), 0, 2);
		        empModel.setValueAt(claimDetail.getEmployee_phone_number(), 0, 3);
		        
		        processModel = (DefaultTableModel) processStatusTable.getModel();
		        processModel.setValueAt(claimDetail.getClaim_date(), 0, 0);
		        processModel.setValueAt(claimDetail.getDocument_review_date(), 0, 1);
		        processModel.setValueAt(claimDetail.getAccident_investigation_date(), 0, 2);
		        processModel.setValueAt(claimDetail.getPayment_review_date(), 0, 3);
		        processModel.setValueAt(claimDetail.getCompletion_date(), 0, 4);
		        processModel.setValueAt(claimDetail.getTotal_paid_amount(), 0, 5); 
		        
		        System.out.println("[DEBUG] 로그인 ID: " + cm.getLogin_id());
		        
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,  "청구 정보 로딩 중 오류 발생: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}