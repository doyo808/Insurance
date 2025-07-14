package customer.payment.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ContractTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	 private static final int ROW_HEIGHT = 30; // 줄 높이 고정
	 private final List<JLabel> selectButtons = new ArrayList<>();
	 
	    public ContractTablePanel(List<String[]> data) {
	    	setBackground(new Color(255, 255, 255));
	        setLayout(new BorderLayout());
	        setBounds(0, 0, 1040, 300);
	        String[] headers = {"No", "보험상품명", "계약번호", "가입일", "만기일", "납입방법", "보험료", "보험수익자"};
	        int rowCount = data.size() + 1;

	        // 표 패널 (고정 높이 줄 사용)
	        JPanel tablePanel = new JPanel(new GridLayout(rowCount, headers.length, 1, 1));
	        tablePanel.setBackground(new Color(255, 255, 255));
	        
	     // 1행: 헤더
	        for (String header : headers) {
	            JLabel headerLabel = createCellLabel(header, true);
	            tablePanel.add(headerLabel);
	        }

	        // 2행~: 데이터 + 선택 버튼
	        for (int row = 0; row < data.size(); row++) {


	            // 나머지 셀
	            String[] rowData = data.get(row);
	            for (String cell : rowData) {
	                JLabel cellLabel = createCellLabel(cell, false);
	                tablePanel.add(cellLabel);
	            }
	            
	            // 선택 버튼 셀
	            JLabel selectLabel = createSelectorLabel();
	            selectButtons.add(selectLabel);
	            tablePanel.add(selectLabel);
	        }

	        // 스크롤 가능하게 감싸기
	        JScrollPane scrollPane = new JScrollPane(tablePanel);
	        scrollPane.setPreferredSize(new Dimension(850, 150));
	        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // 스크롤 속도 개선

	        add(scrollPane, BorderLayout.CENTER);
	    }

	    // 셀용 라벨 생성 메서드
	    private JLabel createCellLabel(String text, boolean isHeader) {
	        JLabel label = new JLabel(text, SwingConstants.CENTER);
	        label.setOpaque(true);
	        label.setPreferredSize(new Dimension(100, ROW_HEIGHT));
	        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

	        if (isHeader) {
	            label.setFont(new Font("SansSerif", Font.BOLD, 12));
	            label.setBackground(new Color(230, 230, 230));
	        } else {
	            label.setBackground(Color.WHITE);
	        }

	        return label;
	    }
	    
	    // 동그라미 선택 버튼 셀 생성
	    private JLabel createSelectorLabel() {
	        JLabel label = new JLabel("○", SwingConstants.CENTER); // 초기 상태
	        label.setFont(new Font("Dialog", Font.BOLD, 16));
	        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	        label.setOpaque(true);
	        label.setBackground(Color.WHITE);
	        label.setPreferredSize(new Dimension(40, ROW_HEIGHT));

	        // 토글 이벤트
	        label.addMouseListener(new MouseAdapter() {
	            private boolean selected = false;

	            @Override
	            public void mouseClicked(MouseEvent e) {
	                selected = !selected;
	                label.setText(selected ? "●" : "○");
	            }
	        });

	        return label;
	    }
}
