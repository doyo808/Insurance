package customer.claim.gui;

import java.awt.*;
import javax.swing.*;

// 빼는게 나을 것 같다...
public class ClaimCategoryPanel extends JPanel {
    private JPanel parentCardPanel;

    public ClaimCategoryPanel(JPanel parentCardPanel) {
        this.parentCardPanel = parentCardPanel;
        CardLayout cl = (CardLayout) parentCardPanel.getLayout();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1440, 1024));

        // ===== 상단 영역 (청구유형 선택 라벨) =====
        JLabel titleLabel = new JLabel("청구유형 선택");
        titleLabel.setFont(new Font("굴림", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // ===== 중앙 영역 (라디오 버튼 + 설명들) =====
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(70, 450, 20, 40)); // 패딩

        ButtonGroup group = new ButtonGroup();

        centerPanel.add(createOption("질병", 
            "신체 내부의 요인으로 몸이 불편하여 의사의 진단을 요하는 치료를 받는 경우\n"
            + "[예시] 감기, 장염, 당뇨, 고혈압, 추간판탈출증, 암 등", group));

        centerPanel.add(Box.createVerticalStrut(20));

        centerPanel.add(createOption("일반상해", 
            "급격하고 우연한 외래의 사고로 신체에 부상을 입고 치료를 받는 경우\n"
            + "[예시] 골절, 화상, 염좌, 절단, 상처 등", group));

        centerPanel.add(Box.createVerticalStrut(20));

        centerPanel.add(createOption("교통사고", 
            "운전 중 교통사고로 인한 손해를 입은 경우\n"
            + "[예시] 경찰신고, 기소, 벌금, 형사합의, 면허정지 등", group));

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // ===== 하단 영역 (이전 / 다음 버튼) =====
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        JButton 이전버튼 = new JButton("이전");
        JButton 다음버튼 = new JButton("다음");
        bottomPanel.add(이전버튼);
        bottomPanel.add(다음버튼);
        add(bottomPanel, BorderLayout.SOUTH);

        // 버튼 이벤트
        이전버튼.addActionListener(e -> {
            cl.show(parentCardPanel, "AccidentDatePanel");
            group.clearSelection();
        });

        다음버튼.addActionListener(e -> {
            if (group.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "청구유형을 선택해주세요.", "안내", JOptionPane.INFORMATION_MESSAGE);
            } else {
                cl.show(parentCardPanel, "ClaimSituationPanel");
            }
        });
    }

    private JPanel createOption(String title, String description, ButtonGroup group) {
        JPanel panel = new JPanel(new BorderLayout());
        JRadioButton radioButton = new JRadioButton(title);
        radioButton.setFont(new Font("굴림", Font.BOLD, 18));
        group.add(radioButton);
        panel.add(radioButton, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(description);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        panel.add(textArea, BorderLayout.CENTER);

        return panel;
    }
}
