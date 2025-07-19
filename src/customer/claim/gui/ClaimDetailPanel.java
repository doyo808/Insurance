package customer.claim.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// 현재는 추가청구 버튼에 연결되어있음 추후 내역조회 -> 조회버튼 클릭시 나타날 수 있도록 연결하기
// 레이아웃 수정하고, 데이터 연결하고
public class ClaimDetailPanel extends JPanel {
	
	private JPanel parentCardPanel;

	public ClaimDetailPanel(JPanel parentCardPanel) {
		this.parentCardPanel = parentCardPanel;
		CardLayout cl = (CardLayout) parentCardPanel.getLayout();
		setLayout(new BorderLayout());
		

		        // 1. 메인 패널 (수직 배치)
		        JPanel mainPanel = new JPanel();
		        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		        mainPanel.setPreferredSize(new Dimension(1440, 1000)); // 스크롤 위해 충분히 큰 사이즈

		        // 2. 제목
		        JLabel titleLabel = new JLabel("보험금 청구 상세내역");
		        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		        mainPanel.add(titleLabel);

		        // 3. 청구 요약 패널
		        JPanel summaryPanel = new JPanel(new GridLayout(2, 3, 10, 10));
		        summaryPanel.setMaximumSize(new Dimension(1400, 100));
		        summaryPanel.setBorder(BorderFactory.createTitledBorder("청구 요약"));

		        for (int i = 0; i < 6; i++) {
		            summaryPanel.add(new JLabel("항목 " + (i + 1) + ": 값"));
		        }
		        mainPanel.add(summaryPanel);

		        // 테이블 추가 헬퍼 메서드
		        mainPanel.add(createTableSection("보험금 청구내역", 5, 3));
		        mainPanel.add(createTableSection("보상 담당자", 3, 2));
		        mainPanel.add(createDualTableSection("처리현황 좌", "처리현황 우", 4, 2));

		        // 5. 버튼 패널
		        JPanel buttonPanel = new JPanel();
		        JButton backButton = new JButton("청구 내역 목록");
		        buttonPanel.add(backButton);
		        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		        mainPanel.add(buttonPanel);

		        // 스크롤 가능하게 설정
		        JScrollPane scrollPane = new JScrollPane(mainPanel);
		        scrollPane.setPreferredSize(new Dimension(1440, 700));
		        add(scrollPane, BorderLayout.CENTER);
		    }

		    // 일반 테이블 섹션 생성
		    private JPanel createTableSection(String title, int rows, int cols) {
		        JPanel panel = new JPanel(new BorderLayout());
		        panel.setBorder(BorderFactory.createTitledBorder(title));
		        String[][] data = new String[rows][cols];
		        String[] columnNames = new String[cols];
		        for (int i = 0; i < cols; i++) columnNames[i] = "컬럼 " + (i + 1);

		        JTable table = new JTable(new DefaultTableModel(data, columnNames));
		        table.setPreferredScrollableViewportSize(new Dimension(1400, 100));
		        table.setFillsViewportHeight(true);

		        panel.add(new JScrollPane(table), BorderLayout.CENTER);
		        panel.setMaximumSize(new Dimension(1440, 150));
		        return panel;
		    }

		    // 좌우로 나뉜 두 개의 테이블 섹션
		    private JPanel createDualTableSection(String leftTitle, String rightTitle, int rows, int cols) {
		        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
		        panel.setBorder(BorderFactory.createTitledBorder("처리 현황"));
		        panel.setMaximumSize(new Dimension(1440, 200));

		        panel.add(createSingleTablePanel(leftTitle, rows, cols));
		        panel.add(createSingleTablePanel(rightTitle, rows, cols));

		        return panel;
		    }

		    private JPanel createSingleTablePanel(String title, int rows, int cols) {
		        JPanel panel = new JPanel(new BorderLayout());
		        panel.setBorder(BorderFactory.createTitledBorder(title));

		        String[][] data = new String[rows][cols];
		        String[] columnNames = new String[cols];
		        for (int i = 0; i < cols; i++) columnNames[i] = "컬럼 " + (i + 1);

		        JTable table = new JTable(new DefaultTableModel(data, columnNames));
		        table.setPreferredScrollableViewportSize(new Dimension(680, 100));
		        table.setFillsViewportHeight(true);

		        panel.add(new JScrollPane(table), BorderLayout.CENTER);
		        return panel;
		    }
		}

