package customer.payment.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class ContractTablePanel extends JPanel {

    private static final long serialVersionUID = -2071017780652711595L;
    private static final int ROW_HEIGHT = 20;

    private final List<JRadioButton> radioButtons = new ArrayList<>();
    private final List<String[]> contractData;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private int selectedIndex = -1; // 선택된 행 인덱스 저장

    public ContractTablePanel(List<String[]> data) {
        this.contractData = data;
        setLayout(new BorderLayout());

        String[] headers = {"No", "보험상품명", "계약번호", "가입일", "만기일", "납입방법", "보험료", "선택"};
        int columnCount = headers.length;
        int rowCount = data.size() + 1; // +1 for header row

        JLabel titleLabel = new JLabel("계약정보");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new GridLayout(rowCount, columnCount, 1, 1));

        // 헤더 행 생성
        for (String header : headers) {
            JLabel headerLabel = createCellLabel(header, true);
            tablePanel.add(headerLabel);
        }

        // 데이터 행 생성
        for (int row = 0; row < data.size(); row++) {
            String[] rowData = data.get(row);

            // 일반 데이터 셀
            for (String cell : rowData) {
                JLabel cellLabel = createCellLabel(cell, false);
                tablePanel.add(cellLabel);
            }

            // 선택용 라디오 버튼 셀
            JRadioButton radioButton = new JRadioButton();
            radioButton.setHorizontalAlignment(SwingConstants.CENTER);
            radioButton.setOpaque(true);
            radioButton.setBackground(Color.WHITE);
            radioButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            radioButton.setPreferredSize(new Dimension(40, ROW_HEIGHT));

            final int index = row;
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedIndex = index;
                }
            });

            buttonGroup.add(radioButton);
            radioButtons.add(radioButton);
            tablePanel.add(radioButton);
        }

        JPanel containerForTable = new JPanel(new BorderLayout());
        containerForTable.add(tablePanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(containerForTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(850, 200));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }


    /**
     * 현재 선택된 계약 정보를 반환합니다.
     * 선택되지 않았으면 null을 반환합니다
     * @return String[] data
     */
    public String[] getSelectedContract() {
        if (selectedIndex >= 0 && selectedIndex < contractData.size()) {
            return contractData.get(selectedIndex);
        }
        return null;
    }

    // 셀 라벨 생성 함수
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
}