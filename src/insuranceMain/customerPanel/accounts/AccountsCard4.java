package insuranceMain.customerPanel.accounts;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.account.login.LoginService;
import common.account.login.Session;
import common.database.model.CustomerModel;

public class AccountsCard4 extends JPanel {
	private JTextField textField;
	
	public AccountsCard4(AccountsMainPanel parentPanel) {
		addComponents(parentPanel);
		
	}
	
	void addComponents(AccountsMainPanel parentPanel) {
		setLayout(null);

		/// FIXME: 라벨
		JLabel label1 = new JLabel("로그인 페이지");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setBounds(424, 41, 573, 67);
		label1.setBackground(new Color(192, 192, 192));
		Font currentFont = label1.getFont();
		label1.setFont(new Font(currentFont.getName(), currentFont.getStyle(), 30));
		
		JLabel errorLabel = new JLabel("아이디와 비밀번호를 확인하세요.");
		errorLabel.setForeground(Color.red);
		errorLabel.setBounds(650, 250, 200, 20);
		errorLabel.setVisible(false);
		
		
		/// FIXME: 텍스트필드(입력란)
		textField = new JTextField("아이디");
		textField.setBounds(650, 200, 116, 21);
		textField.setColumns(10);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				errorLabel.setVisible(false);
				if (textField.getText().equals("아이디")) {
					textField.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setText("아이디");
				}
			}
		});
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(650, 231, 116, 21);
		passwordField.setColumns(10);

		// 이름을 보이게 하기 위한 초기 설정
		passwordField.setEchoChar((char) 0);
		passwordField.setText("비밀번호");

		// 포커스 이벤트로 힌트 제어
		passwordField.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent e) {
		    	errorLabel.setVisible(false);
		        if (new String(passwordField.getPassword()).equals("비밀번호")) {
		            passwordField.setText("");
		            passwordField.setEchoChar('●');  // 입력 시 비밀번호 마스킹
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (passwordField.getPassword().length == 0) {
		            passwordField.setText("비밀번호");
		            passwordField.setEchoChar((char) 0);  // 이름을 보이고 싶을 땐 마스킹 제거
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
				Session.setCustomer(c);
				AccountsCard2.n2.updateUserName(c.getCustomer_name());
				parentPanel.showCard("회원_메인");
			}
			textField.setText("아이디");
			passwordField.setText("비밀번호");
			passwordField.setEchoChar((char) 0);
			label1.requestFocusInWindow();
		});
		
		/// FIXME: 버튼(페이지 이동)
		JButton logInBtn = new JButton("로그인");
		logInBtn.setBounds(661, 280, 97, 23);
		logInBtn.addActionListener(e -> {
			String login_id = textField.getText();
			String password_input = new String(passwordField.getPassword());
			
			CustomerModel c = LoginService.login(login_id, password_input);
			if (c == null) {
				errorLabel.setVisible(true);
			} else {
				Session.setCustomer(c);
				AccountsCard2.n2.updateUserName(c.getCustomer_name());
				parentPanel.showCard("회원_메인");
			}
			textField.setText("아이디");
			passwordField.setText("비밀번호");
			passwordField.setEchoChar((char) 0);
			label1.requestFocusInWindow();
		});
		
		
		JButton signupBtn = new JButton("회원가입");
		signupBtn.setBounds(661, 315, 97, 23);
		signupBtn.addActionListener(e -> {
			parentPanel.showCard("회원가입_메인");
		});
		
		add(label1);
		add(errorLabel);
		add(textField);
		add(passwordField);
		add(logInBtn);
		add(signupBtn);
	}
}
