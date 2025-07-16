package insurancePanelPractice.Contract;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InsuredInfoPanel extends JPanel {
    private JTextField nameField;
    private JTextField personalIdField;
    private JComboBox<String> genderCombo;
    private JTextField birthDateField;
    private JTextField ageField;
    private JComboBox<String> isSmokerCombo;
    private JComboBox<String> drinksCombo;
    private JComboBox<String> drivingStatusCombo;
    private JTextField medicalHistoryField;

    public InsuredInfoPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

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

        // 성별
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("성별:"), gbc);
        gbc.gridx = 1;
        genderCombo = new JComboBox<>(new String[]{"M", "F"});
        add(genderCombo, gbc);

        // 생년월일
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("생년월일(yyyy-mm-dd):"), gbc);
        gbc.gridx = 1;
        birthDateField = new JTextField(20);
        add(birthDateField, gbc);

        // 나이
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("나이:"), gbc);
        gbc.gridx = 1;
        ageField = new JTextField(5);
        add(ageField, gbc);

        // 흡연여부
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("흡연여부:"), gbc);
        gbc.gridx = 1;
        isSmokerCombo = new JComboBox<>(new String[]{"Y", "N"});
        add(isSmokerCombo, gbc);

        // 음주여부
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("음주여부:"), gbc);
        gbc.gridx = 1;
        drinksCombo = new JComboBox<>(new String[]{"Y", "N"});
        add(drinksCombo, gbc);

        // 운전상태
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("운전상태:"), gbc);
        gbc.gridx = 1;
        drivingStatusCombo = new JComboBox<>(new String[]{"0: 안함", "1: 소형차", "2: 이륜차", "3: 화물차"});
        add(drivingStatusCombo, gbc);

        // 병력 (첨부파일 경로)
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("병력 (첨부파일 경로):"), gbc);
        gbc.gridx = 1;
        medicalHistoryField = new JTextField(20);
        add(medicalHistoryField, gbc);

        // 하단 버튼
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        JButton prevButton = new JButton("이전");
        JButton nextButton = new JButton("확인");
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, gbc);
    }

    // 필요 시 getter 메서드를 추가하여 데이터 가져오기 가능

}
