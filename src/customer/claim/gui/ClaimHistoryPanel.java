package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import common.account.login.Session;
import common.database.dao.ClaimDAO;
import common.database.model.ClaimsModel;
import common.database.model.CustomerModel;
import common.database.model.NewClaimDataModel;
import common.method.InsuranceTeamConnector;

public class ClaimHistoryPanel extends JPanel {
	private JTextField startDateField;
	private JTextField endDateField;
	private DefaultTableModel model;
	private JPanel parentCardPanel;
	private List<ClaimsModel> currentClaims = new ArrayList<>();

	// 버튼 누르면 상세 페이지로 가는 기능 추가하기
	public ClaimHistoryPanel(JPanel parentCardPanel, NewClaimDataModel claimData) {

		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) parentCardPanel.getLayout();
		setLayout(new BorderLayout());

		this.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentShown(java.awt.event.ComponentEvent e) {
				clearTable();
			}
		});

		// === 상단 패널 (조회 조건) ===
		JPanel topPanel = new JPanel(new FlowLayout());

		startDateField = new JTextField(10);
		endDateField = new JTextField(10);
		startDateField.setText(LocalDate.now().minusDays(30).toString());
		endDateField.setText(LocalDate.now().toString());

		topPanel.add(new JLabel("조회기간:"));
		topPanel.add(startDateField);
		topPanel.add(new JLabel("~"));
		topPanel.add(endDateField);

		JButton[] periodButtons = {new JButton("7일"), new JButton("30일"), new JButton("6개월"), new JButton("1년"),
				new JButton("3년") };

		for (JButton btn : periodButtons) {
			btn.addActionListener(e -> {
				int days = switch (btn.getText()) {
				case "7일" -> 7;
				case "30일" -> 30;
				case "6개월" -> 180;
				case "1년" -> 365;
				case "3년" -> 1095;
				default -> 30;
				};
				startDateField.setText(LocalDate.now().minusDays(days).toString());
				endDateField.setText(LocalDate.now().toString());
			});
			topPanel.add(btn);
		}

		JButton searchBtn = new JButton("조회");
		searchBtn.addActionListener(e -> fetchData());
		topPanel.add(searchBtn);

		add(topPanel, BorderLayout.NORTH);

		// === 테이블 ===
		String[] columnNames = { "접수번호", "청구일자", "진단명", "재해자/물 보상구분", "상품명", "보상담당자", "처리상태" };

		model = new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(model);
		table.setRowHeight(35);

		// 가운데 정렬 렌더러
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		// 모든 열에 가운데 정렬 적용
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(i).setResizable(false);
		}

		// 버튼 렌더러 및 에디터 설정 (버튼이 있는 열만)
		table.getColumn("처리상태").setCellRenderer(new ButtonRenderer());
		table.getColumn("처리상태").setCellEditor(new ButtonEditor(new JCheckBox()));

		add(new JScrollPane(table), BorderLayout.CENTER);
		table.revalidate();
		table.repaint();

		add(new JScrollPane(table), BorderLayout.CENTER);

		// 하단 버튼
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
		JButton previousButton = new JButton("이전");

		previousButton.addActionListener((e) -> {
			clearTable();
			cl.show(parentCardPanel, "ClaimFirstPanel");
		});

		buttonPanel.add(previousButton);
		add(buttonPanel, BorderLayout.SOUTH);

	}

	private void fetchData() {
		try (Connection conn = InsuranceTeamConnector.getConnection()) {

			CustomerModel cm = Session.getCustomer();
			if (cm == null) {
				JOptionPane.showMessageDialog(this, "로그인 정보가 없습니다.");
				return;
			}

			String startDateStr = startDateField.getText();
			String endDateStr = endDateField.getText();

			// 문자열 → LocalDate → java.sql.Date
			java.sql.Date startDate = java.sql.Date.valueOf(LocalDate.parse(startDateStr));
			java.sql.Date endDate = java.sql.Date.valueOf(LocalDate.parse(endDateStr));

			System.out.println("[DEBUG] 조회 시작일: " + startDate + ", 종료일: " + endDate);
			List<ClaimsModel> claims = ClaimDAO.getClaims(cm.getLogin_id(), startDate, endDate, conn);

			System.out.println("[DEBUG] 조회된 청구 건수: " + claims.size());
			System.out.println("[DEBUG] 로그인 ID: " + cm.getLogin_id());
			currentClaims = claims;
			model.setRowCount(0);
			

			for (ClaimsModel claim : claims) {
				Vector<Object> row = new Vector<>();
				row.add(claim.getClaim_id());
				row.add(claim.getClaim_date());
				row.add(claim.getDiagnosis_name());
				row.add(claim.getInsured_name());
				row.add(claim.getProduct_name());
				row.add(claim.getEmployee_name());
				row.add(claim.getClaim_status());
//				System.out.println("[DEBUG] 추가할 행 데이터: " + row);
//				System.out.println("[DEBUG] 클레임 ID: " + claim.getClaim_id());
				model.addRow(row);
			}
			model.fireTableDataChanged(); // 테이블 데이터 변경 알림
//			System.out.println("[DEBUG] 테이블 데이터 갱신 완료");

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "DB 오류: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// === 버튼 렌더러 ===
	class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setText("접수");
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {
			return this;
		}
	}

	// === 버튼 에디터 ===
	class ButtonEditor extends DefaultCellEditor {
		protected JButton button;
		private String label;
		private boolean clicked;
		private int currentRow;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);

			button.addActionListener(e -> {
				fireEditingStopped(); // 편집 종료 이벤트 발생

				// 버튼 클릭 시 상세 화면으로 이동하는 코드 실행
				// 여기서 currentRow를 이용해 청구 ID 등 정보 가져올 수 있음
				ClaimsModel selectedClaim = getClaimAtRow(currentRow);
				if (selectedClaim != null) {
					// 상세 패널로 이동하는 예시 코드 (필요에 맞게 구현)
//					openClaimDetailPanel(selectedClaim);
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			currentRow = row; // 클릭한 행 저장
			label = (value == null) ? "" : value.toString();

			if (label.equals("접수")) {
				button.setText("접수");
			} else if (label.equals("종결")) {
				button.setText("종결");
			} else {
				button.setText(label);
			}
			clicked = true;
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			return label;
		}

		// 행 번호로 청구 데이터 가져오는 메서드 (model에서 ID 가져와서 리스트 조회 가능)
		private ClaimsModel getClaimAtRow(int row) {
			try {
				Object claim_idObj = model.getValueAt(row, 0); // 0번째 열이 접수번호(청구ID)
				if (claim_idObj == null) {
					return null;
				}
				
				// claim_idObj를 String로 변환 후 Integer로 변환
				int claim_idInt = Integer.parseInt(claim_idObj.toString());
				

				// 현재 테이블에 보이는 청구목록 중 claim_id와 같은 ClaimsModel 찾아서 리턴
				for (ClaimsModel claim : currentClaims) {
					if (claim.getClaim_id().equals(claim_idInt)) {
						return claim;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}
	

	// 표 초기화 메서드
	private void clearTable() {
		model.setRowCount(0);
	}

}
