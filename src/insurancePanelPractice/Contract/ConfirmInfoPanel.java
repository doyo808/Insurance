package insurancePanelPractice.Contract;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ConfirmInfoPanel extends JPanel {
    public ConfirmInfoPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // 타이틀
        JLabel titleLabel = new JLabel("선택하신 정보를 확인해주세요.");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(titleLabel, gbc);

        // 피보험자 정보
        gbc.gridy++;
        JLabel insuredLabel = new JLabel("피보험자: 25세 남성");
        insuredLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(insuredLabel, gbc);

        // 상품명
        gbc.gridy++;
        JTextArea productArea = new JTextArea("평생보장 비갱신 손해보험");
        productArea.setEditable(false);
        productArea.setBackground(Color.LIGHT_GRAY);
        add(productArea, gbc);

        // 보장항목
        gbc.gridy++;
        JTextArea coverageArea = new JTextArea("보장항목");
        coverageArea.setEditable(false);
        coverageArea.setBackground(Color.LIGHT_GRAY);
        add(coverageArea, gbc);

        // 하단 버튼
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        JButton prevButton = new JButton("이전");
        JButton confirmButton = new JButton("확인");
        buttonPanel.add(prevButton);
        buttonPanel.add(confirmButton);
        add(buttonPanel, gbc);
    }
}
