package insuranceMain.customerPanel.accounts;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.account.login.LoginService;
import common.account.login.Session;
import common.database.model.CustomerModel;
import common.gui.FooterImagePanel;
import common.gui.HomeButton;
import insuranceMain.MainFrame;
import insuranceMain.customerPanel.CustomerMainPanel;
import insuranceMain.customerPanel.services.ServicesMainPanel;

public class LogInCard extends JPanel {
	private JTextField textField;
	private JLabel errorLabel;
	
	public LogInCard(AccountsMainPanel parentPanel, ServicesMainPanel smp, CustomerMainPanel cmp) {
		addComponents(parentPanel, smp);
//		if (MainFrame.TEST) add(new HomeButton(cmp, 700, 10));
	}
	
	void addComponents(AccountsMainPanel parentPanel, ServicesMainPanel smp) {
	    setLayout(null);
	    setBackground(new Color(245, 245, 245));

	    JLabel titleLabel = new JLabel("고객 로그인", SwingConstants.CENTER);
	    titleLabel.setBounds(646, 132, 400, 60);
	    titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));

	    errorLabel = new JLabel("아이디 또는 비밀번호를 확인하세요.");
	    errorLabel.setBounds(128, 126, 228, 20);
	    errorLabel.setForeground(Color.RED);
	    errorLabel.setVisible(false);
	    
	    
	    // 로그인 폼 박스
	    JPanel formPanel = new JPanel(null);
	    formPanel.setBounds(619, 217, 440, 270);
	    formPanel.setBackground(Color.WHITE);
	    formPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	    textField = new JTextField("아이디");
	    textField.setBounds(78, 31, 300, 35);
	    textField.setFont(new Font("Dialog", Font.PLAIN, 16));
	    textField.addFocusListener(new FocusAdapter() {
	        public void focusGained(FocusEvent e) {
	            if (textField.getText().equals("아이디")) textField.setText("");
	        }
	        public void focusLost(FocusEvent e) {
	            if (textField.getText().isEmpty()) textField.setText("아이디");
	        }
	    });

	    JPasswordField passwordField = new JPasswordField("비밀번호");
	    passwordField.setBounds(78, 81, 300, 35);
	    passwordField.setFont(new Font("Dialog", Font.PLAIN, 16));
	    passwordField.setEchoChar((char) 0);
	    passwordField.addFocusListener(new FocusAdapter() {
	        public void focusGained(FocusEvent e) {
	            if (new String(passwordField.getPassword()).equals("비밀번호")) {
	                passwordField.setText("");
	                passwordField.setEchoChar('●');
	            }
	        }
	        public void focusLost(FocusEvent e) {
	            if (passwordField.getPassword().length == 0) {
	                passwordField.setText("비밀번호");
	                passwordField.setEchoChar((char) 0);
	            }
	        }
	    });
	    passwordField.addActionListener(e -> {
	        String login_id = textField.getText();
	        String password_input = new String(passwordField.getPassword());

	        CustomerModel c = LoginService.login(login_id, password_input);
	        if (c == null) {
	        	errorLabel.setVisible(true);
	        } else {
	        	errorLabel.setVisible(false);
	            Session.setCustomer(c);
	            smp.refreshPanels();
	            parentPanel.showCard("회원_메인");
	        }
	        textField.setText("아이디");
	        passwordField.setText("비밀번호");
	        passwordField.setEchoChar((char) 0);
	    });
	    
	    
	    JButton logInBtn = new JButton("로그인");
	    logInBtn.setBounds(78, 159, 300, 35);
	    logInBtn.setBackground(new Color(255, 140, 66));
	    logInBtn.setForeground(Color.WHITE);
	    logInBtn.setFont(new Font("Dialog", Font.BOLD, 16));
	    logInBtn.setFocusPainted(false);
	    logInBtn.addActionListener(e -> {
	        String login_id = textField.getText();
	        String password_input = new String(passwordField.getPassword());

	        CustomerModel c = LoginService.login(login_id, password_input);
	        if (c == null) {
	        	errorLabel.setVisible(true);
	        } else {
	        	errorLabel.setVisible(false);
	            Session.setCustomer(c);
	            smp.refreshPanels();
	            parentPanel.showCard("회원_메인");
	        }
	        textField.setText("아이디");
	        passwordField.setText("비밀번호");
	        passwordField.setEchoChar((char) 0);
	    });

	    JButton signupBtn = new JButton("회원가입");
	    signupBtn.setBounds(78, 209, 300, 35);
	    signupBtn.setBackground(Color.WHITE);
	    signupBtn.setForeground(Color.black);
	    signupBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
	    signupBtn.setFocusPainted(false);
	    signupBtn.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 66)));
	    signupBtn.addActionListener(e -> {
	    	parentPanel.initSignupPanel();
	    	parentPanel.showCard("회원가입_메인");
	    });

	    formPanel.add(textField);
	    formPanel.add(passwordField);
	    formPanel.add(logInBtn);
	    formPanel.add(signupBtn);
	    formPanel.add(errorLabel);
	    
	    add(titleLabel);
	    add(formPanel);
	    
	    FooterImagePanel fip = new FooterImagePanel();
	    fip.setBounds(0, 874, 1550, 150);
	    add(fip);
	}

}
