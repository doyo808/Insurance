package insuranceMain.customerPanel.accounts.signup;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import common.account.signup.customer.LoginIdValidator;
import common.database.model.CustomerModel;
import net.miginfocom.swing.MigLayout;

public class SignupCard3 extends JPanel {

    private JTextField loginIdField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField nameField, personalField, phoneField, zipField, addressField, detailAddressField, emailIdField, emailDomainField;
    private JButton idCheckBtn;
    private JButton emailCheckBtn;

    private JLabel idStatusLabel;
    private JLabel passwordStatusLabel;
    private JLabel emailStatusLabel;

    private boolean idChk = false;
    private boolean passwordChk = false;
    private boolean eamilChk = false;

    public SignupCard3(CardLayout c, JPanel parentPanel, CustomerModel cm) {
        setLayout(new MigLayout("wrap 2", "[][grow]", ""));

        // 이름
        add(new JLabel("이름:"));
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(180, 25));
        add(nameField);

        // 주민등록번호
        add(new JLabel("주민등록번호:"));
        personalField = new JTextField();
        personalField.setPreferredSize(new Dimension(180, 25));
        add(personalField);

        // 전화번호
        add(new JLabel("전화번호:"));
        phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(180, 25));
        add(phoneField);

        // 우편번호
        add(new JLabel("우편번호:"));
        zipField = new JTextField();
        zipField.setPreferredSize(new Dimension(180, 25));
        add(zipField);

        // 주소
        add(new JLabel("주소:"));
        addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(180, 25));
        add(addressField);

        // 상세 주소
        add(new JLabel("상세 주소:"));
        detailAddressField = new JTextField();
        detailAddressField.setPreferredSize(new Dimension(180, 25));
        add(detailAddressField);

        // 로그인 아이디
        add(new JLabel("로그인 아이디:"));
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        loginIdField = new JTextField();
        loginIdField.setPreferredSize(new Dimension(180, 25));
        idStatusLabel = new JLabel("❌");
        idStatusLabel.setForeground(Color.RED);
        idCheckBtn = new JButton("아이디 중복확인");

        idPanel.add(loginIdField);
        idPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        idPanel.add(idCheckBtn);
        idPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        idPanel.add(idStatusLabel);
        add(idPanel, "wrap");

        // 아이디 중복 확인 버튼 이벤트
        idCheckBtn.addActionListener(e -> {
            String loginId = loginIdField.getText();
            if (loginId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
                setIdCheck(false);
            } else {
                int idChkNum = LoginIdValidator.getVerifiedLoginId(loginId);
                if (idChkNum == 0) {
                    JOptionPane.showMessageDialog(this, "이미 사용 중인 아이디입니다.");
                    setIdCheck(false);
                } else if (idChkNum == 1) {
                    JOptionPane.showMessageDialog(this, "사용 가능한 아이디입니다.");
                    setIdCheck(true);
                } else {
                    JOptionPane.showMessageDialog(this, "아이디형식이 잘못됐습니다.");
                    setIdCheck(false);
                }
            }
        });

        // 비밀번호
        add(new JLabel("비밀번호:"));
        JPanel pwPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(180, 25));
        passwordStatusLabel = new JLabel("❌");
        JLabel passwordHintLabel = new JLabel("비밀번호는 8~20자, 특수문자(!@#$%^&*-_=+;:,.?~`) 및 숫자 포함");
        passwordHintLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
        passwordHintLabel.setForeground(Color.GRAY);
        passwordStatusLabel.setForeground(Color.RED);
        pwPanel.add(passwordField);
        pwPanel.add(passwordStatusLabel);
        add(pwPanel, "wrap");
        add(passwordHintLabel, "span 2, wrap");

        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validatePasswordLive(); }
            public void removeUpdate(DocumentEvent e) { validatePasswordLive(); }
            public void changedUpdate(DocumentEvent e) { validatePasswordLive(); }
        });

        // 이메일
        add(new JLabel("이메일:"));
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(180, 25));
        emailStatusLabel = new JLabel("❌");
        emailStatusLabel.setForeground(Color.RED);
        emailCheckBtn = new JButton("이메일 확인");

        emailPanel.add(emailField);
        emailPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        emailPanel.add(emailCheckBtn);
        emailPanel.add(emailStatusLabel);
        add(emailPanel, "wrap");

        emailCheckBtn.addActionListener(e -> {
            String email = emailField.getText();
            if (email.contains("@") && email.contains(".")) {
                JOptionPane.showMessageDialog(this, "유효한 이메일 형식입니다.");
                setEmailCheck(true);
            } else {
                JOptionPane.showMessageDialog(this, "이메일 형식이 잘못되었습니다.");
                setEmailCheck(false);
            }
        });
    }

    private void setIdCheck(boolean valid) {
        idChk = valid;
        idStatusLabel.setText(valid ? "✔" : "❌");
        idStatusLabel.setForeground(valid ? new Color(0, 128, 0) : Color.RED);
    }

    private void setPasswordCheck(boolean valid) {
        passwordChk = valid;
        passwordStatusLabel.setText(valid ? "✔" : "❌");
        passwordStatusLabel.setForeground(valid ? new Color(0, 128, 0) : Color.RED);
    }

    private void setEmailCheck(boolean valid) {
        eamilChk = valid;
        emailStatusLabel.setText(valid ? "✔" : "❌");
        emailStatusLabel.setForeground(valid ? new Color(0, 128, 0) : Color.RED);
    }

    private void validatePasswordLive() {
        String pw = new String(passwordField.getPassword());
        boolean valid = pw.length() >= 8 && pw.length() <= 20 && pw.matches(".*[0-9].*") && pw.matches(".*[!@#$%^&*\\-_=+;:,.?~`].*");
        setPasswordCheck(valid);
    }
}
