package insuranceMain.customerPanel.accounts.signup;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import common.account.signup.customer.LoginIdValidator;
import common.account.signup.customer.PasswordEncryptor;
import common.account.signup.customer.PasswordValidator;
import common.database.dao.CustomerDAO;
import common.database.model.CustomerModel;
import common.method.InsuranceTeamConnector;

public class SignupCard3 extends JPanel {
    private JTextField nameField, personalField, phoneField, zipField, addressField, detailAddressField;
    private JTextField companyNameField, jobZipField, jobAddress1Field, jobAddress2Field, jobCustomField;
    private JTextField[] fields;
    private JTextField loginIdField, emailField;
    private JPasswordField passwordField;
    private JButton idCheckBtn, emailCheckBtn;
    private JRadioButton agreeButton, disagreeButton;
    private ButtonGroup group;
    
    private JLabel idStatusLabel, passwordStatusLabel, emailStatusLabel, passwordHint;
    private boolean emailChk, loginidChk, passwordChk = false;
    private CustomerModel cm;
    private String email, selectedJob;
    
    private static final String[] JOB_OPTIONS = {
    	    "선택하세요", "학생", "무직", "회사원", "공무원", "군인", "자영업", "프리랜서", "의사",
    	    "간호사", "교사", "교수", "예술가", "연구원", "운송업 종사자", "건설업 종사자", "농업/어업 종사자",
    	    "서비스직", "판매직", "기타", "직접입력"
    	};
    private static final String[] EMAIL_DOMAINS = {"gmail.com", "naver.com", "daum.net", "직접입력"};
    private final int FIELD_WIDTH = 180;
    private final int FIELD_HEIGHT = 25;
    private final Double FIELD_GAP = FIELD_HEIGHT * 1.5;
    private final int LABEL_WIDTH = 100;
    private final int H_MARGIN = 10;
    private final int PANEL_WIDTH = 1440;

