package insurancePanelPractice.Contract;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BeneficiaryInfoPanel extends JPanel {
    private JTextField nameField;
    private JTextField personalIdField;
    private JTextField relationshipField;
    private JTextField phoneNumberField;
    private JTextField bankAccountField;
    private JTextField bankNameField;

    public BeneficiaryInfoPanel() {
        setLayout(new GridBagLayout()); // 유연한 레이아웃
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // 가로로 꽉 채움
        gbc.insets = new Insets(5, 10, 5, 10); // 컴포넌트 사이 여백
        gbc.gridx = 0; // 첫 번째 열
        gbc.gridy = 0; // 첫 번째 행

        // 이름
        add(new JLabel("이름:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        // 주민번호
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("주민번호:"), gbc);
        gbc.gridx = 1;
        personalIdField = new JTextField(20);
        add(personalIdField, gbc);

        // 고객과의 관계
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("고객과의 관계:"), gbc);
        gbc.gridx = 1;
        relationshipField = new JTextField(20);
        add(relationshipField, gbc);

        // 전화번호
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("전화번호:"), gbc);
        gbc.gridx = 1;
        phoneNumberField = new JTextField(20);
        add(phoneNumberField, gbc);

        // 계좌번호
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("계좌번호:"), gbc);
        gbc.gridx = 1;
        bankAccountField = new JTextField(20);
        add(bankAccountField, gbc);

        // 은행명
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("은행명:"), gbc);
        gbc.gridx = 1;
        bankNameField = new JTextField(20);
        add(bankNameField, gbc);

        // 하단 버튼
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // 버튼을 두 칸에 걸쳐서 배치
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        JButton prevButton = new JButton("이전");
        JButton confirmButton = new JButton("확인");
        buttonPanel.add(prevButton);
        buttonPanel.add(confirmButton);
        add(buttonPanel, gbc);
    }

    // 필요하면 입력값 가져오는 Getter 메서드 추가 가능
}