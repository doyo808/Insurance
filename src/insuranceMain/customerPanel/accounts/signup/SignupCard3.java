package insuranceMain.customerPanel.accounts.signup;

import javax.swing.*;

import common.database.model.CustomerModel;

import java.awt.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

public class SignupCard3 extends JPanel {

    // JTextField
	private JTextField nameField, personalField, phoneField, emailIdField, emailDomainField,
            zipField, addr1Field, addr2Field, jobField, companyField, jobZipField, jobAddr1Field,
            jobAddr2Field, jobPhoneField, loginIdField;
    private CustomerModel cm;
    // JPasswordField
    private JPasswordField passwordField;

    // JRadioButton
    private JRadioButton agreeYesBtn, agreeNoBtn;

    // JButton
    private JButton emailCheckBtn;

    // JComboBox for email domains
    private JComboBox<String> emailDomainCombo;

    public SignupCard3(CardLayout c, JPanel parentPanel, CustomerModel cm) {
        setLayout(new MigLayout("insets 0, fillx", "[right][]", "[]15[]15[]15[]15[]15[]15[]15[]15[]15[]15[]15[]"));
        setPreferredSize(new Dimension(1440, 700));
        setBackground(Color.WHITE);

        // 이름 (비활성)
        add(new JLabel("이름:"));
        nameField = new JTextField(cm.getCustomer_name());
        nameField.setEnabled(false);
        nameField.setPreferredSize(new Dimension(250, 25));
        add(nameField, "wrap");

        // 주민번호 (비활성)
        add(new JLabel("주민번호:"));
        personalField = new JTextField(cm.getPersonal_id());
        personalField.setEnabled(false);
        personalField.setPreferredSize(new Dimension(250, 25));
        add(personalField, "wrap");

        // 연락처 (비활성)
        add(new JLabel("연락처:"));
        phoneField = new JTextField(cm.getPhone_number());
        phoneField.setEnabled(false);
        phoneField.setPreferredSize(new Dimension(250, 25));
        add(phoneField, "wrap");

        // 이메일 (앞부분 + 도메인콤보 + 직접입력 필드)
        add(new JLabel("이메일:"));

        JPanel emailPanel = new JPanel(new MigLayout("insets 0, gap 5 0", "[][pref!][50!]", "[]"));
        emailIdField = new JTextField();
        emailIdField.setPreferredSize(new Dimension(150, 25));
        emailPanel.add(emailIdField);

        emailPanel.add(new JLabel("@"));

        String[] domains = {"naver.com", "gmail.com", "daum.net", "직접입력"};
        emailDomainCombo = new JComboBox<>(domains);
        emailDomainCombo.setPreferredSize(new Dimension(120, 25));
        emailPanel.add(emailDomainCombo);

        emailDomainField = new JTextField();
        emailDomainField.setPreferredSize(new Dimension(150, 25));
        emailDomainField.setVisible(false);
        emailPanel.add(emailDomainField, "wrap");

        add(emailPanel, "wrap");

        // 이메일 확인 버튼
        add(new JLabel(""));
        emailCheckBtn = new JButton("이메일 확인");
        add(emailCheckBtn, "wrap");

        // 자택 우편번호
        add(new JLabel("자택 우편번호:"));
        zipField = new JTextField();
        zipField.setPreferredSize(new Dimension(250, 25));
        add(zipField, "wrap");

        // 자택 주소1
        add(new JLabel("자택 주소1:"));
        addr1Field = new JTextField();
        addr1Field.setPreferredSize(new Dimension(250, 25));
        add(addr1Field, "wrap");

        // 자택 주소2
        add(new JLabel("자택 주소2:"));
        addr2Field = new JTextField();
        addr2Field.setPreferredSize(new Dimension(250, 25));
        add(addr2Field, "wrap");

        // 직업
        add(new JLabel("직업:"));
        jobField = new JTextField();
        jobField.setPreferredSize(new Dimension(250, 25));
        add(jobField, "wrap");

        // 회사명
        add(new JLabel("회사명:"));
        companyField = new JTextField();
        companyField.setPreferredSize(new Dimension(250, 25));
        add(companyField, "wrap");

        // 직장 우편번호
        add(new JLabel("직장 우편번호:"));
        jobZipField = new JTextField();
        jobZipField.setPreferredSize(new Dimension(250, 25));
        add(jobZipField, "wrap");

        // 직장 주소1
        add(new JLabel("직장 주소1:"));
        jobAddr1Field = new JTextField();
        jobAddr1Field.setPreferredSize(new Dimension(250, 25));
        add(jobAddr1Field, "wrap");

        // 직장 주소2
        add(new JLabel("직장 주소2:"));
        jobAddr2Field = new JTextField();
        jobAddr2Field.setPreferredSize(new Dimension(250, 25));
        add(jobAddr2Field, "wrap");

        // 직장 연락처
        add(new JLabel("직장 연락처:"));
        jobPhoneField = new JTextField();
        jobPhoneField.setPreferredSize(new Dimension(250, 25));
        add(jobPhoneField, "wrap");

        // 로그인 아이디
        add(new JLabel("로그인 아이디:"));
        loginIdField = new JTextField();
        loginIdField.setPreferredSize(new Dimension(250, 25));
        add(loginIdField, "wrap");

        // 비밀번호
        add(new JLabel("비밀번호:"));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 25));
        add(passwordField, "wrap");

        // 비밀번호 규칙 라벨 (회색)
        add(new JLabel("")); // 빈 공간
        JLabel pwRuleLabel = new JLabel("영문, 숫자, 특수문자 포함 8자 이상");
        pwRuleLabel.setForeground(Color.GRAY);
        add(pwRuleLabel, "wrap");

        // 메시지 알림 수신 동의 여부 (라디오 버튼)
        add(new JLabel("메시지 알림 수신 여부:"));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        agreeYesBtn = new JRadioButton("수신함");
        agreeNoBtn = new JRadioButton("수신안함");
        ButtonGroup group = new ButtonGroup();
        group.add(agreeYesBtn);
        group.add(agreeNoBtn);
        radioPanel.add(agreeYesBtn);
        radioPanel.add(agreeNoBtn);
        add(radioPanel, "wrap");

        // 콤보박스 선택에 따른 도메인 입력 필드 노출 제어
        emailDomainCombo.addActionListener(e -> {
            if ("직접입력".equals(emailDomainCombo.getSelectedItem())) {
                emailDomainField.setVisible(true);
                emailDomainField.requestFocus();
            } else {
                emailDomainField.setVisible(false);
            }
            revalidate();
            repaint();
        });
    }
}