    public SignupCard3(CardLayout c, JPanel parentPanel, CustomerModel cm) {
    	this.cm = cm;

    	
    	addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentShown(ComponentEvent e) {
    	        updateFields();
    	    }
    	});
    	
        setLayout(null);
        setPreferredSize(new Dimension(1440, 700));

        int startX = (PANEL_WIDTH - FIELD_WIDTH) / 2;
        int y = 40;

        // 이름
        addLabel("이름:", startX, y);
        nameField = addTextField(startX + LABEL_WIDTH + H_MARGIN, y);
        nameField.setName("이름");
        nameField.setText(cm.getCustomer_name());
        nameField.setEnabled(false);
        nameField.setDisabledTextColor(Color.gray);
        y += FIELD_GAP;

        // 주민등록번호
        addLabel("주민등록번호:", startX, y);
        personalField = addTextField(startX + LABEL_WIDTH + H_MARGIN, y);
        personalField.setName("주민등록번호");
        personalField.setText(cm.getPersonal_id());
        personalField.setEnabled(false);
        personalField.setDisabledTextColor(Color.gray);
        y += FIELD_GAP;
        

        // 전화번호
        addLabel("전화번호:", startX, y);
        phoneField = addTextField(startX + LABEL_WIDTH + H_MARGIN, y);
        phoneField.setName("전화번호");
        phoneField.setText(cm.getPhone_number());
        phoneField.setEnabled(false);
        phoneField.setDisabledTextColor(Color.gray);
        y += FIELD_GAP;

        // 우편번호
        addLabel("우편번호:", startX, y);
        zipField = addTextField(startX + LABEL_WIDTH + H_MARGIN, y);
        zipField.setName("우편번호");
        y += FIELD_GAP;

        // 주소
        addLabel("주소:", startX, y);
        addressField = addTextField(startX + LABEL_WIDTH + H_MARGIN, y);
        addressField.setName("주소");
        y += FIELD_GAP;

        // 상세 주소
        addLabel("상세 주소:", startX, y);
        detailAddressField = addTextField(startX + LABEL_WIDTH + H_MARGIN, y);
        detailAddressField.setName("상세 주소");
        y += FIELD_GAP;

        // 직업 (콤보박스)
        addLabel("직업:", startX, y);


        JComboBox<String> jobComboBox = new JComboBox<>(JOB_OPTIONS);
        jobComboBox.setBounds(startX + LABEL_WIDTH + H_MARGIN, y, FIELD_WIDTH, FIELD_HEIGHT);
        add(jobComboBox);
        y += FIELD_GAP;

        // 직접 입력 필드 (초기엔 숨김)
        jobCustomField = new JTextField();
        jobCustomField.setBounds(startX + LABEL_WIDTH + H_MARGIN, y, FIELD_WIDTH, FIELD_HEIGHT);
        jobCustomField.setVisible(false); // 기본은 숨김
        add(jobCustomField);
        y += FIELD_GAP;

        // 콤보박스 이벤트 - "직접입력" 선택 시 텍스트필드 표시
        jobComboBox.addActionListener(e -> {
            selectedJob = (String) jobComboBox.getSelectedItem();
            jobCustomField.setVisible("직접입력".equals(selectedJob));
            if ("무직".equals(selectedJob) || "학생".equals(selectedJob)) {
            	setJobFieldAbled(false);
            } else {
            	setJobFieldAbled(true);
            }
        });

        // 회사명
        addLabel("회사명:", startX, y);
        companyNameField = addTextField(startX + LABEL_WIDTH + H_MARGIN, y);
        companyNameField.setName("회사명");
        y += FIELD_GAP;

        // 회사 우편번호
        addLabel("회사 우편번호:", startX, y);
        jobZipField = addTextField(startX + LABEL_WIDTH + H_MARGIN, y);
        jobZipField.setName("회사 우편번호");
        y += FIELD_GAP;
        
        // 회사 주소
        addLabel("회사 주소:", startX, y);
        jobAddress1Field = addTextField(startX + LABEL_WIDTH + H_MARGIN, y);
        jobAddress1Field.setName("회사 주소");
        y += FIELD_GAP;

        // 회사 상세주소
        addLabel("회사 상세주소:", startX, y);
        jobAddress2Field = addTextField(startX + LABEL_WIDTH + H_MARGIN, y);
        jobAddress2Field.setName("회사 상세주소");
        y += FIELD_GAP;
        
        // 로그인 아이디
        addLabel("로그인 아이디:", startX, y);
        loginIdField = addTextField(startX + LABEL_WIDTH + H_MARGIN, y);
        loginIdField.setName("로그인 아이디");
        idCheckBtn = new JButton("중복확인");
        idCheckBtn.setBounds(startX + LABEL_WIDTH + H_MARGIN + FIELD_WIDTH + 10, y, 100, FIELD_HEIGHT);
        add(idCheckBtn);
        idStatusLabel = new JLabel("❌");
        idStatusLabel.setForeground(Color.RED);
        idStatusLabel.setBounds(startX + LABEL_WIDTH + H_MARGIN + FIELD_WIDTH + 120, y, 30, FIELD_HEIGHT);
        add(idStatusLabel);
        y += FIELD_GAP;
        loginIdField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				loginidChk = false;
				setIdCheck(loginidChk);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				loginidChk = false;
				setIdCheck(loginidChk);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				loginidChk = false;
				setIdCheck(loginidChk);
			}
		});

        idCheckBtn.addActionListener(e -> {
            String loginId = loginIdField.getText();
            if (loginId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
                setIdCheck(false);
            } else {
                int result = LoginIdValidator.getVerifiedLoginId(loginId);
                if (result == 0) {
                    JOptionPane.showMessageDialog(this, "이미 사용 중인 아이디입니다.");
                    setIdCheck(false);
                } else if (result == 1) {
                    JOptionPane.showMessageDialog(this, "사용 가능한 아이디입니다.");
                    setIdCheck(true);
                    loginidChk = true;
                } else {
                    JOptionPane.showMessageDialog(this, "아이디 형식이 잘못됐습니다.");
                    setIdCheck(false);
                }
            }
        });

        // 비밀번호
        addLabel("비밀번호:", startX, y);
        passwordField = new JPasswordField();
        passwordField.setBounds(startX + LABEL_WIDTH + H_MARGIN, y, FIELD_WIDTH, FIELD_HEIGHT);
        passwordField.setName("비밀번호");
        add(passwordField);

        passwordStatusLabel = new JLabel("❌");
        passwordStatusLabel.setForeground(Color.RED);
        passwordStatusLabel.setBounds(startX + LABEL_WIDTH + H_MARGIN + FIELD_WIDTH + 10, y, 30, FIELD_HEIGHT);
        add(passwordStatusLabel);
        y += FIELD_GAP;

        passwordHint = new JLabel(String.format("<html><font size='2' color='gray'>8~20자, 숫자 및 특수문자 포함(%s)</font></html>", PasswordValidator.specialChars));
        passwordHint.setBounds(startX + LABEL_WIDTH + H_MARGIN, y, 400, 20);
        passwordHint.setFont(new Font("굴림", Font.PLAIN, 13));
        passwordHint.setForeground(Color.RED);
        add(passwordHint);
        y += FIELD_GAP;

        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validatePasswordLive(); }
            public void removeUpdate(DocumentEvent e) { validatePasswordLive(); }
            public void changedUpdate(DocumentEvent e) { validatePasswordLive(); }
        });
        
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == ' ') {
                    e.consume();  // 스페이스 입력 무시
                    Toolkit.getDefaultToolkit().beep();  // 경고음 (선택사항)
                }
            }
        });
        
     // 이메일
        addLabel("이메일:", startX, y);
        JTextField emailFrontField = new JTextField();
        emailFrontField.setBounds(startX + LABEL_WIDTH + H_MARGIN, y, 100, FIELD_HEIGHT);
        add(emailFrontField);

        JLabel atLabel = new JLabel("@");
        atLabel.setBounds(startX + LABEL_WIDTH + H_MARGIN + 105, y, 20, FIELD_HEIGHT);
        add(atLabel);

        // 콤보박스 (도메인 선택)
        JComboBox<String> domainCombo = new JComboBox<>(EMAIL_DOMAINS);
        domainCombo.setBounds(startX + LABEL_WIDTH + H_MARGIN + 120, y, 120, FIELD_HEIGHT);
        domainCombo.setSelectedIndex(0);
        add(domainCombo);

        // 직접입력용 텍스트필드 (초기에는 비활성화)
        JTextField domainField = new JTextField();
        domainField.setBounds(startX + LABEL_WIDTH + H_MARGIN + 250, y, 120, FIELD_HEIGHT);
        domainField.setEnabled(false);
        domainField.setVisible(false);
        add(domainField);

        y += FIELD_GAP;  // emailFrontField 아래로 이동

        // 이메일 확인 버튼 (emailFrontField 바로 아래에 배치)
        emailCheckBtn = new JButton("이메일 확인");
        emailCheckBtn.setBounds(startX + LABEL_WIDTH + H_MARGIN, y, 100, FIELD_HEIGHT);
        add(emailCheckBtn);

        // 이메일 상태 라벨은 이메일 확인 버튼 오른쪽에 배치
        emailStatusLabel = new JLabel("❌");
        emailStatusLabel.setForeground(Color.RED);
        emailStatusLabel.setBounds(startX + LABEL_WIDTH + H_MARGIN + 110, y, 30, FIELD_HEIGHT);
        add(emailStatusLabel);

        y += FIELD_GAP;  // 다음 필드를 위한 y 증가

        // 콤보박스 선택 이벤트 (도메인 직접입력 활성화/비활성화)
        domainCombo.addActionListener(e -> {
            String selected = (String) domainCombo.getSelectedItem();
            if ("직접입력".equals(selected)) {
                domainField.setEnabled(true);
                domainField.setVisible(true);
            } else {
                domainField.setEnabled(false);
                domainField.setVisible(false);
            }
        });

        // 이메일 확인 버튼 클릭 이벤트
        emailCheckBtn.addActionListener(e -> {
            String front = emailFrontField.getText().trim();
            String domain = domainField.isVisible() ? domainField.getText().trim() : (String) domainCombo.getSelectedItem();
            email = front + "@" + domain;

            if (!front.isEmpty() && domain.contains(".") && email.contains("@")) {
                if (CustomerDAO.getCustomerByEmail(email) == null) {
                    JOptionPane.showMessageDialog(this, "유효한 이메일입니다.");
                    setEmailCheck(true);
                } else {
                    JOptionPane.showMessageDialog(this, "중복된 이메일이 있습니다.");
                    setEmailCheck(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "이메일 형식이 잘못되었습니다.");
                setEmailCheck(false);
            }
        });
        
        // 이메일 수신동의
        addLabel("이메일 수신동의여부:", startX, y); // 라벨 추가
        agreeButton = new JRadioButton("동의함");
        disagreeButton = new JRadioButton("동의하지 않음");

        agreeButton.setBounds(startX + LABEL_WIDTH + H_MARGIN, y, 100, FIELD_HEIGHT);
        disagreeButton.setBounds(startX + LABEL_WIDTH + H_MARGIN + 110, y, 130, FIELD_HEIGHT);

        group = new ButtonGroup();
        group.add(agreeButton);
        group.add(disagreeButton);

        add(agreeButton);
        add(disagreeButton);
        y += FIELD_GAP;
    	
    	//이전, 다음버튼
        
    	JButton previousBtn = new JButton("이전");
    	previousBtn.setBounds(720, 720, 97, 23);
    	add(previousBtn);
    	previousBtn.addActionListener(e -> {
    		c.previous(parentPanel);
    	});
    	
    	JButton nextBtn = new JButton("다음");
    	nextBtn.setBounds(829, 720, 97, 23);
    	nextBtn.addActionListener(e -> {
    		if (fieldCheck()) {
    			updateCustomerModel();
    			c.next(parentPanel);
    		}
    	});
    	add(nextBtn);
    }

    // 공통 라벨 추가
    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setBounds(x, y, LABEL_WIDTH, FIELD_HEIGHT);
        add(label);
    }

    // 공통 텍스트필드 추가
    private JTextField addTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, FIELD_WIDTH, FIELD_HEIGHT);
        add(tf);
        return tf;
    }

    private void setIdCheck(boolean valid) {
        idStatusLabel.setText(valid ? "✔" : "❌");
        idStatusLabel.setForeground(valid ? new Color(0, 128, 0) : Color.RED);
        loginidChk = valid;
    }

    private void setPasswordCheck(boolean valid) {
        passwordStatusLabel.setText(valid ? "✔" : "❌");
        passwordStatusLabel.setForeground(valid ? new Color(0, 128, 0) : Color.RED);
        passwordChk = valid;
    }

    private void setEmailCheck(boolean valid) {
        emailStatusLabel.setText(valid ? "✔" : "❌");
        emailStatusLabel.setForeground(valid ? new Color(0, 128, 0) : Color.RED);
        emailChk = valid;
    }

    private void validatePasswordLive() {
    	
    	passwordHint.setText(PasswordValidator.validatePassword(this.loginIdField.getText(), new String(passwordField.getPassword())));
    	if (passwordHint.getText().equals("사용가능한 비밀번호 입니다.")) {
    		setPasswordCheck(true);
    	} else {
    		setPasswordCheck(false);
    	}
    }
    
    private boolean fieldCheck() {
    	fields = new JTextField[] {nameField, personalField, phoneField, zipField, addressField, detailAddressField};
    	for (JTextField field : fields) {
    		
    		if (field.getText().trim().isEmpty()) {
    			JOptionPane.showMessageDialog(this, field.getName() + "(을)를 확인해주세요");
    			return false;
    		}
    	}
    	
    	
    	if (group.getSelection() == null) {
    		JOptionPane.showMessageDialog(this, "이메일 수신동의 여부" + "를 확인해주세요");
    		return false;
    	}
    	
    	if (!loginidChk) {
    		JOptionPane.showMessageDialog(this, loginIdField.getName() + "(을)를 확인해주세요");
    		return false;
    	}
    	if (!passwordChk) {
    		JOptionPane.showMessageDialog(this, passwordField.getName() + "(을)를 확인해주세요");
    		return false;
    	}
    	if (!emailChk) {
    		JOptionPane.showMessageDialog(this, "이메일" + "(을)를 확인해주세요");
    		return false;
    	}
    	
    	return true;	
    }
    
    private void updateFields() {
        if (cm != null) {
            nameField.setText(cm.getCustomer_name());
            personalField.setText(cm.getPersonal_id());
            phoneField.setText(cm.getPhone_number());
            // 필요한 다른 필드도 갱신
        }
    }
    
    private void updateCustomerModel() {
    	cm.setZip_code(zipField.getText());
    	cm.setAddress_1(addressField.getText());
    	cm.setAddress_2(detailAddressField.getText());
    	cm.setEmail(email);
    	if (selectedJob.equals("직접입력")) { 
    		selectedJob = jobCustomField.getText();
    	}
    	cm.setJob(selectedJob);
    	cm.setJob_zip_code(jobZipField.getText());
    	cm.setJob_address1(jobAddress1Field.getText());
    	cm.setJob_address2(jobAddress2Field.getText());
    	cm.setAgree_yn(agreeButton.isSelected() ? "Y" : "N");
    	cm.setLogin_id(loginIdField.getText());
    	byte[] salt = PasswordEncryptor.generateSalt();
    	cm.setPassword_salt(Base64.getEncoder().encodeToString(salt));
    	try {
			String hash = PasswordEncryptor.hashPassword(new String(passwordField.getPassword()), salt);
			cm.setPassword_hash(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	try (Connection conn = InsuranceTeamConnector.getConnection()) {
    		CustomerDAO.addCustomer(cm, conn);
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void setJobFieldAbled(boolean valid) {
    	companyNameField.setEnabled(valid);
    	companyNameField.setText("");
    	jobZipField.setEnabled(valid);
    	jobZipField.setText("");
    	jobAddress1Field.setEnabled(valid);
    	jobAddress1Field.setText("");
    	jobAddress2Field.setEnabled(valid);
    	jobAddress2Field.setText("");
    	
    }
}